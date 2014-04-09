package com.atomrockets.marketanalyzer.dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.joda.time.LocalDate;

import com.atomrockets.marketanalyzer.helpers.MarketRetriever;
import com.atomrockets.marketanalyzer.models.IndexCalcs;
import com.atomrockets.marketanalyzer.models.YahooIndexData;
import com.atomrockets.marketanalyzer.spring.init.PropCache;

public class IndexYahooDataTableManager extends GenericDBSuperclass {
	
	/*
	 * Important Stuff inherited from GenericDBSuperclass
	 * 
	 * m_connection
	 * log
	 * 
	 */
	public IndexYahooDataTableManager() throws ClassNotFoundException, SQLException {
		log.trace("IY.0 Yahoo Table Manager Created");
		
		//m_connection is declared in GenericDBSuperclass, which this class extends, so it gets to use it
		m_connection = getConnection();
	}
	
	public IndexYahooDataTableManager(Connection connection) {
		log.trace("IY.0 Yahoo Table Manager Created");
		
		//m_connection is declared in GenericDBSuperclass, which this class extends, so it gets to use it
		m_connection = connection;
	}

	public synchronized void tableInitialization(String[] indexList) {
		log.trace("IY.1 Starting Market Index Database Initialization");

		/*
		 * Checking to see if a table with the index name exists
		 * If it does, print to the command prompt
		 * if not create the table
		 */
		log.trace("IY.1.1 Checking if table " + g_YahooIndexTableName + " exists.");
		if(!tableExists(g_YahooIndexTableName)) {
			// Table does not exist, so create it
			String createTableSQL = "CREATE TABLE IF NOT EXISTS `" + g_YahooIndexTableName + "` (" +
					" id INT not NULL AUTO_INCREMENT," +
					" symbol VARCHAR(10)," +
					" date DATE not NULL," +
					" open FLOAT(20)," +
					" high FLOAT(20)," +
					" low FLOAT(20)," +
					" close FLOAT(20)," +
					" volume BIGINT(50)," +
					" PRIMARY KEY (id))";
			createTable(createTableSQL, g_YahooIndexTableName);
		}
		
		/*
		 * Checking to see if the table is empty
		 * If they are populate them from Yahoo
		 * If not, check if they are up to date
		 * 		If not, update them
		 */
		log.trace("IY.1.2 Checking if table " + g_YahooIndexTableName + " is empty.");
		if(tableEmpty(g_YahooIndexTableName)){
			//if table is empty
			//populate it
			populateFreshDB(indexList);
		}
		
		log.trace("IY.1.3 Updating the indexes with the most current info");
		updateIndexes(indexList);
	}
	
	public void updateIndexes(String[] indexList) {
		//Loop for each Price Volume DBs for each index
		log.trace("IY.2.0 Looping through each index to update them");
		for(String index:indexList) {			
			log.trace("IY.2.1 Checking to see if table " + index +" is up to date.");
			int indexDaysBehind = getIndexDaysBehind(index);
			log.info("IY.2.2 " +index + " is " + indexDaysBehind + " days out of date.");
			if(indexDaysBehind>0)
			{
				log.info("IY.2.3 Starting to update " + index + " in the DB");
				updateIndexDB(index, indexDaysBehind);
			}
		}
	}

	private int getIndexDaysBehind(String index) {

		//initializing variables
		//java.sql.Date newestDateInDB=null;
		LocalDate newestDate=null;

		String getNewestDateInDBQuery = "SELECT Date FROM `" + g_YahooIndexTableName + "` "
				+ "WHERE `symbol` = '" + index + "' "
				+ "ORDER BY date "
				+ "DESC LIMIT 1";
		PreparedStatement ps=null;
		ResultSet rs = null;

		try {
			// Querying the database for the newest date
			ps = m_connection.prepareStatement(getNewestDateInDBQuery);
			rs = ps.executeQuery();

			if (!rs.next() ) {
				log.info("no data");
				//java.util.Calendar cal = java.util.Calendar.getInstance(); 
				//newestDateInDB = new Date(cal.getTimeInMillis());
				newestDate = new LocalDate();
			} else {
				//newestDateInDB = rs.getDate("Date");
				newestDate = LocalDate.fromDateFields(rs.getDate("Date"));
			}
		} catch (SQLException e) {
			log.error("SQLException: " + e.getMessage());
			log.error("SQLState: " + e.getSQLState());
			log.error("VendorError: " + e.getErrorCode());
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			log.error(e);
		} catch(NullPointerException e) {
			// probably don't bother doing clean up
		} finally {
			//This if statement doesn't make much sense
			try {
				if(rs!=null) {
					rs.close();
				}
				if(ps!=null) {
					ps.close();
				}
			} catch (SQLException sqlEx) { } // ignore
		}
		//calls the getNumberOfDaysFromNow method from market retriever and immediately returns
		//how many behind the database is from the current date
		//log.info("          The newest date in the database is " + newestDateInDB.toString() + ".");
		log.debug("IY.3.1--The newest date in the database for " + index + "is " + newestDate.toString() + ".");
		
		int DBDaysTilNow = MarketRetriever.getNumberOfDaysFromNow(newestDate);
		
		return DBDaysTilNow;//DBDaysTilNow;
	}

	public void populateFreshDB(String[] indexList) {
		
		for(String index : indexList)
		{
			log.info("     -Populating Table with data for " + index);		
		
			//Container to hold the downloaded data
			List<YahooIndexData> rowsFromYahoo = null;
			//This date represents the beginning of time as far as any of the indexes go
			LocalDate beginningDate = new LocalDate(PropCache.getCachedProps("yahoo.startdate"));
	
			//calculates the number of days from today back to beginning date
			int numDays = MarketRetriever.getNumberOfDaysFromNow(beginningDate);
	
			//Creates a yahoo URL given the index symbol from now back a given number of days
			String URL = MarketRetriever.getYahooURL(index, numDays);
	
			rowsFromYahoo = MarketRetriever.yahooDataParser(URL, index);
	
			initialAddRecordsFromData(rowsFromYahoo);
		}
	}

	public void updateIndexDB(String index,int indexDaysBehind) {
		//Container to hold the downloaded data
		List<YahooIndexData> rowsFromYahoo = null;
		//Creates a yahoo URL given the index symbol from now back a given number of days
		String URL = MarketRetriever.getYahooURL(index, indexDaysBehind);

		rowsFromYahoo = MarketRetriever.yahooDataParser(URL, index);

		// extract price and volume data for URL, # of yahoo days
		addRecordsFromData(rowsFromYahoo);
	}
	
	public void initialAddRecordsFromData(List<YahooIndexData> rowsFromYahoo) {
		//This query ignores duplicate dates
		String insertQuery = "INSERT INTO `" + g_YahooIndexTableName + "` "
				+ "(symbol,date,open,high,low,close,volume) VALUES"
				+ "(?,?,?,?,?,?,?)";
		String [] columnNames = {"symbol","date","open","high","low","close","volume"};
		PreparedStatement ps=null;
		int batchSize = 100;
		try {
			//preparing the MySQL statement
			ps = m_connection.prepareStatement(insertQuery);
			//creating DbUtils QuerryRunner
			QueryRunner runner = new QueryRunner();
			int i=0;
			//Iterate through the list backwards. I want the oldest date in first and this achieves that
			for (i = rowsFromYahoo.size()-1; i > 0 ; i--) {
				
				runner.fillStatementWithBean(ps, rowsFromYahoo.get(i), columnNames);
				
				ps.addBatch();
				if (i % batchSize == 0) //if i/batch size remainder == 0 execute batch
				{
					ps.executeBatch();
					log.info("Executed at i="+i);
				}
			}
			//Execute the last batch, in case the last value of i isn't a multiple of batchSize
			ps.executeBatch();
			log.info("Final batch executed at i="+i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			log.info(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException sqlEx) { } // ignore

				ps = null;
			}
		}
	}
	
	public void addRecordsFromData(List<YahooIndexData> rowsFromYahoo) {

		//This query ignores duplicate dates
		String insertQuery = "INSERT INTO `" + g_YahooIndexTableName + "` "
				+ "(symbol,date,open,high,low,close,volume) VALUES"
				+ "(?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		int batchSize = 20;
		
		try {
			//prepare the statement
			ps = m_connection.prepareStatement(insertQuery);

			//Iterate through the list backwards. I want the oldest date in first and this achieves that
			for (int i = rowsFromYahoo.size()-1; i >= 0 ; i--) {
				
				//Check if the row is already in the DB
				if( !isAlreadyInDB(rowsFromYahoo.get(i)) ) {
					//if the row is not in the DB prepare it for insertion
					ps.setString(1, rowsFromYahoo.get(i).getSymbol());
					ps.setString(2, rowsFromYahoo.get(i).getDate());
					ps.setDouble(3,  rowsFromYahoo.get(i).getOpen());
					ps.setDouble(4,  rowsFromYahoo.get(i).getHigh());
					ps.setDouble(5,  rowsFromYahoo.get(i).getLow());
					ps.setDouble(6,  rowsFromYahoo.get(i).getClose());
					ps.setLong(7,  rowsFromYahoo.get(i).getVolume());
					ps.addBatch();
				}
				
				if (i % batchSize == 0) //if i/batch size remainder == 0 execute batch
				{
					ps.executeBatch();
					log.info("Executed at i="+i);
				}
			}
			//Execute the last batch, in case the last value of i isn't a multiple of batchSize
			ps.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			log.info(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException sqlEx) { } // ignore

				ps = null;
			}
		}
	}
		
	public boolean isAlreadyInDB(YahooIndexData row) throws SQLException {
		
		boolean alreadyExists = false;
		int j=0;
		String checkQuery = "SELECT `id` FROM `" + g_YahooIndexTableName + "`"
				+ " WHERE `date` = ?"
				+ " AND `symbol` = ?";
		
		PreparedStatement ps_check = null;
		
		ps_check = m_connection.prepareStatement(checkQuery);

		ps_check.setString(1, row.getDate());
		ps_check.setString(2, row.getSymbol());
		ResultSet rs = ps_check.executeQuery();
			
		while(rs.next())
		{
			log.debug("Just tried to insert a duplicate row into table " + g_YahooIndexTableName + ". The id of the entry already in the table is " + rs.getInt("id"));
			alreadyExists = true;
		}
		return alreadyExists;
	}

	public List<YahooIndexData> getRowsBetweenDatesBySymbol(String symbol, LocalDate startDate, LocalDate endDate) {
		List<YahooIndexData> dDayList = new ArrayList<YahooIndexData>();
		
		String YahooTableName = getG_YahooIndexTableName();
		
		String query = "SELECT *"
				+ " FROM `" + YahooTableName + "`"
				+ " WHERE symbol = ?"
				+ " AND date BETWEEN ? and ?"
				+ " ORDER BY date ASC";

		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner();
		// Use the BeanListHandler implementation to convert all
		// ResultSet rows into a List of Person JavaBeans.
		ResultSetHandler<List<YahooIndexData>> h = new BeanListHandler<YahooIndexData>(YahooIndexData.class);
		
		try{
			dDayList = run.query(
		    		m_connection, //connection
		    		query, //query (in the same form as for a prepared statement
		    		h, //ResultSetHandler
		    		// an arg should be entered for every ? in the query
		    		symbol,
		    		startDate.toString(),
		    		endDate.toString());
		        // do something with the result
		        
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return dDayList;
		
	}

	public IndexCalcs getFirstBySymbol(String symbol) {
		
		IndexCalcs dataPoint=null;
		
		String query = "SELECT * FROM `" + g_YahooIndexTableName + "`"
				+ " WHERE `symbol` = ?"
				+ " ORDER BY `date` ASC"
				+ " LIMIT 1";
		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner();
		// Use the BeanListHandler implementation to convert all
		// ResultSet rows into a List of Person JavaBeans.
		ResultSetHandler<IndexCalcs> h = new BeanHandler<IndexCalcs>(IndexCalcs.class);
		
		try{
			dataPoint = run.query(
		    		m_connection, //connection
		    		query, //query (in the same form as for a prepared statement
		    		h, //ResultSetHandler
		    		// an arg should be entered for every ? in the query
		    		symbol);
		        // do something with the result
		        
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return dataPoint;
	}
	
	public IndexCalcs getLastBySymbol(String symbol) {
		
		IndexCalcs dataPoint=null;
		
		String query = "SELECT * FROM `" + g_YahooIndexTableName + "`"
				+ " WHERE `symbol` = ?"
				+ " ORDER BY `date` DESC"
				+ " LIMIT 1";
		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner();
		// Use the BeanListHandler implementation to convert all
		// ResultSet rows into a List of Person JavaBeans.
		ResultSetHandler<IndexCalcs> h = new BeanHandler<IndexCalcs>(IndexCalcs.class);
		
		try{
			dataPoint = run.query(
		    		m_connection, //connection
		    		query, //query (in the same form as for a prepared statement
		    		h, //ResultSetHandler
		    		// an arg should be entered for every ? in the query
		    		symbol);
		        // do something with the result
		        
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return dataPoint;
	}
}
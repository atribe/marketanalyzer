package com.atomrockets.marketanalyzer.dbManagers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.joda.time.LocalDate;

import com.atomrockets.marketanalyzer.beans.IndexOHLCVCalcs;
import com.atomrockets.marketanalyzer.beans.OHLCVData;
import com.atomrockets.marketanalyzer.helpers.MarketRetriever;
import com.atomrockets.marketanalyzer.spring.init.PropCache;

public class OHLCVDao extends GenericDBSuperclass {
	
	/*
	 * Important Stuff inherited from GenericDBSuperclass
	 * 
	 * m_connection
	 * log
	 * 
	 */	
	public OHLCVDao() throws ClassNotFoundException, SQLException {
		log.trace("IY.0 Yahoo Table Manager Created");
		
		setM_ds(MarketPredDataSource.setDataSource());
	}

	public OHLCVDao(DataSource ds) {
		log.trace("IY.0 Yahoo Table Manager Created");
			
		m_ds = ds;
	}

	public synchronized void tableInitialization(String[] indexList) {
		log.trace("IY.1 Starting Market Index Database Initialization");

		/*
		 * Checking to see if a table with the index name exists
		 * If it does, print to the command prompt
		 * if not create the table
		 */
		
		
		OHLCVData a = new OHLCVData();
		
		String tableName = OHLCVData.getTablename();
		
		log.trace("IY.1.1 Checking if table " + tableName + " exists.");
		if(!tableExists(tableName)) {
			// Table does not exist, so create it
			String createTableSQL = a.tableCreationString();
			createTable(createTableSQL, tableName);
		}
		
		/*
		 * Checking to see if the table is empty
		 * If they are populate them from Yahoo
		 * If not, check if they are up to date
		 * 		If not, update them
		 */
		log.trace("IY.1.2 Checking if table " + tableName + " is empty.");
		if(tableEmpty(tableName)){
			//if table is empty -> populate it
			populateFreshDB(indexList);
		}
		
		log.trace("IY.1.3 Updating the indexes with the most current info");
		updateIndexes(indexList);
	}
	
	public void updateIndexes(String[] indexList) {
		//Loop for each Price Volume DBs for each index
		log.trace("IY.2.0 Looping through each index to update them");
		
		for(String symbol:indexList) {			
			log.trace("IY.2.1 Checking to see if table " + symbol +" is up to date.");
			int indexDaysBehind = getIndexDaysBehind(symbol);
			
			log.info("IY.2.2 " +symbol + " is " + indexDaysBehind + " days out of date.");
			if(indexDaysBehind>0)
			{
				log.info("IY.2.3 Starting to update " + symbol + " in the DB");
				updateIndexDB(symbol, indexDaysBehind);
			}
		}
	}

	private int getIndexDaysBehind(String symbol) {

		//initializing variables
		//java.sql.Date newestDateInDB=null;
		LocalDate newestDate=null;

		String getNewestDateInDBQuery = "SELECT Date FROM `" + OHLCVData.getTablename() + "` "
				+ "WHERE `symbol` = ?"
				+ "ORDER BY date "
				+ "DESC LIMIT 1";
	
		OHLCVData a = new OHLCVData();

		try {
			QueryRunner runner = new QueryRunner(m_ds);
			ResultSetHandler<OHLCVData> h = new BeanHandler<OHLCVData>(OHLCVData.class);
			
			a = runner.query(
					getNewestDateInDBQuery,
					h,
					symbol
					);

			if (a == null ) {
				log.debug("no data in the database for the symbol " + symbol );
				newestDate = new LocalDate();
			} else {
				//newestDateInDB = rs.getDate("Date");
				newestDate = a.getLocalDate();
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
		} 
		
		//calls the getNumberOfDaysFromNow method from market retriever and immediately returns
		//how many behind the database is from the current date
		//log.info("          The newest date in the database is " + newestDateInDB.toString() + ".");
		log.debug("IY.3.1--The newest date in the database for " + symbol + "is " + newestDate.toString() + ".");
		
		int DBDaysTilNow = MarketRetriever.getNumberOfDaysFromNow(newestDate);
		
		return DBDaysTilNow;//DBDaysTilNow;
	}

	public void populateFreshDB(String[] indexList) {
		
		for(String index : indexList)
		{
			log.info("     -Populating Table with data for " + index);		
		
			//Container to hold the downloaded data
			List<OHLCVData> rowsFromYahoo = null;
			//This date represents the beginning of time as far as any of the indexes go
			LocalDate beginningDate = new LocalDate(PropCache.getCachedProps("yahoo.startdate"));
	
			//calculates the number of days from today back to beginning date
			int numDays = MarketRetriever.getNumberOfDaysFromNow(beginningDate);
	
			//Creates a yahoo URL given the index symbol from now back a given number of days
			String URL = MarketRetriever.getYahooURL(index, numDays);
	
			try {
				rowsFromYahoo = MarketRetriever.yahooDataParser(URL, index);
			} catch (FileNotFoundException fe) {
				log.error("the yahoo URL: " + URL + " was not valid. It is probably just after midnight and the yahoo servers have not yet updated.", fe);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			if(rowsFromYahoo != null) {
				initialAddRecordsFromData(rowsFromYahoo);
			}
		}
	}

	public void updateIndexDB(String index,int indexDaysBehind) {
		//Container to hold the downloaded data
		List<OHLCVData> rowsFromYahoo = null;
		//Creates a yahoo URL given the index symbol from now back a given number of days
		String URL = MarketRetriever.getYahooURL(index, indexDaysBehind);

		try {
			rowsFromYahoo = MarketRetriever.yahooDataParser(URL, index);
			
			// extract price and volume data for URL, # of yahoo days
			addRecordsFromData(rowsFromYahoo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initialAddRecordsFromData(List<OHLCVData> rowsFromYahoo) {
		//This query ignores duplicate dates
		String insertQuery = "INSERT INTO `" + OHLCVData.getTablename() + "` "
				+ "(symbol,date,open,high,low,close,volume) VALUES"
				+ "(?,?,?,?,?,?,?)";
		String [] columnNames = {"symbol","date","open","high","low","close","volume"};
		PreparedStatement ps=null;
		int batchSize = 1000;
		try {
			Connection con = m_ds.getConnection();
			//preparing the MySQL statement
			ps = con.prepareStatement(insertQuery);
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
			con.close();
			
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
	
	public void addRecordsFromData(List<OHLCVData> rowsFromYahoo) {

		OHLCVData a = new OHLCVData();
		
		String insertQuery = a.getInsertOrUpdateQuery();
		
		String[] columnNames = a.getColumnNameList();
		
		PreparedStatement ps=null;
		int batchSize = 1000;
		
		try {
			long counter = 0;
			
			Connection con = m_ds.getConnection();
			//prepare the statement
			ps = con.prepareStatement(insertQuery);
			
			//creating DbUtils QuerryRunner
			QueryRunner runner = new QueryRunner();
			
			for(OHLCVData row : rowsFromYahoo) {
				
				//Check to see if row is already in the DB
				if(!isAlreadyInDB(row)) {
					//DbUtils QueryRunner fills in the preparedStatement
					runner.fillStatementWithBean(ps, row, columnNames);
					
					ps.addBatch();
				}
				counter++;
				
				if (counter % batchSize == 0) { //if i/batch size remainder == 0 execute batch
					ps.executeBatch();
					log.info("OHLCV insert executed at i="+counter);
				}
			}
			//execute the batch at the end for the leftovers that didn't hit counter%batch==0
			ps.executeBatch();
			log.info("OHLCV insert executed at i="+counter);
			con.close();
			
		} catch (SQLException e) {
			log.info("SQLException: " + e.getMessage());
			log.info("SQLState: " + e.getSQLState());
			log.info("VendorError: " + e.getErrorCode());
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
		
	public boolean isAlreadyInDB(OHLCVData row) throws SQLException {
		
		boolean alreadyExists = false;

		String checkQuery = "SELECT `id` FROM `" + OHLCVData.getTablename() + "`"
				+ " WHERE `date` = ?"
				+ " AND `symbol` = ?";
		
		QueryRunner runner = new QueryRunner(m_ds);
		ResultSetHandler<OHLCVData> h = new BeanHandler<OHLCVData>(OHLCVData.class);
		
		OHLCVData result = runner.query(
							checkQuery,
							h,
							row.getDate(),
							row.getSymbol()
							);
			
		if(result != null) {
			log.debug("Just tried to insert a duplicate row into table " + OHLCVData.getTablename() + ". The id of the entry already in the table is " + result.getId());
			alreadyExists = true;
		}
		
		return alreadyExists;
	}

	public List<OHLCVData> getRowsBetweenDatesBySymbol(String symbol, LocalDate startDate, LocalDate endDate) {
		List<OHLCVData> dDayList = new ArrayList<OHLCVData>();
		
		String YahooTableName = OHLCVData.getTablename();
		
		String query = "SELECT *"
				+ " FROM `" + YahooTableName + "`"
				+ " WHERE symbol = ?"
				+ " AND date BETWEEN ? and ?"
				+ " ORDER BY date ASC";

		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner(m_ds);
		// Use the BeanListHandler implementation to convert all
		// ResultSet rows into a List of Person JavaBeans.
		ResultSetHandler<List<OHLCVData>> h = new BeanListHandler<OHLCVData>(OHLCVData.class);
		try{		
			dDayList = run.query(
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

	public IndexOHLCVCalcs getFirstBySymbol(String symbol) {
		
		IndexOHLCVCalcs dataPoint=null;
		
		String query = "SELECT * FROM `" + OHLCVData.getTablename() + "`"
				+ " WHERE `symbol` = ?"
				+ " ORDER BY `date` ASC"
				+ " LIMIT 1";
		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner(m_ds);
		// Use the BeanListHandler implementation to convert all
		// ResultSet rows into a List of Person JavaBeans.
		ResultSetHandler<IndexOHLCVCalcs> h = new BeanHandler<IndexOHLCVCalcs>(IndexOHLCVCalcs.class);
		
		try{			
			dataPoint = run.query(
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
	
	public IndexOHLCVCalcs getLastBySymbol(String symbol) {
		
		IndexOHLCVCalcs dataPoint=null;
		
		String query = "SELECT * FROM `" + OHLCVData.getTablename() + "`"
				+ " WHERE `symbol` = ?"
				+ " ORDER BY `date` DESC"
				+ " LIMIT 1";
		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner(m_ds);
		// Use the BeanListHandler implementation to convert all
		// ResultSet rows into a List of Person JavaBeans.
		ResultSetHandler<IndexOHLCVCalcs> h = new BeanHandler<IndexOHLCVCalcs>(IndexOHLCVCalcs.class);
		
		try{
			dataPoint = run.query(
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

	public OHLCVData getBySymbolAndDate(String symbol, LocalDate date) {
		OHLCVData dataPoint=null;
		
		String query = "SELECT * FROM `" + OHLCVData.getTablename() + "`"
				+ " WHERE `symbol` = ?"
				+ " AND `date` = ?";
		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner(m_ds);
		// Use the BeanListHandler implementation to convert all
		// ResultSet rows into a List of Person JavaBeans.
		ResultSetHandler<OHLCVData> h = new BeanHandler<OHLCVData>(OHLCVData.class);
		
		try{		
			dataPoint = run.query(
		    		query, //query (in the same form as for a prepared statement
		    		h, //ResultSetHandler
		    		// an arg should be entered for every ? in the query
		    		symbol,
		    		date.toDate());
		        // do something with the result
		
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return dataPoint;
	}
	public IndexOHLCVCalcs getBySymbolAndDateAsCalcs(String symbol, LocalDate date) {
		IndexOHLCVCalcs dataPoint=null;
		
		String query = "SELECT * FROM `" + OHLCVData.getTablename() + "`"
				+ " WHERE `symbol` = ?"
				+ " AND `date` = ?";
		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner(m_ds);
		// Use the BeanListHandler implementation to convert all
		// ResultSet rows into a List of Person JavaBeans.
		ResultSetHandler<IndexOHLCVCalcs> h = new BeanHandler<IndexOHLCVCalcs>(IndexOHLCVCalcs.class);
		
		try{
			dataPoint = run.query(
		    		query, //query (in the same form as for a prepared statement
		    		h, //ResultSetHandler
		    		// an arg should be entered for every ? in the query
		    		symbol,
		    		date.toDate());
		        // do something with the result
		        
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return dataPoint;
	}
	private boolean checkDate(String symbol, LocalDate date) {
		OHLCVData a = getBySymbolAndDate(symbol, date);
		if(a!=null)
			return true;
		else
			return false;
	}
	
	public IndexOHLCVCalcs getValidDateAsCalc(String symbol, LocalDate date, boolean futureSearchDirection) {
		int counter=0;
		
		while(!checkDate(symbol, date) && counter < 7) {
			if(futureSearchDirection)
				date = date.plusDays(1);
			else
				date = date.minusDays(1);
			counter++;
		}
		
		IndexOHLCVCalcs a = getBySymbolAndDateAsCalcs(symbol, date);;
		
		return a;
	}
	public OHLCVData getValidDate(String symbol, LocalDate date, boolean futureSearchDirection) {
		int counter=0;
		
		while(!checkDate(symbol, date) && counter < 7) {
			if(futureSearchDirection)
				date = date.plusDays(1);
			else
				date = date.minusDays(1);
			counter++;
		}
		
		OHLCVData a = getBySymbolAndDate(symbol, date);;
		
		return a;
	}
}
package com.ar.marketanalyzer.indexbacktest.dao;

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

import com.ar.marketanalyzer.core.securities.services.YahooOhlcvService;
import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.indexbacktest.beans.IndexOHLCVCalcs;
import com.ar.marketanalyzer.indexbacktest.beans.IndexOHLCVData;
import com.ar.marketanalyzer.spring.init.PropCache;

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
		
		setDs(MarketPredDataSource.setDataSource());
	}

	public OHLCVDao(DataSource newDs) {
		log.trace("IY.0 Yahoo Table Manager Created");
			
		ds = newDs;
	}

	public synchronized void tableInitialization(String[] indexList) {
		log.trace("IY.1 Starting Market Index Database Initialization");

		/*
		 * Checking to see if a table with the index name exists
		 * If it does, print to the command prompt
		 * if not create the table
		 */
		
		
		IndexOHLCVData a = new IndexOHLCVData();
		
		String tableName = IndexOHLCVData.getTablename();
		
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

		String getNewestDateInDBQuery = "SELECT Date FROM `" + IndexOHLCVData.getTablename() + "` "
				+ "WHERE `symbol` = ?"
				+ "ORDER BY date "
				+ "DESC LIMIT 1";
	
		IndexOHLCVData a = new IndexOHLCVData();

		try {
			QueryRunner runner = new QueryRunner(ds);
			ResultSetHandler<IndexOHLCVData> h = new BeanHandler<IndexOHLCVData>(IndexOHLCVData.class);
			
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
		
		int DBDaysTilNow = YahooOhlcvService.getNumberOfDaysFromNow(newestDate);
		
		return DBDaysTilNow;//DBDaysTilNow;
	}

	public void populateFreshDB(String[] indexList) {
		
		for(String index : indexList)
		{
			log.info("     -Populating Table with data for " + index);		
		
			//Container to hold the downloaded data
			List<IndexOHLCVData> rowsFromYahoo = null;
			//This date represents the beginning of time as far as any of the indexes go
			LocalDate beginningDate = new LocalDate(PropCache.getCachedProps("yahoo.startdate"));
	
			//calculates the number of days from today back to beginning date
			int numDays = YahooOhlcvService.getNumberOfDaysFromNow(beginningDate);
	
			//Creates a yahoo URL given the index symbol from now back a given number of days
			String URL = YahooOhlcvService.getYahooURL(index, numDays);
	

			rowsFromYahoo = YahooOhlcvService.getIndexFromYahoo(URL, index);

	
			if(rowsFromYahoo != null) {
				initialAddRecordsFromData(rowsFromYahoo);
			}
		}
	}

	public void updateIndexDB(String index,int indexDaysBehind) {
		//Container to hold the downloaded data
		List<IndexOHLCVData> rowsFromYahoo = null;
		//Creates a yahoo URL given the index symbol from now back a given number of days
		String URL = YahooOhlcvService.getYahooURL(index, indexDaysBehind);

		rowsFromYahoo = YahooOhlcvService.getIndexFromYahoo(URL, index);
			
		// extract price and volume data for URL, # of yahoo days
		addRecordsFromData(rowsFromYahoo);

	}
	
	public void initialAddRecordsFromData(List<IndexOHLCVData> rowsFromYahoo) {
		//This query ignores duplicate dates
		String insertQuery = "INSERT INTO `" + IndexOHLCVData.getTablename() + "` "
				+ "(symbol,date,open,high,low,close,volume) VALUES"
				+ "(?,?,?,?,?,?,?)";
		String [] columnNames = {"symbol","date","open","high","low","close","volume"};
		PreparedStatement ps=null;
		int batchSize = 1000;
		try {
			Connection con = ds.getConnection();
			//preparing the MySQL statement
			ps = con.prepareStatement(insertQuery);
			//creating DbUtils QueryRunner
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
	
	public void addRecordsFromData(List<IndexOHLCVData> rowsFromYahoo) {

		IndexOHLCVData a = new IndexOHLCVData();
		
		String insertQuery = a.getInsertOrUpdateQuery();
		
		String[] columnNames = a.getColumnNameList();
		
		PreparedStatement ps=null;
		int batchSize = 1000;
		
		try {
			long counter = 0;
			
			Connection con = ds.getConnection();
			//prepare the statement
			ps = con.prepareStatement(insertQuery);
			
			//creating DbUtils QueryRunner
			QueryRunner runner = new QueryRunner();
			
			for(IndexOHLCVData row : rowsFromYahoo) {
				
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
		
	public boolean isAlreadyInDB(IndexOHLCVData row) throws SQLException {
		
		boolean alreadyExists = false;

		String checkQuery = "SELECT `id` FROM `" + IndexOHLCVData.getTablename() + "`"
				+ " WHERE `date` = ?"
				+ " AND `symbol` = ?";
		
		QueryRunner runner = new QueryRunner(ds);
		ResultSetHandler<IndexOHLCVData> h = new BeanHandler<IndexOHLCVData>(IndexOHLCVData.class);
		
		IndexOHLCVData result = runner.query(
							checkQuery,
							h,
							row.getDate(),
							row.getSymbol()
							);
			
		if(result != null) {
			log.debug("Just tried to insert a duplicate row into table " + IndexOHLCVData.getTablename() + ". The id of the entry already in the table is " + result.getId());
			alreadyExists = true;
		}
		
		return alreadyExists;
	}

	public List<IndexOHLCVData> getRowsBetweenDatesBySymbol(String symbol, LocalDate startDate, LocalDate endDate) {
		List<IndexOHLCVData> dDayList = new ArrayList<IndexOHLCVData>();
		
		String YahooTableName = IndexOHLCVData.getTablename();
		
		String query = "SELECT *"
				+ " FROM `" + YahooTableName + "`"
				+ " WHERE symbol = ?"
				+ " AND date BETWEEN ? and ?"
				+ " ORDER BY date ASC";

		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner(ds);
		// Use the BeanListHandler implementation to convert all
		// ResultSet rows into a List of Person JavaBeans.
		ResultSetHandler<List<IndexOHLCVData>> h = new BeanListHandler<IndexOHLCVData>(IndexOHLCVData.class);
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
		
		String query = "SELECT * FROM `" + IndexOHLCVData.getTablename() + "`"
				+ " WHERE `symbol` = ?"
				+ " ORDER BY `date` ASC"
				+ " LIMIT 1";
		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner(ds);
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
		
		String query = "SELECT * FROM `" + IndexOHLCVData.getTablename() + "`"
				+ " WHERE `symbol` = ?"
				+ " ORDER BY `date` DESC"
				+ " LIMIT 1";
		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner(ds);
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

	public IndexOHLCVData getBySymbolAndDate(String symbol, LocalDate date) {
		IndexOHLCVData dataPoint=null;
		
		String query = "SELECT * FROM `" + IndexOHLCVData.getTablename() + "`"
				+ " WHERE `symbol` = ?"
				+ " AND `date` = ?";
		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner(ds);
		// Use the BeanListHandler implementation to convert all
		// ResultSet rows into a List of Person JavaBeans.
		ResultSetHandler<IndexOHLCVData> h = new BeanHandler<IndexOHLCVData>(IndexOHLCVData.class);
		
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
		
		String query = "SELECT * FROM `" + IndexOHLCVData.getTablename() + "`"
				+ " WHERE `symbol` = ?"
				+ " AND `date` = ?";
		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner(ds);
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
		IndexOHLCVData a = getBySymbolAndDate(symbol, date);
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
	public IndexOHLCVData getValidDate(String symbol, LocalDate date, boolean futureSearchDirection) {
		int counter=0;
		
		while(!checkDate(symbol, date) && counter < 7) {
			if(futureSearchDirection)
				date = date.plusDays(1);
			else
				date = date.minusDays(1);
			counter++;
		}
		
		IndexOHLCVData a = getBySymbolAndDate(symbol, date);;
		
		return a;
	}
}
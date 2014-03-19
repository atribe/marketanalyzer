package com.atomrockets.marketanalyzer.dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import com.atomrockets.marketanalyzer.beans.MarketIndexAnalysisObject;
import com.atomrockets.marketanalyzer.helpers.MarketRetriever;
import com.atomrockets.marketanalyzer.persistence.model.YahooIndexData;
import com.atomrockets.marketanalyzer.persistence.service.impl.YahooIndexDataService;

public class IndexYahooDataTableManager extends GenericDBSuperclass {
	
	/*
	 * Important Stuff inherited from GenericDBSuperclass
	 * 
	 * m_connection
	 * log
	 * 
	 */
	@Autowired
	private YahooIndexDataService yahooIndexDataService;
	
	//Name of the table that contains all the data from Yahoo
	private static final String m_yDTname = "yahooDataTable";
	
	public IndexYahooDataTableManager(Connection connection) {
		log.debug("------------------------------Yahoo Table Manager Created--------------------------");
		
		//m_connection is declared in GenericDBSuperclass, which this class extends, so it gets to use it
		m_connection = connection;
	}

	public synchronized void tableInitialization(String[] indexList) {		
		log.info("Starting Market Index Database Initialization");

		/*
		 * Checking to see if a table with the index name exists
		 * If it does, print to the command prompt
		 * if not create the table
		 */
		log.info("     -Checking if table " + m_yDTname + " exists.");
		if(!tableExists(m_yDTname)) {
			// Table does not exist, so create it
			String createTableSQL = "CREATE TABLE IF NOT EXISTS `" + m_yDTname + "` (" +
					" yd_id INT not NULL AUTO_INCREMENT," +
					" symbol VARCHAR(10)," +
					" date DATE not NULL," +
					" open FLOAT(20)," +
					" high FLOAT(20)," +
					" low FLOAT(20)," +
					" close FLOAT(20)," +
					" volume BIGINT(50)," +
					" PRIMARY KEY (yd_id))";
			createTable(createTableSQL, m_yDTname);
		}
		
		/*
		 * Checking to see if the table is empty
		 * If they are populate them from Yahoo
		 * If not, check if they are up to date
		 * 		If not, update them
		 */
		log.info("     -Checking if table " + m_yDTname + " is empty.");
		if(tableEmpty(m_yDTname)){
			//if table is empty
			//populate it
			populateFreshDB(indexList);
		}
		
		//Iteration tracking variable for System.out.printing and debugging
		int interationCounter = 0;
		
		//Loop for each Price Volume DBs for each index
		for(String index:indexList) {
			interationCounter++;
			log.info("Loop Iteration " + interationCounter + ":");
			
			log.info("     -Checking to see if table " + index +" is up to date.");
			int indexDaysBehind = getIndexDaysBehind(index);
			if(indexDaysBehind>0)
			{
				updateIndexDB(index, indexDaysBehind);
			}
		}
		log.info("--------------------------------------------------------------------");
	}

	private int getIndexDaysBehind(String index) {

		//initializing variables
		//java.sql.Date newestDateInDB=null;
		LocalDate newestDate=null;

		String getNewestDateInDBQuery = "SELECT Date FROM `" + m_yDTname + "` "
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
			log.info("SQLException: " + e.getMessage());
			log.info("SQLState: " + e.getSQLState());
			log.info("VendorError: " + e.getErrorCode());
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			log.info(e);
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
		log.info("          The newest date in the database is " + newestDate.toString() + ".");

		
		int DBDaysTilNow = MarketRetriever.getNumberOfDaysFromNow(newestDate);
		//log.info("          Which is " + DBDaysTilNow + " days out of date.");
		log.info("          Which is " + DBDaysTilNow + " days out of date.");
		return DBDaysTilNow;//DBDaysTilNow;
	}

	public void populateFreshDB(String[] indexList) {
		
		Properties prop = propertiesLoader.loadActivePropertiesFile();
		
		for(String index : indexList)
		{
			log.info("     -Populating Table " + index);		
		
			//Container to hold the downloaded data
			List<YahooIndexData> rowsFromYahoo = null;
			//This date represents the beginning of time as far as any of the indexes go
			LocalDate beginningDate = new LocalDate(prop.getProperty("yahoo.startdate"));
	
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

	/**
	 * @param connection
	 * @param tableName
	 * @param Date
	 * @param isStartDate determines whether you look for alternative days before or after the supplied date
	 * @return
	 */
	public int getIdByDate(String index, LocalDate Date, boolean isStartDate){
		int value = 0;
		String query = "SELECT yd_id FROM `" + m_yDTname + "`"
				+ " WHERE `date` = ?"
				+ " AND `symbol` = ?";
		

		try {
			PreparedStatement selectStatement = m_connection.prepareStatement(query);
			selectStatement.setString(1, Date.toString());
			selectStatement.setString(2, index);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()) {
				value = rs.getInt("yd_id");
			} else if (isStartDate) {
				log.info("     The date of " + Date.toString() + " not found in the database.");
				log.info("          Let me check the preceeding couple of days in case you chose a weekend or holiday.");
				for(int i = 1;i<7;i++)
				{
					selectStatement.setString(1, Date.minusDays(i).toString());
					selectStatement.setString(2, index);
					rs = selectStatement.executeQuery();
					if(rs.next())
					{
						value = rs.getInt("yd_id");
						log.info("          Looks like I found one...and you got all worried for nothing.");
						break;
					}
					else if(i==6)
					{
						log.info("          I didn't find an earlier date, so I'll just choose the first date in the data set");
						value=1;
					}
				}
			} else {
				log.info("     The date of " + Date.toString() + " not found in the database.");
				log.info("          Let me check the next couple days in case you chose a weekend or holiday.");
				for(int i = 1;i<7;i++)
				{
					selectStatement.setString(1, Date.plusDays(i).toString());
					selectStatement.setString(2, index);
					rs = selectStatement.executeQuery();
					if(rs.next())
					{
						value = rs.getInt("yd_id");
						log.info("          Looks like I found one...and you got all worried for nothing.");
						break;
					}
					else if(i==6)
					{
						log.info("          I didn't find an earlier date, so I'll just choose the last date in the data set");
						value= getLastRowId(m_yDTname);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info("There was an error in the getIdByDate method. And that error is: ");
			log.info(e.toString());
			e.printStackTrace();
		}
		return value;
	}

	public void initialAddRecordsFromData(List<YahooIndexData> rowsFromYahoo) {
		/*
		 * This new section attempts to use Hibernate
		 */
		for (int i = rowsFromYahoo.size()-1; i > 0 ; i--) {
			yahooIndexDataService.create(rowsFromYahoo.get(i));
		}
		
		
		
		/*
		 * This is the old stuff
		 */
		//This query ignores duplicate dates
		String insertQuery = "INSERT INTO `" + m_yDTname + "` "
				+ "(symbol,date,open,high,low,close,volume) VALUES"
				+ "(?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		int batchSize = 500;
		try {
			ps = m_connection.prepareStatement(insertQuery);

			//Iterate through the list backwards. I want the oldest date in first and this achieves that
			for (int i = rowsFromYahoo.size()-1; i > 0 ; i--) {
				ps.setString(1, rowsFromYahoo.get(i).getSymbol());
				ps.setString(2, rowsFromYahoo.get(i).getDate());
				ps.setDouble(3,  rowsFromYahoo.get(i).getOpen());
				ps.setDouble(4,  rowsFromYahoo.get(i).getHigh());
				ps.setDouble(5,  rowsFromYahoo.get(i).getLow());
				ps.setDouble(6,  rowsFromYahoo.get(i).getClose());
				ps.setLong(7,  rowsFromYahoo.get(i).getVolume());
				ps.addBatch();
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
	
	public void addRecordsFromData(List<YahooIndexData> rowsFromYahoo) {

		//This query ignores duplicate dates
		String insertQuery = "INSERT INTO `" + m_yDTname + "` "
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
	
	public List<MarketIndexAnalysisObject> getDataBetweenIds(String symbol, int beginId, int endId) {
		List<MarketIndexAnalysisObject> rowsFromDB = new ArrayList<MarketIndexAnalysisObject>();
		
		
		String query = "SELECT * FROM `" + m_yDTname + "`"
		+ " WHERE `yd_id` BETWEEN ? AND ?"
		+ " AND `symbol` = ?"
		+ " ORDER BY `yd_id` ASC";
		
		try {
			PreparedStatement selectStatement = m_connection.prepareStatement(query);
			selectStatement.setInt(1, beginId);
			selectStatement.setInt(2, endId);
			selectStatement.setString(3, symbol);
			ResultSet rs = selectStatement.executeQuery();

			while (rs.next()) {
				MarketIndexAnalysisObject singleRow = new MarketIndexAnalysisObject();
				singleRow.setId(rs.getInt("yd_id"));
				singleRow.setSymbol(rs.getString("symbol"));
				singleRow.setConvertedDate(rs.getDate("date"));
				singleRow.setOpen(rs.getFloat("open"));
				singleRow.setHigh(rs.getFloat("high"));
				singleRow.setLow(rs.getFloat("low"));
				singleRow.setClose(rs.getFloat("close"));
				singleRow.setVolume(rs.getLong("volume"));
				
				rowsFromDB.add(singleRow);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info("There was an error in the getDatesBetweenIds method. And that error is: ");
			log.info(e.toString());
			e.printStackTrace();
		}
		
		return rowsFromDB;
	}
	
	public boolean isAlreadyInDB(YahooIndexData row) throws SQLException {
		
		boolean alreadyExists = false;
		int j=0;
		String checkQuery = "SELECT `yd_id` FROM `" + m_yDTname + "`"
				+ " WHERE `date` = ?"
				+ " AND `symbol` = ?";
		
		PreparedStatement ps_check = null;
		
		ps_check = m_connection.prepareStatement(checkQuery);

		ps_check.setString(1, row.getDate());
		ps_check.setString(2, row.getSymbol());
		ResultSet rs = ps_check.executeQuery();
			
		while(rs.next())
		{
			log.debug("Just tried to insert a duplicate row into table " + m_yDTname + ". The yd_id of the entry already in the table is " + rs.getInt("yd_id"));
			alreadyExists = true;
		}
		return alreadyExists;
	}

	public int getRowByIndexAndDate(String index, LocalDate date, boolean isStartDate) {
		int value = 0;
		String query = "SELECT yd_id FROM `" + m_yDTname + "`"
				+ " WHERE `date` = ?"
				+ " AND `symbol` = ?";
		

		try {
			PreparedStatement selectStatement = m_connection.prepareStatement(query);
			selectStatement.setString(1, date.toString());
			selectStatement.setString(2, index);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()) {
				value = rs.getInt("yd_id");
			} else if (isStartDate) {
				log.info("     The date of " + date.toString() + " not found in the database.");
				log.info("          Let me check the preceeding couple of days in case you chose a weekend or holiday.");
				for(int i = 1;i<7;i++)
				{
					selectStatement.setString(1, date.minusDays(i).toString());
					selectStatement.setString(2, index);
					rs = selectStatement.executeQuery();
					if(rs.next())
					{
						value = rs.getInt("yd_id");
						log.info("          Looks like I found one...and you got all worried for nothing.");
						break;
					}
					else if(i==6)
					{
						log.info("          I didn't find an earlier date, so I'll just choose the first date in the data set");
						value=1;
					}
				}
			} else {
				log.info("     The date of " + date.toString() + " not found in the database.");
				log.info("          Let me check the next couple days in case you chose a weekend or holiday.");
				for(int i = 1;i<7;i++)
				{
					selectStatement.setString(1, date.plusDays(i).toString());
					selectStatement.setString(2, index);
					rs = selectStatement.executeQuery();
					if(rs.next())
					{
						value = rs.getInt("yd_id");
						log.info("          Looks like I found one...and you got all worried for nothing.");
						break;
					}
					else if(i==6)
					{
						log.info("          I didn't find an earlier date, so I'll just choose the last date in the data set");
						value= getLastRowId(m_yDTname);
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info("There was an error in the getIdByDate method. And that error is: ");
			log.info(e.toString());
			e.printStackTrace();
		}
		return value;
		
	}

}
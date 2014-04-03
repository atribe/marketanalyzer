package com.atomrockets.marketanalyzer.dbManagers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.atomrockets.marketanalyzer.services.IndexCalcsService;
import com.atomrockets.marketanalyzer.spring.init.PropCache;

/**
 * This class is the highest level class that deals with all things market index.
 * Initializes and updates databases
 * Runs the model to determine buy and sell dates
 * @author Allan
 *
 */
@Component
public class DatabaseInitialization{

	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(DatabaseInitialization.class.getName());
	
	//							  		  Nasdaq  S&P500
	static private String[] indexList;
	
	//Database Table Managers
	static private IndexYahooDataTableManager m_indexYahooTable;
	static private IndexParameterTableManager m_indexParamTable;
	static private IndexCalcsService m_indexAnalysisService;
	

	static public void marketsDBInitialization() {
		Connection connection = null;
		try {
			log.trace("3.0 Starting markets DB init method");
			
			//Get a database connection
			log.trace("3.1 Getting a connection");
			connection = GenericDBSuperclass.getConnection();
	
			/*
			 * Getting the index list from the property file
			 */
			indexList = PropCache.getCachedProps("index.names").split(",");
			/*
			 * Dabase initialization Section
			 */
			//Creating the table manager
			log.trace("3.2 Creating IndexyahooDataTableManager");
			m_indexYahooTable = new IndexYahooDataTableManager(connection);
			//Initialize the price/volume databases for each index
			log.info("3.3 Initializing YahooIndexData Table");
			m_indexYahooTable.tableInitialization(indexList);
	
			//Initialize the parameter table
			log.trace("3.4 Creating IndexParameterTableManager");
			m_indexParamTable = new IndexParameterTableManager(connection);
			log.info("3.5 Initializing IndexParameter Table");
			m_indexParamTable.tableInitialization(indexList);
			
			/*
			 * Analysis data for each index is stored in a separate database. I chose this because it makes it easier
			 * to clear and repopulate when doing debugging or optimization. The analysis data table can be dropped without
			 * having to reimport all the data, which is quite time consuming.
			 * 
			 * IndexAnalysisDBInitialization checks if the table already
			 */
			log.trace("3.6 Creating IndexCalcsService");
			m_indexAnalysisService = new IndexCalcsService(connection);
			log.info("3.7 Initializing IndexCalcs table");
			//skipping this while I work on the view portion of the program
			m_indexAnalysisService.init(indexList);
			
		} catch (ClassNotFoundException e) {
			// Handles errors if the JDBC driver class not found.
			log.error("Database Driver not found in " + GenericDBSuperclass.class.getSimpleName() + ". Error as follows: "+e);
		} catch (SQLException ex){
			// Handles any errors from MySQL
			log.error("Did you forget to turn on Apache and MySQLL again? From Exception:");
			log.error("SQLException: " + ex.getMessage());
			log.error("SQLState: " + ex.getSQLState());
			log.error("VendorError: " + ex.getErrorCode());
		} finally {
			try {
				connection.close();
			} catch (NullPointerException ne) {
				//do nothing, just means that the connection was never initialized
				log.debug("Connection didn't need to be closed, it was never initialized");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//uncomment to get the scheduling back
	//@Scheduled(fixedDelay = 5000)
    public void demoServiceMethod()
    {
        log.debug("Method executed at every 15 seconds. Current time is :: "+ new Date());
        
        /*
         * Useful code that tells you where the log file is being written to for log4j
         *
        Enumeration e = Logger.getRootLogger().getAllAppenders();
        while ( e.hasMoreElements() )
        {
        	Appender app = (Appender)e.nextElement();
        	if ( app instanceof FileAppender ){
        		System.out.println("File: " + ((FileAppender)app).getFile());
        	}
        }*/
    }

	//getters
	static private String[] getIndexList() {
		return indexList;
	}
}

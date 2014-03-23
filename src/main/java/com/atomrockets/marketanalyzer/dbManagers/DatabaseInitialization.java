package com.atomrockets.marketanalyzer.dbManagers;

import java.sql.Connection;
import java.util.Date;
import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.atomrockets.marketanalyzer.services.IndexCalcsService;

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
	static private String[] indexList = {"^IXIC","^GSPC","^SML","^MID"};
	
	//Database Table Managers
	static private IndexYahooDataTableManager m_indexYahooTable;
	static private IndexParameterTableManager m_indexParamTable;
	static private IndexCalcsService m_indexAnalysisService;
	

	static public void marketsDBInitialization() {

		//Get a database connection
		Connection connection = GenericDBSuperclass.getConnection();

		/*
		 * Dabase initialization Section
		 */
		//Creating the table manager
		m_indexYahooTable = new IndexYahooDataTableManager(connection);
		//Initialize the price/volume databases for each index
		m_indexYahooTable.tableInitialization(indexList);

		
		//Initialize the parameter table
		m_indexParamTable = new IndexParameterTableManager(connection);
		m_indexParamTable.tableInitialization(indexList);

		
		/*
		 * Analysis data for each index is stored in a separate database. I chose this because it makes it easier
		 * to clear and repopulate when doing debugging or optimization. The analysis data table can be dropped without
		 * having to reimport all the data, which is quite time consuming.
		 * 
		 * IndexAnalysisDBInitialization checks if the table already
		 */
		m_indexAnalysisService = new IndexCalcsService(connection);
		m_indexAnalysisService.init(indexList);
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

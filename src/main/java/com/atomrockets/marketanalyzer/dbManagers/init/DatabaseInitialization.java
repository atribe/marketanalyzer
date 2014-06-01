package com.atomrockets.marketanalyzer.dbManagers.init;

import java.util.Date;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.atomrockets.marketanalyzer.dbManagers.MarketPredDataSource;
import com.atomrockets.marketanalyzer.dbManagers.OHLCVDao;
import com.atomrockets.marketanalyzer.services.BacktestService;
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
	static private OHLCVDao m_OHLCVDao;
	static private BacktestService m_BacktestService;
	static private IndexCalcsService m_indexAnalysisService;
	

	static public void marketsDBInitialization() {
		log.trace("3.0 Starting markets DB init method");
		
		//Get a database connection
		log.trace("3.1 Getting a connection");
		DataSource ds = MarketPredDataSource.setDataSource();
		
		/*
		 * Getting the index list from the property file
		 */
		indexList = PropCache.getCachedProps("index.names").split(",");
		/*
		 * Dabase initialization Section
		 */
		//Creating the table manager
		log.trace("3.2 Creating IndexyahooDataTableManager");
		m_OHLCVDao = new OHLCVDao(ds);
		//Initialize the price/volume databases for each index
		log.info("3.3 Initializing YahooIndexData Table");
		m_OHLCVDao.tableInitialization(indexList);

		//Initialize the parameter table
		log.trace("3.4 Creating BacktestResultDAO");
		m_BacktestService = new BacktestService(ds);
		log.info("3.5 Initializing IndexParameter Table");
		m_BacktestService.init(indexList);
		
		
		/*
		 * Analysis data for each index is stored in a separate database. I chose this because it makes it easier
		 * to clear and repopulate when doing debugging or optimization. The analysis data table can be dropped without
		 * having to reimport all the data, which is quite time consuming.
		 * 
		 * IndexAnalysisDBInitialization checks if the table already
		 */
		log.trace("3.6 Creating IndexCalcsService");
		m_indexAnalysisService = new IndexCalcsService(ds);
		log.info("3.7 Initializing IndexOHLCVCalcs table");
		//skipping this while I work on the view portion of the program
		m_indexAnalysisService.init(indexList);
		
		log.info("3.8 Running backtests based on current parameters");
		m_BacktestService.runAllIndexModels(indexList);
	}
	
	//Scheduled to run every weekday at 5 pm
	@Scheduled(cron="0 0 18 * * MON-FRI")
    public void dataBaseUpdate()
    {
        log.debug("Method executed every weekday at 5 pm. Current time is :: "+ new Date());
        
        indexList = PropCache.getCachedProps("index.names").split(",");
        
        m_OHLCVDao.updateIndexes(indexList);
        
        
        m_indexAnalysisService.updateIndexCalcs(indexList);
        log.info("End of scheduled update method at: "+ new Date());
    }
}

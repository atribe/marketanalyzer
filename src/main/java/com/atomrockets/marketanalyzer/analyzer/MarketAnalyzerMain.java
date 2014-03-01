package com.atomrockets.marketanalyzer.analyzer;

import com.atomrockets.marketanalyzer.dbManagers.GenericDBSuperclass;
import com.atomrockets.marketanalyzer.dbManagers.IndexAnalysisTableManager;
import com.atomrockets.marketanalyzer.dbManagers.IndexYahooDataTableManager;
import com.atomrockets.marketanalyzer.dbManagers.IndexParameterTableManager;
import com.atomrockets.marketanalyzer.analyzer.IndexAnalyzer;

import java.sql.Connection;

/**
 * This class is the highest level class that deals with all things market index.
 * Initializes and updates databases
 * Runs the model to determine buy and sell dates
 * @author Allan
 *
 */
public class MarketAnalyzerMain{

	//							  		  Nasdaq  S&P500
	static private String[] indexList = {"^IXIC","^GSPC","^SML","^MID"};
	
	//Database Table Managers
	static private IndexYahooDataTableManager m_indexYahooTable;
	static private IndexParameterTableManager m_indexParamTable;
	static private IndexAnalysisTableManager m_indexAnalysisTable;
	

	static public void main() {

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
		m_indexAnalysisTable = new IndexAnalysisTableManager(connection);
		m_indexAnalysisTable.tableInitialization(indexList);

		
		//Market Index Models
		/*
		 * Models runs are not looped because you may want to run or optimize them one at a time
		 * I'll figure out this code after I figure out the above
		 */
		IndexAnalyzer.runIndexAnalysis(connection, "^IXIC");
		//Run model for Nasdaq
		//Run model for SP500
		
		
	}
	
	static private String[] getIndexList() {
		return indexList;
	}
}

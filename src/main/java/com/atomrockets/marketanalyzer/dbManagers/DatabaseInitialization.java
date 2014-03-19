package com.atomrockets.marketanalyzer.dbManagers;

import com.atomrockets.marketanalyzer.analyzer.IndexAnalyzer;

import java.sql.Connection;

import org.joda.time.LocalDate;

/**
 * This class is the highest level class that deals with all things market index.
 * Initializes and updates databases
 * Runs the model to determine buy and sell dates
 * @author Allan
 *
 */
public class DatabaseInitialization{

	//							  		  Nasdaq  S&P500
	static private String[] indexList = {"^IXIC","^GSPC","^SML","^MID"};
	
	//Database Table Managers
	static private IndexYahooDataTableManager m_indexYahooTable;
	static private IndexParameterTableManager m_indexParamTable;
	static private IndexAnalysisTableManager m_indexAnalysisTable;
	

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
		m_indexAnalysisTable = new IndexAnalysisTableManager(connection);
		m_indexAnalysisTable.tableInitialization(indexList);
	}

	//getters
	static private String[] getIndexList() {
		return indexList;
	}
}

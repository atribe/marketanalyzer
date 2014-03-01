package com.atomrockets.marketanalyzer.analyzer;

import com.atomrockets.marketanalyzer.dbManagers.GenericDBSuperclass;
import com.atomrockets.marketanalyzer.dbManagers.MarketIndexAnalysisDB;
import com.atomrockets.marketanalyzer.dbManagers.MarketIndexDB;
import com.atomrockets.marketanalyzer.dbManagers.MarketIndexParametersDB;
import com.atomrockets.marketanalyzer.analyzer.IndexAnalyzer;

import java.sql.Connection;

/**
 * This class is the highest level class that deals with all things market index.
 * Initializes and updates databases
 * Runs the model to determine buy and sell dates
 * @author Allan
 *
 */
public class MarketIndicesAnalyzer{

	//							  		  Nasdaq  S&P500
	static private String[] indexList = {"^IXIC","^GSPC","^SML","^MID"};

	static public void main() {

		//Get a database connection
		Connection connection = GenericDBSuperclass.getConnection();

		/*
		 * Dabase initialization Section
		 */
		//Initialize the price/volume databases for each index 
		MarketIndexDB.yahooDataDBInitialization(connection, indexList);

		//TODO start here tomoroow
		//Initialize the model variables
		MarketIndexParametersDB.indexModelParametersInitialization(connection, indexList);		


		//Market Index Models
		/*
		 * Models runs are not looped because you may want to run or optimize them one at a time
		 * I'll figure out this code after I figure out the above
		 */
		
		/*
		 * Analysis data for each index is stored in a separate database. I chose this because it makes it easier
		 * to clear and repopulate when doing debugging or optimization. The analysis data table can be dropped without
		 * having to reimport all the data, which is quite time consuming.
		 * 
		 * IndexAnalysisDBInitialization checks if the table already
		 */
		MarketIndexAnalysisDB.IndexAnalysisTableInitialization(connection, indexList);
		
		IndexAnalyzer.runIndexAnalysis(connection, "^IXIC", "^IXICvars");
		//Run model for Nasdaq
		//Run model for SP500
		
		
	}
	
	static private String[] getIndexList() {
		return indexList;
	}
}

package com.atomrockets.marketanalyzer.dbManagers.init;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.atomrockets.marketanalyzer.dbManagers.IBD50Dao;
import com.atomrockets.marketanalyzer.dbManagers.MarketPredDataSource;

public class IBD50Init {

	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(IBD50Init.class.getName());
	
	static private IBD50Dao ibd50Dao;
	
	public static void main() {
		log.trace("Starting IBD50 DB init method");
		
		DataSource ds = MarketPredDataSource.setDataSource();
		
		ibd50Dao = new IBD50Dao(ds);
	}
}

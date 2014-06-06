package com.ar.marketanalyzer.database.init;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.services.IBD50Service;

public class IBD50Init {

	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(IBD50Init.class.getName());
	
	public static void main() {
		log.trace("Starting IBD50 DB init method");
		
		DataSource ds = MarketPredDataSource.setDataSource();
		
		IBD50Service ibd50Service = new IBD50Service(ds);
		ibd50Service.tableInit();
		ibd50Service.pullWebIbd50();
	}
}

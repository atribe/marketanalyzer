package com.ar.marketanalyzer.database.init;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;
import com.ar.marketanalyzer.ibd50.services.IBD50DBService;
import com.ar.marketanalyzer.ibd50.services.IBD50StatService;
import com.ar.marketanalyzer.ibd50.services.TickerSymbolService;

@Component
public class IBD50Init {

	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(IBD50Init.class.getName());
	
	private static boolean dropTables = true; 
	
	@Autowired
	private TickerSymbolService tsService;
	
	@Autowired
	
	
	public void main() {
		log.trace("Starting IBD50 DB init method");
		
		DataSource ds = MarketPredDataSource.setDataSource();
		
		IBD50DBService ibd50Service = new IBD50DBService(ds);
		
		ibd50Service.updateFromIbd50Web();
		
		IBD50StatService ibd50StatService = new IBD50StatService();
		
		ibd50StatService.calcIbd50Stats();
	}
	
	//Scheduled to run every Monday at 5 am
	@Scheduled(cron="0 0 5 * * MON-FRI")
	public void weeklyUpdateIbd50() {
		IBD50DBService ibd50Service = new IBD50DBService();
		
		ibd50Service.updateFromIbd50Web();
	}
}

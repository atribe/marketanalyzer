package com.ar.marketanalyzer.database.init;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.models.Ibd50Ranking;
import com.ar.marketanalyzer.ibd50.models.Ibd50Tracking;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;
import com.ar.marketanalyzer.ibd50.services.IBD50DBService;
import com.ar.marketanalyzer.ibd50.services.IBD50StatService;
import com.ar.marketanalyzer.ibd50.services.Ibd50RankingService;
import com.ar.marketanalyzer.ibd50.services.Ibd50TrackingService;
import com.ar.marketanalyzer.ibd50.services.TickerSymbolService;

@Component
public class IBD50Init {

	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(IBD50Init.class.getName());
	
	private static boolean dropTables = true; 
	
	@Autowired
	private TickerSymbolService tsService;
	
	@Autowired
	private Ibd50RankingService rankingService;
	
	@Autowired
	private Ibd50TrackingService trackingService;
	
	public void main() {
		log.trace("Starting IBD50 DB init method");
		
		Ibd50Ranking a = new Ibd50Ranking();
		
		TickerSymbol s = new TickerSymbol();
		s.setSymbol("NOV");
		s.setName("National Oilwell Varco");
		s.setType("Stock");
		tsService.create(s);
		
		Ibd50Tracking t = new Ibd50Tracking();
		t.setActive(Boolean.TRUE);
		t.setTicker(s);
		trackingService.create(t);
		
		a.setTicker(s);
		a.setTracker(t);
		a.setRank(1);
		a.setCurrentPrice(new BigDecimal(55.32));
		a.setPriceChange(new BigDecimal(5.20));
		a.setPricePercentChange(.23);
		
		rankingService.create(a);
		
		
		
		
		
		
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

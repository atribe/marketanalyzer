package com.ar.marketanalyzer.ibd50.logic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.ibd50.services.Ibd50UpdateService;

@Component
public class IBD50Init {

	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(IBD50Init.class.getName());
	
	@Autowired
	private Ibd50UpdateService ibd50UpdateService;
	
	public void main() {
		log.trace("Starting IBD50 DB init method");
		
		/*
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
		*/
		updateFromIbd50Web();

		//ibd50StatService.calcIbd50Stats();
	}

	//Scheduled to run every Monday at 5 am
	@Scheduled(cron="0 0 5 * * MON-FRI")
	public void weeklyUpdateIbd50() {
		updateFromIbd50Web();
	}
	
	private void updateFromIbd50Web() {
		ibd50UpdateService.updateIbd50();
	}
}

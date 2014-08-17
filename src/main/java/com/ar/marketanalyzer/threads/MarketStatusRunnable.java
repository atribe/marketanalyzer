package com.ar.marketanalyzer.threads;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.marketstatus.logic.MarketStatusInit;

@Component("MarketStatus")
@Scope("prototype")
public class MarketStatusRunnable implements Runnable {

	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private MarketStatusInit marketStatusInit;
	
	@Override
	public void run() {
		log.info("MarketStatus thread started.");
		
		marketStatusInit.init();
		
		log.info("MarketStatus thread is done.");
	}
}

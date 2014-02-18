package com.atomrockets.marketanalyzer.beans;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;

import com.atomrockets.marketanalyzer.analyzer.MarketIndicesAnalyzer;
import com.atomrockets.marketanalyzer.spring.controller.AccountController;

public class MyAsyncBean {

	static Logger log = Logger.getLogger(AccountController.class.getName());
	
	public void MyAsyncBean() { }
	
	@Async
	static public void startMarketIndiceAnalyzer() {
		log.info("Starting Async Bean MyAsyncBean");
		MarketIndicesAnalyzer.main();
	}
}

package com.atomrockets.marketanalyzer.beans;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.atomrockets.marketanalyzer.analyzer.MarketIndicesAnalyzer;
import com.atomrockets.marketanalyzer.spring.controller.AccountController;

@Component
@Scope("prototype")
public class MarketAnalyzerBean implements Runnable {

	String name="MarketAnalyzerBean";
	static Logger log = Logger.getLogger(AccountController.class.getName());
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void run() { 
		log.info(name + " is running");
		
		MarketIndicesAnalyzer.main();
		
		log.info(name + " had ended");
	}
}

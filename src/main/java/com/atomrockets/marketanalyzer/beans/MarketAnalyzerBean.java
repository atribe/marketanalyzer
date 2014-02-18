package com.atomrockets.marketanalyzer.beans;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.atomrockets.marketanalyzer.analyzer.MarketIndicesAnalyzer;

@Component
public class MarketAnalyzerBean {

	@Async
	static public void doSomething() { 
		MarketIndicesAnalyzer.main();
	}
}

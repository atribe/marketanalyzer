package com.atomrockets.marketanalyzer.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

import com.atomrockets.marketanalyzer.analyzer.MarketAnalyzerMain;
import com.atomrockets.marketanalyzer.beans.MarketAnalyzerBean;

@Configuration
@ComponentScan(basePackages = "com.atomrockets.marketanalyzer")
public class AppConfig {
	/*
	 * The main AppConfig configuration class doesn’t do anything but hits Spring
	 * on where to look for its components through @ComponentScan annotation.
	 */
	@Bean
	public MarketAnalyzerBean maBean() {
		MarketAnalyzerBean maBean = new MarketAnalyzerBean();
		return maBean;
	}
	
	@Bean
	public MarketAnalyzerMain maMain() {
	    return new MarketAnalyzerMain();
	}
}

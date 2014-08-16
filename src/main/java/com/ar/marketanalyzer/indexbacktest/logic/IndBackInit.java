package com.ar.marketanalyzer.indexbacktest.logic;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource({ "classpath:common.properties" })
public class IndBackInit {

	/* Get actual class name to be printed on */
	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Resource
	private Environment env;
	
	
	
	static private String[] indexList;
	
	public void main() {
		log.trace("Starting Index Backtest (IndBack) Thread");
		
		indexList = env.getRequiredProperty("index.names").split(",");		// Getting the index list from the property file
		
		
	}
}

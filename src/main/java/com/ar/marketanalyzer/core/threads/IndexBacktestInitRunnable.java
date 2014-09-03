package com.ar.marketanalyzer.core.threads;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.database.init.IndexBacktestInit;

/**
 * @author Allan
 *
 */
@Component("IndexBacktestInit")
@Scope("prototype")
public class IndexBacktestInitRunnable implements Runnable {

	Logger log = Logger.getLogger(this.getClass().getName());

	@Override
	public void run() { 
		log.info("Index Backtest thread commenced running");
		
		IndexBacktestInit.marketsDBInitialization();
		
		log.info("Index Backtest thread has ended");
	}
}

package com.ar.marketanalyzer.core.threads;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.backtest.logic.BacktestLogic;

/**
 * @author Allan
 *
 */
@Component("IndexBacktestInit")
@Scope("prototype")
public class BacktestInitThread implements Runnable {

	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	BacktestLogic backtestLogic;

	@Override
	public void run() { 
		log.info("Backtest thread commenced running");
		
		backtestLogic.init();
		
		log.info("Backtest thread has ended");
	}
}

package com.ar.marketanalyzer.core.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(BacktestInitThread.class);
	
	@Autowired
	BacktestLogic backtestLogic;

	@Override
	public void run() { 
		logger.info("Backtest thread commenced running");
		
		backtestLogic.init();
		
		logger.info("Backtest thread has ended");
	}
}

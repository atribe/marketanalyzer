package com.ar.marketanalyzer.core.threads.runnables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.ibd50.logic.IBD50Init;

@Component
@Scope("prototype")
public class IBD50InitRunnable implements Runnable{

	private static final Logger logger = LogManager.getLogger(IBD50InitRunnable.class);
	
	@Autowired
	private IBD50Init ibd50Init;

	@Override
	public void run() {
		logger.info("IBD 50 Thread commenced running");

		ibd50Init.main();

		logger.info("IBD 50 Thread has ended");
	}
}

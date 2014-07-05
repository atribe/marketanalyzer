package com.ar.marketanalyzer.threads;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.database.init.IBD50Init;

@Component
@Scope("prototype")
public class IBD50InitRunnable implements Runnable{

	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private IBD50Init ibd50Init;

	@Override
	public void run() {
		log.info("IBD 50 Thread commenced running");

		ibd50Init.main();

		log.info("IBD 50 Thread has ended");
	}
}

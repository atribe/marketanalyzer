package com.atomrockets.marketanalyzer.threads;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.atomrockets.marketanalyzer.dbManagers.init.DatabaseInitialization;
import com.atomrockets.marketanalyzer.spring.init.PropCache;

@Component
@Scope("prototype")
public class MarketsDBInitRunnable implements Runnable {

	
	Logger log = Logger.getLogger(this.getClass().getName());
	
	private final String thread_name = PropCache.getCachedProps("threads.dbinit");
	
	public String getThread_name() {
		return thread_name;
	}

	@Override
	public void run() { 
		log.info("2.0 " + getThread_name() + " commenced running");
		
		DatabaseInitialization.marketsDBInitialization();
		
		log.info("2.1 " + getThread_name() + " has ended");
	}
}

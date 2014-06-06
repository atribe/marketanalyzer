package com.ar.marketanalyzer.threads;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.database.init.IBD50Init;
import com.ar.marketanalyzer.spring.init.PropCache;

@Component
@Scope("prototype")
public class IBD50InitRunnable implements Runnable{

	Logger log = Logger.getLogger(this.getClass().getName());
	
	private final String thread_name = PropCache.getCachedProps("threads.IBD50");
	
	public String getThread_name() {
		return thread_name;
	}
	
	@Override
	public void run() {
		log.info(getThread_name() + " commenced running");

		IBD50Init.main();

		log.info(getThread_name() + " has ended");
	}
}

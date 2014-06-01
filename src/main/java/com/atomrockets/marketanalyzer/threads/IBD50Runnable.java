package com.atomrockets.marketanalyzer.threads;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.atomrockets.marketanalyzer.ibd50.IBD50Grabber;
import com.atomrockets.marketanalyzer.spring.init.PropCache;

@Component
@Scope("prototype")
public class IBD50Runnable implements Runnable{

	Logger log = Logger.getLogger(this.getClass().getName());
	
	private final String thread_name = PropCache.getCachedProps("threads.IBD50");
	
	public String getThread_name() {
		return thread_name;
	}
	
	@Override
	public void run() {
		log.info(getThread_name() + " commenced running");
		
		try {
			
			IBD50Grabber.main();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info(getThread_name() + " has ended");
	}
}

package com.atomrockets.marketanalyzer.threads;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.atomrockets.marketanalyzer.dbManagers.DatabaseInitialization;

@Component
@Scope("prototype")
public class MarketsDBInitRunnable implements Runnable {

	String name="MarketsDBInitRunnable";
	Logger log = Logger.getLogger(this.getClass().getName());
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void run() { 
		log.info(name + " is running");
		
		DatabaseInitialization.marketsDBInitialization();
		
		log.info(name + " had ended");
	}
	
	@Scheduled(fixedDelay = 5000)
    //@Scheduled(fixedRate = 5000)
    public void demoServiceMethod()
    {
        System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
    }
}

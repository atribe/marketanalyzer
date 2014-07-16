package com.ar.marketanalyzer.ibd50.logic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IBD50Init {

	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(IBD50Init.class.getName());
	
	@Autowired
	private Ibd50UpdateLogic ibd50Update;
	
	public void main() {
		log.trace("Starting IBD50 DB init method");

		updateFromIbd50Web();

		//ibd50StatService.calcIbd50Stats();
	}

	//Scheduled to run every Monday at 5 am
	@Scheduled(cron="0 0 5 * * MON-FRI")
	public void weeklyUpdateIbd50() {
		updateFromIbd50Web();
	}
	
	private void updateFromIbd50Web() {
		ibd50Update.updateIbd50();
	}
}

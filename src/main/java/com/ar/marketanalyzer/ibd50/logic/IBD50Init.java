package com.ar.marketanalyzer.ibd50.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IBD50Init {

	/* Get actual class name to be printed on */
	private static final Logger logger = LogManager.getLogger(IBD50Init.class.getName());
	
	@Autowired
	private Ibd50UpdateLogic ibd50Update;
	@Autowired
	private IBD50StatsLogic ibd50Stats;
	public void main() {
		logger.trace("Starting IBD50 DB init method");

		updateFromIbd50Web();

		runIbd50Stats();
	}

	//Scheduled to run every Monday at 5 am
	@Scheduled(cron="0 0 5 * * MON-FRI")
	public void weeklyUpdateIbd50() {
		updateFromIbd50Web();
	}
	
	private void updateFromIbd50Web() {
		ibd50Update.updateIbd50();
	}
	
	private void runIbd50Stats() {
		ibd50Stats.calcIbd50Stats();
	}
}

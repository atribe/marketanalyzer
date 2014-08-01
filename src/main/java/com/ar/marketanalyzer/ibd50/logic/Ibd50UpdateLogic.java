package com.ar.marketanalyzer.ibd50.logic;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.marketanalyzer.ibd50.dao.Ibd50WebDao;
import com.ar.marketanalyzer.ibd50.exceptions.Ibd50NotFound;
import com.ar.marketanalyzer.ibd50.exceptions.Ibd50TooManyFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import com.ar.marketanalyzer.ibd50.models.Ibd50Tracking;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;
import com.ar.marketanalyzer.ibd50.services.Ibd50RankService;
import com.ar.marketanalyzer.ibd50.services.Ibd50TrackingService;
import com.ar.marketanalyzer.ibd50.services.StockOhlcvService;
import com.ar.marketanalyzer.ibd50.services.TickerSymbolService;
import com.ar.marketanalyzer.indexbacktest.dao.YahooDataRetriever;

/**
 * @author Allan
 *
 */
@Service
public class Ibd50UpdateLogic {
	
	//logger
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	private Ibd50WebDao webDao;
	
	@Autowired
	private TickerSymbolService tsService;
	@Autowired
	private Ibd50RankService rankingService;
	@Autowired
	private Ibd50TrackingService trackingService;
	@Autowired
	private StockOhlcvService ohlcvService;
	
	public Ibd50UpdateLogic() {
	}

	/**
	 * Pulls the current top 50 from investors.com and adds them to the database 
	 */
	public void updateIbd50() {
		if( !isDbUpToDate() ) {
			webDao = new Ibd50WebDao();
			List<Ibd50Rank> webIbd50 = webDao.grabIbd50();
	
			archiveOldInfo(webIbd50);
			
			addThisWeeksListToDB(webIbd50);
		}
	}

	private void archiveOldInfo(List<Ibd50Rank> webIbd50) {
		deactivateOldTrackers(webIbd50);
		
		deactivateAllCurrentRankings();
		
		updateOHLCVforInactiveTracker();
	}

	/**
	 * Adds weekly list to the database
	 * <p>
	 * Operations are as follows
	 * <ol>
	 * <li>Check to see if there already is an entry in the Ranking DB for this weeks list</li>
	 * <li>Cycle through each stock in the list</li>
	 * <ol>
	 * 	<li>Check to see if a stock is in the symbol database</li>
	 * 	<ul><li>If not add it</li></ul>
	 * 	<li>Check to see if that symbol has an active tracking DB entry</li>
	 * 		<ul><li>If not add it to the tracking DB</li></ul>
	 * 	<li>Check to see if that symbol is in the ranking DB</li>
	 * 		<ul><li>If not add it to the ranking DB</li></ul>
	 * 	<li>Check to see if that symbol has updated OHLCV data</li>
	 * 		<ul><li>If not update the data from Yahoo</li></ul>
	 * </ol>
	 * </ol>
	 * @param webIbd50
	 */
	private boolean isDbUpToDate() {
		LocalDate previousMonday = new LocalDate().withDayOfWeek(1);								// Get the most recent monday
		
		List<Ibd50Rank> newestRankings;
		try {
			newestRankings = rankingService.findByModificationTimeAfter(previousMonday.toDate());	// Check the db for any rankings modified after monday
		} catch (Ibd50NotFound e) {
			return false;																			// None found, so db is not up to date
		}
		Ibd50Rank newestRanking;
		try {
			newestRanking = newestRankings.get(0);
		} catch (IndexOutOfBoundsException e) {
			return false;																			// if there isn't an element 0 then db isn't updated
		}
		if(newestRanking.getLocalDateModificationTime().isAfter(previousMonday) || newestRanking.getLocalDateRankDate().equals(previousMonday)) {							// Doing a check with the LocalDate to make sure it is after monday
			return true;																			// test is true, so return true
		} else {
			return false;																			// Date is not after monday so return false
		}
	}
	
	private void deactivateOldTrackers(List<Ibd50Rank> webIbd50) {
		List<Ibd50Tracking> activeTrackers = trackingService.findByActiveTrue();			// Find all active trackers
		
		for( Ibd50Tracking tracker : activeTrackers ) {										// Loop through the active trackers
			boolean trackerStillActive = false;												// Start with the assumption that the tracker is now inactive
			
			for( Ibd50Rank rank : webIbd50 ) {												// Loop through the downloaded rankings
				if( rank.getTicker().getSymbol().equals( tracker.getTicker().getSymbol() ) ) {		// Check for a match in symbols 
					trackerStillActive = true;
					break;
				}
			}
			
			if(!trackerStillActive) {														// After all the new ranks are checked against the current tracker and the tracker isn't active 
				runOhlcvUpdate(tracker.getTicker());
				StockOhlcv leaveOhlcv = null;
				try {
					leaveOhlcv = ohlcvService.findByTickerAndDate(tracker.getTicker(), tracker.newMonday().toDate());
				} catch (Ibd50NotFound e) {
					e.printStackTrace();
					log.info("This shouldn't happen since I just updated the ohclv the line before this.");
				}
				tracker.deactivate(leaveOhlcv.getClose());														// Set it to inactive
				trackingService.updateActivity(tracker);									// And update the tracker db
			}
		}
	}
	
	private void deactivateAllCurrentRankings() {
		rankingService.deactivateAllCurrentRankings();
	}

	
	private void addThisWeeksListToDB(List<Ibd50Rank> webIbd50) {
		for(Ibd50Rank rankingRow : webIbd50) {										// Cycle through the each of row of the top 50
			
			TickerSymbol rowTicker = rankingRow.getTicker();						// Getting the ticker out of the ranking
			
			TickerSymbol foundTicker = tsService.createOrFindDuplicate(rowTicker);	// Adding the ticker to the DB or getting the one in the db out
			
			rankingRow.setTicker(foundTicker);										// Set the found ticker to be the ticker for the row
			
																					// Check to see if the OHLCV data is up to date for this stock symbol
			runOhlcvUpdate(foundTicker);											// add the last 6 months of OHLCV data to the db
			
			final boolean isActive = true;
	
			Ibd50Tracking foundTracker;
			try {
				foundTracker = trackingService.findByActiveAndTicker(isActive, foundTicker);
			} catch (Ibd50NotFound e) {
				log.info(e.getMessage());
				
																					//Tracker not found
				Ibd50Tracking newTracker = new Ibd50Tracking();						//create a new tracker
				newTracker.setTicker(foundTicker);									//set the Ticker for the new tracker
				newTracker.setActive(Boolean.TRUE);
				StockOhlcv joinDayOhlcv = null;
				try {
					joinDayOhlcv = ohlcvService.findByTickerAndDate(foundTicker, newTracker.getJoinDate());
				} catch (Ibd50NotFound e1) {
					log.info(e.getMessage());
					e.printStackTrace();
				}
				newTracker.setJoinPrice(joinDayOhlcv.getClose());
				
				foundTracker = trackingService.create(newTracker);					//add the new tracker to the db
			} catch (Ibd50TooManyFound e) {
				log.info(e.getMessage());
				e.printStackTrace();
				break;
			} 
			
			rankingRow.setTracker(foundTracker);									//Set the found tracker to be the tracker for the row
	
			rankingService.create(rankingRow);										//add the row to the db
		}
	}

	/**
	 * Check to see if the symbol is already in the db.
	 * If so then get the last date
	 * 		if that date is within six months of today
	 * 			use that date as the start date and today as the end date
	 *	if not in the db
	 *		use six months ago as the start date and today as the end date
	 *  	
	 * 
	 * @param row
	 * @throws SQLException 
	 */
	private void runOhlcvUpdate(TickerSymbol ticker) {
		final int monthsOfData = 6; 

		List<StockOhlcv> currentOhlcvList;
		LocalDate ohlcvDesiredStartDate = new LocalDate().minusMonths(monthsOfData);									//set the oldestDate as today
		LocalDate startDate;
		LocalDate today = new LocalDate();
		try {
			currentOhlcvList = ohlcvService.findByTickerAndDateAfter(ticker, ohlcvDesiredStartDate.toDate());	//see if there is anything in the db after 6 months ago from today
			
			//older data was found in the db
			//so find the newest, the above query sorts so newest is at position 0
			StockOhlcv mostRecentDate = currentOhlcvList.get(0);
			startDate = mostRecentDate.getLocalDate().plusDays(1); 		//Then add a day so we don't have duplicate data
		} catch (Ibd50NotFound e) {
			log.info(e.getMessage());
			
			//nothing was found in the db
			//so set the furthest date back to be six months ago
			startDate = ohlcvDesiredStartDate;
		}
		
		List<StockOhlcv> ohlcvData = YahooDataRetriever.getStockFromYahoo(ticker, startDate);
		
		ohlcvService.batchInsert(ohlcvData);
	}
	
	private void updateOHLCVforInactiveTracker() {
		/*
		 * The plan for this method
		 * 
		 * 1. get all trackers that are 6 months old or less and are inactive
		 * 
		 * 2. update ohlcv for them
		 */
		final int inactiveUpdateMonthsWindow = 6;
		LocalDate inactiveUpdateWindow = new LocalDate().minusMonths( inactiveUpdateMonthsWindow );
		
		List<Ibd50Tracking> deactiveTrackers = null;
		try {
			deactiveTrackers = trackingService.findByActiveFalseAndDateAfter(inactiveUpdateWindow);
		} catch (Ibd50NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Ibd50Tracking tracker: deactiveTrackers) {
			runOhlcvUpdate(tracker.getTicker());
		}
		
	}
}

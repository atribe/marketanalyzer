package com.ar.marketanalyzer.ibd50.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.marketanalyzer.ibd50.dao.Ibd50WebDao;
import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.exceptions.Ibd50TooManyFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Ranking;
import com.ar.marketanalyzer.ibd50.models.Ibd50Tracking;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;
import com.ar.marketanalyzer.indexbacktest.dao.YahooDataRetriever;

/**
 * @author Allan
 *
 */
@Service
public class Ibd50UpdateServiceImpl implements Ibd50UpdateService {
	
	//logger
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	private Ibd50WebDao webDao;
	
	@Autowired
	private TickerSymbolService tsService;
	
	@Autowired
	private Ibd50RankingService rankingService;
	
	@Autowired
	private Ibd50TrackingService trackingService;
	
	@Autowired
	private StockOhlcvService ohlcvService;
	
	/**
	 * Constructor that gets a new DataSource and then creates all 
	 * required DAOs with the DataSource
	 */
	public Ibd50UpdateServiceImpl() {
	}

	/**
	 * Pulls the current top 50 from investors.com and adds them to the database 
	 */
	@Override
	public void updateIbd50() {
		 webDao = new Ibd50WebDao();
		 
		List<Ibd50Ranking> webIbd50 = webDao.grabIbd50();
		
		addWeeklyListToDB(webIbd50);
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
	private void addWeeklyListToDB(List<Ibd50Ranking> webIbd50) {
		
		if(!isDbUpToDate(webIbd50)) { //if db not up to date
			addThisWeeksListToDB(webIbd50);
		}
	}

	private boolean isDbUpToDate(List<Ibd50Ranking>	webIbd50) {
		/*
		 * Check to see if the first in the downloaded list matches the current first. 
		 * See if the symbol and rank are the same. then check if the dates are within 7 days of each other, but not both from the same day
		 */
		Ibd50Ranking rankOne = webIbd50.get(0);
		
		try {
			rankingService.findByRankAndTicker(rankOne.getRank(), rankOne.getTicker());
		} catch (GenericIbd50NotFound e) {
			log.info(e.getMessage());
			log.info("The above message means that the DB is out of date and to proceed with updating it.");
			return false;			//no results were found for the query, that must mean the list is not up to date
		}
		
		//TODO make sure whatever happens when the exceptions aren't thrown is what is supposed to happen
		return true;
	}
	
	private void addThisWeeksListToDB(List<Ibd50Ranking> webIbd50) {
		for(Ibd50Ranking rankingRow : webIbd50) {
			
			TickerSymbol rowTicker = rankingRow.getTicker();
			TickerSymbol foundTicker;
			
			try {
				foundTicker = tsService.findBySymbol(rowTicker.getSymbol());
				
					//No Exception is thrown, then the ticker must be in the DB
				rowTicker = foundTicker;											//set the ticker to be the found ticker (this sets the ID of the ticker) 
			} catch (GenericIbd50NotFound e) {
					//Exception thrown, wasn't in the db
				foundTicker = tsService.create(rowTicker);							//add it, return the added ticker (with the set id)
			}
			
			rankingRow.setTicker(foundTicker);										//Set the found ticker to be the ticker for the row
			final boolean isActive = true;
	
			Ibd50Tracking foundTracker;
			try {
				foundTracker = trackingService.findByActiveAndTicker(isActive, foundTicker);
			} catch (GenericIbd50NotFound e) {
				log.info(e.getMessage());
				
					//Tracker not found
				Ibd50Tracking newTracker = new Ibd50Tracking();						//create a new tracker
				newTracker.setTicker(foundTicker);									//set the Ticker for the new tracker
				newTracker.setActive(Boolean.TRUE);
				newTracker.setJoinDate(new LocalDate());
				//Need to set the join price
				
				foundTracker = trackingService.create(newTracker);					//add the new tracker to the db
			} catch (Ibd50TooManyFound e) {
				log.info(e.getMessage());
				e.printStackTrace();
				break;
			}
			
			rankingRow.setTracker(foundTracker);									//Set the found tracker to be the tracker for the row
	
			rankingService.create(rankingRow);										//add the row to the db
			
				//Check to see if the OHLCV data is up to date for this stock symbol
			runOhlcvUpdate(rankingRow);												//add the last 6 months of OHLCV data to the db
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
	private void runOhlcvUpdate(Ibd50Ranking row) {
		final int monthsOfData = 6; 

		List<StockOhlcv> currentOhlcvList;
		LocalDate oldestDate = new LocalDate();									//set the oldestDate as today
		try {
			currentOhlcvList = ohlcvService.findByTicker(row.getTicker());
																			//Get the oldest date in the db for the given symbol	
			for(StockOhlcv ohlcv: currentOhlcvList) {							//cycle through each ohlcv date and see if they are older than today and then, as it cycles, each other
				if(ohlcv.getLocalDate().isBefore(oldestDate)) {					//if the date is older than the current oldest date
					oldestDate=ohlcv.getLocalDate();							//set the new oldest date as the oldestDate
				}
			}
			
		} catch (GenericIbd50NotFound e) {
			// No ohlcv data exists in the db yet for this stock. Proceed and add monthsOfData (6) of it
		}
		
		LocalDate today = new LocalDate();										//get todays date
		LocalDate startDate = today.minusMonths(monthsOfData);					//get monthsOfData (6) months agos date

		if( oldestDate.isBefore(startDate) ) { 								//if the date in the db is after the proposed start date
			startDate = oldestDate;											//then use the date from the db
		}																	//else keep original start date
		
		List<StockOhlcv> ohlcvData = YahooDataRetriever.getStockFromYahoo(row.getTicker(), startDate, today);
		
		ohlcvService.batchInsert(ohlcvData);
	}
}

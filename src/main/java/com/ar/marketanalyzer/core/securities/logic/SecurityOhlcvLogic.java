package com.ar.marketanalyzer.core.securities.logic;

import com.ar.marketanalyzer.backtest.logic.BacktestLogic;
import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.models.YahooOHLCV;
import com.ar.marketanalyzer.core.securities.services.YahooOhlcvService;
import com.ar.marketanalyzer.core.securities.services.interfaces.SecurityOhlcvServiceInterface;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource(value="classpath:common.properties")
public class SecurityOhlcvLogic {
	
	final Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Resource
	private Environment env;
	@Autowired
	private SymbolServiceInterface symbolService;
	@Autowired
	private SecurityOhlcvServiceInterface secOhlcvService;
	@Autowired
	private YahooOhlcvService yahooService;
	@Autowired
	private BacktestLogic backtestLogic;

	public void updateOhlcv(List<Symbol> symbolList) {
		log.trace("start updateOhlcv on list of symbols");
		for(Symbol symbol: symbolList) {
			updateOhlcv(symbol);
		}
		log.trace("end updateOhlcv on list of symbols");
	}
	
	public void updateOhlcv(Symbol symbol) {
		log.trace("start updateOhlcv on symbol " + symbol.getName());
		//Setting up initial variables
		final int DESIRED_MONTHS_OF_DATA = Integer.parseInt(env.getProperty("default.ModelMonths"));
		LocalDateTime today = LocalDateTime.now();
		final long FRIDAY = 5;

		if( isWeekend(today) ) { 	// if today is a weekend
			today = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));						// shift today back to Friday
		}

		LocalDateTime desiredStartDate = LocalDateTime.now().minusMonths(DESIRED_MONTHS_OF_DATA);	//The minimum oldest date that should be in the db
		List<YahooOHLCV> yahooOhlcv = new ArrayList<>(); 							//empty list to fill from yahoo
		
		try {
			try {
				/*
				 * Need to find the first and last date in the DB, then fill in the gaps from Yahoo
				 */
				LocalDateTime oldestDate = secOhlcvService.findSymbolsLastDate(symbol);		// Try to find the last date in the DB
				LocalDateTime mostCurrentDate = secOhlcvService.findSymbolsFirstDate(symbol);	// Try to find the last date in the DB
				log.trace("Min Oldest Date: " + desiredStartDate + " Database Oldest Date:" + oldestDate);
				log.trace("Min Newest Date: " + today + " Database Newest Date:" + mostCurrentDate);
				
				List<YahooOHLCV> yahooList = null;
				if( desiredStartDate.isBefore(oldestDate) && !desiredStartDate.equals(oldestDate) ) {	// If the last date is not before desired months ago
					yahooList = yahooService.getYahooOhlcvData(symbol.getSymbol(), desiredStartDate, oldestDate.minusDays(1)); //Get from yahoo the gap
				} else if( today.isAfter(mostCurrentDate) && !today.equals(mostCurrentDate)) {
					yahooList = yahooService.getYahooOhlcvData(symbol.getSymbol(), mostCurrentDate.plusDays(1), today);	//Get from yahoo the gap
				}
				if( yahooList != null) {
					yahooOhlcv.addAll( yahooList );	
				}
				// Need a check to see if it up to date as well
			} catch(FileNotFoundException e) {
				//FileNotFoundException is thrown when I'm requesting an older date from yahoo than exists for a particular stock.
				symbol.setOldestDateInDb(Boolean.TRUE);
				symbolService.update(symbol);
			} catch (IllegalArgumentException|SecuritiesNotFound e) {
			
				// Catch block if there is no Ohlcv data for the symbol in the DB
				yahooOhlcv = yahooService.getYahooOhlcvData(symbol.getSymbol(), desiredStartDate);
			} 
		}
		catch (IOException e) {
			// Catch block if fetching from Yahoo screws up
			e.printStackTrace();
		}
		
		if( yahooOhlcv != null && yahooOhlcv.size() > 0 ) {
			secOhlcvService.batchInsertYahoo(yahooOhlcv, symbol);
		}
		log.trace("end updateOhlcv on symbol " + symbol.getName());
	}

	//Scheduled to run every weekday at 5 pm
	@Scheduled(cron="0 0 17 * * MON-FRI")
	public void ohlcScheduledUpdate() {
		List<Symbol> symbolList = symbolService.findAll();	// Find all symbols in the DB 
		
		updateOhlcv(symbolList);							// and then update their ohlcv
		
		//Then update their models
		backtestLogic.runCurrentModels(symbolList);
		
	}

	private boolean isWeekend(final LocalDateTime ld)
	{
		DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
		return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
	}
}

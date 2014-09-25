package com.ar.marketanalyzer.backtest.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.models.YahooOHLCV;
import com.ar.marketanalyzer.core.securities.services.YahooOhlcvService;
import com.ar.marketanalyzer.core.securities.services.interfaces.SecurityOhlcvServiceInterface;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@Component
@PropertySource(value="classpath:common.properties")
public class BacktestLogic {
	/* Get actual class name to be printed on */
	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Resource
	private Environment env;
	@Autowired
	private SymbolServiceInterface symbolService;
	@Autowired
	private SecurityOhlcvServiceInterface secOhlcvService;
	@Autowired
	private YahooOhlcvService yahooService;

	@Autowired
	private BacktestModelLogic modelLogic;
	
	public void init() {
		List<Symbol> defaultSymbols = getDefaultSymbols();
		
		populateDefaultOhlcv(defaultSymbols);
		
		runCurrentModel(defaultSymbols);
	}

	private List<Symbol> getDefaultSymbols() {
		String[] indexSymbolList = env.getRequiredProperty("index.symbols").split(",");
		String[] indexNamesList = env.getRequiredProperty("index.names").split(",");	
		String indexType = env.getRequiredProperty("index.type");
		
		List<Symbol> defaultSymbols = new ArrayList<Symbol>();
		
		for(int i = 0; i < indexSymbolList.length; i++) {
			Symbol symbol = new Symbol(indexSymbolList[i], indexNamesList[i], indexType); // create a new symbol
			symbol = symbolService.createOrFindDuplicate(symbol);				// add it to the db and return the symbol including the ID
			defaultSymbols.add(symbol);
		}
		
		return defaultSymbols;
	}
	
	private void populateDefaultOhlcv(List<Symbol> defaultSymbols) {
		final int DESIRED_MONTHS_OF_DATA = Integer.parseInt(env.getProperty("default.ModelMonths"));
		final LocalDate today = new LocalDate();
		
		LocalDate desiredStartDate = new LocalDate().minusMonths(DESIRED_MONTHS_OF_DATA);
		List<YahooOHLCV> yahooOhlcv = new ArrayList<YahooOHLCV>();
		
		for(Symbol symbol: defaultSymbols) {
			try {
				try {
					/*
					 * Need to find the first and last date in the DB, then fill in the gaps from Yahoo
					 */
					LocalDate earliestDate = secOhlcvService.findSymbolsLastDate(symbol);		// Try to find the last date in the DB
					LocalDate mostCurrentDate = secOhlcvService.findSymbolsFirstDate(symbol);		// Try to find the last date in the DB
					
					if( desiredStartDate.isAfter(earliestDate) ) {								// If the last date is not before desired months ago
						yahooOhlcv.addAll( yahooService.getYahooOhlcvData(symbol.getSymbol(), desiredStartDate, earliestDate) );	//Get from yahoo the gap
					}
					if( mostCurrentDate.isBefore( today )) {
						yahooOhlcv.addAll( yahooService.getYahooOhlcvData(symbol.getSymbol(), mostCurrentDate, today) );	//Get from yahoo the gap
					}
					
					// Need a check to see if it up to date as well
				} catch (IllegalArgumentException|SecuritiesNotFound e) {
					// Catch block if there is no Ohlcv data for the symbol in the DB
					yahooOhlcv = yahooService.getYahooOhlcvData(symbol.getSymbol(), desiredStartDate);
				}
			}
			catch (IOException e) {
				// Catch block if fetching from Yahoo screws up
				e.printStackTrace();
			}
			
			if( yahooOhlcv != null ) {
				secOhlcvService.batchInsertYahoo(yahooOhlcv, symbol);
			}
		}
	}
	
	private void runCurrentModel(List<Symbol> defaultSymbols) {
		for( Symbol symbol : defaultSymbols ) {
			modelLogic.runCurrentModel(symbol);
		}
	}
}

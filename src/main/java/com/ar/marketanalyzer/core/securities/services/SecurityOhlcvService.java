package com.ar.marketanalyzer.core.securities.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.core.securities.exceptions.OhlcvNotFound;
import com.ar.marketanalyzer.core.securities.exceptions.SymbolNotFound;
import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.models.YahooOHLCV;
import com.ar.marketanalyzer.core.securities.repo.SecurityOhlcvRepo;
import com.ar.marketanalyzer.core.securities.services.interfaces.SecurityOhlcvServiceInterface;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@Service
public class SecurityOhlcvService implements SecurityOhlcvServiceInterface {
	
	final Logger log = LogManager.getLogger(this.getClass().getName());

	@Resource
	private Environment env;
	@Resource
	private SecurityOhlcvRepo secohlcvRepo;
	@Autowired
	private SymbolServiceInterface symbolService;
	@Autowired
	private YahooOhlcvService yahooService;
	
	@Override
	@Transactional
	public SecuritiesOhlcv create(SecuritiesOhlcv secOhlcv) {

		if( secOhlcv.getSymbol().getId() == null ) {							// if the id of the symbol is not set
			Symbol foundSymbol = symbolService.createOrFindDuplicate(secOhlcv.getSymbol());		// get or create the id of the symbol
		
			secOhlcv.setSymbol(foundSymbol);									// set the foundSymbol to be the symbol of the security OHLCV to be created
		}
		
		return secohlcvRepo.save(secOhlcv);											// add the security ohlcv to the database
	}
	
	@Override
	@Transactional(rollbackFor=SymbolNotFound.class)
	public void batchInsert(List<SecuritiesOhlcv> ohlcvList) {
		if(ohlcvList.size()>0) {
			Symbol symbol = ohlcvList.get(0).getSymbol();							//Get the ticker from the ohlcvList
			
			if( symbol.getId() == null ) {											// If the symbol ID is null, create one in the DB
				Symbol foundSymbol = symbolService.createOrFindDuplicate(symbol);						//create a variable to hold the ticker from the db
				
				for(SecuritiesOhlcv ohlcv: ohlcvList) {
					ohlcv.setSymbol(foundSymbol);									//cycle through the list of ohlcv and add the ticker from the db
				}
			}
			
			secohlcvRepo.save(ohlcvList);												//batch add
		} 
	}
	@Override
	public void batchInsertYahoo(List<YahooOHLCV> ohlcvList, Symbol symbol) {
		List<SecuritiesOhlcv> secOhlcv = new ArrayList<SecuritiesOhlcv>();
		
		for(YahooOHLCV y: ohlcvList) {
			secOhlcv.add(new SecuritiesOhlcv(y, symbol));
		}
		
		batchInsert(secOhlcv);
	}
	@Override
	@Transactional(rollbackFor=SymbolNotFound.class)
	public SecuritiesOhlcv update(SecuritiesOhlcv secOhlcv)
			throws SymbolNotFound {
		SecuritiesOhlcv updatedSecOhlcv = secohlcvRepo.findOne(secOhlcv.getId());
		
		if( updatedSecOhlcv == null) {
			throw new SymbolNotFound();
		}
		
		//TODO Filler until I really write this code
		//updatedStockOhlcv.setRank(StockOhlcv.getRank());
		
		return updatedSecOhlcv;
	}
	
	@Override
	@Transactional(rollbackFor=SymbolNotFound.class)
	public SecuritiesOhlcv delete(long id) throws SymbolNotFound {
		SecuritiesOhlcv deletedSecOhlcv = findById(id);
		
		// if the findById method fails, then exception thrown and this code not run
		secohlcvRepo.delete(id);
		
		return deletedSecOhlcv;
	}
	
	@Override
	@Transactional
	public List<SecuritiesOhlcv> findAll() {
		return secohlcvRepo.findAll();
	}

	@Override
	@Transactional
	public SecuritiesOhlcv findById(long id) throws SymbolNotFound  {
		SecuritiesOhlcv ohlcv = secohlcvRepo.findOne(id);
		
		if( ohlcv == null ) {
			throw new SymbolNotFound();
		}
		
		return ohlcv; 
	}

	@Override
	@Transactional
	public List<SecuritiesOhlcv> findBySymbol(Symbol symbol) throws SymbolNotFound {
		Symbol foundTickerSymbol = null;
		if(symbol.getId() == null && symbol.getId() < 0) {
			foundTickerSymbol = symbolService.findBySymbol(symbol.getSymbol());
		} else {
			foundTickerSymbol = symbol;
		}
			
		if( foundTickerSymbol == null ) {
			throw new SymbolNotFound("By Ticker search for " + symbol.getSymbol() + " failed because the Ticker was not found in the Ticker DB.");
		}
		
		List<SecuritiesOhlcv> ohlcvList = secohlcvRepo.findBySymbol(symbol);
		
		if(ohlcvList.isEmpty()) {
			throw new SymbolNotFound("The Ticker '" + symbol.getSymbol() + "' was not found in the stock ohlcv db.");
		}
		
		return ohlcvList;
	}
	
	@Transactional
	public List<SecuritiesOhlcv> findBySymbolAsc(Symbol symbol) throws SymbolNotFound {
		Symbol foundTickerSymbol = null;
		if(symbol.getId() == null && symbol.getId() < 0) {
			foundTickerSymbol = symbolService.findBySymbol(symbol.getSymbol());
		} else {
			foundTickerSymbol = symbol;
		}
			
		if( foundTickerSymbol == null ) {
			throw new SymbolNotFound("By Ticker search for " + symbol.getSymbol() + " failed because the Ticker was not found in the Ticker DB.");
		}
		
		List<SecuritiesOhlcv> ohlcvList = secohlcvRepo.findBySymbolOrderByDateAsc(symbol);
		
		if(ohlcvList.isEmpty()) {
			throw new SymbolNotFound("The Ticker '" + symbol.getSymbol() + "' was not found in the stock ohlcv db.");
		}
		
		return ohlcvList;
	}
	
	@Override
	@Transactional
	public SecuritiesOhlcv findBySymbolAndDate(Symbol symbol, Date date)
			throws SymbolNotFound {
		SecuritiesOhlcv ohlcv = secohlcvRepo.findBySymbolAndDate(symbol, date);
		
		if( ohlcv == null ) {
			throw new SymbolNotFound("The Ticker '" + symbol.getSymbol() + "' was not found with date " + date.toString() + " in the stock ohlcv db.");
		}
		
		return ohlcv;
	}

	@Override
	@Transactional
	public List<SecuritiesOhlcv> findBySymbolAndDateAfterDesc(Symbol symbol,
			Date date) throws SymbolNotFound {
		List<SecuritiesOhlcv> ohlcvList = secohlcvRepo.findBySymbolAndDateAfterOrderByDateDesc(symbol, date);
		
		if(ohlcvList.isEmpty()) {
			throw new SymbolNotFound("The Ticker '" + symbol.getSymbol() + "' was not found after date " + date.toString() + " in the stock ohlcv db.");
		}
		
		return ohlcvList;
	}
	
	@Override
	@Transactional
	public List<SecuritiesOhlcv> findBySymbolAndDateAfterAsc(Symbol symbol,
			Date date) throws SymbolNotFound {
		List<SecuritiesOhlcv> ohlcvList = secohlcvRepo.findBySymbolAndDateAfterOrderByDateAsc(symbol, date);
		
		if(ohlcvList.isEmpty()) {
			throw new SymbolNotFound("The Ticker '" + symbol.getSymbol() + "' was not found after date " + date.toString() + " in the stock ohlcv db.");
		}
		
		return ohlcvList;
	}

	@Override
	@Transactional
	public LocalDate findSymbolsLastDate(Symbol symbol) throws OhlcvNotFound {
		Date date = secohlcvRepo.findBySymbolsLastDate(symbol);

		if( date == null ) {														// if the list is empty 
			throw new OhlcvNotFound( "No Ohlcv data found for the symbol: " + symbol.getName() );	//Throw and exception
		}
		
		return new LocalDate(date);
	}
	
	@Override
	@Transactional
	public LocalDate findSymbolsFirstDate(Symbol symbol) throws OhlcvNotFound {
		Date date = secohlcvRepo.findBySymbolsFirstDate(symbol);

		if( date == null ) {														// if the list is empty 
			throw new OhlcvNotFound( "No Ohlcv data found for the symbol: " + symbol.getName() );	//Throw and exception
		}
		
		return new LocalDate(date);
	}
	/*
	 * Helper Methods
	 */

	@Override
	@Transactional
	public List<SecuritiesOhlcv> findBySymbolAndDateBetween(Symbol symbol, Date startDate, Date endDate) throws SymbolNotFound {
		List <SecuritiesOhlcv> ohlcvList = secohlcvRepo.findBySymbolAndDateBetweenOrderByDateAsc(symbol, startDate, endDate);		// Get a list of ohlcv data from the query
		
		if( ohlcvList.isEmpty() ) {														// if the list is empty 
			throw new SymbolNotFound( "No OHLCV data found for " + symbol.getName() + " between " + startDate.toString() + " and " + endDate.toString() + ".");	//Throw and exception
		}
		
		return ohlcvList;
	}

	/*
	 * Non Transactional Methods
	 */
	/**
	 * This is the public method to initiate an update of the ohlcv database.
	 *  
	 * @param symbol - Symbol Object
	 * @return true if the method was successful
	 * @throws SymbolNotFound
	 * @throws IOException
	 */
	public int updateOhlcvFromYahoo(Symbol symbol) throws SymbolNotFound, IOException {
		log.trace("start updateOhlcv on symbol " + symbol.getName());
		
		final int DESIRED_MONTHS_OF_DATA = Integer.parseInt(env.getProperty("default.ModelMonths"));
		
		LocalDate desiredStartDate = adjustDateToFridayIfNeeded(new LocalDate().minusMonths(DESIRED_MONTHS_OF_DATA));	//The minimum oldest date that should be in the db		
		LocalDate today = adjustDateToFridayIfNeeded(new LocalDate());	//Get today's date and move to friday if it is a weekend

		List<YahooOHLCV> yahooList = new ArrayList<YahooOHLCV>();
		
		//Get first and last ohlcv dates from the db
		LocalDate oldestDate=null;
		LocalDate mostCurrentDate;
		try {
			if(symbol.getOldestDateInDb() == null || !symbol.getOldestDateInDb()) {
				oldestDate = findSymbolsLastDate(symbol);
				log.trace("Min Oldest Date: " + desiredStartDate + " Database Oldest Date:" + oldestDate);
			}
			mostCurrentDate = findSymbolsFirstDate(symbol);	// Try to find the last date in the DB
		
			
			log.trace("Min Newest Date: " + today + " Database Newest Date:" + mostCurrentDate);

			
			//See if the db needs to be updated and then get the data from Yahoo
			if( symbol.getOldestDateInDb() != null && symbol.getOldestDateInDb() && today.isAfter(mostCurrentDate) && !today.equals(mostCurrentDate)) {
				yahooList = yahooService.getYahooOhlcvData(symbol.getSymbol(), mostCurrentDate.plusDays(1), today);	//Get from yahoo the gap
			} else if( desiredStartDate.isBefore(oldestDate) && !desiredStartDate.equals(oldestDate) ) {	// If the last date is not before desired months ago
				yahooList = yahooService.getYahooOhlcvData(symbol.getSymbol(), desiredStartDate, oldestDate.minusDays(1)); //Get from yahoo the gap
			}
			/*
			 * IOException could be thrown by the two requests above. Will be handled at next level up
			 */
			
		} catch (OhlcvNotFound e) {		//No OHLCV data in the DB for this symbol, so go get it all
			yahooList = yahooService.getYahooOhlcvData(symbol.getSymbol(), desiredStartDate, today); //Get from yahoo the gap
		}		
		

		//If there is stuff to be added to the DB, add it
		if( yahooList != null && yahooList.size() > 0 ) {
			batchInsertYahoo(yahooList, symbol);
		
			//Did the query cover the oldest data from yahoo, if so set oldest date found field
			if( new LocalDate(yahooList.get(0).getDate()).isAfter(desiredStartDate) ) {
				symbol.setOldestDateInDb(Boolean.TRUE);
				symbolService.update(symbol);
			}
		}
		
		log.trace("Successfuly updated Ohlcv for symbol " + symbol.getSymbol());
		
		return yahooList.size();
	}
	private LocalDate adjustDateToFridayIfNeeded(LocalDate date) {
		final int FRIDAY = 5;
		int offset;
		
		if( (offset = date.dayOfWeek().get() - FRIDAY ) > 0 ) { 	// if today is a weekend
			date = date.minusDays(offset);						// shift today back to Friday
		}
		
		return date;
	}
}

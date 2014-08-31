package com.ar.marketanalyzer.securities.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.securities.models.Symbol;
import com.ar.marketanalyzer.securities.models.YahooOHLCV;
import com.ar.marketanalyzer.securities.repo.SecuritiesRepo;
import com.ar.marketanalyzer.securities.services.interfaces.SecuritiesOhlcvServiceInterface;
import com.ar.marketanalyzer.securities.services.interfaces.SymbolServiceInterface;

@Service
public class SecuritiesOhlcvService implements SecuritiesOhlcvServiceInterface {

	@Resource
	private SecuritiesRepo secRepo;
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
		
		return secRepo.save(secOhlcv);											// add the security ohlcv to the database
	}
	
	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public void batchInsert(List<SecuritiesOhlcv> ohlcvList) {
		Symbol symbol = ohlcvList.get(0).getSymbol();								//Get the ticker from the ohlcvList
		
		Symbol foundSymbol = symbolService.createOrFindDuplicate(symbol);						//create a variable to hold the ticker from the db
		
		for(SecuritiesOhlcv ohlcv: ohlcvList) {
			ohlcv.setSymbol(foundSymbol);											//cycle through the list of ohlcv and add the ticker from the db
		}

		secRepo.save(ohlcvList);													//batch add 
	}
	
	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public SecuritiesOhlcv update(SecuritiesOhlcv secOhlcv)
			throws SecuritiesNotFound {
		SecuritiesOhlcv updatedSecOhlcv = secRepo.findOne(secOhlcv.getId());
		
		if( updatedSecOhlcv == null) {
			throw new SecuritiesNotFound();
		}
		
		//TODO Filler until I really write this code
		//updatedStockOhlcv.setRank(StockOhlcv.getRank());
		
		return updatedSecOhlcv;
	}
	
	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public SecuritiesOhlcv delete(long id) throws SecuritiesNotFound {
		SecuritiesOhlcv deletedSecOhlcv = secRepo.findOne(id);
		
		if(deletedSecOhlcv == null) {
			throw new SecuritiesNotFound();
		} 

		secRepo.delete(id);
		
		return deletedSecOhlcv;
	}
	
	@Override
	@Transactional
	public List<SecuritiesOhlcv> findAll() {
		return secRepo.findAll();
	}

	@Override
	@Transactional
	public SecuritiesOhlcv findById(long id) {
		return secRepo.findOne(id);
	}

	@Override
	@Transactional
	public List<SecuritiesOhlcv> findBySymbol(Symbol symbol)
			throws SecuritiesNotFound {
		Symbol foundTickerSymbol = symbolService.findBySymbol(symbol.getSymbol());
		
		if( foundTickerSymbol == null ) {
			throw new SecuritiesNotFound("By Ticker search for " + symbol.getSymbol() + " failed because the Ticker was not found in the Ticker DB.");
		}
		
		List<SecuritiesOhlcv> ohlcvList = secRepo.findBySymbol(symbol);
		
		if(ohlcvList.isEmpty()) {
			throw new SecuritiesNotFound("The Ticker '" + symbol.getSymbol() + "' was not found in the stock ohlcv db.");
		}
		
		return ohlcvList;
	}

	@Override
	@Transactional
	public SecuritiesOhlcv findBySymbolAndDate(Symbol symbol, Date date)
			throws SecuritiesNotFound {
		SecuritiesOhlcv ohlcv = secRepo.findBySymbolAndDate(symbol, date);
		
		if( ohlcv == null ) {
			throw new SecuritiesNotFound("The Ticker '" + symbol.getSymbol() + "' was not found with date " + date.toString() + " in the stock ohlcv db.");
		}
		
		return ohlcv;
	}

	@Override
	@Transactional
	public List<SecuritiesOhlcv> findBySymbolAndDateAfter(Symbol symbol, Date date) throws SecuritiesNotFound {
		List<SecuritiesOhlcv> ohlcvList = secRepo.findBySymbolAndDateAfterOrderByDateDesc(symbol, date);
		
		if(ohlcvList.isEmpty()) {
			throw new SecuritiesNotFound("The Ticker '" + symbol.getSymbol() + "' was not found after date " + date.toString() + " in the stock ohlcv db.");
		}
		
		return ohlcvList;
	}

	@Override
	public boolean updateOhlcv(Symbol symbol) {
		
		LocalDate updateStartDate = new LocalDate();
		List<YahooOHLCV> yahooDataList = null;
		List<SecuritiesOhlcv> secOhlcvList = null;
		
		try {
			SecuritiesOhlcv lastDate = findSymbolsLastDate(symbol);			// Looking up the last ohlcv date in the db for the symbol
			
			if( lastDate.getLocalDate().equals(new LocalDate()) ) {			// Checking if the last date in the db is today, then db is already up to date
				return false;												// Database not updated, no need
			} else {
				updateStartDate = lastDate.getLocalDate().plusDays(1);		// Set start date to be the last date in the db plus 1, so no duplicate date
			}
		} catch (SecuritiesNotFound e) {									// The symbol wasn't in the ohlcv db
			updateStartDate = updateStartDate.minusMonths(6);				// Default ohlcv period is 6 months
		}
		
		try {
			yahooDataList = yahooService.getYahooOhlcvData(symbol.getName(), updateStartDate);	// Get data from Yahoo
			secOhlcvList = convertYahooOhlcvDataToSecurityOhlcvData(yahooDataList, symbol);		// Convert the data, in prep to be stored in the db
			batchInsert(secOhlcvList);															// batch insert the data
			return true;																		// database updated, return true
		} catch (IOException e) {
			Log.debug("Yahoo OHLCV IOException Error!");
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	@Transactional
	public SecuritiesOhlcv findSymbolsLastDate(Symbol symbol) throws SecuritiesNotFound {
		
		List <SecuritiesOhlcv> ohlcvList = secRepo.findBySymbolsLastDate(symbol);		// Get a list of ohlcv data from the query
		
		if( ohlcvList.isEmpty() ) {														// if the list is empty 
			throw new SecuritiesNotFound( "No Ohlcv data found for the symbol: " + symbol.getName() );	//Throw and exception
		}
		
		return ohlcvList.get(0);														// Else return the first item in the list, aka the newest date
	}
	
	/*
	 * Helper Methods
	 */
	private List<SecuritiesOhlcv> convertYahooOhlcvDataToSecurityOhlcvData(	List<YahooOHLCV> yahooDataList, Symbol symbol ) {
		List<SecuritiesOhlcv> secOhlcvList = new ArrayList<SecuritiesOhlcv>();	// Create secOhlcv list variable
		
		for(YahooOHLCV y:yahooDataList) {										// Cycle though the Yahoo Data
			SecuritiesOhlcv s = new SecuritiesOhlcv(y, symbol);					// create new secOhlcv objects from the yahoo objects
			secOhlcvList.add(s);												// add the secOhlcv objects to the list
		}
		
		return secOhlcvList;													// return the list of secOhlcv objects
	}
}

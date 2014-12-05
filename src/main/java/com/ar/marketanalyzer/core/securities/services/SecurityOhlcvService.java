package com.ar.marketanalyzer.core.securities.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.models.YahooOHLCV;
import com.ar.marketanalyzer.core.securities.repo.SecurityOhlcvRepo;
import com.ar.marketanalyzer.core.securities.services.interfaces.SecurityOhlcvServiceInterface;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@Service
public class SecurityOhlcvService implements SecurityOhlcvServiceInterface {

	@Resource
	private SecurityOhlcvRepo secRepo;
	@Autowired
	private SymbolServiceInterface symbolService;
	
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
		if(ohlcvList.size()>0) {
			Symbol symbol = ohlcvList.get(0).getSymbol();							//Get the ticker from the ohlcvList
			
			if( symbol.getId() == null ) {											// If the symbol ID is null, create one in the DB
				Symbol foundSymbol = symbolService.createOrFindDuplicate(symbol);						//create a variable to hold the ticker from the db
				
				for(SecuritiesOhlcv ohlcv: ohlcvList) {
					ohlcv.setSymbol(foundSymbol);									//cycle through the list of ohlcv and add the ticker from the db
				}
			}
			
			secRepo.save(ohlcvList);												//batch add
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
		SecuritiesOhlcv deletedSecOhlcv = findById(id);
		
		// if the findById method fails, then exception thrown and this code not run
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
	public SecuritiesOhlcv findById(long id) throws SecuritiesNotFound  {
		SecuritiesOhlcv ohlcv = secRepo.findOne(id);
		
		if( ohlcv == null ) {
			throw new SecuritiesNotFound();
		}
		
		return ohlcv; 
	}

	@Override
	@Transactional
	public List<SecuritiesOhlcv> findBySymbol(Symbol symbol) throws SecuritiesNotFound {
		Symbol foundTickerSymbol = null;
		if(symbol.getId() == null && symbol.getId() < 0) {
			foundTickerSymbol = symbolService.findBySymbol(symbol.getSymbol());
		} else {
			foundTickerSymbol = symbol;
		}
			
		if( foundTickerSymbol == null ) {
			throw new SecuritiesNotFound("By Ticker search for " + symbol.getSymbol() + " failed because the Ticker was not found in the Ticker DB.");
		}
		
		List<SecuritiesOhlcv> ohlcvList = secRepo.findBySymbol(symbol);
		
		if(ohlcvList.isEmpty()) {
			throw new SecuritiesNotFound("The Ticker '" + symbol.getSymbol() + "' was not found in the stock ohlcv db.");
		}
		
		return ohlcvList;
	}

	public List<SecuritiesOhlcv> findBySymbolAsc(Symbol symbol) throws SecuritiesNotFound {
		Symbol foundTickerSymbol = null;
		if(symbol.getId() == null && symbol.getId() < 0) {
			foundTickerSymbol = symbolService.findBySymbol(symbol.getSymbol());
		} else {
			foundTickerSymbol = symbol;
		}
			
		if( foundTickerSymbol == null ) {
			throw new SecuritiesNotFound("By Ticker search for " + symbol.getSymbol() + " failed because the Ticker was not found in the Ticker DB.");
		}
		
		List<SecuritiesOhlcv> ohlcvList = secRepo.findBySymbolOrderByDateAsc(symbol);
		
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
	public List<SecuritiesOhlcv> findBySymbolAndDateAfterDesc(Symbol symbol,
			Date date) throws SecuritiesNotFound {
		List<SecuritiesOhlcv> ohlcvList = secRepo.findBySymbolAndDateAfterOrderByDateDesc(symbol, date);
		
		if(ohlcvList.isEmpty()) {
			throw new SecuritiesNotFound("The Ticker '" + symbol.getSymbol() + "' was not found after date " + date.toString() + " in the stock ohlcv db.");
		}
		
		return ohlcvList;
	}
	
	@Override
	@Transactional
	public List<SecuritiesOhlcv> findBySymbolAndDateAfterAsc(Symbol symbol,
			Date date) throws SecuritiesNotFound {
		List<SecuritiesOhlcv> ohlcvList = secRepo.findBySymbolAndDateAfterOrderByDateAsc(symbol, date);
		
		if(ohlcvList.isEmpty()) {
			throw new SecuritiesNotFound("The Ticker '" + symbol.getSymbol() + "' was not found after date " + date.toString() + " in the stock ohlcv db.");
		}
		
		return ohlcvList;
	}

	@Override
	@Transactional
	public LocalDate findSymbolsLastDate(Symbol symbol) throws SecuritiesNotFound {
		Date date = secRepo.findBySymbolsLastDate(symbol);

		if( date == null ) {														// if the list is empty 
			throw new SecuritiesNotFound( "No Ohlcv data found for the symbol: " + symbol.getName() );	//Throw and exception
		}
		
		return new LocalDate(date);
	}
	
	@Override
	@Transactional
	public LocalDate findSymbolsFirstDate(Symbol symbol) throws SecuritiesNotFound {
		Date date = secRepo.findBySymbolsFirstDate(symbol);

		if( date == null ) {														// if the list is empty 
			throw new SecuritiesNotFound( "No Ohlcv data found for the symbol: " + symbol.getName() );	//Throw and exception
		}
		
		return new LocalDate(date);
	}
	/*
	 * Helper Methods
	 */

	@Override
	@Transactional
	public List<SecuritiesOhlcv> findBySymbolAndDateBetween(Symbol symbol, Date startDate, Date endDate) throws SecuritiesNotFound {
		List <SecuritiesOhlcv> ohlcvList = secRepo.findBySymbolAndDateBetweenOrderByDateAsc(symbol, startDate, endDate);		// Get a list of ohlcv data from the query
		
		if( ohlcvList.isEmpty() ) {														// if the list is empty 
			throw new SecuritiesNotFound( "No OHLCV data found for " + symbol.getName() + " between " + startDate.toString() + " and " + endDate.toString() + ".");	//Throw and exception
		}
		
		return ohlcvList;
	}
}

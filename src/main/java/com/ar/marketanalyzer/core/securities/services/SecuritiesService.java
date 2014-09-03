package com.ar.marketanalyzer.core.securities.services;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.repo.SecuritiesRepo;
import com.ar.marketanalyzer.core.securities.services.interfaces.SecuritiesServiceInterface;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

public class SecuritiesService implements SecuritiesServiceInterface {

	@Resource
	private SecuritiesRepo secRepo;
	@Autowired
	private SymbolServiceInterface symbolService;
	
	@Override
	@Transactional
	public SecuritiesOhlcv create(SecuritiesOhlcv secOhlcv) {

		if( secOhlcv.getSymbol().getId() == null ) {							// if the id of the symbol is not set
			Symbol foundSymbol = getOrCreateSymbol(secOhlcv.getSymbol());		// get or create the id of the symbol
		
			secOhlcv.setSymbol(foundSymbol);									// set the foundSymbol to be the symbol of the security OHLCV to be created
		}
		
		return secRepo.save(secOhlcv);											// add the security ohlcv to the database
	}
	
	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public void batchInsert(List<SecuritiesOhlcv> ohlcvList) {
		Symbol symbol = ohlcvList.get(0).getSymbol();								//Get the ticker from the ohlcvList
		
		Symbol foundSymbol = getOrCreateSymbol(symbol);						//create a variable to hold the ticker from the db
		
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
	public List<SecuritiesOhlcv> findBySymbolAndDateAfter(Symbol symbol,
			Date date) throws SecuritiesNotFound {
		List<SecuritiesOhlcv> ohlcvList = secRepo.findBySymbolAndDateAfterOrderByDateDesc(symbol, date);
		
		if(ohlcvList.isEmpty()) {
			throw new SecuritiesNotFound("The Ticker '" + symbol.getSymbol() + "' was not found after date " + date.toString() + " in the stock ohlcv db.");
		}
		
		return ohlcvList;
	}

	/*
	 * Helper Methods
	 */
	private Symbol getOrCreateSymbol(Symbol symbol) {
		Symbol foundSymbol;														//create a variable to hold the ticker from the db
		try {
			foundSymbol = symbolService.findBySymbol(symbol.getSymbol());		//find the ticker in the db
		} catch (SecuritiesNotFound e) {
			foundSymbol = symbolService.createOrFindDuplicate(symbol);	
		}
		
		return foundSymbol;
	}
}

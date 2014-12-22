package com.ar.marketanalyzer.core.securities.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.repo.SecurityOhlcvRepo;
import com.ar.marketanalyzer.core.securities.repo.SymbolRepo;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@Service
public class SymbolService implements SymbolServiceInterface{

	@Resource
	private SymbolRepo symbolRepo;
	@Resource
	private SecurityOhlcvRepo secRepo;
	
	@Override
	@Transactional
	public Symbol createOrFindDuplicate(Symbol symbol) {
		Symbol foundSymbol;
		try {
			foundSymbol = findBySymbol(symbol.getSymbol());					// Looks to see if the symbol is already in the db
		} catch (SecuritiesNotFound e) {													// If there is not exception is caught
			return symbolRepo.save(symbol);								// No duplicate so add this ticker to the db
		}
		return foundSymbol;															// Ticker was already in the db, returning the found one.
	}

	@Override
	@Transactional
	public Symbol findById(int id) throws SecuritiesNotFound {
		Symbol foundSymbol = symbolRepo.findOne(id);
		
		if( foundSymbol == null) {
			throw new SecuritiesNotFound("Ticker not found with an id of " + id + ". Better luck next time.");
		}
		return foundSymbol;
	}

	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public Symbol delete(int id) throws SecuritiesNotFound {
		Symbol deletedSymbol;				// Creating variable for the deletedTicker
		
		try {
			deletedSymbol = findById(id);			// Looking up the ticker by the provided ID to make sure that ID exists
		} catch (SecuritiesNotFound e) {						// If ID is not found exception is caught here
			throw e;									// Throw the exception caught up to the next level to be dealt with
		}

		symbolRepo.delete(id);					// No exception thrown, so go ahead and delete the ticker with the provided ID
		
		return deletedSymbol;						// Return the full ticker that was just deleted...Maybe I'll need it for something.
	}

	@Override
	@Transactional
	public List<Symbol> findAll() {
		return symbolRepo.findAll();
	}
	
	@Override
	@Transactional
	public List<Symbol> findAllFetchOhlcv() {
		List<Symbol> symbols = symbolRepo.findAll();
		for(Symbol symbol: symbols) {
			symbol.setOhlcv(secRepo.findBySymbol(symbol));
		}
		return symbols;
	}

	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public Symbol update(Symbol symbol) {
		Symbol updatedSymbol=null;							// Creating variable for the ticker to be updated
		
		try {
			updatedSymbol = findById(symbol.getId());	// Looking up the ticker by the provided ID to make sure that ID exists
		} catch (SecuritiesNotFound e) {							// If ID is not found exception is caught here
			//throw e;												// Throw the exception caught up to the next level to be dealt with
		}
		
		updatedSymbol.setName(symbol.getName());		// Update all fields of the ticker
		updatedSymbol.setSymbol(symbol.getSymbol());
		updatedSymbol.setType(symbol.getType());
		updatedSymbol.setOldestDateInDb(symbol.getOldestDateInDb());
		
		return symbolRepo.save(updatedSymbol);										// Return the updated ticker
	}

	@Override
	@Transactional
	public Symbol findBySymbol(String symbol) throws SecuritiesNotFound {
		Symbol foundSymbolSymbol = symbolRepo.findBySymbol(symbol);
		
		if( foundSymbolSymbol == null ) {
			throw new SecuritiesNotFound("A Ticker with the Symbol '" + symbol + "' was not found in the Ticker DB");
		}
		
		return foundSymbolSymbol;
	}
}

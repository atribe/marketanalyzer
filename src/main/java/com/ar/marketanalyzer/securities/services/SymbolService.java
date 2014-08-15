package com.ar.marketanalyzer.securities.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.securities.models.Symbol;
import com.ar.marketanalyzer.securities.repo.SymbolRepo;
import com.ar.marketanalyzer.securities.services.interfaces.SymbolServiceInterface;

@Service
public class SymbolService implements SymbolServiceInterface{

	@Resource
	private SymbolRepo symbolRepo;
	
	@Override
	@Transactional
	public Symbol createOrFindDuplicate(Symbol tickerSymbol) {
		Symbol foundSymbol;
		try {
			foundSymbol = findBySymbol(tickerSymbol.getSymbol());					// Looks to see if the symbol is already in the db
		} catch (SecuritiesNotFound e) {													// If there is not exception is caught
			return symbolRepo.save(tickerSymbol);								// No duplicate so add this ticker to the db
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
		Symbol deletedTickerSymbol;				// Creating variable for the deletedTicker
		
		try {
			deletedTickerSymbol = findById(id);			// Looking up the ticker by the provided ID to make sure that ID exists
		} catch (SecuritiesNotFound e) {						// If ID is not found exception is caught here
			throw e;									// Throw the exception caught up to the next level to be dealt with
		}

		symbolRepo.delete(id);					// No exception thrown, so go ahead and delete the ticker with the provided ID
		
		return deletedTickerSymbol;						// Return the full ticker that was just deleted...Maybe I'll need it for something.
	}

	@Override
	@Transactional
	public List<Symbol> findAll() {
		return symbolRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public Symbol update(Symbol tickerSymbol) throws SecuritiesNotFound {
		Symbol updatedSymbol;							// Creating variable for the ticker to be updated
		
		try {
			updatedSymbol = findById(tickerSymbol.getId());	// Looking up the ticker by the provided ID to make sure that ID exists
		} catch (SecuritiesNotFound e) {							// If ID is not found exception is caught here
			throw e;												// Throw the exception caught up to the next level to be dealt with
		}
		
		updatedSymbol.setName(tickerSymbol.getName());		// Update all fields of the ticker
		updatedSymbol.setSymbol(tickerSymbol.getSymbol());
		updatedSymbol.setType(tickerSymbol.getType());
		
		return updatedSymbol;									// Return the updated ticker
	}

	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public Symbol findBySymbol(String symbol) throws SecuritiesNotFound {
		Symbol foundSymbolSymbol = symbolRepo.findBySymbol(symbol);
		
		if( foundSymbolSymbol == null ) {
			throw new SecuritiesNotFound("A Ticker with the Symbol '" + symbol + "' was not found in the Ticker DB");
		}
		
		return foundSymbolSymbol;
	}
}

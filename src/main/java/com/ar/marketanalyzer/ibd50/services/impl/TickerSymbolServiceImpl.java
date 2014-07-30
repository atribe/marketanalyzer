package com.ar.marketanalyzer.ibd50.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.ibd50.exceptions.Ibd50NotFound;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;
import com.ar.marketanalyzer.ibd50.repositories.TickerSymbolRepository;
import com.ar.marketanalyzer.ibd50.services.TickerSymbolService;

@Service
public class TickerSymbolServiceImpl implements TickerSymbolService{

	@Resource
	private TickerSymbolRepository tickerSymbolRepo;
	
	@Override
	@Transactional
	public TickerSymbol createOrFindDuplicate(TickerSymbol tickerSymbol) {
		TickerSymbol foundTicker;
		try {
			foundTicker = findBySymbol(tickerSymbol.getSymbol());					// Looks to see if the symbol is already in the db
		} catch (Ibd50NotFound e) {													// If there is not exception is caught
			return tickerSymbolRepo.save(tickerSymbol);								// No duplicate so add this ticker to the db
		}
		return foundTicker;															// Ticker was already in the db, returning the found one.
	}

	@Override
	@Transactional
	public TickerSymbol findById(int id) throws Ibd50NotFound {
		TickerSymbol foundTicker = tickerSymbolRepo.findOne(id);
		
		if( foundTicker == null) {
			throw new Ibd50NotFound("Ticker not found with an id of " + id + ". Better luck next time.");
		}
		return foundTicker;
	}

	@Override
	@Transactional(rollbackFor=Ibd50NotFound.class)
	public TickerSymbol delete(int id) throws Ibd50NotFound {
		TickerSymbol deletedTickerSymbol;				// Creating variable for the deletedTicker
		
		try {
			deletedTickerSymbol = findById(id);			// Looking up the ticker by the provided ID to make sure that ID exists
		} catch (Ibd50NotFound e) {						// If ID is not found exception is caught here
			throw e;									// Throw the exception caught up to the next level to be dealt with
		}

		tickerSymbolRepo.delete(id);					// No exception thrown, so go ahead and delete the ticker with the provided ID
		
		return deletedTickerSymbol;						// Return the full ticker that was just deleted...Maybe I'll need it for something.
	}

	@Override
	@Transactional
	public List<TickerSymbol> findAll() {
		return tickerSymbolRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor=Ibd50NotFound.class)
	public TickerSymbol update(TickerSymbol tickerSymbol) throws Ibd50NotFound {
		TickerSymbol updatedTickerSymbol;							// Creating variable for the ticker to be updated
		
		try {
			updatedTickerSymbol = findById(tickerSymbol.getId());	// Looking up the ticker by the provided ID to make sure that ID exists
		} catch (Ibd50NotFound e) {							// If ID is not found exception is caught here
			throw e;												// Throw the exception caught up to the next level to be dealt with
		}
		
		updatedTickerSymbol.setName(tickerSymbol.getName());		// Update all fields of the ticker
		updatedTickerSymbol.setSymbol(tickerSymbol.getSymbol());
		updatedTickerSymbol.setType(tickerSymbol.getType());
		
		return updatedTickerSymbol;									// Return the updated ticker
	}

	@Override
	@Transactional(rollbackFor=Ibd50NotFound.class)
	public TickerSymbol findBySymbol(String symbol) throws Ibd50NotFound {
		TickerSymbol foundTickerSymbol = tickerSymbolRepo.findBySymbol(symbol);
		
		if( foundTickerSymbol == null ) {
			throw new Ibd50NotFound("A Ticker with the Symbol '" + symbol + "' was not found in the Ticker DB");
		}
		
		return foundTickerSymbol;
	}
}

package com.ar.marketanalyzer.ibd50.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;
import com.ar.marketanalyzer.ibd50.repositories.TickerSymbolRepository;
import com.ar.marketanalyzer.ibd50.services.TickerSymbolService;

@Service
public class TickerSymbolServiceImpl implements TickerSymbolService{

	@Resource
	private TickerSymbolRepository tickerSymbolRepo;
	
	@Override
	@Transactional
	public TickerSymbol create(TickerSymbol tickerSymbol) {
		TickerSymbol createdTickerSymbol = tickerSymbol;
		return tickerSymbolRepo.save(createdTickerSymbol);
	}

	@Override
	@Transactional
	public TickerSymbol findById(int id) {
		return tickerSymbolRepo.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public TickerSymbol delete(int id) throws GenericIbd50NotFound {
		TickerSymbol deletedTickerSymbol = tickerSymbolRepo.findOne(id);
		
		if(deletedTickerSymbol == null) {
			throw new GenericIbd50NotFound();
		} 

		tickerSymbolRepo.delete(id);
		
		return deletedTickerSymbol;	
	}

	@Override
	@Transactional
	public List<TickerSymbol> findAll() {
		return tickerSymbolRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public TickerSymbol update(TickerSymbol tickerSymbol) throws GenericIbd50NotFound {
		TickerSymbol updatedTickerSymbol = tickerSymbolRepo.findOne(tickerSymbol.getId());
		
		if( updatedTickerSymbol == null) {
			throw new GenericIbd50NotFound();
		}
		
		updatedTickerSymbol.setName(tickerSymbol.getName());
		updatedTickerSymbol.setSymbol(tickerSymbol.getSymbol());
		updatedTickerSymbol.setType(tickerSymbol.getType());
		
		return updatedTickerSymbol;
	}

	
	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public TickerSymbol findBySymbol(String symbol) throws GenericIbd50NotFound {
		TickerSymbol foundTickerSymbol = tickerSymbolRepo.findBySymbol(symbol);
		
		if( foundTickerSymbol == null ) {
			throw new GenericIbd50NotFound("A Ticker with the Symbol '" + symbol + "' was not found in the Ticker DB");
		}
		
		return foundTickerSymbol;
	}
}

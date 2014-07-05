package com.ar.marketanalyzer.ibd50.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.ibd50.beans.TickerSymbol;
import com.ar.marketanalyzer.ibd50.exceptions.TickerSymbolNotFound;
import com.ar.marketanalyzer.ibd50.repositories.TickerSymbolRepository;
import com.ar.marketanalyzer.ibd50.services.TickerSymbolService;

@Service
public class TickerSymbolServiceImpl implements TickerSymbolService{

	@Resource
	private TickerSymbolRepository tickerSymbolRepository;
	
	@Override
	@Transactional
	public TickerSymbol create(TickerSymbol tickerSymbol) {
		TickerSymbol createdTickerSymbol = tickerSymbol;
		return tickerSymbolRepository.save(createdTickerSymbol);
	}

	@Override
	@Transactional
	public TickerSymbol findById(int id) {
		return tickerSymbolRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=TickerSymbolNotFound.class)
	public TickerSymbol delete(int id) throws TickerSymbolNotFound {
		TickerSymbol deletedTickerSymbol = tickerSymbolRepository.findOne(id);
		
		if(deletedTickerSymbol == null) {
			throw new TickerSymbolNotFound();
		} 

		tickerSymbolRepository.delete(id);
		
		return deletedTickerSymbol;	
	}

	@Override
	public List<TickerSymbol> findAll() {
		return tickerSymbolRepository.findAll();
	}

	@Override
	public TickerSymbol update(TickerSymbol tickerSymbol) throws TickerSymbolNotFound {
		TickerSymbol updatedTickerSymbol = tickerSymbolRepository.findOne(tickerSymbol.getId());
		
		if( updatedTickerSymbol == null) {
			throw new TickerSymbolNotFound();
		}
		
		updatedTickerSymbol.setName(tickerSymbol.getName());
		updatedTickerSymbol.setSymbol(tickerSymbol.getSymbol());
		updatedTickerSymbol.setType(tickerSymbol.getType());
		
		return updatedTickerSymbol;
	}
}

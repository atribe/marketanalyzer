package com.ar.marketanalyzer.ibd50.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;
import com.ar.marketanalyzer.ibd50.repositories.StockOhlcvRepository;
import com.ar.marketanalyzer.ibd50.services.StockOhlcvService;
import com.ar.marketanalyzer.ibd50.services.TickerSymbolService;

@Service
public class StockOhlcvServiceImpl implements StockOhlcvService {
	
	@Resource
	private StockOhlcvRepository stockOhlcvRepo;
	@Autowired
	private TickerSymbolService tickerSymbolService;
	
	@Override
	@Transactional
	public StockOhlcv create(StockOhlcv stockOhlcv) {
		StockOhlcv createdStockOhlcv = stockOhlcv;
		return stockOhlcvRepo.save(createdStockOhlcv);
	}

	@Override
	@Transactional
	public StockOhlcv findById(int id) {
		return stockOhlcvRepo.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public StockOhlcv delete(int id) throws GenericIbd50NotFound {
		StockOhlcv deletedStockOhlcv = stockOhlcvRepo.findOne(id);
		
		if(deletedStockOhlcv == null) {
			throw new GenericIbd50NotFound();
		} 

		stockOhlcvRepo.delete(id);
		
		return deletedStockOhlcv;	
	}

	@Override
	@Transactional
	public List<StockOhlcv> findAll() {
		return stockOhlcvRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public StockOhlcv update(StockOhlcv stockOhlcv) throws GenericIbd50NotFound {
		StockOhlcv updatedStockOhlcv = stockOhlcvRepo.findOne(stockOhlcv.getId());
		
		if( updatedStockOhlcv == null) {
			throw new GenericIbd50NotFound();
		}
		
		//TODO Filler until I really write this code
		//updatedStockOhlcv.setRank(StockOhlcv.getRank());
		
		return updatedStockOhlcv;
	}

	
	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public List<StockOhlcv> findByTicker(TickerSymbol ticker) throws GenericIbd50NotFound {
		TickerSymbol foundTickerSymbol = tickerSymbolService.findBySymbol(ticker.getSymbol());
		
		if( foundTickerSymbol == null ) {
			throw new GenericIbd50NotFound("By Ticker search for " + ticker.getSymbol() + " failed because the Ticker was not found in the Ticker DB.");
		}
		
		List<StockOhlcv> ohlcvList = stockOhlcvRepo.findByTicker(ticker);
		
		if(ohlcvList.isEmpty()) {
			throw new GenericIbd50NotFound("The Ticker '" + ticker.getSymbol() + "' was not found in the stock ohlcv db.");
		}
		
		return ohlcvList;
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public void batchInsert(List<StockOhlcv> ohlcvList) {
		
		TickerSymbol ticker = ohlcvList.get(0).getTicker();									//Get the ticker from the ohlcvList
		TickerSymbol foundTickerSymbol;														//create a variable to hold the ticker from the db
		try {
			foundTickerSymbol = tickerSymbolService.findBySymbol(ticker.getSymbol());		//find the ticker in the db
		} catch (GenericIbd50NotFound e) {
			foundTickerSymbol = tickerSymbolService.create(ticker);							//ticker wasn't in the db, so create it
			
			for(StockOhlcv ohlcv: ohlcvList) {
				ohlcv.setTicker(foundTickerSymbol);											//cycle through the list of ohlcv and add the ticker from the db
			}
		}
		
		//TODO This doesn't work
		/*
		for(StockOhlcv ohlcv: ohlcvList) {
		 	stockOhlcvRepo.save(ohlcv);
		}
		*/
		stockOhlcvRepo.save(ohlcvList);														//batch add
		stockOhlcvRepo.flush();																//save the batch
		
	}
}

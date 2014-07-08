package com.ar.marketanalyzer.ibd50.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;
import com.ar.marketanalyzer.ibd50.repositories.StockOhlcvRepository;
import com.ar.marketanalyzer.ibd50.services.StockOhlcvService;

@Service
public class StockOhlcvServiceImpl implements StockOhlcvService {
	
	@Resource
	private StockOhlcvRepository stockOhlcvRepository;
	
	@Override
	@Transactional
	public StockOhlcv create(StockOhlcv stockOhlcv) {
		StockOhlcv createdStockOhlcv = stockOhlcv;
		return stockOhlcvRepository.save(createdStockOhlcv);
	}

	@Override
	@Transactional
	public StockOhlcv findById(int id) {
		return stockOhlcvRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public StockOhlcv delete(int id) throws GenericIbd50NotFound {
		StockOhlcv deletedStockOhlcv = stockOhlcvRepository.findOne(id);
		
		if(deletedStockOhlcv == null) {
			throw new GenericIbd50NotFound();
		} 

		stockOhlcvRepository.delete(id);
		
		return deletedStockOhlcv;	
	}

	@Override
	public List<StockOhlcv> findAll() {
		return stockOhlcvRepository.findAll();
	}

	@Override
	public StockOhlcv update(StockOhlcv stockOhlcv) throws GenericIbd50NotFound {
		StockOhlcv updatedStockOhlcv = stockOhlcvRepository.findOne(stockOhlcv.getId());
		
		if( updatedStockOhlcv == null) {
			throw new GenericIbd50NotFound();
		}
		
		//TODO Filler until I really write this code
		//updatedStockOhlcv.setRank(StockOhlcv.getRank());
		
		return updatedStockOhlcv;
	}
}

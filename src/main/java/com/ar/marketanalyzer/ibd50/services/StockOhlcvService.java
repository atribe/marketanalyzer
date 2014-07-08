package com.ar.marketanalyzer.ibd50.services;

import java.util.List;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;

public interface StockOhlcvService {

	public StockOhlcv create(StockOhlcv stockOhlcv);
	public StockOhlcv delete(int id) throws GenericIbd50NotFound;
	public List<StockOhlcv> findAll();
	public StockOhlcv update(StockOhlcv stockOhlcv) throws GenericIbd50NotFound;
	public StockOhlcv findById(int id);
}

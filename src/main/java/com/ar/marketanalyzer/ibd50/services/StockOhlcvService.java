package com.ar.marketanalyzer.ibd50.services;

import java.util.Date;
import java.util.List;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;

public interface StockOhlcvService {

	public StockOhlcv create(StockOhlcv stockOhlcv);
	public StockOhlcv delete(int id) throws GenericIbd50NotFound;
	public List<StockOhlcv> findAll();
	public StockOhlcv update(StockOhlcv stockOhlcv) throws GenericIbd50NotFound;
	public StockOhlcv findById(int id);
	public List<StockOhlcv> findByTicker(TickerSymbol ticker) throws GenericIbd50NotFound;
	public void batchInsert(List<StockOhlcv> ohlcvList);
	public StockOhlcv findByTickerAndDate(TickerSymbol foundTicker,	Date date);
}

package com.ar.marketanalyzer.ibd50.services;

import java.util.Date;
import java.util.List;

import com.ar.marketanalyzer.core.securities.exceptions.SymbolNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;

public interface StockOhlcvService {

	public StockOhlcv create(StockOhlcv stockOhlcv);
	public StockOhlcv delete(int id) throws SymbolNotFound;
	public List<StockOhlcv> findAll();
	public StockOhlcv update(StockOhlcv stockOhlcv) throws SymbolNotFound;
	public StockOhlcv findById(int id);
	public List<StockOhlcv> findByTicker(Symbol ticker) throws SymbolNotFound;
	public void batchInsert(List<StockOhlcv> ohlcvList);
	public StockOhlcv findByTickerAndDate(Symbol foundTicker,	Date date) throws SymbolNotFound;
	public List<StockOhlcv> findByTickerAndDateAfter(Symbol ticker, Date date)throws SymbolNotFound;
}

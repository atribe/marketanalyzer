package com.ar.marketanalyzer.ibd50.services;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;

import java.util.Date;
import java.util.List;

public interface StockOhlcvService {

	public StockOhlcv create(StockOhlcv stockOhlcv);
	public StockOhlcv delete(int id) throws SecuritiesNotFound;
	public List<StockOhlcv> findAll();
	public StockOhlcv update(StockOhlcv stockOhlcv) throws SecuritiesNotFound;
	public StockOhlcv findById(int id);
	public List<StockOhlcv> findByTicker(Symbol ticker) throws SecuritiesNotFound;
	public void batchInsert(List<StockOhlcv> ohlcvList);
	public StockOhlcv findByTickerAndDate(Symbol foundTicker,	Date date) throws SecuritiesNotFound;
	public List<StockOhlcv> findByTickerAndDateAfter(Symbol ticker, Date date)throws SecuritiesNotFound;
}

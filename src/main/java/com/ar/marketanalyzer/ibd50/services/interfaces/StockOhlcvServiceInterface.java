package com.ar.marketanalyzer.ibd50.services.interfaces;

import java.util.Date;
import java.util.List;

import com.ar.marketanalyzer.ibd50.models.StockOhlcv;
import com.ar.marketanalyzer.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.securities.models.Symbol;

public interface StockOhlcvServiceInterface {

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

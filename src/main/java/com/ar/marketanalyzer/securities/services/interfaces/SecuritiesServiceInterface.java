package com.ar.marketanalyzer.securities.services.interfaces;

import java.util.Date;
import java.util.List;

import com.ar.marketanalyzer.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.securities.models.Symbol;

public interface SecuritiesServiceInterface {

	/**
	 * @param secOhlcv
	 * @return
	 */
	public SecuritiesOhlcv create(SecuritiesOhlcv secOhlcv);
	public void batchInsert(List<SecuritiesOhlcv> ohlcvList);
	public SecuritiesOhlcv update(SecuritiesOhlcv secOhlcv) throws SecuritiesNotFound;
	public SecuritiesOhlcv delete(long id) throws SecuritiesNotFound;
	public List<SecuritiesOhlcv> findAll();
	public SecuritiesOhlcv findById(long id);
	public List<SecuritiesOhlcv> findBySymbol(Symbol symbol) throws SecuritiesNotFound;
	public SecuritiesOhlcv findBySymbolAndDate(Symbol foundSymbol,	Date date) throws SecuritiesNotFound;
	public List<SecuritiesOhlcv> findBySymbolAndDateAfter(Symbol symbol, Date date)throws SecuritiesNotFound;
	public boolean updateOhlcv(Symbol symbol);
	public SecuritiesOhlcv findSymbolsLastDate(Symbol symbol) throws SecuritiesNotFound;
}

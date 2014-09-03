package com.ar.marketanalyzer.core.securities.services.interfaces;

import java.util.Date;
import java.util.List;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.models.YahooOHLCV;

public interface SecurityOhlcvServiceInterface {

	/**
	 * @param secOhlcv
	 * @return
	 */
	public SecuritiesOhlcv create(SecuritiesOhlcv secOhlcv);
	public void batchInsert(List<SecuritiesOhlcv> ohlcvList);
	public void batchInsertYahoo(List<YahooOHLCV> ohlcvList, Symbol symbol);
	public SecuritiesOhlcv update(SecuritiesOhlcv secOhlcv) throws SecuritiesNotFound;
	public SecuritiesOhlcv delete(long id) throws SecuritiesNotFound;
	public List<SecuritiesOhlcv> findAll();
	public SecuritiesOhlcv findById(long id);
	public List<SecuritiesOhlcv> findBySymbol(Symbol symbol) throws SecuritiesNotFound;
	public SecuritiesOhlcv findBySymbolAndDate(Symbol foundSymbol,	Date date) throws SecuritiesNotFound;
	public List<SecuritiesOhlcv> findBySymbolAndDateAfter(Symbol symbol, Date date)throws SecuritiesNotFound;
	SecuritiesOhlcv findSymbolsLastDate(Symbol symbol) throws SecuritiesNotFound;
}

package com.ar.marketanalyzer.core.securities.services.interfaces;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;

import com.ar.marketanalyzer.core.securities.exceptions.OhlcvNotFound;
import com.ar.marketanalyzer.core.securities.exceptions.SymbolNotFound;
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
	public SecuritiesOhlcv update(SecuritiesOhlcv secOhlcv) throws SymbolNotFound;
	public SecuritiesOhlcv delete(long id) throws SymbolNotFound;
	public List<SecuritiesOhlcv> findAll();
	public SecuritiesOhlcv findById(long id) throws SymbolNotFound;
	public List<SecuritiesOhlcv> findBySymbol(Symbol symbol) throws SymbolNotFound;
	public List<SecuritiesOhlcv> findBySymbolAsc(Symbol symbol) throws SymbolNotFound;
	public SecuritiesOhlcv findBySymbolAndDate(Symbol foundSymbol,	Date date) throws SymbolNotFound;
	public List<SecuritiesOhlcv> findBySymbolAndDateAfterDesc(Symbol symbol, Date date)throws SymbolNotFound;
	public List<SecuritiesOhlcv> findBySymbolAndDateAfterAsc(Symbol symbol, Date date)throws SymbolNotFound;
	public List<SecuritiesOhlcv> findBySymbolAndDateBetween(Symbol symbol, Date startDate, Date endDate)throws SymbolNotFound;
	public LocalDate findSymbolsLastDate(Symbol symbol)	throws OhlcvNotFound;
	public LocalDate findSymbolsFirstDate(Symbol symbol) throws OhlcvNotFound;
	public Long countBySymbol(Symbol symbol);
	
	/*
	 * Non transactional methods
	 */
	public int updateOhlcvFromYahoo(Symbol symbol) throws SymbolNotFound, IOException;
	
}

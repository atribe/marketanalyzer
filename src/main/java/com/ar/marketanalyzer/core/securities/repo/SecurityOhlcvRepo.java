package com.ar.marketanalyzer.core.securities.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.core.securities.models.Symbol;

public interface SecurityOhlcvRepo extends JpaRepository<SecuritiesOhlcv, Long> {

	public List<SecuritiesOhlcv> findBySymbol(Symbol symbol);
	public SecuritiesOhlcv findBySymbolAndDate(Symbol symbol, Date date);
	public List<SecuritiesOhlcv> findBySymbolAndDateAfterOrderByDateDesc(Symbol symbol, Date date);
	
	@Query(	  "SELECT date FROM SecuritiesOhlcv O "
			+ "WHERE O.symbol = :symbol "
			+ "ORDER BY date ASC ")
	public List<Date> findBySymbolsLastDate( @Param("symbol")Symbol symbol);
	public List<SecuritiesOhlcv> findBySymbolAndDateBetween(Symbol symbol,
			Date startDate, Date endDate);
}
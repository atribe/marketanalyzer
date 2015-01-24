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
	public List<SecuritiesOhlcv> findBySymbolOrderByDateAsc(Symbol symbol);
	public SecuritiesOhlcv findBySymbolAndDate(Symbol symbol, Date date);
	public List<SecuritiesOhlcv> findBySymbolAndDateAfterOrderByDateDesc(Symbol symbol, Date date);
	public List<SecuritiesOhlcv> findBySymbolAndDateAfterOrderByDateAsc(Symbol symbol, Date date);
	
	@Query(	"SELECT MIN(date) FROM SecuritiesOhlcv O WHERE O.symbol = :symbol" )
	public Date findBySymbolsLastDate( @Param("symbol")Symbol symbol);
	@Query(	"SELECT MAX(date) FROM SecuritiesOhlcv O WHERE O.symbol = :symbol" )
	public Date findBySymbolsFirstDate( @Param("symbol")Symbol symbol);
	
	public List<SecuritiesOhlcv> findBySymbolAndDateBetweenOrderByDateAsc(Symbol symbol,
			Date startDate, Date endDate);
	
	public Long countBySymbol(Symbol symbol);
}

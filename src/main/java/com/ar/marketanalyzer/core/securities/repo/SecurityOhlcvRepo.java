package com.ar.marketanalyzer.core.securities.repo;

import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface SecurityOhlcvRepo extends JpaRepository<SecuritiesOhlcv, Long> {

	List<SecuritiesOhlcv> findBySymbol(Symbol symbol);
	List<SecuritiesOhlcv> findBySymbolOrderByDateAsc(Symbol symbol);
	SecuritiesOhlcv findBySymbolAndDate(Symbol symbol, LocalDate date);
	List<SecuritiesOhlcv> findBySymbolAndDateAfterOrderByDateDesc(Symbol symbol, LocalDate date);
	List<SecuritiesOhlcv> findBySymbolAndDateAfterOrderByDateAsc(Symbol symbol, LocalDate date);
	
	@Query(	"SELECT MIN(date) FROM SecuritiesOhlcv O WHERE O.symbol = :symbol" )
	LocalDateTime findBySymbolsLastDate( @Param("symbol")Symbol symbol);
	@Query(	"SELECT MAX(date) FROM SecuritiesOhlcv O WHERE O.symbol = :symbol" )
	LocalDateTime findBySymbolsFirstDate(@Param("symbol")Symbol symbol);
	
	List<SecuritiesOhlcv> findBySymbolAndDateBetweenOrderByDateAsc(Symbol symbol, LocalDate startDate, LocalDate endDate);
}

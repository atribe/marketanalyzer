package com.ar.marketanalyzer.securities.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.securities.models.Symbol;

public interface SecuritiesRepo extends JpaRepository<SecuritiesOhlcv, Long> {

	public List<SecuritiesOhlcv> findBySymbol(Symbol symbol);
	public SecuritiesOhlcv findBySymbolAndDate(Symbol symbol, Date date);
	public List<SecuritiesOhlcv> findBySymbolAndDateAfterOrderByDateDesc(Symbol symbol, Date date);
}

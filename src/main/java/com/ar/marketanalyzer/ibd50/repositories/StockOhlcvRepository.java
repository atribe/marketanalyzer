package com.ar.marketanalyzer.ibd50.repositories;

import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StockOhlcvRepository extends JpaRepository<StockOhlcv, Integer>{

	public List<StockOhlcv> findByTicker(Symbol ticker);
	public StockOhlcv findByTickerAndDate(Symbol ticker, LocalDateTime date);

	public List<StockOhlcv> findByTickerAndDateAfterOrderByDateDesc(Symbol ticker, LocalDateTime date);
}

package com.ar.marketanalyzer.ibd50.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;

public interface StockOhlcvRepository extends JpaRepository<StockOhlcv, Integer>{

	public List<StockOhlcv> findByTicker(Symbol ticker);
	public StockOhlcv findByTickerAndDate(Symbol ticker, Date date);

	public List<StockOhlcv> findByTickerAndDateAfterOrderByDateDesc(Symbol ticker, Date date);
}

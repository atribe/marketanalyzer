package com.ar.marketanalyzer.ibd50.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.ibd50.models.StockOhlcv;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;

public interface StockOhlcvRepository extends JpaRepository<StockOhlcv, Integer>{

	public List<StockOhlcv> findByTicker(TickerSymbol ticker);

}
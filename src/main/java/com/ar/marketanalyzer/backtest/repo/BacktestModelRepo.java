package com.ar.marketanalyzer.backtest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.backtest.model.BacktestModel;
import com.ar.marketanalyzer.securities.models.Symbol;

public interface BacktestModelRepo extends JpaRepository<BacktestModel, Integer>{

	public BacktestModel findBySymbolAndCurrentTrue(Symbol symbol);
}

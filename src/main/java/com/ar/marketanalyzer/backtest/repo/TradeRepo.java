package com.ar.marketanalyzer.backtest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.backtest.models.Trade;

public interface TradeRepo extends JpaRepository<Trade, Integer>{

}

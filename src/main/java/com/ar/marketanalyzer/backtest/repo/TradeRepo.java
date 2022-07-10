package com.ar.marketanalyzer.backtest.repo;

import com.ar.marketanalyzer.backtest.models.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepo extends JpaRepository<Trade, Integer>{

}

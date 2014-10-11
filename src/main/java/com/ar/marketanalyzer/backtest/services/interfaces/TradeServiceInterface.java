package com.ar.marketanalyzer.backtest.services.interfaces;

import java.util.List;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.Trade;

public interface TradeServiceInterface {

	Trade create(Trade trade);

	Trade update(Trade trade);

	Trade delete(int id) throws ModelNotFound;

	Trade findById(int id) throws ModelNotFound;

	void batchCreate(List<Trade> tradeList);
}

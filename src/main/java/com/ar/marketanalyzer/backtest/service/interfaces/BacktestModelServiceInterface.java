package com.ar.marketanalyzer.backtest.service.interfaces;

import com.ar.marketanalyzer.backtest.model.BacktestModel;
import com.ar.marketanalyzer.securities.models.Symbol;

public interface BacktestModelServiceInterface {
	public void runCurrentModel(Symbol symbol);
	public BacktestModel getCurrentBacktestModelBySymbol(Symbol symbol);
}

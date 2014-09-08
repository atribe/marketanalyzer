package com.ar.marketanalyzer.backtest.logic;

import com.ar.marketanalyzer.backtest.models.models.IndexBacktestingModel;
import com.ar.marketanalyzer.core.securities.models.Symbol;

public class BacktestModelLogic {

	public void createDefaultModel(Symbol symbol) {
		IndexBacktestingModel defaultModel = new IndexBacktestingModel(symbol);
	}
}

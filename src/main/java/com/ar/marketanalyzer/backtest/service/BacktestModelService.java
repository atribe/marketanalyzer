package com.ar.marketanalyzer.backtest.service;

import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.backtest.model.BacktestModel;
import com.ar.marketanalyzer.backtest.service.interfaces.BacktestModelServiceInterface;
import com.ar.marketanalyzer.securities.models.Symbol;

@Component
public class BacktestModelService implements BacktestModelServiceInterface {

	public void runCurrentModel(Symbol symbol) {
		BacktestModel model = getCurrentBacktestModelBySymbol(symbol);
	}

	public BacktestModel getCurrentBacktestModelBySymbol(Symbol symbol) {
		
		return null;
	}

}

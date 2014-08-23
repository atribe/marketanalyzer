package com.ar.marketanalyzer.backtest.service.interfaces;

import com.ar.marketanalyzer.backtest.model.BacktestModel;
import com.ar.marketanalyzer.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.securities.models.Symbol;

public interface BacktestModelServiceInterface {
	public BacktestModel getCurrentBacktestModelBySymbol(Symbol symbol) throws SecuritiesNotFound;
}

package com.ar.marketanalyzer.backtest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.backtest.models.enums.ModelStatus;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.core.securities.models.Symbol;

public interface AbstractModelRepo extends JpaRepository<AbstractModel, Integer>{
	public AbstractModel findBySymbolAndModelStatus(Symbol symbol, ModelStatus modelStatus);
	
	//public AbstractModel findBySymbolAndModelStatusEager(Symbol symbol, ModelStatus modelStatus);
}

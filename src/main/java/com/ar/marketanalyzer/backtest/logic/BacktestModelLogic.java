package com.ar.marketanalyzer.backtest.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.backtest.models.models.IndexBacktestingModel;
import com.ar.marketanalyzer.backtest.services.interfaces.AbstractModelServiceInterface;
import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.SecurityOhlcvService;
import com.ar.marketanalyzer.core.securities.services.interfaces.SecurityOhlcvServiceInterface;

@Component
public class BacktestModelLogic {

	@Autowired
	private AbstractModelServiceInterface modelService;
	
	@Autowired
	private SecurityOhlcvServiceInterface ohlcvService;
	
	public void createDefaultModel(Symbol symbol) {
		IndexBacktestingModel defaultModel = new IndexBacktestingModel(symbol);
		
		defaultModel = getModelOhlcv(defaultModel);
		//defaultModel
		/*
		 * setOhlcvData(); // Looks up the ohclv data from the database
		
		calcStats();	// calculate stats needed for the model
		 */
		
		modelService.create(defaultModel);
		
		
	}
	
	private AbstractModel getModelOhlcv(AbstractModel model) {
		try {
			ohlcvService = new SecurityOhlcvService();
			model.setOhlcvData(ohlcvService.findBySymbolAndDateBetween(model.getSymbol(), model.getStartDate(), model.getEndDate()));
		} catch (SecuritiesNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
}

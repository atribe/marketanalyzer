package com.ar.marketanalyzer.backtest.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.enums.ModelStatus;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.backtest.models.models.IndexBacktestingModel;
import com.ar.marketanalyzer.backtest.services.interfaces.AbstractModelServiceInterface;
import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.interfaces.SecurityOhlcvServiceInterface;

@Component
public class BacktestModelLogic {

	@Autowired
	private AbstractModelServiceInterface modelService;
	
	@Autowired
	private SecurityOhlcvServiceInterface ohlcvService;
	
	private AbstractModel model;
	
	public void runCurrentModel(Symbol symbol) {
		/* Get current model
		 * 	If no current model, the use the default model
		 * Check the model needs to be run
		 * 	model results already calculated?
		 * If model already ran, do nothing
		 * If model not yet run,
		 * 	Run model
		 */

		if( !findCurrentModel(symbol) ) {	// If the current model isn't found
											// create a default and run it
			prepModel();

			runModel();
			
			saveModel();
		}
	}

	private boolean findCurrentModel(Symbol symbol) {
		try {
			this.model = modelService.findBySymbolAndModelStatus(symbol, ModelStatus.CURRENT);
			return true;
		} catch (ModelNotFound e) {
			this.model = createDefaultModel(symbol);
			return false;
		}
	}

	private AbstractModel createDefaultModel(Symbol symbol) {
		AbstractModel defaultModel = new IndexBacktestingModel(symbol);
		
		//defaultModel = modelService.create(defaultModel);
		
		return defaultModel;
	}
	
	private void prepModel() {
		getModelOhlcv();
		
		model.calcStats();	// calculate stats needed for the model
	}
	
	private void getModelOhlcv() {
		try {
			model.setOhlcvData(ohlcvService.findBySymbolAndDateBetween(model.getSymbol(), model.getStartDate(), model.getEndDate()));
		} catch (SecuritiesNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void runModel() {
		model.runModel();
	}

	private void saveModel() {
		modelService.create(model);
	}
}

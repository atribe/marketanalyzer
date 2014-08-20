package com.ar.marketanalyzer.marketstatus.logic;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.backtest.logic.BacktestModelLogic;
import com.ar.marketanalyzer.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.securities.models.Symbol;
import com.ar.marketanalyzer.securities.services.interfaces.SecuritiesServiceInterface;
import com.ar.marketanalyzer.securities.services.interfaces.SymbolServiceInterface;

@Component
@PropertySource({ "classpath:common.properties" })
public class MarketStatusInit {

	/* Get actual class name to be printed on */
	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Resource
	private Environment env;
	
	@Autowired
	private SymbolServiceInterface symbolService;
	@Autowired
	private SecuritiesServiceInterface secService;
	@Autowired
	private BacktestModelLogic backtestLogic;
	
	private String[] indexSymbolList;
	private String[] indexNamesList;
	private String indexType;
	
	public void init() {
		log.trace("Starting Index Backtest (IndBack) Thread");
		
		indexSymbolList = env.getRequiredProperty("index.symbols").split(",");		// Getting the index symbol list from the property file
		indexNamesList = env.getRequiredProperty("index.names").split(",");			// Getting the corresponding name list
		indexType = env.getRequiredProperty("index.type");
		
		for(int i = 0; i < indexSymbolList.length; i++) {
			Symbol symbol = null;
			
			try {
				symbol = symbolService.findBySymbol(indexSymbolList[i]);			// See if a symbol with the provided symbol is in the db and return it if found
			} catch (SecuritiesNotFound e) {										// Provided symbol wasn't in the db
				symbol = new Symbol(indexSymbolList[i], indexNamesList[i], indexType); // create a new symbol
				symbol = symbolService.createOrFindDuplicate(symbol);				// add it to the db and return the symbol including the ID
			}
			
			boolean ohlcvAlreadyUpToDate = !secService.updateOhlcv(symbol);			// Inverting the boolean returned because return is (was db updated) and I want the opposite of that
			
			backtestLogic.runCurrentModel(symbol);									// Run the current model
		}
	}

}

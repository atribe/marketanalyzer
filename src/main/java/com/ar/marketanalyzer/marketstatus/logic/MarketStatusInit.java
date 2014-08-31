package com.ar.marketanalyzer.marketstatus.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.backtest.service.BacktestModelService;
import com.ar.marketanalyzer.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.securities.models.Symbol;
import com.ar.marketanalyzer.securities.services.interfaces.SecuritiesOhlcvServiceInterface;
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
	private SecuritiesOhlcvServiceInterface secService;
	@Autowired
	private BacktestModelService backtestService;
	
	private String[] indexSymbolList;
	private String[] indexNamesList;
	private String indexType;
	
	public void init() {
		log.trace("Starting Index Backtest (IndBack) Thread");
		
		List<Symbol> indexList = populateIndexList();
		
		for(Symbol symbol:indexList) {
			boolean ohlcvAlreadyUpToDate = !secService.updateOhlcv(symbol);			// Inverting the boolean returned because return is (was db updated) and I want the opposite of that
			
			//backtestService.runCurrentModel(symbol);									// Run the current model
		}
	}
	
	/**
	 * Retrieves the symbols symbol, name, and type from the properties files
	 * Creates the symbols in the DB if they don't exist.
	 * 
	 * @return List of Indexes from the properties file
	 */
	private List<Symbol> populateIndexList() {
		List<Symbol> indexList = new ArrayList<Symbol>();
		
		indexSymbolList = env.getRequiredProperty("index.symbols").split(",");		// Getting the index symbol list from the property file
		indexNamesList = env.getRequiredProperty("index.names").split(",");			// Getting the corresponding name list
		indexType = env.getRequiredProperty("index.type");
		
		for(int i = 0; i < indexSymbolList.length; i++) {
			Symbol symbol = null;
			
			symbol = new Symbol(indexSymbolList[i], indexNamesList[i], indexType); 	// create a new symbol
			symbol = symbolService.createOrFindDuplicate(symbol);					// add it to the db and return the symbol including the ID or find the symbol in the db
			
			indexList.add(symbol);
		}
		
		return indexList;
	}

}

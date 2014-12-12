package com.ar.marketanalyzer.backtest.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.core.securities.logic.SecurityOhlcvLogic;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@Component
@PropertySource(value="classpath:common.properties")
public class BacktestLogic {
	/* Get actual class name to be printed on */
	final Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Resource
	private Environment env;
	@Autowired
	private SymbolServiceInterface symbolService;
	@Autowired
	private SecurityOhlcvLogic ohlcvLogic;
	@Autowired
	private BacktestModelLogic modelLogic;
	
	public void init() {
		log.trace("Start init()");
		List<Symbol> defaultSymbols = getDefaultSymbols();
		
		ohlcvLogic.updateOhlcv(defaultSymbols);
		
		runCurrentModels(defaultSymbols);
		log.trace("End init()");
	}

	private List<Symbol> getDefaultSymbols() {
		log.trace("start getDefaultSymbols()");
		String[] indexSymbolList = env.getRequiredProperty("index.symbols").split(",");
		String[] indexNamesList = env.getRequiredProperty("index.names").split(",");	
		String indexType = env.getRequiredProperty("index.type");
		
		List<Symbol> defaultSymbols = new ArrayList<Symbol>();
		
		for(int i = 0; i < indexSymbolList.length; i++) {
			Symbol symbol = new Symbol(indexSymbolList[i], indexNamesList[i], indexType); // create a new symbol
			symbol = symbolService.createOrFindDuplicate(symbol);				// add it to the db and return the symbol including the ID
			defaultSymbols.add(symbol);
		}
		
		log.trace("end getDefaultSymbols()");
		return defaultSymbols;
	}
	
	public void runCurrentModels(List<Symbol> defaultSymbols) {
		for( Symbol symbol : defaultSymbols ) {
			runCurrentModel(symbol);
		}
	}
	public void runCurrentModel(Symbol symbol) {
		modelLogic.runCurrentModel(symbol);
	}
}

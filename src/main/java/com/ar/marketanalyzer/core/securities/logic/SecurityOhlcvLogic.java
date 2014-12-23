package com.ar.marketanalyzer.core.securities.logic;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.backtest.logic.BacktestLogic;
import com.ar.marketanalyzer.core.securities.exceptions.SymbolNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.YahooOhlcvService;
import com.ar.marketanalyzer.core.securities.services.interfaces.SecurityOhlcvServiceInterface;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@Component
@PropertySource(value="classpath:common.properties")
public class SecurityOhlcvLogic {
	
	final Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Resource
	private Environment env;
	@Autowired
	private SymbolServiceInterface symbolService;
	@Autowired
	private SecurityOhlcvServiceInterface secOhlcvService;
	@Autowired
	private YahooOhlcvService yahooService;
	@Autowired
	private BacktestLogic backtestLogic;

	public void updateOhlcv(List<Symbol> symbolList) {
		log.trace("start updateOhlcv on list of symbols");
		for(Symbol symbol: symbolList) {
			try {
				secOhlcvService.updateOhlcvFromYahoo(symbol);
			} catch (SymbolNotFound | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.trace("end updateOhlcv on list of symbols");
	}

	//Scheduled to run every weekday at 5 pm
	@Scheduled(cron="0 0 17 * * MON-FRI")
	public void ohlcScheduledUpdate() {
		List<Symbol> symbolList = symbolService.findAll();	// Find all symbols in the DB 
		
		updateOhlcv(symbolList);							// and then update their ohlcv
		
		//Then update their models
		backtestLogic.runCurrentModels(symbolList);
		
	}
}

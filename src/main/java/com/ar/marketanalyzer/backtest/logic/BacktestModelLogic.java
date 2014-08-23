package com.ar.marketanalyzer.backtest.logic;

import javax.annotation.Resource;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.backtest.model.BacktestModel;
import com.ar.marketanalyzer.backtest.service.interfaces.BacktestModelServiceInterface;
import com.ar.marketanalyzer.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.securities.models.Symbol;

@Component
@PropertySource({ "classpath:common.properties" })
public class BacktestModelLogic {
	
	@Resource
	private Environment env;
	@Autowired
	private BacktestModelServiceInterface modelService;
	
	public void runCurrentModel(Symbol symbol) {
		BacktestModel model = null;
		try {
			model = modelService.getCurrentBacktestModelBySymbol(symbol);		// try to get the current model for the symbol
		} catch (SecuritiesNotFound e) {
			// No current model, use the default one
			model = populateWithDefaultModel(symbol);
			
			runBaselineModel(model);
		}
		
		runModel(model);
	}

	private BacktestModel populateWithDefaultModel(Symbol symbol) {
		BacktestModel b = new BacktestModel();

		b.setSymbol(symbol);
		b.setEndDate(new LocalDate());
		b.setStartDate(new LocalDate().minusMonths(6));
		b.setdDayWindow(Integer.parseInt(env.getProperty("backtest.dDayWindow")));
		b.setdDayParam(Integer.parseInt(env.getProperty("backtest.dDayParam")));
		b.setdDayPriceDrop(Double.parseDouble(env.getProperty("backtest.setdDayPriceDrop")));
		b.setChurnVolRange(Double.parseDouble(env.getProperty("backtest.setChurnVolRange")));
		b.setChurnPriceRange(Double.parseDouble(env.getProperty("backtest.setChurnPriceRange")));
		b.setChurnPriceCloseHigherOn(Boolean.parseBoolean(env.getProperty("backtest.setChurnPriceCloseHigherOn")));
		b.setChurnAVG50On(Boolean.parseBoolean(env.getProperty("backtest.setChurnAVG50On")));
		b.setChurnPriceTrend35On(Boolean.parseBoolean(env.getProperty("backtest.setChurnPriceTrend35On")));
		b.setChurnPriceTrend35(Double.parseDouble(env.getProperty("backtest.setChurnPriceTrend35")));
		b.setVolVolatilityOn(Boolean.parseBoolean(env.getProperty("backtest.setVolVolatilityOn")));
		b.setVolumeMult(Double.parseDouble(env.getProperty("backtest.setVolumeMult")));
		b.setVolMultTop(Double.parseDouble(env.getProperty("backtest.setVolMultTop")));
		b.setVolMultBot(Double.parseDouble(env.getProperty("backtest.setVolMultBot")));
		b.setPriceVolatilityOn(Boolean.parseBoolean(env.getProperty("backtest.setPriceVolatilityOn")));
		b.setPriceMult(Double.parseDouble(env.getProperty("backtest.setPriceMult")));
		b.setPriceMultTop(Double.parseDouble(env.getProperty("backtest.setPriceMultTop")));
		b.setPriceMultBot(Double.parseDouble(env.getProperty("backtest.setPriceMultBot")));
		b.setrDaysMin(Integer.parseInt(env.getProperty("backtest.setrDaysMin")));
		b.setrDaysMax(Integer.parseInt(env.getProperty("backtest.setrDaysMax")));
		b.setPivotTrend35On(Boolean.parseBoolean(env.getProperty("backtest.setPivotTrend35On")));
		b.setPivotTrend35(Double.parseDouble(env.getProperty("backtest.setPivotTrend35")));
		b.setRallyVolAVG50On(Boolean.parseBoolean(env.getProperty("backtest.setRallyVolAVG50On")));
		b.setRallyPriceHighOn(Boolean.parseBoolean(env.getProperty("backtest.setRallyPriceHighOn")));
		
		return b;
	}

	private void runBaselineModel(BacktestModel model) {
		
	}
	
	private void runModel(BacktestModel model) {
		
	}
}

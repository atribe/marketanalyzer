package com.ar.marketanalyzer.backtest.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.backtest.model.BacktestModel;
import com.ar.marketanalyzer.backtest.model.BacktestModel.modelTypeEnum;
import com.ar.marketanalyzer.backtest.repo.BacktestModelRepo;
import com.ar.marketanalyzer.backtest.service.interfaces.BacktestModelServiceInterface;
import com.ar.marketanalyzer.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.securities.models.Symbol;

@Component
@PropertySource({ "classpath:common.properties" })
public class BacktestModelService implements BacktestModelServiceInterface {

	@Resource
	private BacktestModelRepo modelRepo;


	@Override
	@Transactional
	public BacktestModel getCurrentBacktestModelBySymbol(Symbol symbol) throws SecuritiesNotFound {
		BacktestModel model = modelRepo.findBySymbolAndModelType(symbol, modelTypeEnum.CURRENT);
		
		nullCheck(model);
		
		return model;
	}
	
	private void nullCheck(BacktestModel model) throws SecuritiesNotFound {
		if( model == null) {
			throw new SecuritiesNotFound("No Model found");
		}
	}
}

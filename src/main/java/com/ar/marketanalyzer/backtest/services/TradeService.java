package com.ar.marketanalyzer.backtest.services;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.Trade;
import com.ar.marketanalyzer.backtest.repo.TradeRepo;
import com.ar.marketanalyzer.backtest.services.interfaces.TradeServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TradeService implements TradeServiceInterface{
	@Resource
	private TradeRepo tradeRepo;

	@Override
	@Transactional
	public Trade create(Trade trade) {
		
		return tradeRepo.save(trade);
	}
	
	@Override
	@Transactional
	public void batchCreate(List<Trade> resultList) {
		tradeRepo.save(resultList);
	}

	@Override
	@Transactional
	public Trade update(Trade trade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Trade delete(int id) throws ModelNotFound {
		Trade resultToDelete = findById(id);
		
		// if the findById method fails, then exception thrown and this code not run
		tradeRepo.delete(id);
		
		return resultToDelete;
	}

	@Override
	@Transactional
	public Trade findById(int id) throws ModelNotFound {
		Trade foundresult = tradeRepo.findOne(id);
		
		if( foundresult == null ) {
			throw new ModelNotFound();
		}
		
		return foundresult;
	}
}

package com.ar.marketanalyzer.backtest.services;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.DollarValue;
import com.ar.marketanalyzer.backtest.repo.DollarValueRepo;
import com.ar.marketanalyzer.backtest.services.interfaces.DollarValueServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.SortedSet;

@Service
public class DollarValueService implements DollarValueServiceInterface{
	@Resource
	private DollarValueRepo DollarValueRepo;

	@Override
	@Transactional
	public DollarValue create(DollarValue dollarValue) {
		
		return DollarValueRepo.save(dollarValue);
	}
	
	@Override
	@Transactional
	public void batchCreate(SortedSet<DollarValue> dollarValueList) {
		DollarValueRepo.save(dollarValueList);
	}

	@Override
	@Transactional
	public DollarValue update(DollarValue dollarValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public DollarValue delete(int id) throws ModelNotFound {
		DollarValue resultToDelete = findById(id);
		
		// if the findById method fails, then exception thrown and this code not run
		DollarValueRepo.delete(id);
		
		return resultToDelete;
	}

	@Override
	@Transactional
	public DollarValue findById(int id) throws ModelNotFound {
		DollarValue foundDollarValue = DollarValueRepo.findOne(id);
		
		if( foundDollarValue == null ) {
			throw new ModelNotFound();
		}
		
		return foundDollarValue;
	}
}

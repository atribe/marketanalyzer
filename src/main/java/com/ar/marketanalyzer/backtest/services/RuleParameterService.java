package com.ar.marketanalyzer.backtest.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.RuleParameter;
import com.ar.marketanalyzer.backtest.repo.RuleParameterRepo;
import com.ar.marketanalyzer.backtest.services.interfaces.RuleParameterServiceInterface;

@Service
public class RuleParameterService implements RuleParameterServiceInterface {

	@Resource
	private RuleParameterRepo paramRepo;

	@Override
	@Transactional
	public RuleParameter create(RuleParameter rule) {
		
		return paramRepo.save(rule);
	}

	@Override
	@Transactional
	public RuleParameter update(RuleParameter rule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public RuleParameter delete(int id) throws ModelNotFound {
		RuleParameter resultToDelete = findById(id);
		
		// if the findById method fails, then exception thrown and this code not run
		paramRepo.delete(id);
		
		return resultToDelete;
	}

	@Override
	@Transactional
	public RuleParameter findById(int id) throws ModelNotFound {
		RuleParameter foundresult = paramRepo.findOne(id);
		
		if( foundresult == null ) {
			throw new ModelNotFound();
		}
		
		return foundresult;
	}
}

package com.ar.marketanalyzer.backtest.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.RuleResult;
import com.ar.marketanalyzer.backtest.repo.RuleResultRepo;
import com.ar.marketanalyzer.backtest.services.interfaces.RuleResultServiceInterface;

@Service
public class RuleResultService implements RuleResultServiceInterface {

	@Resource
	private RuleResultRepo resultRepo;

	@Override
	@Transactional
	public RuleResult create(RuleResult rule) {
		
		return resultRepo.save(rule);
	}
	
	@Override
	@Transactional
	public void batchCreate(List<RuleResult> resultList) {
		resultRepo.save(resultList);
	}

	@Override
	@Transactional
	public RuleResult update(RuleResult rule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public RuleResult delete(int id) throws ModelNotFound {
		RuleResult resultToDelete = findById(id);
		
		// if the findById method fails, then exception thrown and this code not run
		resultRepo.delete(id);
		
		return resultToDelete;
	}

	@Override
	@Transactional
	public RuleResult findById(int id) throws ModelNotFound {
		RuleResult foundresult = resultRepo.findOne(id);
		
		if( foundresult == null ) {
			throw new ModelNotFound();
		}
		
		return foundresult;
	}
}

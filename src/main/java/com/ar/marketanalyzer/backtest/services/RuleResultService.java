package com.ar.marketanalyzer.backtest.services;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.ruleresults.AbstractRuleResult;
import com.ar.marketanalyzer.backtest.repo.RuleResultRepo;
import com.ar.marketanalyzer.backtest.services.interfaces.RuleResultServiceInterface;

@Service
public class RuleResultService implements RuleResultServiceInterface {

	@Resource
	private RuleResultRepo resultRepo;

	@Override
	@Transactional
	public AbstractRuleResult create(AbstractRuleResult rule) {
		
		return resultRepo.save(rule);
	}
	
	@Override
	@Transactional
	public void batchCreate(SortedMap<Date, AbstractRuleResult> resultList) {
		resultRepo.save((Map<Date,AbstractRuleResult>)resultList);
	}

	@Override
	@Transactional
	public AbstractRuleResult update(AbstractRuleResult rule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public AbstractRuleResult delete(int id) throws ModelNotFound {
		AbstractRuleResult resultToDelete = findById(id);
		
		// if the findById method fails, then exception thrown and this code not run
		resultRepo.delete(id);
		
		return resultToDelete;
	}

	@Override
	@Transactional
	public AbstractRuleResult findById(int id) throws ModelNotFound {
		AbstractRuleResult foundresult = resultRepo.findOne(id);
		
		if( foundresult == null ) {
			throw new ModelNotFound();
		}
		
		return foundresult;
	}
}

package com.ar.marketanalyzer.backtest.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.backtest.repo.AbstractRuleRepo;
import com.ar.marketanalyzer.backtest.services.interfaces.AbstractRuleServiceInterface;

@Service
public class AbstractRuleService implements AbstractRuleServiceInterface {

	@Resource
	private AbstractRuleRepo ruleRepo;

	@Override
	@Transactional
	public AbstractRule create(AbstractRule rule) {
		AbstractRule createdRule = ruleRepo.save(rule);
		return createdRule;
	}

	@Override
	@Transactional
	public AbstractRule update(AbstractRule rule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public AbstractRule delete(int id) throws ModelNotFound {
		AbstractRule ruleToDelete = findById(id);
		
		// if the findById method fails, then exception thrown and this code not run
		ruleRepo.delete(id);
		
		return ruleToDelete;
	}

	@Override
	@Transactional
	public AbstractRule findById(int id) throws ModelNotFound {
		AbstractRule foundrule = ruleRepo.findOne(id);
		
		if( foundrule == null ) {
			throw new ModelNotFound();
		}
		
		return foundrule;
	}
}

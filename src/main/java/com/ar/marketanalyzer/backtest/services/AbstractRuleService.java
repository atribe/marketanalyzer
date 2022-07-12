package com.ar.marketanalyzer.backtest.services;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.backtest.repo.AbstractRuleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AbstractRuleService {

	private final AbstractRuleRepo ruleRepo;

	@Transactional
	public AbstractRule create(AbstractRule rule) {
		AbstractRule createdRule = ruleRepo.save(rule);
		return createdRule;
	}

	@Transactional
	public AbstractRule update(AbstractRule rule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public AbstractRule delete(int id) throws ModelNotFound {
		AbstractRule ruleToDelete = findById(id);

		// if the findById method fails, then exception thrown and this code not run
		ruleRepo.deleteById(id);

		return ruleToDelete;
	}

	@Transactional
	public AbstractRule findById(int id) throws ModelNotFound {
		var foundrule = ruleRepo.findById(id);

		if (foundrule.isEmpty()) {
			throw new ModelNotFound();
		}

		return foundrule.get();
	}
}

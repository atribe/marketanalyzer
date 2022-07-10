package com.ar.marketanalyzer.backtest.services.interfaces;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.ruleresults.AbstractRuleResult;

import java.util.SortedSet;

public interface RuleResultServiceInterface {

	AbstractRuleResult create(AbstractRuleResult rule);

	AbstractRuleResult update(AbstractRuleResult rule);

	AbstractRuleResult delete(int id) throws ModelNotFound;

	AbstractRuleResult findById(int id) throws ModelNotFound;

	void batchCreate(SortedSet<AbstractRuleResult> resultList);

}

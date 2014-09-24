package com.ar.marketanalyzer.backtest.services.interfaces;

import java.util.List;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.RuleResult;

public interface RuleResultServiceInterface {

	RuleResult create(RuleResult rule);

	RuleResult update(RuleResult rule);

	RuleResult delete(int id) throws ModelNotFound;

	RuleResult findById(int id) throws ModelNotFound;

	void batchCreate(List<RuleResult> resultList);

}

package com.ar.marketanalyzer.backtest.services.interfaces;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.RuleParameter;

import java.util.List;

public interface RuleParameterServiceInterface {

	RuleParameter create(RuleParameter rule);

	RuleParameter update(RuleParameter rule);

	RuleParameter delete(int id) throws ModelNotFound;

	RuleParameter findById(int id) throws ModelNotFound;

	void batchCreate(List<RuleParameter> paramList);

}

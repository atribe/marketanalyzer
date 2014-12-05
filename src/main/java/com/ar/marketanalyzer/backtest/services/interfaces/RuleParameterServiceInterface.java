package com.ar.marketanalyzer.backtest.services.interfaces;

import java.util.List;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.RuleParameter;

public interface RuleParameterServiceInterface {

	RuleParameter create(RuleParameter rule);

	RuleParameter update(RuleParameter rule);

	RuleParameter delete(int id) throws ModelNotFound;

	RuleParameter findById(int id) throws ModelNotFound;

	void batchCreate(List<RuleParameter> paramList);

}

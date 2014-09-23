package com.ar.marketanalyzer.backtest.services.interfaces;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;

public interface AbstractRuleServiceInterface {
	public AbstractRule create(AbstractRule model);
	public AbstractRule update(AbstractRule model);
	public AbstractRule delete(int id) throws ModelNotFound;
	public AbstractRule findById(int id) throws ModelNotFound;
}

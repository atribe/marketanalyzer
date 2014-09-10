package com.ar.marketanalyzer.backtest.services.interfaces;

import java.util.List;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.core.securities.models.Symbol;

public interface AbstractRuleServiceInterface {
	public AbstractRule create(AbstractRule model);
	public AbstractRule update(AbstractRule model);
	public AbstractRule delete(int id) throws ModelNotFound;
	public List<AbstractRule> findBySymbol(Symbol symbol) throws ModelNotFound;
	public AbstractRule findById(int id) throws ModelNotFound;
}

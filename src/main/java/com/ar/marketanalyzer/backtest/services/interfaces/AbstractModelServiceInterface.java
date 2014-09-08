package com.ar.marketanalyzer.backtest.services.interfaces;

import java.util.List;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.core.securities.models.Symbol;

public interface AbstractModelServiceInterface {
	public AbstractModel create(AbstractModel model);
	public AbstractModel update(AbstractModel model);
	public AbstractModel delete(int id) throws ModelNotFound;
	public List<AbstractModel> findBySymbol(Symbol symbol) throws ModelNotFound;
	public AbstractModel findById(int id) throws ModelNotFound;
}

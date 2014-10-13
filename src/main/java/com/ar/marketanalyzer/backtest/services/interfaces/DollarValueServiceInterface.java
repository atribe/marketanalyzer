package com.ar.marketanalyzer.backtest.services.interfaces;

import java.util.List;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.DollarValue;

public interface DollarValueServiceInterface {

	DollarValue create(DollarValue DollarValue);

	DollarValue update(DollarValue DollarValue);

	DollarValue delete(int id) throws ModelNotFound;

	DollarValue findById(int id) throws ModelNotFound;

	void batchCreate(List<DollarValue> DollarValueList);
}

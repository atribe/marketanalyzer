package com.ar.marketanalyzer.backtest.services.interfaces;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.DollarValue;

import java.util.SortedSet;

public interface DollarValueServiceInterface {

	DollarValue create(DollarValue DollarValue);

	DollarValue update(DollarValue DollarValue);

	DollarValue delete(int id) throws ModelNotFound;

	DollarValue findById(int id) throws ModelNotFound;

	void batchCreate(SortedSet<DollarValue> DollarValueList);
}

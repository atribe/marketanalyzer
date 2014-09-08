package com.ar.marketanalyzer.backtest.models.models;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.ar.marketanalyzer.core.securities.models.Symbol;

@Entity
@DiscriminatorValue("Index Backtesting")
public class IndexBacktestingModel extends AbstractModel{
	private static final long serialVersionUID = -5707374797673699670L;

	public IndexBacktestingModel() {
		super();
	}
	public IndexBacktestingModel(Symbol symbol) {
		super(symbol);
	}
	public IndexBacktestingModel(Symbol symbol, Date startDate) {
		super(symbol,startDate);
	}
	public IndexBacktestingModel(Symbol symbol, Date startDate, Date endDate) {
		super(symbol,startDate,endDate);
	}
}

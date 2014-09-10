package com.ar.marketanalyzer.backtest.models.models;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.ar.marketanalyzer.backtest.models.rules.Above30DayAverage;
import com.ar.marketanalyzer.backtest.models.rules.Above30DayVolumeAverage;
import com.ar.marketanalyzer.core.securities.models.Symbol;

@Entity
@DiscriminatorValue("Index Backtesting")
public class IndexBacktestingModel extends AbstractModel{
	private static final long serialVersionUID = -5707374797673699670L;

	/*
	 * Constructors
	 */
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

	@Override
	protected void assignRules() {
		/*
		 * 1. create a new rule instance
		 * 2. Add rule instance to buy rule list
		 * 3. Define the buy rule relationships: and, or, not, Xor
		 * 4. Add rule instance to sell rule list
		 * 5. Define the sell rule relationships: and, or, not, Xor
		 */
		
		Above30DayAverage buyRule1 = new Above30DayAverage();
		Above30DayVolumeAverage buyRule2 = new Above30DayVolumeAverage();
		buyRuleList.add(buyRule1);
		buyRuleList.add(buyRule2);
	}
	
	@Override
	protected void evaluateRules() {
		/*
		 * for ohlcv list or something {
		 * 		if( rule1 && rule2)
		 * 			true	//buy
		 * 		else
		 * 			false	//sell
		 * }
		 */
		{
			//if rule1 && rule2
			
		}
	}
}

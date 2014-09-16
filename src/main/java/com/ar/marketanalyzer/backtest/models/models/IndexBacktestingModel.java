package com.ar.marketanalyzer.backtest.models.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.ar.marketanalyzer.backtest.models.rules.Above30DayAverage;
import com.ar.marketanalyzer.backtest.models.rules.Above30DayVolumeAverage;
import com.ar.marketanalyzer.backtest.models.stats.FollowThruStats;
import com.ar.marketanalyzer.backtest.models.stats.Stats;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.mysql.jdbc.log.Log;

@Entity
@DiscriminatorValue("Index Backtesting")
public class IndexBacktestingModel extends AbstractModel {
	private static final long serialVersionUID = -5707374797673699670L;

	@Transient
	protected List<FollowThruStats> stats;
	
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
	
	public void calcStats() {
		super.calcStats();
		stats = FollowThruStats.convertStatList(defaultStats);
		// calc more stats as needed
		/*
		 * Loop for 35 days
		 * Calculates Price Trend of the previous 35 days'
		 * 		This is the average percent gain over the last 35 days (excluding today)
		 */
		int loopDays = 35;
		
		for(int i=ohlcvData.size()-1; i>0; i--) {
			double closePercentChange = 0;
			
			for(int j=i; j>i-loopDays && j>2; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				closePercentChange+=(ohlcvData.get(j-1).getClose().subtract(ohlcvData.get(j-2).getClose())).doubleValue() / ohlcvData.get(j-2).getClose().doubleValue();
			}
			stats.get(i-1).setPriceTrend35(closePercentChange/loopDays);
		}
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

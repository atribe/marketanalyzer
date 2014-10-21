package com.ar.marketanalyzer.backtest.models.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.backtest.models.rules.RuleBuyFollowThru;
import com.ar.marketanalyzer.backtest.models.rules.RuleSellDDaysAndChurnDays;
import com.ar.marketanalyzer.backtest.models.stats.FollowThruStats;
import com.ar.marketanalyzer.core.securities.models.Symbol;

@Entity
@DiscriminatorValue("Index Backtesting")
public class IndexBacktestingModel extends AbstractModel {
	private static final long serialVersionUID = -5707374797673699670L;

	@Transient
	protected SortedMap<Date, FollowThruStats> stats;
	
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
		
		// Create all rules
		AbstractRule sellRule1 = new RuleSellDDaysAndChurnDays(this);
		AbstractRule buyRule1 = new RuleBuyFollowThru(this);
		
		// Initialize Rules?
		
		// Add rules to model
		ruleList.add(sellRule1);
		ruleList.add(buyRule1);
	}
	
	@Override
	public void calcStats() {
		super.calcStats();
		stats = (TreeMap<Date, FollowThruStats>)FollowThruStats.convertStatList(defaultStats);
		// calc more stats as needed
		/*
		 * Loop for 35 days
		 * Calculates Price Trend of the previous 35 days'
		 * 		This is the average percent gain over the last 35 days (excluding today)
		 */
		int loopDays = 35;
		
		ArrayList<Date> keys = new ArrayList<Date>(ohlcvData.keySet());
		
		for(int i = keys.size() -1; i >= 0; i--) {

			double closePercentChange = 0;
			
			for(int j=i; j>i-loopDays && j>2; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				closePercentChange+=(ohlcvData.get(keys.get(j)).getClose().subtract(ohlcvData.get(keys.get(j-1)).getClose())).doubleValue() / ohlcvData.get(keys.get(j-1)).getClose().doubleValue();
			}
			stats.get(keys.get(i)).setPriceTrend35(closePercentChange/loopDays);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TreeMap<Date, FollowThruStats> getStats() {
		return (TreeMap<Date, FollowThruStats>) stats;
	}
	public void setStats(SortedMap<Date, FollowThruStats> stats) {
		this.stats = stats;
	}
}

package com.ar.marketanalyzer.backtest.models.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.backtest.models.BuySellTrigger;
import com.ar.marketanalyzer.backtest.models.DollarValue;
import com.ar.marketanalyzer.backtest.models.Trade;
import com.ar.marketanalyzer.backtest.models.enums.ModelStatus;
import com.ar.marketanalyzer.backtest.models.enums.RuleType;
import com.ar.marketanalyzer.backtest.models.ruleresults.AbstractRuleResult;
import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.backtest.models.stats.Stats;
import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;
import com.ar.marketanalyzer.spring.init.PropCache;

@Component	//this is so the autowired works for the ohlcv service
@Entity
@Inheritance
@DiscriminatorColumn(name="MODEL_NAME") //http://en.wikibooks.org/wiki/Java_Persistence/Inheritance#Single_Table_Inheritance
@Table(name="backtest_model")
public abstract class AbstractModel extends PersistableEntityInt{
	
	private static final long serialVersionUID = 5829380092032471186L;
	
	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="symbol_id", referencedColumnName="id")
	protected Symbol symbol;
	
	@Enumerated(EnumType.STRING)
	@Column( name="model_status", nullable=false)
	protected ModelStatus modelStatus;
	
	@Column( name="start_date", nullable=false)
	protected Date startDate;
	
	@Column( name="end_date", nullable=false)
	protected Date endDate;
	
	@Column( name="initial_investment")
	protected BigDecimal initialInvestment;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="backtest_model_rule",
				joinColumns={@JoinColumn(name="model_id")},
				inverseJoinColumns={@JoinColumn(name="rule_id")})
	protected List<AbstractRule> ruleList = new ArrayList<AbstractRule>();
	
	@Transient
	protected SortedMap<Date, BuySellTrigger> buySellTriggers = new TreeMap<Date, BuySellTrigger>();

	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	protected List<Trade> tradeList = new ArrayList<Trade>();
	
	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	protected SortedSet<DollarValue> valueSet = new TreeSet<DollarValue>();
	
	@Transient
	protected SortedMap<Date, DollarValue> valueMap = new TreeMap<Date, DollarValue>();
	
	/*
	 * Not DB stored fields
	 */
	@Transient
	protected SortedMap<Date, SecuritiesOhlcv> ohlcvData = new TreeMap<Date, SecuritiesOhlcv>();
	@Transient
	protected SortedMap<Date, Stats> defaultStats = new TreeMap<Date, Stats>();

	protected static final int modelMonthRange = Integer.parseInt(PropCache.getCachedProps("default.ModelMonths"));
	protected static final BigDecimal defaultInitialInvestment = new BigDecimal(1000);
	protected static final Date defaultStartDate = new Date( new LocalDate().minusMonths(modelMonthRange).toDateTimeAtStartOfDay().getMillis() );
	protected static final Date defaultEndDate = new Date( new LocalDate().toDateTimeAtStartOfDay().getMillis() );
	
	/*
	 * Constructors
	 */
	public AbstractModel() {
	}
	public AbstractModel(Symbol symbol) {
		this(symbol, defaultStartDate, defaultEndDate);
	}
	public AbstractModel(Symbol symbol, Date startDate) {
		this(symbol, defaultStartDate, defaultEndDate);
	}
	public AbstractModel(Symbol symbol, Date startDate, Date endDate) {
		this.symbol = symbol;
		this.startDate = startDate;
		this.endDate = endDate;
		this.initialInvestment = defaultInitialInvestment;
		this.modelStatus = ModelStatus.CURRENT;
		
		ruleList = new ArrayList<AbstractRule>();

		assignRules();	// assigns the rules to this model, must be overridden by the child class
	}
	
	/*
	 * Helper Methods that do not need to be implemented
	 */

	/*
	 * Helper Methods that must be implemented by the extending class
	 */
	protected abstract void assignRules();
	
	public void runModel() {
		evaluateRules();
		
		makeTrades();
		
		calculateDollarValue();
	}
	
	/*
	 * This method must be implemented by the extending class, can call super()
	 */
	public void evaluateRules() {
		for(AbstractRule rule: ruleList) {
			rule.runRule();
		}
	}
	
	private void makeTrades() {
		/*
		 * loop through the rule results
		 * 
		 * if there is a buy trigger, buy it
		 * if there is a sell trigger, sell
		 * 
		 */
		setBuySellTriggers();
		
		//Initializing the trade
		Trade trade = new Trade();
		
		Set<Date> keys = buySellTriggers.keySet();
		for( Date key : keys ) {
			if(buySellTriggers.containsKey(key)) {
				BuySellTrigger trigger = buySellTriggers.get(key);
				
				if(trigger.getSell() && trade.getBuyDate() != null) {										//if today is a sell day and the buy date is set
					trade.sell(trigger.getDate(), ohlcvData.get(key).getClose() );										//set sell price
				} else if (trigger.getBuy() && trade.getBuyDate() == null && trade.getSellDate() == null) {	//if today is a buy day, and buy and sell dates are not set
					trade.buy(trigger.getDate(), ohlcvData.get(key).getClose() );
				}
				
				if(trade.getSellDate() != null) { //need the tradeList.size > 1 so it won't start for the 
					tradeList.add(trade);
					trade = new Trade();
				}
			}
		}
	}
	
	public void setBuySellTriggers() {
		//Initialize the buySellTriggers to the same length as the OHLCV data
		for(Map.Entry<Date, SecuritiesOhlcv> ohlcv: ohlcvData.entrySet()) {
			buySellTriggers.put(ohlcv.getKey(), new BuySellTrigger(ohlcv.getKey()));
		}
		
		for(AbstractRule rule: ruleList) {								//Loop through the rules
			SortedMap<Date, AbstractRuleResult> results = rule.getRuleResult();
			
			for(int i = 0; i < results.size(); i++)	{					//Loop through the results of the rule
				if(results.get(i).getRuleResult()) {							//If the result is true
					if(rule.getRuleType() == RuleType.BUY) {			//If the rule is a buy rule
						buySellTriggers.get(i).setBuy(Boolean.TRUE);
					} else if(rule.getRuleType() == RuleType.SELL) {
						buySellTriggers.get(i).setSell(Boolean.TRUE);
					}
				}
			}
		}
	}
	
	private void calculateDollarValue() {
		initializeValueList();
		
		int tradeIterator = 0;
		
		boolean tradeOpen = false; //false for no open trade, true if there is an open trade
		Trade currentTrade = tradeList.get(tradeIterator);
		
		for(int i = 0; i < ohlcvData.size(); i++)
		{
			SecuritiesOhlcv currentOhlcv = ohlcvData.get(i);
			if( currentTrade.getBuyDate().equals( currentOhlcv.getDate() ) ) {
				tradeOpen = true;
			} else if(currentTrade.getSellDate().equals( currentOhlcv.getDate() ) ) {
				tradeOpen = false;
			}
			
			
			
			if( i > 0 ) {
				DollarValue currentValue = valueMap.get(i);
				DollarValue previousValue = valueMap.get(i-1);
				
				if(tradeOpen) {
					SecuritiesOhlcv previousOhlcv = ohlcvData.get(i-1);
					
					double percentChange = currentOhlcv.getClose().subtract( previousOhlcv.getClose() ).doubleValue() / previousOhlcv.getClose().doubleValue();
					
					currentValue.setDollarValue( new BigDecimal( previousValue.getDollarValue().doubleValue() * percentChange ) );
				} else {
					currentValue.setDollarValue( previousValue.getDollarValue() );
				}
			}
		}
	}
	
	private void initializeValueList() {
		for(Map.Entry<Date, SecuritiesOhlcv> ohlcv: ohlcvData.entrySet()) {
			valueMap.put(ohlcv.getKey(), new DollarValue(ohlcv.getKey()));
		}
		
		valueMap.get(valueMap.firstKey()).setDollarValue(initialInvestment);
	}
	
	/*
	 * Helper Methods that may be overridden and call super(), but don't have to be
	 */
	public void calcStats() {
		/*
		 * Default stats calculated below:
		 * 1. 50 day close price average
		 * 2. 100 day close price average
		 * 3. 200 day close price average
		 * 4. 50 day volume average
		 */
		BigDecimal close50DayAvg;
		BigDecimal close100DayAvg;
		BigDecimal close200DayAvg;
		long vol50DayAvg;
		
		ArrayList<Date> keys = new ArrayList<Date>(ohlcvData.keySet());
		
		for(int i = keys.size() -1; i >= 0; i--) {
			int loopDays = 50;
			BigDecimal priceCloseSum = new BigDecimal(0.0);
			long volumeSum = 0;
			
			/*
			 * Loop for 50 days
			 * Calculates the 50 day moving average
			 * and calculates the 50 day moving volume average
			 */
			for(int j=i; j>i-loopDays && j >= 0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum = priceCloseSum.add(ohlcvData.get(keys.get(j)).getClose());

				//summing up for volumeAverage
				volumeSum+=ohlcvData.get(keys.get(j)).getVolume();
			}
			close50DayAvg = priceCloseSum.divide(new BigDecimal(loopDays));
			vol50DayAvg = volumeSum/loopDays;
			
			/*
			 * Loop for 100 days
			 * Calculates the 100 day moving average
			 */
			loopDays = 100;
			priceCloseSum = new BigDecimal(0);
			for(int j=i; j>i-loopDays && j>=0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum = priceCloseSum.add(ohlcvData.get(keys.get(j)).getClose());
			}
			close100DayAvg = priceCloseSum.divide(new BigDecimal(loopDays));
			
			/*
			 * Loop for 200 days
			 * Calculates the 200 day moving average
			 */
			loopDays = 200;
			priceCloseSum = new BigDecimal(0);
			for(int j=i; j>i-loopDays && j>=0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum = priceCloseSum.add(ohlcvData.get(keys.get(j)).getClose());
			}
			close200DayAvg = priceCloseSum.divide(new BigDecimal(loopDays));
			
			Stats stat = new Stats(ohlcvData.get(keys.get(i)).getLocalDate(), close50DayAvg, close100DayAvg, close200DayAvg, vol50DayAvg);
			defaultStats.put(keys.get(i), stat);
		}
	}

	/*
	 * Getters and Setters
	 */
	public Symbol getSymbol() {
		return symbol;
	}
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public ModelStatus getModelStatus() {
		return modelStatus;
	}
	public void setModelStatus(ModelStatus modelStatus) {
		this.modelStatus = modelStatus;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getInitialInvestment() {
		return initialInvestment;
	}
	public void setInitialInvestment(BigDecimal initialInvestment) {
		this.initialInvestment = initialInvestment;
	}

	public List<AbstractRule> getRuleList() {
		return ruleList;
	}
	public void setRuleList(List<AbstractRule> ruleList) {
		this.ruleList = ruleList;
	}
	
	/*
	public List<Trade> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<Trade> tradeList) {
		this.tradeList = tradeList;
	}

	public List<DollarValue> getValueList() {
		return valueList;
	}

	public void setValueList(List<DollarValue> valueList) {
		this.valueList = valueList;
	}
*/
	
	public SortedMap<Date, SecuritiesOhlcv> getOhlcvData() {
		return ohlcvData;
	}
	public void setOhlcvData(List<SecuritiesOhlcv> ohlcvData) {
		for(SecuritiesOhlcv ohlcv: ohlcvData) {
			this.ohlcvData.put(ohlcv.getDate(), ohlcv);
		}
	}
	public void setOhlcvData(SortedMap<Date, SecuritiesOhlcv> ohlcvData) {
		this.ohlcvData = ohlcvData;
	}

	public abstract <T> TreeMap<Date,T> getStats();
	public List<Trade> getTradeList() {
		return tradeList;
	}
	public void setTradeList(List<Trade> tradeList) {
		this.tradeList = tradeList;
	}
	public SortedMap<Date, DollarValue> getValueList() {
		return valueMap;
	}
	public void setValueMap(List<DollarValue> valueMap) {
		for(DollarValue value: valueMap) {
			this.valueMap.put(value.getDate(), value);
		}
	}
	public SortedMap<Date, DollarValue> getValueMap() {
		return valueMap;
	}
	public void setValueMap(SortedMap<Date,DollarValue> valueMap) {
		this.valueMap = valueMap;
	}
	public SortedSet<DollarValue> getValueSet() {
		return valueSet;
	}
	public void setValueSet(SortedSet<DollarValue> valueSet) {
		this.valueSet = valueSet;
	}
	
}

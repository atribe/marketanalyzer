package com.ar.marketanalyzer.backtest.models.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.backtest.models.enums.ModelStatus;
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
public abstract class AbstractModel extends PersistableEntityInt {
	
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
	
	@ManyToMany(mappedBy = "model", cascade = CascadeType.ALL)
	protected List<AbstractRule> buyRuleList;
	
	@ManyToMany(mappedBy = "model", cascade = CascadeType.ALL)
	protected List<AbstractRule> sellRuleList;
	
	/*
	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	protected List<Trade> tradeList;
	
	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	protected List<DollarValue> valueList;
	*/
	/*
	 * Not DB stored fields
	 */
		
	@Transient
	protected List<SecuritiesOhlcv> ohlcvData;
	@Transient
	protected List<Stats> defaultStats;

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

		assignRules();	// assigns the rules to this model, must be overridden by the child class
	}
	
	/*
	 * Helper Methods that do not need to be implemented
	 */

	/*
	 * Helper Methods that must be implemented by the extending class
	 */
	protected void assignRules() {
		
	}
	
	/*
	 * This method must be implemented by the extending class, can call super()
	 */
	
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
		
		defaultStats = new ArrayList<Stats>();
		
		for(int i=ohlcvData.size()-1; i>0; i--) {
			int loopDays = 50;
			BigDecimal priceCloseSum = new BigDecimal(0.0);
			long volumeSum = 0;
			
			/*
			 * Loop for 50 days
			 * Calculates the 50 day moving average
			 * and calculates the 50 day moving volume average
			 */
			for(int j=i; j>i-loopDays && j>0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum = priceCloseSum.add(ohlcvData.get(j).getClose());

				//summing up for volumeAverage
				volumeSum+=ohlcvData.get(j).getVolume();
			}
			close50DayAvg = priceCloseSum.divide(new BigDecimal(loopDays));
			vol50DayAvg = volumeSum/loopDays;
			
			/*
			 * Loop for 100 days
			 * Calculates the 100 day moving average
			 */
			loopDays = 100;
			priceCloseSum = new BigDecimal(0);
			for(int j=i; j>i-loopDays && j>0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum = priceCloseSum.add(ohlcvData.get(j).getClose());
			}
			close100DayAvg = priceCloseSum.divide(new BigDecimal(loopDays));
			
			/*
			 * Loop for 200 days
			 * Calculates the 200 day moving average
			 */
			loopDays = 200;
			priceCloseSum = new BigDecimal(0);
			for(int j=i; j>i-loopDays && j>0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum = priceCloseSum.add(ohlcvData.get(j).getClose());
			}
			close200DayAvg = priceCloseSum.divide(new BigDecimal(loopDays));
			
			Stats stat = new Stats(ohlcvData.get(i).getLocalDate(), close50DayAvg, close100DayAvg, close200DayAvg, vol50DayAvg);
			defaultStats.add(stat);
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

	public List<AbstractRule> getBuyRuleList() {
		return buyRuleList;
	}
	public void setBuyRuleList(List<AbstractRule> buyRuleList) {
		this.buyRuleList = buyRuleList;
	}
	public List<AbstractRule> getSellRuleList() {
		return sellRuleList;
	}
	public void setSellRuleList(List<AbstractRule> sellRuleList) {
		this.sellRuleList = sellRuleList;
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
	
	public List<SecuritiesOhlcv> getOhlcvData() {
		return ohlcvData;
	}
	public void setOhlcvData(List<SecuritiesOhlcv> ohlcvData) {
		this.ohlcvData = ohlcvData;
	}

	
	protected void evaluateRules() {
		// TODO Auto-generated method stub
		
	}
}

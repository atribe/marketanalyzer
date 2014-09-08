package com.ar.marketanalyzer.backtest.models.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.LocalDate;

import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

@Entity
@Inheritance
@DiscriminatorColumn(name="MODEL_NAME") //http://en.wikibooks.org/wiki/Java_Persistence/Inheritance#Single_Table_Inheritance
@Table(name="backtest_model")
public abstract class AbstractModel extends PersistableEntityInt {

	public enum modelStatus { CURRENT, PREVIOUS }
	
	private static final long serialVersionUID = 5829380092032471186L;

	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="symbol_id", referencedColumnName="id")
	protected Symbol symbol;
	
	@Enumerated(EnumType.STRING)
	@Column( name="model_status", nullable=false)
	protected modelStatus modelStatus;
	
	@Column( name="start_date", nullable=false)
	protected Date startDate;
	
	@Column( name="end_date", nullable=false)
	protected Date endDate;
	
	@Column( name="initial_investment")
	protected BigDecimal initialInvestment;
	/*
	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	protected List<AbstractRule> ruleList;
	
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

	protected final static int modelMonthRange = 60;
	protected final BigDecimal defaultInitialInvestment = new BigDecimal(1000);
	protected final static Date defaultStartDate = new Date( new LocalDate().minusMonths(modelMonthRange).toDateTimeAtStartOfDay().getMillis() );
	protected final static Date defaultEndDate = new Date( new LocalDate().toDateTimeAtStartOfDay().getMillis() );
	/*
	 * Constructors
	 */
	public AbstractModel() {
	}
	public AbstractModel(Symbol symbol) {
		this(symbol, defaultStartDate);
	}
	public AbstractModel(Symbol symbol, Date startDate) {
		this(symbol, defaultStartDate, defaultEndDate);
	}

	public AbstractModel(Symbol symbol, Date startDate, Date endDate) {
		this.symbol = symbol;
		this.startDate = startDate;
		this.endDate = endDate;
		this.initialInvestment = defaultInitialInvestment;
		this.modelStatus = modelStatus.CURRENT;
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

	public modelStatus getModelStatus() {
		return modelStatus;
	}
	public void setModelStatus(modelStatus modelStatus) {
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
/*
	public List<AbstractRule> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<AbstractRule> ruleList) {
		this.ruleList = ruleList;
	}

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
}

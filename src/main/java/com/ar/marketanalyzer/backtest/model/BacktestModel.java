package com.ar.marketanalyzer.backtest.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import com.ar.marketanalyzer.indexbacktest.beans.BacktestBean;
import com.ar.marketanalyzer.securities.models.Symbol;
import com.ar.marketanalyzer.securities.models.parents.PersistableEntityInt;

@Entity
@Table(name = "backtest_model")
public class BacktestModel extends PersistableEntityInt {

	public static enum modelTypeEnum {
		BASE(0),
		CURRENT(1),
		PREVIOUS(2);
		
		private final int value;
		modelTypeEnum(int value) {this.value = value; }
		public int getValue() {return value;}
	}
	
	private static final long serialVersionUID = 14323523542L;

	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="symbol_id", referencedColumnName="id")
	private Symbol symbol;
	//Parameters
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	@Enumerated(EnumType.ORDINAL)
	private modelTypeEnum modelType;
	@Column
	private int dDayWindow;
	@Column
	private int dDayParam;
	@Column
	private double dDayPriceDrop;
	@Column
	private double churnVolRange;
	@Column
	private double churnPriceRange;
	@Column
	private Boolean churnPriceCloseHigherOn;
	@Column
	private Boolean churnAVG50On;
	@Column
	private Boolean churnPriceTrend35On;
	@Column
	private double churnPriceTrend35;
	@Column
	private Boolean volVolatilityOn;
	@Column
	private double volumeMult;
	@Column
	private double volMultTop;
	@Column
	private double volMultBot;
	@Column
	private Boolean priceVolatilityOn;
	@Column
	private double priceMult;
	@Column
	private double priceMultTop;
	@Column
	private double priceMultBot;
	@Column
	private int rDaysMin;
	@Column
	private int rDaysMax;
	@Column
	private Boolean pivotTrend35On;
	@Column
	private double pivotTrend35;
	@Column
	private Boolean rallyVolAVG50On;
	@Column
	private Boolean rallyPriceHighOn;
	
	//Results
	@Column
	private double totalPercentReturn;
	@Column
	private BigDecimal costBasis;
	@Column
	private BigDecimal finalValue;
	@Column
	private int numberOfTrades;
	@Column
	private int numberOfProfitableTrades;
	
	public boolean equals(Object o) {
		if(!(o instanceof BacktestModel)) {
			return false;
		}
		BacktestModel b = (BacktestModel) o;
		
		if( b.churnAVG50On.equals(this.churnAVG50On) &&
			b.churnPriceCloseHigherOn.equals(this.churnPriceCloseHigherOn)	&&
			b.churnPriceTrend35On.equals(this.churnPriceTrend35On) && 
			b.pivotTrend35On.equals(this.pivotTrend35On) &&
			b.priceVolatilityOn.equals(this.priceVolatilityOn) &&
			b.rallyPriceHighOn.equals(this.rallyPriceHighOn) &&
			b.rallyVolAVG50On.equals(this.rallyVolAVG50On) &&
			b.volVolatilityOn.equals(this.volVolatilityOn) &&
			b.churnPriceRange == this.churnPriceRange &&
			b.churnPriceTrend35 == this.churnPriceTrend35 &&
			b.churnVolRange == this.churnVolRange &&
			b.dDayParam == this.dDayParam &&
			b.dDayPriceDrop == this.dDayPriceDrop &&
			b.dDayWindow == this.dDayWindow &&
			b.endDate.equals(this.endDate) &&
			b.pivotTrend35 == this.pivotTrend35 &&
			b.priceMult == this.priceMult &&
			b.priceMultBot == this.priceMultBot &&
			b.priceMultTop == this.priceMultTop &&
			b.rDaysMax == this.rDaysMax &&
			b.rDaysMin == this.rDaysMin &&
			b.startDate.equals(this.startDate) &&
			b.volMultBot == this.volMultBot &&
			b.volMultTop == this.volMultTop &&
			b.volumeMult == this.volumeMult ) {
			return true;
		} else {
			return false;
		}	
	}
	
	public Symbol getSymbol() {
		return symbol;
	}
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = new Date(startDate.toDateTimeAtStartOfDay().getMillis());
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = new Date(endDate.toDateTimeAtStartOfDay().getMillis());
	}
	public modelTypeEnum getModelType() {
		return modelType;
	}
	public void setModelType(modelTypeEnum modelType) {
		this.modelType = modelType;
	}
	public int getdDayWindow() {
		return dDayWindow;
	}
	public void setdDayWindow(int dDayWindow) {
		this.dDayWindow = dDayWindow;
	}
	public int getdDayParam() {
		return dDayParam;
	}
	public void setdDayParam(int dDayParam) {
		this.dDayParam = dDayParam;
	}
	public double getdDayPriceDrop() {
		return dDayPriceDrop;
	}
	public void setdDayPriceDrop(double dDayPriceDrop) {
		this.dDayPriceDrop = dDayPriceDrop;
	}
	public double getChurnVolRange() {
		return churnVolRange;
	}
	public void setChurnVolRange(double churnVolRange) {
		this.churnVolRange = churnVolRange;
	}
	public double getChurnPriceRange() {
		return churnPriceRange;
	}
	public void setChurnPriceRange(double churnPriceRange) {
		this.churnPriceRange = churnPriceRange;
	}
	public Boolean getChurnPriceCloseHigherOn() {
		return churnPriceCloseHigherOn;
	}
	public void setChurnPriceCloseHigherOn(Boolean churnPriceCloseHigherOn) {
		this.churnPriceCloseHigherOn = churnPriceCloseHigherOn;
	}
	public Boolean getChurnAVG50On() {
		return churnAVG50On;
	}
	public void setChurnAVG50On(Boolean churnAVG50On) {
		this.churnAVG50On = churnAVG50On;
	}
	public Boolean getChurnPriceTrend35On() {
		return churnPriceTrend35On;
	}
	public void setChurnPriceTrend35On(Boolean churnPriceTrend35On) {
		this.churnPriceTrend35On = churnPriceTrend35On;
	}
	public double getChurnPriceTrend35() {
		return churnPriceTrend35;
	}
	public void setChurnPriceTrend35(double churnPriceTrend35) {
		this.churnPriceTrend35 = churnPriceTrend35;
	}
	public Boolean getVolVolatilityOn() {
		return volVolatilityOn;
	}
	public void setVolVolatilityOn(Boolean volVolatilityOn) {
		this.volVolatilityOn = volVolatilityOn;
	}
	public double getVolumeMult() {
		return volumeMult;
	}
	public void setVolumeMult(double volumeMult) {
		this.volumeMult = volumeMult;
	}
	public double getVolMultTop() {
		return volMultTop;
	}
	public void setVolMultTop(double volMultTop) {
		this.volMultTop = volMultTop;
	}
	public double getVolMultBot() {
		return volMultBot;
	}
	public void setVolMultBot(double volMultBot) {
		this.volMultBot = volMultBot;
	}
	public Boolean getPriceVolatilityOn() {
		return priceVolatilityOn;
	}
	public void setPriceVolatilityOn(Boolean priceVolatilityOn) {
		this.priceVolatilityOn = priceVolatilityOn;
	}
	public double getPriceMult() {
		return priceMult;
	}
	public void setPriceMult(double priceMult) {
		this.priceMult = priceMult;
	}
	public double getPriceMultTop() {
		return priceMultTop;
	}
	public void setPriceMultTop(double priceMultTop) {
		this.priceMultTop = priceMultTop;
	}
	public double getPriceMultBot() {
		return priceMultBot;
	}
	public void setPriceMultBot(double priceMultBot) {
		this.priceMultBot = priceMultBot;
	}
	public int getrDaysMin() {
		return rDaysMin;
	}
	public void setrDaysMin(int rDaysMin) {
		this.rDaysMin = rDaysMin;
	}
	public int getrDaysMax() {
		return rDaysMax;
	}
	public void setrDaysMax(int rDaysMax) {
		this.rDaysMax = rDaysMax;
	}
	public Boolean getPivotTrend35On() {
		return pivotTrend35On;
	}
	public void setPivotTrend35On(Boolean pivotTrend35On) {
		this.pivotTrend35On = pivotTrend35On;
	}
	public double getPivotTrend35() {
		return pivotTrend35;
	}
	public void setPivotTrend35(double pivotTrend35) {
		this.pivotTrend35 = pivotTrend35;
	}
	public Boolean getRallyVolAVG50On() {
		return rallyVolAVG50On;
	}
	public void setRallyVolAVG50On(Boolean rallyVolAVG50On) {
		this.rallyVolAVG50On = rallyVolAVG50On;
	}
	public Boolean getRallyPriceHighOn() {
		return rallyPriceHighOn;
	}
	public void setRallyPriceHighOn(Boolean rallyPriceHighOn) {
		this.rallyPriceHighOn = rallyPriceHighOn;
	}
	public double getTotalPercentReturn() {
		return totalPercentReturn;
	}
	public void setTotalPercentReturn(double totalPercentReturn) {
		this.totalPercentReturn = totalPercentReturn;
	}
	public BigDecimal getCostBasis() {
		return costBasis;
	}
	public void setCostBasis(BigDecimal costBasis) {
		this.costBasis = costBasis;
	}
	public BigDecimal getFinalValue() {
		return finalValue;
	}
	public void setFinalValue(BigDecimal finalValue) {
		this.finalValue = finalValue;
	}
	public int getNumberOfTrades() {
		return numberOfTrades;
	}
	public void setNumberOfTrades(int numberOfTrades) {
		this.numberOfTrades = numberOfTrades;
	}
	public int getNumberOfProfitableTrades() {
		return numberOfProfitableTrades;
	}
	public void setNumberOfProfitableTrades(int numberOfProfitableTrades) {
		this.numberOfProfitableTrades = numberOfProfitableTrades;
	}
}

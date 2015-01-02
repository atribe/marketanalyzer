package com.ar.marketanalyzer.ibd50.beans;

import java.math.BigDecimal;
import java.util.List;

public class Ibd50CustomIndexBean {
	
	private long customIndex_id;
	private List<String> indexSymbols;
	private int numberOfStocksInIndex;
	private BigDecimal weekStartValue;
	private BigDecimal weekEndValue;
	
	public long getCustomIndex_id() {
		return customIndex_id;
	}
	public void setCustomIndex_id(long customIndex_id) {
		this.customIndex_id = customIndex_id;
	}
	public List<String> getIndexSymbols() {
		return indexSymbols;
	}
	public void setIndexSymbols(List<String> indexSymbols) {
		this.indexSymbols = indexSymbols;
	}
	public int getNumberOfStocksInIndex() {
		return numberOfStocksInIndex;
	}
	public void setNumberOfStocksInIndex(int numberOfStocksInIndex) {
		this.numberOfStocksInIndex = numberOfStocksInIndex;
	}
	public BigDecimal getWeekStartValue() {
		return weekStartValue;
	}
	public void setWeekStartValue(BigDecimal weekStartValue) {
		this.weekStartValue = weekStartValue;
	}
	public BigDecimal getWeekEndValue() {
		return weekEndValue;
	}
	public void setWeekEndValue(BigDecimal weekEndValue) {
		this.weekEndValue = weekEndValue;
	}
	
}

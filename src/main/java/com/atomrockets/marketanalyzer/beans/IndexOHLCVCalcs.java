package com.atomrockets.marketanalyzer.beans;

import java.math.BigDecimal;
import java.sql.Date;

import org.joda.time.LocalDate;

import com.atomrockets.marketanalyzer.beans.interfaces.OHLCVInterface;

public class IndexOHLCVCalcs extends IndexCalcs implements OHLCVInterface{
	
	//Data from the OHLCVData bean
	private String symbol;
	private Date date;
	private LocalDate convertedDate;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;
	private long volume;
	private BigDecimal adjClose;

	//add more stuff as needed here

	public IndexOHLCVCalcs() {
		distributionDayCounter=0;
	}

	/*
	 * Helper functions
	 */
	public void addDDayCounter() {
		setDistributionDayCounter(getDistributionDayCounter() + 1);
	}
	
	/*
	 * Getters and Setters
	 */	
	@Override
	public String getSymbol() {
		return symbol;
	}
	@Override
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public Date getDate() {
		return date;
	}
	@Override
	public void setDate(Date date) {
		this.date = date;
		setConvertedDate(date);
	}

	public LocalDate getConvertedDate() {
		return convertedDate;
	}
	public void setConvertedDate(LocalDate date) {
		this.convertedDate = date;
		this.date=new java.sql.Date(convertedDate.toDate().getTime());
	}
	public void setConvertedDate(java.sql.Date date) {
		this.convertedDate = new LocalDate(date);
		this.date=date;
	}
	public void setConvertedDate(String date) {
		this.convertedDate = new LocalDate(date);
		this.date=new java.sql.Date(convertedDate.toDate().getTime());
	}
	
	@Override
	public BigDecimal getOpen() {
		return open;
	}
	@Override
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	
	@Override
	public BigDecimal getHigh() {
		return high;
	}
	@Override
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	
	@Override
	public BigDecimal getLow() {
		return low;
	}
	@Override
	public void setLow(BigDecimal low) {
		this.low = low;
	}

	
	@Override
	public BigDecimal getClose() {
		return close;
	}
	@Override
	public void setClose(BigDecimal close) {
		this.close = close;
	}
	
	@Override
	public long getVolume() {
		return volume;
	}
	@Override
	public void setVolume(long volume) {
		this.volume = volume;
	}
	@Override
	public void setVolume(double volume) {
		this.volume = (long)volume;
	}
	

	@Override
	public BigDecimal getAdjClose() {
		return this.adjClose;
	}
	@Override
	public void setAdjClose(BigDecimal adjClose) {
		this.adjClose = adjClose;
	}
}

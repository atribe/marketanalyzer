package com.atomrockets.marketanalyzer.beans;

import java.sql.Date;

import org.joda.time.LocalDate;

import com.atomrockets.marketanalyzer.beans.interfaces.OHLCVInterface;

public class IndexOHLCVCalcs extends IndexCalcs implements OHLCVInterface{
	
	//Data from the OHLCVData bean
	private String symbol;
	private Date date;
	private LocalDate convertedDate;
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;
	private double adjClose;

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
	public double getOpen() {
		return open;
	}
	@Override
	public void setOpen(double open) {
		this.open = open;
	}
	
	@Override
	public double getHigh() {
		return high;
	}
	@Override
	public void setHigh(double high) {
		this.high = high;
	}
	
	@Override
	public double getLow() {
		return low;
	}
	@Override
	public void setLow(double low) {
		this.low = low;
	}

	
	@Override
	public double getClose() {
		return close;
	}
	@Override
	public void setClose(double close) {
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
	public double getAdjClose() {
		return this.adjClose;
	}
	@Override
	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}
}
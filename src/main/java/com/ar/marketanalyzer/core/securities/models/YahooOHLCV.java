package com.ar.marketanalyzer.core.securities.models;

public class YahooOHLCV {
	private long id;
	private String symbol;
	private String date;
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;
	private double adjClose;
	
	/*
	 * Getters and Setters
	 */
	public YahooOHLCV() { }
	
	@Override
	public String toString() {
		return "Date:" + date + " Open: " + open + " High:" + high + " Low:" + low + " Close:" + close + " Volume:" + volume + " Symbol:" +symbol;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public double getAdjClose() {
		return adjClose;
	}
	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}
}

package com.ar.marketanalyzer.backtest.models;

import java.sql.Date;

public class BuySellTrigger {

	private Date date;
	
	private Boolean sell;
	
	private Boolean buy;
	
	/*
	 * Constructors
	 */
	public BuySellTrigger() {
		sell = false;
		buy = false;
	}
	public BuySellTrigger(Date date) {	
		this.date = date;
		sell = false;
		buy = false;
	}
	
	@Override
	public String toString() {
		return date.toString() + " Buy:" + buy.toString() + " Sell:" + sell.toString();
	}
	
	/*
	 * Getters and Setters
	 */
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Boolean getSell() {
		return sell;
	}
	public void setSell(Boolean sell) {
		this.sell = sell;
	}
	public Boolean getBuy() {
		return buy;
	}
	public void setBuy(Boolean buy) {
		this.buy = buy;
	}
}

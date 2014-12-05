package com.ar.marketanalyzer.backtest.models.stats;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

public class Stats {

	protected LocalDate date;
	protected BigDecimal close50DayAvg;
	protected BigDecimal close100DayAvg;
	protected BigDecimal close200DayAvg;
	protected long vol50DayAvg;
	
	/*
	 * Constructors
	 */
	public Stats() {
		
	}
	public Stats(LocalDate date, BigDecimal close50DayAvg, BigDecimal close100DayAvg, BigDecimal close200DayAvg, long vol50DayAvg) {
		this.date = date;
		this.close50DayAvg = close50DayAvg;
		this.close100DayAvg = close100DayAvg;
		this.close200DayAvg = close200DayAvg;
		this.vol50DayAvg = vol50DayAvg;
	}
	
	/*
	 * Helper Methods
	 */
	@Override
	public String toString() {
		return "Date: " + date.toString() +
				" 50Day: " + close50DayAvg.toString() + 
				" 100Day: " + close100DayAvg.toString() +
				" 200Day: " + close200DayAvg.toString() +
				" vol50Day: " + vol50DayAvg;
	}
	/*
	 * Getters and Setters
	 */
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public BigDecimal getClose50DayAvg() {
		return close50DayAvg;
	}
	public void setClose50DayAvg(BigDecimal close50DayAvg) {
		this.close50DayAvg = close50DayAvg;
	}
	
	public BigDecimal getClose100DayAvg() {
		return close100DayAvg;
	}
	public void setClose100DayAvg(BigDecimal close100DayAvg) {
		this.close100DayAvg = close100DayAvg;
	}
	
	public BigDecimal getClose200DayAvg() {
		return close200DayAvg;
	}
	public void setClose200DayAvg(BigDecimal close200DayAvg) {
		this.close200DayAvg = close200DayAvg;
	}
	
	public long getVol50DayAvg() {
		return vol50DayAvg;
	}
	public void setVol50DayAvg(long vol50DayAvg) {
		this.vol50DayAvg = vol50DayAvg;
	}
}

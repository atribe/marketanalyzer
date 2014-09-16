package com.ar.marketanalyzer.backtest.models.stats;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

public class FollowThruStats extends Stats{

	private double priceTrend35;

	/*
	 * Constructors
	 */
	public FollowThruStats() {
		super();
	}
	public FollowThruStats(LocalDate date, BigDecimal close50DayAvg, BigDecimal close100DayAvg, BigDecimal close200DayAvg, long vol50DayAvg) {
		super(date, close50DayAvg, close100DayAvg, close200DayAvg, vol50DayAvg);
	}
	public FollowThruStats(Stats s) {
		super(s.getDate(), s.getClose50DayAvg(), s.getClose100DayAvg(), s.getClose200DayAvg(), s.getVol50DayAvg());
	}
	/*
	 * I don't think this constructor is needed
	public FollowThruStats(LocalDate date, BigDecimal close50DayAvg, BigDecimal close100DayAvg, BigDecimal close200DayAvg, long vol50DayAvg, double priceTrend35) {
		super(date, close50DayAvg, close100DayAvg, close200DayAvg, vol50DayAvg);
		this.priceTrend35 = priceTrend35;
	}
	*/
	
	/*
	 * Helper Methods
	 */
	public static List<FollowThruStats> convertStatList(List<Stats> defaultStats) {
		List<FollowThruStats> ftsList = new ArrayList<FollowThruStats>();
		
		for(Stats s: defaultStats) {
			FollowThruStats fts = new FollowThruStats(s);
			ftsList.add(fts);
		}
		return ftsList;
	}
	
	
	/*
	 * Getters and Setters
	 */
	public double getPriceTrend35() {
		return priceTrend35;
	}
	public void setPriceTrend35(double priceTrend35) {
		this.priceTrend35 = priceTrend35;
	}
	
}

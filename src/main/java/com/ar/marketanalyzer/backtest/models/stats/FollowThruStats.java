package com.ar.marketanalyzer.backtest.models.stats;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class FollowThruStats extends Stats {

    private double priceTrend35;

    /*
     * Constructors
     */
    public FollowThruStats() {
        super();
    }

    public FollowThruStats(LocalDateTime date,
                           BigDecimal close50DayAvg,
                           BigDecimal close100DayAvg,
                           BigDecimal close200DayAvg,
                           long vol50DayAvg) {
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
    public static SortedMap<LocalDateTime, FollowThruStats> convertStatList(SortedMap<LocalDateTime, Stats> defaultStats) {
        SortedMap<LocalDateTime, FollowThruStats> ftsList = new TreeMap<>();

        for (Map.Entry<LocalDateTime, Stats> entry : defaultStats.entrySet()) {
            FollowThruStats fts = new FollowThruStats(entry.getValue());
            ftsList.put(entry.getKey(), fts);
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

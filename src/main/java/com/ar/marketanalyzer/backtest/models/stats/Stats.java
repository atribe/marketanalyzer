package com.ar.marketanalyzer.backtest.models.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stats {

	protected LocalDate date;
	protected BigDecimal close50DayAvg;
	protected BigDecimal close100DayAvg;
	protected BigDecimal close200DayAvg;
	protected long vol50DayAvg;
	
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
}

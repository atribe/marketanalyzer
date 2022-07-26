package com.ar.marketanalyzer.backtest.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BuySellTrigger {

	private LocalDateTime date;
	
	private boolean sell;

	private boolean buy;

	public BuySellTrigger(LocalDateTime date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return date.toString() + " Buy:" + buy + " Sell:" + sell;
	}
}

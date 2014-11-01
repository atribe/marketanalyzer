package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum Period {
	fff("fff"),
	ss("ss"),
	mm("mm"),
	hh("hh"),
	DD("DD"),
	WW("WW"),
	MM("MM"),
	YYYY("YYYY");
	
	private final String period;
	
	Period(String period) {
		this.period = period;
	}
	
	@Override
	public String toString() {
		return period;
	}
	
}

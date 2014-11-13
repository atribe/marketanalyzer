package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum PeriodEnum {
	fff("fff"),
	ss("ss"),
	mm("mm"),
	hh("hh"),
	DD("DD"),
	WW("WW"),
	MM("MM"),
	YYYY("YYYY"),
	YTD("YTD"),
	MAX("MAX");
	
	private final String period;
	
	PeriodEnum(String period) {
		this.period = period;
	}
	
	@Override
	public String toString() {
		return period;
	}
	
}

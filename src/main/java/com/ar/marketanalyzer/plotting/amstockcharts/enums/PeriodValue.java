package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum PeriodValue {
	OPEN("Open"),
	LOW("Low"),
	HIGT("High"),
	CLOSE("Close"),
	AVERAGE("Average"),
	SUM("Sum");
	
	private final String periodValue;
	
	PeriodValue(String periodValue) {
		this.periodValue = periodValue;
	}
	
	@Override
	public String toString(){
		return periodValue;
	}	
}

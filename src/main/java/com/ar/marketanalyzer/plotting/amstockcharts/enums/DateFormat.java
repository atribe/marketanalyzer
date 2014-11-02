package com.ar.marketanalyzer.plotting.amstockcharts.enums;


public class DateFormat {
	private PeriodEnum period;
	private String format;
	
	public DateFormat() {
		
	}
	public DateFormat(PeriodEnum period, String format) {
		this.period = period;
		this.format = format;
	}
	
	public PeriodEnum getPeriod() {
		return period;
	}
	public void setPeriod(PeriodEnum period) {
		this.period = period;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
}

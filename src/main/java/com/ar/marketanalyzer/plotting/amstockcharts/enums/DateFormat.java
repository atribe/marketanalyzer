package com.ar.marketanalyzer.plotting.amstockcharts.enums;


public class DateFormat {
	private Period period;
	private String format;
	
	public DateFormat() {
		
	}
	public DateFormat(Period period, String format) {
		this.period = period;
		this.format = format;
	}
	
	public Period getPeriod() {
		return period;
	}
	public void setPeriod(Period period) {
		this.period = period;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
}

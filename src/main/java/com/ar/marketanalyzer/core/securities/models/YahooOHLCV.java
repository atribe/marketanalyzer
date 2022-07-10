package com.ar.marketanalyzer.core.securities.models;

import lombok.Data;

@Data
public class YahooOHLCV {
	private long id;
	private String symbol;
	private String date;
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;
	private double adjClose;

	@Override
	public String toString() {
		return "Date:" + date + " Open: " + open + " High:" + high + " Low:" + low + " Close:" + close + " Volume:" + volume + " Symbol:" +symbol;
	}
}

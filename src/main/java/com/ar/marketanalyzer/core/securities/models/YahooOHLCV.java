package com.ar.marketanalyzer.core.securities.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByNames;
import lombok.Data;

@Data
public class YahooOHLCV {
	private long id;

	private String symbol;

	@CsvBindByName
	private String date;

	@CsvBindByName
	private double open;

	@CsvBindByName
	private double high;

	@CsvBindByName
	private double low;

	@CsvBindByName
	private double close;

	@CsvBindByName
	private long volume;

	@CsvBindByName
	private double adjClose;

	@Override
	public String toString() {
		return "Date:" + date + " Open: " + open + " High:" + high + " Low:" + low + " Close:" + close + " Volume:" + volume + " Symbol:" +symbol;
	}
}

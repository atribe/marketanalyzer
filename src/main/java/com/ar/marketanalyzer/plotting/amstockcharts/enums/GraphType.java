package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum GraphType {
	LINE("line"),
	COLUMN("column"),
	STEP("step"),
	SMOOTHEDLINE("smoothedLine"),
	CANDLESTICK("candlestick"),
	OHLC("ohlc");
	
	private final String graphType;
	
	GraphType(String graphType) {
		this.graphType = graphType;
	}
	
	@Override
	public String toString(){
		return graphType;
	}
}

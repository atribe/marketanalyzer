package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum GraphType {
	line("line"),
	column("column"),
	step("step"),
	smoothedline("smoothedLine"),
	candlestick("candlestick"),
	ohlc("ohlc");
	
	private final String graphType;
	
	GraphType(String graphType) {
		this.graphType = graphType;
	}
	
	@Override
	public String toString(){
		return graphType;
	}
}

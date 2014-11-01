package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum ChartTypeAm {
	SERIAL("serial"),
	PIE("pie"),
	XY("xy"),
	RADAR("radar"),
	FUNNEL("funnel"),
	GAUGE("gauge"),
	MAP("map"),
	STOCK("stock");
	
	
	private final String chartTypeAm;
	
	ChartTypeAm(String chartTypeAm) {
		this.chartTypeAm = chartTypeAm;
	}
	
	@Override
	public String toString(){
		return chartTypeAm;
	}
}

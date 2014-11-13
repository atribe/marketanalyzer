package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum ChartTypeAm {
	serial("serial"),
	pie("pie"),
	xy("xy"),
	radar("radar"),
	funnel("funnel"),
	gauge("gauge"),
	map("map"),
	stock("stock");
	
	
	private final String chartTypeAm;
	
	ChartTypeAm(String chartTypeAm) {
		this.chartTypeAm = chartTypeAm;
	}
	
	@Override
	public String toString(){
		return chartTypeAm;
	}
}

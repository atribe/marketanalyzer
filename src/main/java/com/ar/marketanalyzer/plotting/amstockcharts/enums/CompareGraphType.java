package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum CompareGraphType {
	LINE("line"),
	COLUMN("column"),
	STEP("step"),
	SMOOTHEDLINE("smoothedLine");
	
	private final String graphType;
	
	CompareGraphType(String graphType) {
		this.graphType = graphType;
	}
	
	@Override
	public String toString(){
		return graphType;
	}
}

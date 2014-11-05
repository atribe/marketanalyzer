package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum CompareGraphType {
	line("line"),
	column("column"),
	step("step"),
	smoothedline("smoothedLine");
	
	private final String graphType;
	
	CompareGraphType(String graphType) {
		this.graphType = graphType;
	}
	
	@Override
	public String toString(){
		return graphType;
	}
}

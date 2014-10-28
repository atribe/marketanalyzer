package com.ar.marketanalyzer.plotting.amcharts.enums;

public enum PositionHorizontal {
	LEFT("left"),
	RIGHT("right");
	
	private final String positionHorizontal;
	
	PositionHorizontal(String positionHorizontal) {
		this.positionHorizontal = positionHorizontal;
	}
	
	@Override
	public String toString() {
		return positionHorizontal;
	}
}

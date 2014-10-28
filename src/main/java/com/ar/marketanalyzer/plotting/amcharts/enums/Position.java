package com.ar.marketanalyzer.plotting.amcharts.enums;

public enum Position {
	BOTTOM("bottom"),
	LEFT("left"),
	TOP("top"),
	RIGHT("right");
	
	private final String position;
	
	Position(String position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return position;
	}
}

package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum Position {
	bottom("bottom"),
	left("left"),
	top("top"),
	right("right");
	
	private final String position;
	
	Position(String position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return position;
	}
}

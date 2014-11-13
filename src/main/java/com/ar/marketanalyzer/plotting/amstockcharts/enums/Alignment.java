package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum Alignment {
	left("left"),
	center("center"),
	right("right");
	
	private final String alignment;
	
	Alignment(String alignment) {
		this.alignment = alignment;
	}
	
	@Override
	public String toString() {
		return alignment;
	}
}

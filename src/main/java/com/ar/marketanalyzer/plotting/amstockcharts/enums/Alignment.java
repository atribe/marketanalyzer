package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum Alignment {
	LEFT("left"),
	CENTER("center"),
	RIGHT("right");
	
	private final String alignment;
	
	Alignment(String alignment) {
		this.alignment = alignment;
	}
	
	@Override
	public String toString() {
		return alignment;
	}
}

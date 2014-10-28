package com.ar.marketanalyzer.plotting.amcharts.enums;

public enum TextAlignment {
	LEFT("left"),
	MIDDLE("middle"),
	RIGHT("right");
	
	private final String textAlignment;
	
	TextAlignment(String textAlignment) {
		this.textAlignment = textAlignment;
	}
	
	@Override
	public String toString() {
		return textAlignment;
	}
}

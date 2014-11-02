package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum AmTheme {
	NONE("none"),
	PATTERNS("patterns"),
	CHALK("chalk"),
	DARK("dark"),
	LIGHT("light");
	
	private final String amTheme;
	
	AmTheme(String amTheme) {
		this.amTheme = amTheme;
	}
	
	@Override
	public String toString() {
		return amTheme;
	}
}

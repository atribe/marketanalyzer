package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum AmTheme {
	home("none"),
	patterns("patterns"),
	chalk("chalk"),
	dark("dark"),
	light("light");
	
	private final String amTheme;
	
	AmTheme(String amTheme) {
		this.amTheme = amTheme;
	}
	
	@Override
	public String toString() {
		return amTheme;
	}
}

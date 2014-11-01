package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum CreditsPosition {
	TOP_LEFT("top-left"),
	TOP_RIGHT("top-right"),
	BOTTOM_LEFT("bottom-left"),
	BOTTOM_RIGHT("bottom-right");
	
	private final String creditsPosition;
	
	CreditsPosition(String creditsPosition) {
		this.creditsPosition = creditsPosition;
	}
	
	@Override
	public String toString() {
		return creditsPosition;
	}
}

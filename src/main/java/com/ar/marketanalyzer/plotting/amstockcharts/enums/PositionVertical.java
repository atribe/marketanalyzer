package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum PositionVertical {
	TOP("top"),
	BOTTOM("bottom");
	
	private final String positionVertical;
	
	PositionVertical(String positionVertical) {
		this.positionVertical = positionVertical;
	}
	
	@Override
	public String toString() {
		return positionVertical;
	}
}

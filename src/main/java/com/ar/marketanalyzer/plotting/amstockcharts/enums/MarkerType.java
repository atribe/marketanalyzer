package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum MarkerType {
	SQUARE("square"),
	CIRCLE("circle"),
	DIAMOND("diamond"),
	TRIANGLE_UP("triangleUp"),
	TRIANGLE_DOWN("triangleDown"),
	TRIANGLE_LEFT("triangleLeft"),
	TRIANGLE_RIGHT("triangleRight"),
	BUBBLE("bubble"),
	LINE("line"),
	NONE("none");
	
	private final String markerType;
	
	MarkerType(String markerType) {
		this.markerType = markerType;
	}
	
	@Override
	public String toString() {
		return markerType;
	}
}

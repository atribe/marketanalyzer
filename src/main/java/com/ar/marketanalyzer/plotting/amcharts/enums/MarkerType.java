package com.ar.marketanalyzer.plotting.amcharts.enums;

public enum MarkerType {
	SQUARE("square"),
	CIRCLE("circle"),
	DIAMOND("diamond"),
	TRIANGLEUP("triangleUp"),
	TRIANGLEDOWN("triangleDown"),
	TRIANGLELEFT("triangleLeft"),
	TRIANGLERIGHT("triangleRight"),
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

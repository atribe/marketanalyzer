package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum MarkerType {
	square("square"),
	circle("circle"),
	diamond("diamond"),
	triangleUp("triangleUp"),
	triangleDown("triangleDown"),
	triangleLeft("triangleLeft"),
	triangleRight("triangleRight"),
	bubble("bubble"),
	line("line"),
	none("none");
	
	private final String markerType;
	
	MarkerType(String markerType) {
		this.markerType = markerType;
	}
	
	@Override
	public String toString() {
		return markerType;
	}
}

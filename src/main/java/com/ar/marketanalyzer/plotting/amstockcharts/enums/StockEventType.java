package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum StockEventType {
	FLAG("flag"),
	SIGN("sign"),
	PIN("pin"),
	TRIANGLE_UP("triangleUp"),
	TRIANGLE_DOWN("triangleDown"),
	TRIANGLE_LEFT("triangleLeft"),
	TRIANGLE_RIGHT("triangleRight"),
	TEXT("text"),
	ARROW_UP("arrowUp"),
	ARROW_DOWN("arrowDown");
	
	private final String stockEventType;
	
	StockEventType(String stockEventType) {
		this.stockEventType = stockEventType;
	}
	
	@Override
	public String toString() {
		return stockEventType;
	}
}

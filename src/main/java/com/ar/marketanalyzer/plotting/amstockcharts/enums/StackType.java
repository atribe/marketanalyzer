package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum StackType {
	NONE("none"),
	REGULAR("regular"),
	ONE_HUNDRED_PERCENT("100%"),
	THREE_D("3d");
	
	private final String stackType;
	
	StackType(String stackType) {
		this.stackType = stackType;
	}
	
	@Override
	public String toString() {
		return stackType;
	}
}

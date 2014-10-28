package com.ar.marketanalyzer.plotting.amcharts.enums;

public enum SwitchType {
	X("x"),
	V ("v");
	
	private final String switchType;
	
	SwitchType(String switchType) {
		this.switchType = switchType;
	}
	
	@Override
	public String toString() {
		return switchType;
	}
}

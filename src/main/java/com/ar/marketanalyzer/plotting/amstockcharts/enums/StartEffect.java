package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum StartEffect {
	EASE_OUT_SINE("easeOutSine"),
	EASE_IN_SINE("easeInSine"),
	ELASTIC("elastic"),
	BOUNCE("bounce");
	
	private final String startEffect;
	
	StartEffect(String startEffect) {
		this.startEffect = startEffect;
	}
	
	@Override
	public String toString(){
		return startEffect;
	}
}

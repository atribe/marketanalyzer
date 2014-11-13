package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum RecalculateToPercents {
	NEVER("never"),
	ALWAYS("always"),
	WHENCOMPARING("whenComparing");
	
	private final String recalculateToPercents;
	
	RecalculateToPercents(String recalculateToPercents) {
		this.recalculateToPercents = recalculateToPercents;
	}
	
	@Override
	public String toString() {
		return recalculateToPercents;
	}
}

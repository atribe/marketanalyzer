package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public class FieldMapping {
	private final String FROMFIELD = "fromField";
	private final String TOFIELD = "toField";
	
	private String key;
	private String value;
	
	public FieldMapping() {
		
	}
	
	public FieldMapping(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return FROMFIELD + ":\"" + key + "\", " + TOFIELD + ":\"" + value + "\"";
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getFROMFIELD() {
		return FROMFIELD;
	}
	public String getTOFIELD() {
		return TOFIELD;
	}
	
	
}

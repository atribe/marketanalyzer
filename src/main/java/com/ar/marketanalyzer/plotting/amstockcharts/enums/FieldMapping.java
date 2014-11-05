package com.ar.marketanalyzer.plotting.amstockcharts.enums;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FieldMapping {
	
	private String fromField;
	private String toField;
	
	public FieldMapping() {
		
	}
	
	public FieldMapping(String fromField, String toField) {
		this.fromField = fromField;
		this.toField = toField;
	}
	
	@Override
	public String toString() {
		return "fromField:\"" + fromField + "\", " + "toField:\"" + toField + "\"";
	}
	
	public String getFromField() {
		return fromField;
	}
	public void setFromField(String key) {
		this.fromField = key;
	}
	public String getToField() {
		return toField;
	}
	public void setToField(String value) {
		this.toField = value;
	}
	
	
}

package com.ar.marketanalyzer.plotting.highcharts.chartobjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Navigator {
	private Boolean enabled;
	private Boolean adaptToUpdatedData;
	
	/*
	 * Constructors
	 */
	public Navigator() {
		enabled = true;
	}
	
	/*
	 * Getters and Setters
	 */
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getAdaptToUpdatedData() {
		return adaptToUpdatedData;
	}
	public void setAdaptToUpdatedData(Boolean adaptToUpdatedData) {
		this.adaptToUpdatedData = adaptToUpdatedData;
	}
}

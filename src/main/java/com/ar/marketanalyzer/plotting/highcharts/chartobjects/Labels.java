package com.ar.marketanalyzer.plotting.highcharts.chartobjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Labels {

	private String align;
	private Integer x;
	
	public Labels() {
		align = "right";
		x = -3;
	}
	
	/*
	 * Getters and Setters
	 */
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
}

package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmRadarChart extends AmCoordinateChart {

	/**
	* Field in your data provider containing categories.
	* Default Value: 
	*/
	private String categoryField;
	/**
	* Bottom margin of the chart.
	* Default Value: 0
	*/
	private Double marginBottom;
	/**
	* Left margin of the chart.
	* Default Value: 0
	*/
	private Double marginLeft;
	/**
	* Right margin of the chart.
	* Default Value: 0
	*/
	private Double marginRight;
	/**
	* Top margin of the chart.
	* Default Value: 0
	*/
	private Double marginTop;
	/**
	* Radius of a radar.
	* Default Value: 0.35
	*/
	private Double radius;
	
	public String getCategoryField() {
		return categoryField;
	}
	public void setCategoryField(String categoryField) {
		this.categoryField = categoryField;
	}
	public Double getMarginBottom() {
		return marginBottom;
	}
	public void setMarginBottom(Double marginBottom) {
		this.marginBottom = marginBottom;
	}
	public Double getMarginLeft() {
		return marginLeft;
	}
	public void setMarginLeft(Double marginLeft) {
		this.marginLeft = marginLeft;
	}
	public Double getMarginRight() {
		return marginRight;
	}
	public void setMarginRight(Double marginRight) {
		this.marginRight = marginRight;
	}
	public Double getMarginTop() {
		return marginTop;
	}
	public void setMarginTop(Double marginTop) {
		this.marginTop = marginTop;
	}
	public Double getRadius() {
		return radius;
	}
	public void setRadius(Double radius) {
		this.radius = radius;
	}

}

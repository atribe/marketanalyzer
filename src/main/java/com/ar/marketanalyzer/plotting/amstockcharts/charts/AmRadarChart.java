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
	private double marginBottom;
	/**
	* Left margin of the chart.
	* Default Value: 0
	*/
	private double marginLeft;
	/**
	* Right margin of the chart.
	* Default Value: 0
	*/
	private double marginRight;
	/**
	* Top margin of the chart.
	* Default Value: 0
	*/
	private double marginTop;
	/**
	* Radius of a radar.
	* Default Value: 0.35
	*/
	private double radius;
	
	public String getCategoryField() {
		return categoryField;
	}
	public void setCategoryField(String categoryField) {
		this.categoryField = categoryField;
	}
	public double getMarginBottom() {
		return marginBottom;
	}
	public void setMarginBottom(double marginBottom) {
		this.marginBottom = marginBottom;
	}
	public double getMarginLeft() {
		return marginLeft;
	}
	public void setMarginLeft(double marginLeft) {
		this.marginLeft = marginLeft;
	}
	public double getMarginRight() {
		return marginRight;
	}
	public void setMarginRight(double marginRight) {
		this.marginRight = marginRight;
	}
	public double getMarginTop() {
		return marginTop;
	}
	public void setMarginTop(double marginTop) {
		this.marginTop = marginTop;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}

}

package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import java.util.Date;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TrendLine {
	/**
	* Dash length.
	* Default Value: 0
	*/
	private double dashLength;
	/**
	* String, equal to category value to which trend line should be drawn. It should be used if chart doesn't parse dates.
	* Default Value: 
	*/
	private String finalCategory;
	/**
	* Date to which trend line should be drawn. It can be date string (using the same date format as chart.dataDateFormat) or date object.
	* Default Value: 
	*/
	private Date finalDate;
	/**
	* Value at which trend line should end.
	* Default Value: 
	*/
	private double finalValue;
	/**
	* Used by XY chart only. X value at which trend line should end.
	* Default Value: 
	*/
	private double finalXValue;
	/**
	* Unique id of a Trend line. You don't need to set it, unless you want to.
	* Default Value: 
	*/
	private String id;
	/**
	* String, equal to category value from which trend line should start. It should be used if chart doesn't parse dates.
	* Default Value: 
	*/
	private String initialCategory;
	/**
	* Date from which trend line should start. It can be date string (using the same date format as chart.dataDateFormat) or date object.
	* Default Value: 
	*/
	private Date initialDate;
	/**
	* Value from which trend line should start.
	* Default Value: 
	*/
	private double initialValue;
	/**
	* Used by XY chart only. X value from which trend line should start.
	* Default Value: 
	*/
	private double initialXValue;
	/**
	* Used by Stock chart. If this property is set to true, this trend line won't be removed when clicked on eraser tool.
	* Default Value: FALSE
	*/
	private boolean isProtected;
	/**
	* Line opacity.
	* Default Value: 1
	*/
	private double lineAlpha;
	/**
	* Line color.
	* Default Value: #00CC00
	*/
	private Color lineColor;
	/**
	* Line thickness.
	* Default Value: 1
	*/
	private double lineThickness;
	/**
	* Value axis of the trend line. Will use first value axis of the chart if not set any. You can use a reference to the value axis object or id of value axis.
	* Default Value: ValueAxis
	*/
	private ValueAxis valueAxis;
	/**
	* Used by XY chart only. X axis of trend line. Will use first X axis of the chart if not set any. You can use a reference to the value axis object or id of value axis.
	* Default Value: ValueAxis
	*/
	private ValueAxis valueAxisX;
	public double getDashLength() {
		return dashLength;
	}
	public void setDashLength(double dashLength) {
		this.dashLength = dashLength;
	}
	public String getFinalCategory() {
		return finalCategory;
	}
	public void setFinalCategory(String finalCategory) {
		this.finalCategory = finalCategory;
	}
	public Date getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}
	public double getFinalValue() {
		return finalValue;
	}
	public void setFinalValue(double finalValue) {
		this.finalValue = finalValue;
	}
	public double getFinalXValue() {
		return finalXValue;
	}
	public void setFinalXValue(double finalXValue) {
		this.finalXValue = finalXValue;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInitialCategory() {
		return initialCategory;
	}
	public void setInitialCategory(String initialCategory) {
		this.initialCategory = initialCategory;
	}
	public Date getInitialDate() {
		return initialDate;
	}
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}
	public double getInitialValue() {
		return initialValue;
	}
	public void setInitialValue(double initialValue) {
		this.initialValue = initialValue;
	}
	public double getInitialXValue() {
		return initialXValue;
	}
	public void setInitialXValue(double initialXValue) {
		this.initialXValue = initialXValue;
	}
	public boolean isProtected() {
		return isProtected;
	}
	public void setProtected(boolean isProtected) {
		this.isProtected = isProtected;
	}
	public double getLineAlpha() {
		return lineAlpha;
	}
	public void setLineAlpha(double lineAlpha) {
		this.lineAlpha = lineAlpha;
	}
	public Color getLineColor() {
		return lineColor;
	}
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	public double getLineThickness() {
		return lineThickness;
	}
	public void setLineThickness(double lineThickness) {
		this.lineThickness = lineThickness;
	}
	public ValueAxis getValueAxis() {
		return valueAxis;
	}
	public void setValueAxis(ValueAxis valueAxis) {
		this.valueAxis = valueAxis;
	}
	public ValueAxis getValueAxisX() {
		return valueAxisX;
	}
	public void setValueAxisX(ValueAxis valueAxisX) {
		this.valueAxisX = valueAxisX;
	}

}

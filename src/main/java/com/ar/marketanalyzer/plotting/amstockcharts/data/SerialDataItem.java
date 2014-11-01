package com.ar.marketanalyzer.plotting.amstockcharts.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class SerialDataItem {

	/**
	* You can access each GraphDataItem using this object. The data structure is: graphDataItem = serialDataItem.axes[axisId].graphs[graphId].
	* Default Value: 
	*/
	private Object axes;
	/**
	* category value. String if parseDates is false, Date if true.
	* Default Value: 
	*/
	private String category;
	/**
	* Reference to original data object, from dataProvider.
	* Default Value: 
	*/
	private Object dataContext;
	/**
	* Time stamp of a series date. Avalable only if parseDates property of CategoryAxis is set to true.
	* Default Value: 
	*/
	private double time;
	/**
	* Coordinate (horizontal or vertical, depends on chart's rotate property) of the series.
	* Default Value: 
	*/
	private double x;

	public Object getAxes() {
		return axes;
	}
	public void setAxes(Object axes) {
		this.axes = axes;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Object getDataContext() {
		return dataContext;
	}
	public void setDataContext(Object dataContext) {
		this.dataContext = dataContext;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
}

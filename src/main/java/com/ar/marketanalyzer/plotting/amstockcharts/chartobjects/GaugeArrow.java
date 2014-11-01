package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;

public class GaugeArrow {

	/**
	* Opacity of an arrow.
	* Default Value: 1
	*/
	private double alpha;
	/**
	* Axis of the arrow. You can use reference to the axis or id of the axis. If you don't set any axis, the first axis of a chart will be used.
	* Default Value: GaugeAxis
	*/
	private GaugeAxis axis;
	
	public double getAlpha() {
		return alpha;
	}
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	public GaugeAxis getAxis() {
		return axis;
	}
	public void setAxis(GaugeAxis axis) {
		this.axis = axis;
	}
	public double getBorderAlpha() {
		return borderAlpha;
	}
	public void setBorderAlpha(double borderAlpha) {
		this.borderAlpha = borderAlpha;
	}
	public boolean isClockWiseOnly() {
		return clockWiseOnly;
	}
	public void setClockWiseOnly(boolean clockWiseOnly) {
		this.clockWiseOnly = clockWiseOnly;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(double innerRadius) {
		this.innerRadius = innerRadius;
	}
	public double getNailAlpha() {
		return nailAlpha;
	}
	public void setNailAlpha(double nailAlpha) {
		this.nailAlpha = nailAlpha;
	}
	public double getNailBorderAlpha() {
		return nailBorderAlpha;
	}
	public void setNailBorderAlpha(double nailBorderAlpha) {
		this.nailBorderAlpha = nailBorderAlpha;
	}
	public double getNailBorderThickness() {
		return nailBorderThickness;
	}
	public void setNailBorderThickness(double nailBorderThickness) {
		this.nailBorderThickness = nailBorderThickness;
	}
	public double getNailRadius() {
		return nailRadius;
	}
	public void setNailRadius(double nailRadius) {
		this.nailRadius = nailRadius;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getStartWidth() {
		return startWidth;
	}
	public void setStartWidth(double startWidth) {
		this.startWidth = startWidth;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	/**
	* Opacity of arrow border.
	* Default Value: 1
	*/
	private double borderAlpha;
	/**
	* In case you need the arrow to rotate only clock-wise, set this property to true.
	* Default Value: FALSE
	*/
	private boolean clockWiseOnly;
	/**
	* Color of an arrow.
	* Default Value: #000000
	*/
	private Color color;
	/**
	* Unique id of an arrow.
	* Default Value: 
	*/
	private String id;
	/**
	* Inner radius of an arrow.
	* Default Value: 0
	*/
	private double innerRadius;
	/**
	* Opacity of a nail, holding the arrow.
	* Default Value: 1
	*/
	private double nailAlpha;
	/**
	* Opacity of nail border.
	* Default Value: 0
	*/
	private double nailBorderAlpha;
	/**
	* Thickness of nail border.
	* Default Value: 1
	*/
	private double nailBorderThickness;
	/**
	* Radius of a nail, holding the arrow.
	* Default Value: 8
	*/
	private double nailRadius;
	/**
	* Radius of an arrow.
	* Default Value: 0.9
	*/
	private double radius;
	/**
	* Width of arrow root.
	* Default Value: 8
	*/
	private double startWidth;
	/**
	* Value to which the arrow should point at.
	* Default Value: 
	*/
	private double value;

}

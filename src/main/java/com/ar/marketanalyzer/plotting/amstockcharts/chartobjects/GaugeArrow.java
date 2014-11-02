package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class GaugeArrow {

	/**
	* Opacity of an arrow.
	* Default Value: 1
	*/
	private Double alpha;
	/**
	* Axis of the arrow. You can use reference to the axis or id of the axis. If you don't set any axis, the first axis of a chart will be used.
	* Default Value: GaugeAxis
	*/
	private GaugeAxis axis;
	
	public Double getAlpha() {
		return alpha;
	}
	public void setAlpha(Double alpha) {
		this.alpha = alpha;
	}
	public GaugeAxis getAxis() {
		return axis;
	}
	public void setAxis(GaugeAxis axis) {
		this.axis = axis;
	}
	public Double getBorderAlpha() {
		return borderAlpha;
	}
	public void setBorderAlpha(Double borderAlpha) {
		this.borderAlpha = borderAlpha;
	}
	public Boolean isClockWiseOnly() {
		return clockWiseOnly;
	}
	public void setClockWiseOnly(Boolean clockWiseOnly) {
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
	public Double getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(Double innerRadius) {
		this.innerRadius = innerRadius;
	}
	public Double getNailAlpha() {
		return nailAlpha;
	}
	public void setNailAlpha(Double nailAlpha) {
		this.nailAlpha = nailAlpha;
	}
	public Double getNailBorderAlpha() {
		return nailBorderAlpha;
	}
	public void setNailBorderAlpha(Double nailBorderAlpha) {
		this.nailBorderAlpha = nailBorderAlpha;
	}
	public Double getNailBorderThickness() {
		return nailBorderThickness;
	}
	public void setNailBorderThickness(Double nailBorderThickness) {
		this.nailBorderThickness = nailBorderThickness;
	}
	public Double getNailRadius() {
		return nailRadius;
	}
	public void setNailRadius(Double nailRadius) {
		this.nailRadius = nailRadius;
	}
	public Double getRadius() {
		return radius;
	}
	public void setRadius(Double radius) {
		this.radius = radius;
	}
	public Double getStartWidth() {
		return startWidth;
	}
	public void setStartWidth(Double startWidth) {
		this.startWidth = startWidth;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	/**
	* Opacity of arrow border.
	* Default Value: 1
	*/
	private Double borderAlpha;
	/**
	* In case you need the arrow to rotate only clock-wise, set this property to true.
	* Default Value: FALSE
	*/
	private Boolean clockWiseOnly;
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
	private Double innerRadius;
	/**
	* Opacity of a nail, holding the arrow.
	* Default Value: 1
	*/
	private Double nailAlpha;
	/**
	* Opacity of nail border.
	* Default Value: 0
	*/
	private Double nailBorderAlpha;
	/**
	* Thickness of nail border.
	* Default Value: 1
	*/
	private Double nailBorderThickness;
	/**
	* Radius of a nail, holding the arrow.
	* Default Value: 8
	*/
	private Double nailRadius;
	/**
	* Radius of an arrow.
	* Default Value: 0.9
	*/
	private Double radius;
	/**
	* Width of arrow root.
	* Default Value: 8
	*/
	private Double startWidth;
	/**
	* Value to which the arrow should point at.
	* Default Value: 
	*/
	private Double value;

}

package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.GaugeArrow;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.GaugeAxis;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.StartEffect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmAngularGauge extends AmChart{

	/**
	* Uses the whole space of the canvas to draw the gauge.
	* Default Value: TRUE
	*/
	private boolean adjustSize;
	/**
	* Array of arrows.
	* Default Value: 
	*/
	private List<GaugeArrow> arrows;
	/**
	* Array of axes.
	* Default Value: 
	*/
	private List<GaugeAxis> axes;
	/**
	* In case you use gauge to create a clock, set this to true.
	* Default Value: FALSE
	*/
	private boolean clockWiseOnly;
	/**
	* Gauge face opacity.
	* Default Value: 0
	*/
	private double faceAlpha;
	/**
	* Gauge face border opacity.
	* Default Value: 0
	*/
	private double faceBorderAlpha;
	/**
	* Gauge face border color.
	* Default Value: #555555
	*/
	private Color faceBorderColor;
	/**
	* Gauge face border width.
	* Default Value: 1
	*/
	private double faceBorderWidth;
	/**
	* Gauge face color, requires faceAlpha > 0
	* Default Value: #FAFAFA
	*/
	private Color faceColor;
	/**
	* Gauge face image-pattern.{""url"":""../amcharts/patterns/black/pattern1.png"", ""width"":4, ""height"":4}
	* Default Value: 
	*/
	private Object facePattern;
	/**
	* Gauge's horizontal position in pixel, origin is the center. Centered by default.
	* Default Value: 
	*/
	private double gaugeX;
	/**
	* Gauge's vertical position in pixel, origin is the center. Centered by default.
	* Default Value: 
	*/
	private double gaugeY;
	/**
	* Bottom spacing between chart and container.
	* Default Value: 10
	*/
	private double marginBottom;
	/**
	* Left-hand spacing between chart and container.
	* Default Value: 10
	*/
	private double marginLeft;
	/**
	* Right-hand spacing between chart and container.
	* Default Value: 10
	*/
	private double marginRight;
	/**
	* Top spacing between chart and container.
	* Default Value: 10
	*/
	private double marginTop;
	/**
	* Minimum radius of a gauge.
	* Default Value: 10
	*/
	private double minRadius;
	/**
	* Duration of arrow animation.
	* Default Value: 1
	*/
	private double startDuration;
	/**
	* Transition effect of the arrows, possible effects: easeOutSine, easeInSine, elastic, bounce.
	* Default Value: easeInSine
	*/
	private StartEffect startEffect;
	public boolean isAdjustSize() {
		return adjustSize;
	}
	public void setAdjustSize(boolean adjustSize) {
		this.adjustSize = adjustSize;
	}
	public List<GaugeArrow> getArrows() {
		return arrows;
	}
	public void setArrows(List<GaugeArrow> arrows) {
		this.arrows = arrows;
	}
	public List<GaugeAxis> getAxes() {
		return axes;
	}
	public void setAxes(List<GaugeAxis> axes) {
		this.axes = axes;
	}
	public boolean isClockWiseOnly() {
		return clockWiseOnly;
	}
	public void setClockWiseOnly(boolean clockWiseOnly) {
		this.clockWiseOnly = clockWiseOnly;
	}
	public double getFaceAlpha() {
		return faceAlpha;
	}
	public void setFaceAlpha(double faceAlpha) {
		this.faceAlpha = faceAlpha;
	}
	public double getFaceBorderAlpha() {
		return faceBorderAlpha;
	}
	public void setFaceBorderAlpha(double faceBorderAlpha) {
		this.faceBorderAlpha = faceBorderAlpha;
	}
	public Color getFaceBorderColor() {
		return faceBorderColor;
	}
	public void setFaceBorderColor(Color faceBorderColor) {
		this.faceBorderColor = faceBorderColor;
	}
	public double getFaceBorderWidth() {
		return faceBorderWidth;
	}
	public void setFaceBorderWidth(double faceBorderWidth) {
		this.faceBorderWidth = faceBorderWidth;
	}
	public Color getFaceColor() {
		return faceColor;
	}
	public void setFaceColor(Color faceColor) {
		this.faceColor = faceColor;
	}
	public Object getFacePattern() {
		return facePattern;
	}
	public void setFacePattern(Object facePattern) {
		this.facePattern = facePattern;
	}
	public double getGaugeX() {
		return gaugeX;
	}
	public void setGaugeX(double gaugeX) {
		this.gaugeX = gaugeX;
	}
	public double getGaugeY() {
		return gaugeY;
	}
	public void setGaugeY(double gaugeY) {
		this.gaugeY = gaugeY;
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
	public double getMinRadius() {
		return minRadius;
	}
	public void setMinRadius(double minRadius) {
		this.minRadius = minRadius;
	}
	public double getStartDuration() {
		return startDuration;
	}
	public void setStartDuration(double startDuration) {
		this.startDuration = startDuration;
	}
	public StartEffect getStartEffect() {
		return startEffect;
	}
	public void setStartEffect(StartEffect startEffect) {
		this.startEffect = startEffect;
	}

}

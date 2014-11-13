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
	private Boolean adjustSize;
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
	private Boolean clockWiseOnly;
	/**
	* Gauge face opacity.
	* Default Value: 0
	*/
	private Double faceAlpha;
	/**
	* Gauge face border opacity.
	* Default Value: 0
	*/
	private Double faceBorderAlpha;
	/**
	* Gauge face border color.
	* Default Value: #555555
	*/
	private Color faceBorderColor;
	/**
	* Gauge face border width.
	* Default Value: 1
	*/
	private Double faceBorderWidth;
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
	private Double gaugeX;
	/**
	* Gauge's vertical position in pixel, origin is the center. Centered by default.
	* Default Value: 
	*/
	private Double gaugeY;
	/**
	* Bottom spacing between chart and container.
	* Default Value: 10
	*/
	private Double marginBottom;
	/**
	* Left-hand spacing between chart and container.
	* Default Value: 10
	*/
	private Double marginLeft;
	/**
	* Right-hand spacing between chart and container.
	* Default Value: 10
	*/
	private Double marginRight;
	/**
	* Top spacing between chart and container.
	* Default Value: 10
	*/
	private Double marginTop;
	/**
	* Minimum radius of a gauge.
	* Default Value: 10
	*/
	private Double minRadius;
	/**
	* Duration of arrow animation.
	* Default Value: 1
	*/
	private Double startDuration;
	/**
	* Transition effect of the arrows, possible effects: easeOutSine, easeInSine, elastic, bounce.
	* Default Value: easeInSine
	*/
	private StartEffect startEffect;
	public Boolean isAdjustSize() {
		return adjustSize;
	}
	public void setAdjustSize(Boolean adjustSize) {
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
	public Boolean isClockWiseOnly() {
		return clockWiseOnly;
	}
	public void setClockWiseOnly(Boolean clockWiseOnly) {
		this.clockWiseOnly = clockWiseOnly;
	}
	public Double getFaceAlpha() {
		return faceAlpha;
	}
	public void setFaceAlpha(Double faceAlpha) {
		this.faceAlpha = faceAlpha;
	}
	public Double getFaceBorderAlpha() {
		return faceBorderAlpha;
	}
	public void setFaceBorderAlpha(Double faceBorderAlpha) {
		this.faceBorderAlpha = faceBorderAlpha;
	}
	public Color getFaceBorderColor() {
		return faceBorderColor;
	}
	public void setFaceBorderColor(Color faceBorderColor) {
		this.faceBorderColor = faceBorderColor;
	}
	public Double getFaceBorderWidth() {
		return faceBorderWidth;
	}
	public void setFaceBorderWidth(Double faceBorderWidth) {
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
	public Double getGaugeX() {
		return gaugeX;
	}
	public void setGaugeX(Double gaugeX) {
		this.gaugeX = gaugeX;
	}
	public Double getGaugeY() {
		return gaugeY;
	}
	public void setGaugeY(Double gaugeY) {
		this.gaugeY = gaugeY;
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
	public Double getMinRadius() {
		return minRadius;
	}
	public void setMinRadius(Double minRadius) {
		this.minRadius = minRadius;
	}
	public Double getStartDuration() {
		return startDuration;
	}
	public void setStartDuration(Double startDuration) {
		this.startDuration = startDuration;
	}
	public StartEffect getStartEffect() {
		return startEffect;
	}
	public void setStartEffect(StartEffect startEffect) {
		this.startEffect = startEffect;
	}

}

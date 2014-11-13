package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class GaugeBand {
	/**
	* Opacity of band fill. Will use axis.bandAlpha if not set any.
	* Default Value: 
	*/
	private Double alpha;
	/**
	* When rolled-over, band will display balloon if you set some text for this property.
	* Default Value: 
	*/
	private String balloonText;
	/**
	* Color of a band.
	* Default Value: 
	*/
	private Color color;
	/**
	* End value of a fill.
	* Default Value: 
	*/
	private Double endValue;
	/**
	* Unique id of a band.
	* Default Value: 
	*/
	private String id;
	/**
	* Inner radius of a band. If not set any, the band will end with the end of minor ticks. Set 0 if you want the band to be drawn to the axis center.
	* Default Value: 
	*/
	private Double innerRadius;
	
	public Double getAlpha() {
		return alpha;
	}
	public void setAlpha(Double alpha) {
		this.alpha = alpha;
	}
	public String getBalloonText() {
		return balloonText;
	}
	public void setBalloonText(String balloonText) {
		this.balloonText = balloonText;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Double getEndValue() {
		return endValue;
	}
	public void setEndValue(Double endValue) {
		this.endValue = endValue;
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
	public Double getRadius() {
		return radius;
	}
	public void setRadius(Double radius) {
		this.radius = radius;
	}
	public Double getStartValue() {
		return startValue;
	}
	public void setStartValue(Double startValue) {
		this.startValue = startValue;
	}
	/**
	* Band radius. If not set any, the band will start with the axis outline.
	* Default Value: 
	*/
	private Double radius;
	/**
	* Start value of a fill.
	* Default Value: 
	*/
	private Double startValue;

}

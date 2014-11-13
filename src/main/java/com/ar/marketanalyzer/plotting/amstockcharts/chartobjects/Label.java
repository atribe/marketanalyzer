package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Label {

	/**
	* 
	* Default Value: left
	*/
	private String align;
	/**
	* 
	* Default Value: 1
	*/
	private Double alpha;
	/**
	* Specifies if label is bold or not.
	* Default Value: FALSE
	*/
	private Boolean bold;
	/**
	* Color of a label.
	* Default Value: 
	*/
	private Color color;
	/**
	* Unique id of a Label. You don't need to set it, unless you want to.
	* Default Value: 
	*/
	private String id;
	/**
	* Rotation angle.
	* Default Value: 0
	*/
	private Double rotation;
	/**
	* Text size.
	* Default Value: 
	*/
	private Double size;
	/**
	* Text of a label.
	* Default Value: 
	*/
	private String text;
	/**
	* URL which will be access if user clicks on a label.
	* Default Value: 
	*/
	private String url;
	/**
	* X position of a label.
	* Default Value: 
	*/
	private Double x;
	/**
	* y position of a label.
	* Default Value: 
	*/
	private Double y;
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public Double getAlpha() {
		return alpha;
	}
	public void setAlpha(Double alpha) {
		this.alpha = alpha;
	}
	public Boolean isBold() {
		return bold;
	}
	public void setBold(Boolean bold) {
		this.bold = bold;
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
	public Double getRotation() {
		return rotation;
	}
	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
}

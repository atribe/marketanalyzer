package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Title {
	/**
	* Opacity of a title.
	* Default Value: 1
	*/
	private Double alpha;
	/**
	* Specifies if title should be bold or not.
	* Default Value: FALSE
	*/
	private Boolean bold;
	/**
	* Text color of a title.
	* Default Value: 
	*/
	private Color color;
	/**
	* Unique id of a Title. You don't need to set it, unless you want to.
	* Default Value: 
	*/
	private String id;
	/**
	* Text size of a title.
	* Default Value: 
	*/
	private Double size;
	/**
	* Text of a title.
	* Default Value: 
	*/
	private String text;
	
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

}

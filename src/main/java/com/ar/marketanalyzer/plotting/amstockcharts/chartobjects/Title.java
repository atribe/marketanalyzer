package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;

public class Title {
	/**
	* Opacity of a title.
	* Default Value: 1
	*/
	private double alpha;
	/**
	* Specifies if title should be bold or not.
	* Default Value: FALSE
	*/
	private boolean bold;
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
	private double size;
	/**
	* Text of a title.
	* Default Value: 
	*/
	private String text;
	
	public double getAlpha() {
		return alpha;
	}
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	public boolean isBold() {
		return bold;
	}
	public void setBold(boolean bold) {
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
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}

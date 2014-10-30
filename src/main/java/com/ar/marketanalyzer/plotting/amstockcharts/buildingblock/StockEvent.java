package com.ar.marketanalyzer.plotting.amstockcharts.buildingblock;

import java.io.Serializable;
import java.sql.Date;

import com.ar.marketanalyzer.plotting.amcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amcharts.serializers.JacksonObjectToListSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_EMPTY)
@JsonSerialize(using = JacksonObjectToListSerializer.class)
public class StockEvent implements Serializable {

	private static final long serialVersionUID = -1898683159043639816L;
	
	/*
	 * http://docs.amcharts.com/3/javascriptstockchart/StockEvent
	 */
	
	/**
	* Opacity of bullet background.
	* Default Value: 1
	*/
	 private double backgroundAlpha;
	/**
	* Color of bullet background.
	* Default Value: #DADADA
	*/
	 private Color backgroundColor;
	/**
	* Opacity of bullet border.
	* Default Value: 1
	*/
	 private double borderAlpha;
	/**
	* Bullet border color.
	* Default Value: #888888
	*/
	 private Color borderColor;
	/**
	* The color of the event text.
	* Default Value: #000000
	*/
	 private Color color;
	/**
	* Date of an event. Can be a string of date (using chart.dataDateFormat format) or Date object.
	* Default Value: 
	*/
	 private Date date;
	/**
	* A description that will be shown in a balloon when user rolls over mouse cursor over event icon.
	* Default Value: 
	*/
	 private String description;
	/**
	* graph on which event will be displayed. You can use a reference to the stock graph object or id of the graph.
	* Default Value: 
	*/
	 private Something graph;
	/**
	* Roll-over background color.
	* Default Value: #CC0000
	*/
	 private String rollOverColor;
	/**
	* Allows placing event bullets at open/close/low/high values.
	* Default Value: 
	*/
	 private String showAt;
	/**
	* Specifies if the event should be displayed on category axis
	* Default Value: FALSE
	*/
	 private boolean showOnAxis;
	/**
	* Letter which will be displayed on the event. Not all types can display letters. ""text"" type can display longer texts.
	* Default Value: 
	*/
	 private String text;
	/**
	* Type of bullet. Possible values are: ""flag"", ""sign"", ""pin"", ""triangleUp"", ""triangleDown"", ""triangleLeft"", ""triangleRight"", ""text"", ""arrowUp"", ""arrowDown"".
	* Default Value: sign
	*/
	 private String type;
	/**
	* A URL to go to when user clicks the event.
	* Default Value: 
	*/
	 private String url;
	/**
	* target of url, ""_blank"" for example.
	* Default Value: 
	*/
	 private String urlTarget;
	/**
	* Allows placing event bullets at specified value.
	* Default Value: 
	*/
	 private double value;
	 
	 public StockEvent() {
		 
	 }
	 
	public double getBackgroundAlpha() {
		return backgroundAlpha;
	}
	public void setBackgroundAlpha(double backgroundAlpha) {
		this.backgroundAlpha = backgroundAlpha;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public double getBorderAlpha() {
		return borderAlpha;
	}
	public void setBorderAlpha(double borderAlpha) {
		this.borderAlpha = borderAlpha;
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Something getGraph() {
		return graph;
	}
	public void setGraph(Something graph) {
		this.graph = graph;
	}
	public String getRollOverColor() {
		return rollOverColor;
	}
	public void setRollOverColor(String rollOverColor) {
		this.rollOverColor = rollOverColor;
	}
	public String getShowAt() {
		return showAt;
	}
	public void setShowAt(String showAt) {
		this.showAt = showAt;
	}
	public boolean isShowOnAxis() {
		return showOnAxis;
	}
	public void setShowOnAxis(boolean showOnAxis) {
		this.showOnAxis = showOnAxis;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlTarget() {
		return urlTarget;
	}
	public void setUrlTarget(String urlTarget) {
		this.urlTarget = urlTarget;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

}

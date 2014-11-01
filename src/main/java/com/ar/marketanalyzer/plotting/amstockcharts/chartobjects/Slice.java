package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;

public class Slice {

	/**
	* Opacity of a slice.
	* Default Value: 
	*/
	private double alpha;
	/**
	* Color of a slice.
	* Default Value: 
	*/
	private Color color;
	/**
	* Original object from data provider.
	* Default Value: 
	*/
	private Object dataContext;
	/**
	* Slice description.
	* Default Value: 
	*/
	private String description;
	/**
	* Specifies whether the slice is hidden
	* Default Value: 
	*/
	private boolean hidden;
	/**
	* Percent value of a slice.
	* Default Value: 
	*/
	private double percents;
	/**
	* Specifies whether the slice is pulled or not.
	* Default Value: 
	*/
	private boolean pulled;
	/**
	* Slice title
	* Default Value: 
	*/
	private String title;
	/**
	* Url of a slice
	* Default Value: 
	*/
	private String url;
	/**
	* Value of a slice
	* Default Value: 
	*/
	private double value;
	/**
	* specifies whether this slice has a legend entry
	* Default Value: 
	*/
	private boolean visibleInLegend;
	public double getAlpha() {
		return alpha;
	}
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Object getDataContext() {
		return dataContext;
	}
	public void setDataContext(Object dataContext) {
		this.dataContext = dataContext;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public double getPercents() {
		return percents;
	}
	public void setPercents(double percents) {
		this.percents = percents;
	}
	public boolean isPulled() {
		return pulled;
	}
	public void setPulled(boolean pulled) {
		this.pulled = pulled;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public boolean isVisibleInLegend() {
		return visibleInLegend;
	}
	public void setVisibleInLegend(boolean visibleInLegend) {
		this.visibleInLegend = visibleInLegend;
	}

}

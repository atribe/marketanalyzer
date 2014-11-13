package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Slice {

	/**
	* Opacity of a slice.
	* Default Value: 
	*/
	private Double alpha;
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
	private Boolean hidden;
	/**
	* Percent value of a slice.
	* Default Value: 
	*/
	private Double percents;
	/**
	* Specifies whether the slice is pulled or not.
	* Default Value: 
	*/
	private Boolean pulled;
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
	private Double value;
	/**
	* specifies whether this slice has a legend entry
	* Default Value: 
	*/
	private Boolean visibleInLegend;
	public Double getAlpha() {
		return alpha;
	}
	public void setAlpha(Double alpha) {
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
	public Boolean isHidden() {
		return hidden;
	}
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	public Double getPercents() {
		return percents;
	}
	public void setPercents(Double percents) {
		this.percents = percents;
	}
	public Boolean isPulled() {
		return pulled;
	}
	public void setPulled(Boolean pulled) {
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
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Boolean isVisibleInLegend() {
		return visibleInLegend;
	}
	public void setVisibleInLegend(Boolean visibleInLegend) {
		this.visibleInLegend = visibleInLegend;
	}

}

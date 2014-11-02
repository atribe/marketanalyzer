package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmXYChart extends AmRectangularChart{

	/**
	* Specifies if Scrollbar of X axis (horizontal) should be hidden.
	* Default Value: FALSE
	*/
	private Boolean hideXScrollbar;
	/**
	* Specifies if Scrollbar of Y axis (vertical) should be hidden.
	* Default Value: FALSE
	*/
	private Boolean hideYScrollbar;
	/**
	* Maximum zoom factor of the chart.
	* Default Value: 20
	*/
	private Double maxZoomFactor;
	
	public Boolean isHideXScrollbar() {
		return hideXScrollbar;
	}
	public void setHideXScrollbar(Boolean hideXScrollbar) {
		this.hideXScrollbar = hideXScrollbar;
	}
	public Boolean isHideYScrollbar() {
		return hideYScrollbar;
	}
	public void setHideYScrollbar(Boolean hideYScrollbar) {
		this.hideYScrollbar = hideYScrollbar;
	}
	public Double getMaxZoomFactor() {
		return maxZoomFactor;
	}
	public void setMaxZoomFactor(Double maxZoomFactor) {
		this.maxZoomFactor = maxZoomFactor;
	}
}

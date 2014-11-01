package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmXYChart extends AmRectangularChart{

	/**
	* Specifies if Scrollbar of X axis (horizontal) should be hidden.
	* Default Value: FALSE
	*/
	private boolean hideXScrollbar;
	/**
	* Specifies if Scrollbar of Y axis (vertical) should be hidden.
	* Default Value: FALSE
	*/
	private boolean hideYScrollbar;
	/**
	* Maximum zoom factor of the chart.
	* Default Value: 20
	*/
	private double maxZoomFactor;
	
	public boolean isHideXScrollbar() {
		return hideXScrollbar;
	}
	public void setHideXScrollbar(boolean hideXScrollbar) {
		this.hideXScrollbar = hideXScrollbar;
	}
	public boolean isHideYScrollbar() {
		return hideYScrollbar;
	}
	public void setHideYScrollbar(boolean hideYScrollbar) {
		this.hideYScrollbar = hideYScrollbar;
	}
	public double getMaxZoomFactor() {
		return maxZoomFactor;
	}
	public void setMaxZoomFactor(double maxZoomFactor) {
		this.maxZoomFactor = maxZoomFactor;
	}
}

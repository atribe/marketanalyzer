package com.ar.marketanalyzer.plotting.highcharts.charts;

import java.util.ArrayList;
import java.util.List;

import com.ar.marketanalyzer.plotting.highcharts.chartobjects.Labels;
import com.ar.marketanalyzer.plotting.highcharts.chartobjects.Navigator;
import com.ar.marketanalyzer.plotting.highcharts.chartobjects.RangeSelector;
import com.ar.marketanalyzer.plotting.highcharts.chartobjects.Series;
import com.ar.marketanalyzer.plotting.highcharts.chartobjects.Title;
import com.ar.marketanalyzer.plotting.highcharts.chartobjects.YAxis;
import com.ar.marketanalyzer.plotting.highcharts.data.HighstockData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class HighStockOHLCV {

	private RangeSelector rangeSelector;
	private Title title;
	private List<YAxis> yAxis = new ArrayList<YAxis>();
	private List<Series> series = new ArrayList<Series>();
	private Navigator navigator;
	
	/*
	 * Constructors
	 */
	public HighStockOHLCV() {
		rangeSelector = new RangeSelector();
		rangeSelector.setSelected(0);
		
		title = new Title("Test Highstock Chart");
				
		YAxis yaxis1 = new YAxis();
			yaxis1.setLabels(new Labels());
			yaxis1.setTitle(new Title("Price"));
			yaxis1.setHeight("65%");
			yaxis1.setLineWidth(2);	
		yAxis.add(yaxis1);
		
		YAxis yaxis2 = new YAxis();
			yaxis2.setLabels(new Labels());
			yaxis2.setTitle(new Title("Volume"));
			yaxis2.setTop("70%");
			yaxis2.setHeight("30%");
			yaxis2.setOffset(0);
			yaxis2.setLineWidth(2);
		yAxis.add(yaxis2);
		
		navigator = new Navigator();
			
	}
	
	public HighStockOHLCV(List<HighstockData> ohlcData, List<HighstockData> volumeData) {
		this();
		Series ohlc = new Series();
		ohlc.setOHLCSeries(ohlcData);
		series.add(ohlc);
		Series volume = new Series();
		volume.setVolumePanelSeries(volumeData);
		series.add(volume);
		
	}

	/*
	 * Helper Methods
	 */
	public void addSeries(List<HighstockData> highstockSingleValueData) {
		YAxis yaxis3 = new YAxis();
			Labels newLabels = new Labels();
				newLabels.setAlign("left");
				newLabels.setX(0);
			yaxis3.setLabels(newLabels);
			yaxis3.setTitle(new Title("Seconday Axis"));
			yaxis3.setHeight("65%");
			yaxis3.setLineWidth(2);	
			yaxis3.setOpposite(false);
		yAxis.add(yaxis3);
		
		Series newSeries = new Series();
		newSeries.setStockPanelSeries(highstockSingleValueData);
		newSeries.setyAxis(2);
		series.add(newSeries);
	}
	/*
	 * Getters and Setters
	 */
	public RangeSelector getRangeSelector() {
		return rangeSelector;
	}
	public void setRangeSelector(RangeSelector rangeSelector) {
		this.rangeSelector = rangeSelector;
	}

	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}

	public List<YAxis> getyAxis() {
		return yAxis;
	}
	public void setyAxis(List<YAxis> yAxis) {
		this.yAxis = yAxis;
	}

	public List<Series> getSeries() {
		return series;
	}
	public void setSeries(List<Series> series) {
		this.series = series;
	}

	public Navigator getNavigator() {
		return navigator;
	}
	public void setNavigator(Navigator navigator) {
		this.navigator = navigator;
	}
}

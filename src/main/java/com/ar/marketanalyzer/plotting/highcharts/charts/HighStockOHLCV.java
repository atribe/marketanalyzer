package com.ar.marketanalyzer.plotting.highcharts.charts;

import java.util.ArrayList;
import java.util.List;

import com.ar.marketanalyzer.plotting.highcharts.chartobjects.Labels;
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
	

	public HighStockOHLCV() {
		rangeSelector = new RangeSelector();
		rangeSelector.setSelected(0);
		
		title = new Title("Test Highstock Chart");
				
		YAxis yaxis1 = new YAxis();
			yaxis1.setLabels(new Labels());
			yaxis1.setTitle(new Title("ohlc"));
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
			
	}
	
	public HighStockOHLCV(List<HighstockData> ohlcData, List<HighstockData> volumeData) {
		this();
		Series ohlc = new Series();
		ohlc.SetOHLCSeries(ohlcData);
		series.add(ohlc);
		Series volume = new Series();
		volume.SetVolumeSeries(volumeData);
		series.add(volume);
		
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
}

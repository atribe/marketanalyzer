package com.ar.marketanalyzer.plotting.highcharts.chartobjects;

import com.ar.marketanalyzer.plotting.highcharts.data.HighstockData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class Series {
	
	private String type;
	private String name;
	private List<HighstockData> data;
	private Integer yAxis;
	private List<String> dataGrouping;
	private Integer turboThreshold;

	public Series() {
		
	}
	
	
	public void setOHLCSeries(List<HighstockData> ohlcData) {
		setType("ohlc");
		setName("ohlc name");
		setData(ohlcData);
		setTurboThreshold(0);
	}


	public void setStockPanelSeries(List<HighstockData> data) {
		setType("spline");
		setName("SomethingElse");
		setData(data);
		setyAxis(2);
		setTurboThreshold(0);
	}
	public void setVolumePanelSeries(List<HighstockData> volumeData) {
		setType("column");
		setName("Volume");
		setData(volumeData);
		setyAxis(1);
		setTurboThreshold(0);
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<HighstockData> getData() {
		return data;
	}
	public void setData(List<HighstockData> data) {
		this.data = data;
	}
	public Integer getyAxis() {
		return yAxis;
	}
	public void setyAxis(Integer yAxis) {
		this.yAxis = yAxis;
	}
	public List<String> getDataGrouping() {
		return dataGrouping;
	}
	public void setDataGrouping(List<String> dataGrouping) {
		this.dataGrouping = dataGrouping;
	}


	public Integer getTurboThreshold() {
		return turboThreshold;
	}


	public void setTurboThreshold(Integer turboThreshold) {
		this.turboThreshold = turboThreshold;
	}
}

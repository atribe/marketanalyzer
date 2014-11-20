package com.ar.marketanalyzer.plotting.highcharts.chartobjects;

import java.util.List;

import com.ar.marketanalyzer.plotting.highcharts.data.HighstockData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Series {
	
	private String type;
	private String name;
	private List<HighstockData> data;
	private Integer yAxis;
	private List<String> dataGrouping;

	public Series() {
		
	}
	
	
	public void SetOHLCSeries(List<HighstockData> ohlcData) {
		setType("OHLC");
		setName("ohlc name");
		setData(ohlcData);
	}


	public void SetVolumeSeries(List<HighstockData> volumeData) {
		setType("column");
		setName("Volume");
		setData(volumeData);
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
}

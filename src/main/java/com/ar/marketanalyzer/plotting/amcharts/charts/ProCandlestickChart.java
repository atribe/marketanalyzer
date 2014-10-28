package com.ar.marketanalyzer.plotting.amcharts.charts;

import java.util.ArrayList;
import java.util.List;

import com.ar.marketanalyzer.plotting.amcharts.buildingblock.AmGraph;
import com.ar.marketanalyzer.plotting.amcharts.buildingblock.CategoryAxis;
import com.ar.marketanalyzer.plotting.amcharts.buildingblock.ChartCursor;
import com.ar.marketanalyzer.plotting.amcharts.buildingblock.ChartScrollbar;
import com.ar.marketanalyzer.plotting.amcharts.buildingblock.ValueAxis;
import com.ar.marketanalyzer.plotting.amcharts.data.OhlcData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ProCandlestickChart extends AmChart {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5501954688052682008L;

	private List<OhlcData> dataProvider = new ArrayList<OhlcData>();
	
	public ProCandlestickChart() {
		super();
		setType("serial");
		setTheme("light");
		setCategoryField("date");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public ValueAxis getValueAxis() {
		return valueAxis;
	}

	public void setValueAxis(ValueAxis valueAxis) {
		this.valueAxis = valueAxis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPathToImages() {
		return pathToImages;
	}

	public AmGraph getGraphs() {
		return graphs;
	}

	public void setGraphs(AmGraph graphs) {
		this.graphs = graphs;
	}

	public ChartScrollbar getChartScrollbar() {
		return chartScrollbar;
	}

	public void setChartScrollbar(ChartScrollbar chartScrollbar) {
		this.chartScrollbar = chartScrollbar;
	}

	public ChartCursor getChartCursor() {
		return chartCursor;
	}

	public void setChartCursor(ChartCursor chartCursor) {
		this.chartCursor = chartCursor;
	}

	public String getCategoryField() {
		return categoryField;
	}

	public void setCategoryField(String categoryField) {
		this.categoryField = categoryField;
	}

	public CategoryAxis getCategoryAxis() {
		return categoryAxis;
	}

	public void setCategoryAxis(CategoryAxis categoryAxis) {
		this.categoryAxis = categoryAxis;
	}

	public List<OhlcData> getDataProvider() {
		return dataProvider;
	}
}

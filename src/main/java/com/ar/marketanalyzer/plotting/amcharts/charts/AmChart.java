package com.ar.marketanalyzer.plotting.amcharts.charts;

import java.io.Serializable;
import java.util.List;

import com.ar.marketanalyzer.plotting.amcharts.buildingblock.AmGraph;
import com.ar.marketanalyzer.plotting.amcharts.buildingblock.CategoryAxis;
import com.ar.marketanalyzer.plotting.amcharts.buildingblock.ChartCursor;
import com.ar.marketanalyzer.plotting.amcharts.buildingblock.ChartScrollbar;
import com.ar.marketanalyzer.plotting.amcharts.buildingblock.ValueAxis;

public abstract class AmChart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4260410492782692749L;

	protected String type;
	protected String theme;
	protected final String pathToImages = "js/amcharts/images/";
	protected ValueAxis valueAxis;
	protected AmGraph graphs;
	protected ChartScrollbar chartScrollbar;
	protected ChartCursor chartCursor;
	protected String categoryField;
	protected CategoryAxis categoryAxis;
	protected List<?> dataProvider;
	
	public AmChart() {
		valueAxis = new ValueAxis();
		graphs = new AmGraph();
		valueAxis.setPosition("left");
		chartScrollbar = new ChartScrollbar();
		chartCursor = new ChartCursor();
		categoryAxis = new CategoryAxis();
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

	public List<?> getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(List<?> dataProvider) {
		this.dataProvider = dataProvider;
	}

	public String getPathToImages() {
		return pathToImages;
	}
}

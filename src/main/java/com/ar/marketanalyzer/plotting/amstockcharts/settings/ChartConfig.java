package com.ar.marketanalyzer.plotting.amstockcharts.settings;

import java.util.ArrayList;
import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.buildingblock.PeriodSelector;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.StockGraph;
import com.ar.marketanalyzer.plotting.amstockcharts.charts.StockPanel;
import com.ar.marketanalyzer.plotting.amstockcharts.data.DataProviderInterface;
import com.ar.marketanalyzer.plotting.amstockcharts.data.DataSet;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.AmTheme;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.ChartTypeAm;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.GraphType;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.PeriodEnum;

public class ChartConfig {

	private ChartTypeAm type;
	private AmTheme theme;
	/**
	* Specifies path to the folder where images like resize grips, lens and similar are.
	* Default Value: 
	*/
	protected String pathToImages;
	private List<DataSet> dataSets = new ArrayList<DataSet>();
	private List<StockPanel> panels = new ArrayList<StockPanel>();
	private ChartScrollbarSettings chartScrollbarSettings;
	private ChartCursorSettings chartCursorSettings;
	private PeriodSelector periodSelector;
	
	public ChartConfig() {
		type = ChartTypeAm.STOCK;
		theme = AmTheme.LIGHT;
		this.pathToImages = "js/amcharts/images/";
		
		StockPanel p1 = new StockPanel(type);
		panels.add(p1);
		
		chartScrollbarSettings = new ChartScrollbarSettings();
		
		StockPanel panelTest = panels.get(0);
		StockGraph graphTest = (StockGraph)panelTest.getStockGraphs().get(0);
		String graphId = graphTest.getId();
		
		chartScrollbarSettings.setGraph(graphId);
		chartScrollbarSettings.setGraphType(GraphType.LINE);
		chartScrollbarSettings.setUsePeriod(PeriodEnum.WW);
		
		chartCursorSettings = new ChartCursorSettings();
		chartCursorSettings.setValueLineBalloonEnabled(true);
		chartCursorSettings.setValueLineEnabled(true);
		
		periodSelector = new PeriodSelector();
	}
	public ChartConfig(List<DataProviderInterface> dataProviderList) {
		this();
		
		this.dataSets.add(new DataSet(dataProviderList));
	}
	public ChartTypeAm getType() {
		return type;
	}
	public void setType(ChartTypeAm type) {
		this.type = type;
	}
	public AmTheme getTheme() {
		return theme;
	}
	public void setTheme(AmTheme theme) {
		this.theme = theme;
	}
	public List<DataSet> getDataSets() {
		return dataSets;
	}
	public void setDataSets(List<DataSet> dataSets) {
		this.dataSets = dataSets;
	}
	public List<StockPanel> getPanels() {
		return panels;
	}
	public void setPanels(List<StockPanel> panels) {
		this.panels = panels;
	}
	public ChartScrollbarSettings getChartScrollbarSettings() {
		return chartScrollbarSettings;
	}
	public void setChartScrollbarSettings(
			ChartScrollbarSettings chartScrollbarSettings) {
		this.chartScrollbarSettings = chartScrollbarSettings;
	}
	public ChartCursorSettings getChartCursorSettings() {
		return chartCursorSettings;
	}
	public void setChartCursorSettings(ChartCursorSettings chartCursorSettings) {
		this.chartCursorSettings = chartCursorSettings;
	}
	public PeriodSelector getPeriodSelector() {
		return periodSelector;
	}
	public void setPeriodSelector(PeriodSelector periodSelector) {
		this.periodSelector = periodSelector;
	}
}

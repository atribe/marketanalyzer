package com.ar.marketanalyzer.plotting.amstockcharts.settings;

import java.util.ArrayList;
import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.buildingblock.PeriodSelector;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.StockGraph;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.ValueAxis;
import com.ar.marketanalyzer.plotting.amstockcharts.charts.StockPanel;
import com.ar.marketanalyzer.plotting.amstockcharts.data.DataProviderInterface;
import com.ar.marketanalyzer.plotting.amstockcharts.data.DataSet;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.AmTheme;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.ChartTypeAm;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.GraphType;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.MarkerType;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.PeriodEnum;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Position;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ChartConfig {

	private ChartTypeAm type;
	private AmTheme theme;
	/**
	* Specifies path to the folder where images like resize grips, lens and similar are.
	* Default Value: 
	*/
	protected String pathToImages;
	private String dataDateFormat = "YYYY-MM-DD";
	private List<DataSet> dataSets = new ArrayList<DataSet>();
	private List<StockPanel> panels = new ArrayList<StockPanel>();
	private ChartScrollbarSettings chartScrollbarSettings;
	private ChartCursorSettings chartCursorSettings;
	private PeriodSelector periodSelector;
	
	
	public ChartConfig() {
		type = ChartTypeAm.stock;
		//theme = AmTheme.light;
		this.pathToImages = "js/amcharts/images/";
		
		
		
		GraphType p1Type = GraphType.candlestick;
		StockPanel p1 = new StockPanel(p1Type);
		p1.setPercentHeight(70.0);
		p1.setShowCategoryAxis(false);
		p1.setTitle("Value");
		p1.getValueAxes().add( new ValueAxis("v1"));
		StockGraph g1 = new StockGraph(p1Type, "plot1");
		g1.setValueGraphSettings();
		p1.getStockGraphs().add( g1 );
		p1.getLegend().setValueTextRegular("undefined");
		p1.getLegend().setPeriodValueTextComparing("[[percents.value.close]]%");
		panels.add(p1);
		
		GraphType p2Type = GraphType.column;
		StockPanel p2 = new StockPanel(GraphType.column);
		p2.setPercentHeight(30.0);
		p2.setShowCategoryAxis(true);
		p2.setMarginTop(1.0);
		p2.setTitle("Volume");
		p2.getValueAxes().add( new ValueAxis());
		StockGraph g2 = new StockGraph(p2Type);
		g2.setVolumeGraphSettings();
		p2.getStockGraphs().add( g2 );
		p2.getLegend().setPeriodValueTextComparing("[[value.close]]");
		p2.getLegend().setMarkerSize(0.0);
		p2.getLegend().setLabelText("");
		p2.getLegend().setMarkerType(MarkerType.none);
		panels.add(p2);
		
		chartScrollbarSettings = new ChartScrollbarSettings();
		
		String graphId = panels.get(0).getStockGraphs().get(0).getId();
		
		chartScrollbarSettings.setGraph(graphId);
		chartScrollbarSettings.setGraphType(GraphType.line);
		chartScrollbarSettings.setUsePeriod(PeriodEnum.WW);
		
		chartCursorSettings = new ChartCursorSettings();
		chartCursorSettings.setValueLineBalloonEnabled(true);
		chartCursorSettings.setValueLineEnabled(true);
		
		
		periodSelector = new PeriodSelector();
		periodSelector.setPosition(Position.bottom);
	}
	public ChartConfig(List<DataProviderInterface> dataProviderList) {
		this();
		
		this.dataSets.add(new DataSet(dataProviderList));
	}
	public String getPathToImages() {
		return pathToImages;
	}
	public void setPathToImages(String pathToImages) {
		this.pathToImages = pathToImages;
	}
	public String getDataDateFormat() {
		return dataDateFormat;
	}
	public void setDataDateFormat(String dataDateFormat) {
		this.dataDateFormat = dataDateFormat;
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

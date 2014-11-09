package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.buildingblock.AmExport;
import com.ar.marketanalyzer.plotting.amstockcharts.buildingblock.PeriodSelector;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.AmBalloon;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.StockGraph;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.ValueAxis;
import com.ar.marketanalyzer.plotting.amstockcharts.data.DataProviderInterface;
import com.ar.marketanalyzer.plotting.amstockcharts.data.DataSet;
import com.ar.marketanalyzer.plotting.amstockcharts.data.DataSetSelector;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.ChartTypeAm;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.GraphType;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.MarkerType;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.PeriodEnum;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Position;
import com.ar.marketanalyzer.plotting.amstockcharts.settings.CategoryAxesSettings;
import com.ar.marketanalyzer.plotting.amstockcharts.settings.ChartCursorSettings;
import com.ar.marketanalyzer.plotting.amstockcharts.settings.ChartScrollbarSettings;
import com.ar.marketanalyzer.plotting.amstockcharts.settings.LegendSettings;
import com.ar.marketanalyzer.plotting.amstockcharts.settings.PanelsSettings;
import com.ar.marketanalyzer.plotting.amstockcharts.settings.StockEventsSettings;
import com.ar.marketanalyzer.plotting.amstockcharts.settings.ValueAxesSettings;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmStockChart {

	/**
	* Read-only. Type of the chart.
	* Default Value: 
	*/
	private ChartTypeAm type;
	/**
	* Specifies path to the folder where images like resize grips, lens and similar are.
	* Default Value: 
	*/
	private String pathToImages;
	/**
	* Data provider of data set can have dates as Date Objects or as Strings. In case you use strings, you need to set data date format and the chart will parse dates to date objects. Check this page for date formatting strings.
	* Please note that two-digit years (YY) is NOT supported in this setting.
	* Default Value: 
	*/
	private String dataDateFormat;
	/**
	* Array of DataSets.
	* Default Value: 
	*/
	private List<DataSet> dataSets;
	/**
	* AmExport object.
	* Default Value: 
	*/
	private AmExport amExport;
	/**
	* Specifies if animation was already played. Animation is only played once, when chart is rendered for the first time. If you want the animation to be repeated, set this property to false.
	* Default Value: 
	*/
	private Boolean animationPlayed;
	/**
	* Balloon object.
	* Default Value: AmBalloon
	*/
	private AmBalloon balloon;
	/**
	* Settings for category axes.
	* Default Value: CategoryAxesSettings
	*/
	private CategoryAxesSettings categoryAxesSettings;
	/**
	* Read-only. Indicates if the chart is created.
	* Default Value: 
	*/
	private Boolean chartCreated;
	/**
	* Chart cursor settings.
	* Default Value: ChartCursorSettings
	*/
	private ChartCursorSettings chartCursorSettings;
	/**
	* Chart scrollbar settings.
	* Default Value: ChartScrollbarSettings
	*/
	private ChartScrollbarSettings chartScrollbarSettings;
	/**
	* Array of colors used by data sets if no color was set explicitly on data set itself.
	* Default Value: [""#FF6600"", ""#FCD202"", ""#B0DE09"", ""#0D8ECF"", ""#2A0CD0"", ""#CD0D74"", ""#CC0000"", ""#00CC00"", ""#0000CC"", ""#DDDDDD"", ""#999999"", ""#333333"", ""#990000""]
	*/
	private List<Color> colors;
	/**
	* Array of data sets selected for comparing.
	* Default Value: 
	*/
	private List<DataSet> comparedDataSets;
	/**
	* DataSetSelector object. You can add it if you have more than one data set and want users to be able to select/compare them.
	* Default Value: 
	*/
	private DataSetSelector dataSetSelector;
	/**
	* Read-only. Current end date of the selected period, get only. To set start/end dates, use stockChart.zoom(startDate, endDate) method.
	* Default Value: 
	*/
	private Date endDate;
	/**
	* Object of export config. Will enable saving chart as an image for all modern browsers except IE9 (IE10+ works fine).
	* Default Value: 
	*/
	private Object exportConfig;
	/**
	* Specifies if the chart should always display full first and last data item when data is grouped to a longer period if the chart is zoomed from the beginning or end of the data.
	* Default Value: TRUE
	*/
	private Boolean extendToFullPeriod;
	/**
	* Defines on which day week starts. 0 - Sunday, 1 - Monday...
	* Default Value: 1
	*/
	private Double firstDayOfWeek;
	/**
	* If set to true the scope of the data view will be set to the end after data update.
	* Default Value: FALSE
	*/
	private Boolean glueToTheEnd;
	/**
	* Legend settings.
	* Default Value: LegendSettings
	*/
	private LegendSettings legendSettings;
	/**
	* Data set selected as main.
	* Default Value: 
	*/
	private DataSet mainDataSet;
	/**
	* Specifies if scrolling of a chart with mouse wheel is enabled.
	* Default Value: FALSE
	*/
	private Boolean mouseWheelScrollEnabled;
	/**
	* Array of StockPanels (charts).
	* Default Value: 
	*/
	private List<StockPanel> panels;
	/**
	* Settings for stock panels.
	* Default Value: PanelsSettings
	*/
	private PanelsSettings panelsSettings;
	/**
	* Period selector object. You can add it if you want user's to be able to enter date ranges or zoom chart with predefined period buttons.
	* Default Value: 
	*/
	private PeriodSelector periodSelector;
	/**
	* Read-only. Scrollbar's chart object.
	* Default Value: 
	*/
	private AmSerialChart scrollbarChart;
	/**
	* Read-only. Current start date of the selected period. To set start/end dates, use stockChart.zoom(startDate, endDate) method.
	* Default Value: 
	*/
	private Date startDate;
	/**
	* Settings for stock events.
	* Default Value: StockEventsSettings
	*/
	private StockEventsSettings stockEventsSettings;
	/**
	* Settings for value axes.
	* Default Value: ValueAxesSettings
	*/
	private ValueAxesSettings valueAxesSettings;
	/**
	* Read-only. Indicates current version of a script.
	* Default Value: 
	*/
	private String version;
	/**
	* Specifies whether the chart should zoom-out when main data set is changed.
	* Default Value: FALSE
	*/
	private Boolean zoomOutOnDataSetChange;
	
	/*
	 * Constructors
	 */
	public AmStockChart() {
		type = ChartTypeAm.stock;
		this.pathToImages = "js/amcharts/images/";
		dataDateFormat = "YYYY-MM-DD";
		
		panels = new ArrayList<StockPanel>();
		GraphType p1Type = GraphType.candlestick;
		StockPanel p1 = new StockPanel(p1Type);
		p1.setPercentHeight(70.0);
		p1.setShowCategoryAxis(false);
		p1.setTitle("Value");
		p1.getValueAxes().add( new ValueAxis("v1"));
		StockGraph g1 = new StockGraph(p1Type, "plot1");
		g1.setValueGraphSettings();
		p1.getStockGraphs().add( g1 );
		p1.getStockLegend().setValueTextRegular("undefined");
		p1.getStockLegend().setPeriodValueTextComparing("[[percents.value.close]]%");
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
		p2.getStockLegend().setPeriodValueTextComparing("[[value.close]]");
		p2.getStockLegend().setMarkerSize(0.0);
		p2.getStockLegend().setLabelText("");
		p2.getStockLegend().setMarkerType(MarkerType.none);
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
	public AmStockChart(List<DataProviderInterface> dataProviderList) {
		this();
		
		dataSets = new ArrayList<DataSet>();
		this.dataSets.add(new DataSet(dataProviderList));
	}
	
	/*
	 * Getters and Setters
	 */
	public AmExport getAmExport() {
		return amExport;
	}
	public void setAmExport(AmExport amExport) {
		this.amExport = amExport;
	}
	public Boolean isAnimationPlayed() {
		return animationPlayed;
	}
	public void setAnimationPlayed(Boolean animationPlayed) {
		this.animationPlayed = animationPlayed;
	}
	public AmBalloon getBalloon() {
		return balloon;
	}
	public void setBalloon(AmBalloon balloon) {
		this.balloon = balloon;
	}
	public CategoryAxesSettings getCategoryAxesSettings() {
		return categoryAxesSettings;
	}
	public void setCategoryAxesSettings(CategoryAxesSettings categoryAxesSettings) {
		this.categoryAxesSettings = categoryAxesSettings;
	}
	public Boolean isChartCreated() {
		return chartCreated;
	}
	public void setChartCreated(Boolean chartCreated) {
		this.chartCreated = chartCreated;
	}
	public ChartCursorSettings getChartCursorSettings() {
		return chartCursorSettings;
	}
	public void setChartCursorSettings(ChartCursorSettings chartCursorSettings) {
		this.chartCursorSettings = chartCursorSettings;
	}
	public ChartScrollbarSettings getChartScrollbarSettings() {
		return chartScrollbarSettings;
	}
	public void setChartScrollbarSettings(
			ChartScrollbarSettings chartScrollbarSettings) {
		this.chartScrollbarSettings = chartScrollbarSettings;
	}
	public List<Color> getColors() {
		return colors;
	}
	public void setColors(List<Color> colors) {
		this.colors = colors;
	}
	public List<DataSet> getComparedDataSets() {
		return comparedDataSets;
	}
	public void setComparedDataSets(List<DataSet> comparedDataSets) {
		this.comparedDataSets = comparedDataSets;
	}
	public String getDataDateFormat() {
		return dataDateFormat;
	}
	public void setDataDateFormat(String dataDateFormat) {
		this.dataDateFormat = dataDateFormat;
	}
	public List<DataSet> getDataSets() {
		return dataSets;
	}
	public void setDataSets(List<DataSet> dataSets) {
		this.dataSets = dataSets;
	}
	public DataSetSelector getDataSetSelector() {
		return dataSetSelector;
	}
	public void setDataSetSelector(DataSetSelector dataSetSelector) {
		this.dataSetSelector = dataSetSelector;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Object getExportConfig() {
		return exportConfig;
	}
	public void setExportConfig(Object exportConfig) {
		this.exportConfig = exportConfig;
	}
	public Boolean isExtendToFullPeriod() {
		return extendToFullPeriod;
	}
	public void setExtendToFullPeriod(Boolean extendToFullPeriod) {
		this.extendToFullPeriod = extendToFullPeriod;
	}
	public Double getFirstDayOfWeek() {
		return firstDayOfWeek;
	}
	public void setFirstDayOfWeek(Double firstDayOfWeek) {
		this.firstDayOfWeek = firstDayOfWeek;
	}
	public Boolean isGlueToTheEnd() {
		return glueToTheEnd;
	}
	public void setGlueToTheEnd(Boolean glueToTheEnd) {
		this.glueToTheEnd = glueToTheEnd;
	}
	public LegendSettings getLegendSettings() {
		return legendSettings;
	}
	public void setLegendSettings(LegendSettings legendSettings) {
		this.legendSettings = legendSettings;
	}
	public DataSet getMainDataSet() {
		return mainDataSet;
	}
	public void setMainDataSet(DataSet mainDataSet) {
		this.mainDataSet = mainDataSet;
	}
	public Boolean isMouseWheelScrollEnabled() {
		return mouseWheelScrollEnabled;
	}
	public void setMouseWheelScrollEnabled(Boolean mouseWheelScrollEnabled) {
		this.mouseWheelScrollEnabled = mouseWheelScrollEnabled;
	}
	public List<StockPanel> getPanels() {
		return panels;
	}
	public void setPanels(List<StockPanel> panels) {
		this.panels = panels;
	}
	public PanelsSettings getPanelsSettings() {
		return panelsSettings;
	}
	public void setPanelsSettings(PanelsSettings panelsSettings) {
		this.panelsSettings = panelsSettings;
	}
	public String getPathToImages() {
		return pathToImages;
	}
	public void setPathToImages(String pathToImages) {
		this.pathToImages = pathToImages;
	}
	public PeriodSelector getPeriodSelector() {
		return periodSelector;
	}
	public void setPeriodSelector(PeriodSelector periodSelector) {
		this.periodSelector = periodSelector;
	}
	public AmSerialChart getScrollbarChart() {
		return scrollbarChart;
	}
	public void setScrollbarChart(AmSerialChart scrollbarChart) {
		this.scrollbarChart = scrollbarChart;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public StockEventsSettings getStockEventsSettings() {
		return stockEventsSettings;
	}
	public void setStockEventsSettings(StockEventsSettings stockEventsSettings) {
		this.stockEventsSettings = stockEventsSettings;
	}
	public ChartTypeAm getType() {
		return type;
	}
	public void setType(ChartTypeAm type) {
		this.type = type;
	}
	public ValueAxesSettings getValueAxesSettings() {
		return valueAxesSettings;
	}
	public void setValueAxesSettings(ValueAxesSettings valueAxesSettings) {
		this.valueAxesSettings = valueAxesSettings;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Boolean isZoomOutOnDataSetChange() {
		return zoomOutOnDataSetChange;
	}
	public void setZoomOutOnDataSetChange(Boolean zoomOutOnDataSetChange) {
		this.zoomOutOnDataSetChange = zoomOutOnDataSetChange;
	}

}

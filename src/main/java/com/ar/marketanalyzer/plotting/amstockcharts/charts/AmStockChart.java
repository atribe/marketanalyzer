package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import java.sql.Date;

import com.ar.marketanalyzer.plotting.amstockcharts.buildingblock.AmExport;

public class AmStockChart {

	/**
	* AmExport object.
	* Default Value: 
	*/
	 private AmExport amExport;
	/**
	* Specifies if animation was already played. Animation is only played once, when chart is rendered for the first time. If you want the animation to be repeated, set this property to false.
	* Default Value: 
	*/
	 private boolean animationPlayed;
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
	 private boolean chartCreated;
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
	 private Array[Color] colors;
	/**
	* Array of data sets selected for comparing.
	* Default Value: 
	*/
	 private Array[DataSet] comparedDataSets;
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
	 private Array[DataSet] dataSets;
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
	 private boolean extendToFullPeriod;
	/**
	* Defines on which day week starts. 0 - Sunday, 1 - Monday...
	* Default Value: 1
	*/
	 private double firstDayOfWeek;
	/**
	* If set to true the scope of the data view will be set to the end after data update.
	* Default Value: FALSE
	*/
	 private boolean glueToTheEnd;
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
	 private boolean mouseWheelScrollEnabled;
	/**
	* Array of StockPanels (charts).
	* Default Value: 
	*/
	 private Array[StockPanel] panels;
	/**
	* Settings for stock panels.
	* Default Value: PanelsSettings
	*/
	 private PanelsSettings panelsSettings;
	/**
	* Specifies path to the folder where images like resize grips, lens and similar are.
	* Default Value: 
	*/
	 private String pathToImages;
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
	 private 0 startDate;
	/**
	* Settings for stock events.
	* Default Value: StockEventsSettings
	*/
	 private StockEventsSettings stockEventsSettings;
	/**
	* Read-only. Type of the chart.
	* Default Value: 
	*/
	 private String type;
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
	 private boolean zoomOutOnDataSetChange;

}

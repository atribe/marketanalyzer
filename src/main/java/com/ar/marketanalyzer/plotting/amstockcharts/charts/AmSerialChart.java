package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import java.util.Date;

import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.CategoryAxis;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmSerialChart extends AmRectangularChart{

	/**
	* Date format of the graph balloon (if chart parses dates and you don't use chartCursor).
	* Default Value: MMM DD, YYYY
	*/
	protected String balloonDateFormat;
	/**
	* Read-only. Chart creates category axis itself. If you want to change some properties, you should get this axis from the chart and set properties to this object.
	* Default Value: CategoryAxis
	*/
	protected CategoryAxis categoryAxis;
	/**
	* Category field name tells the chart the name of the field in your dataProvider object which will be used for category axis values.
	* Default Value: 
	*/
	protected String categoryField;
	/**
	* The gap in pixels between two columns of the same category.
	* Default Value: 5
	*/
	protected double columnSpacing;
	/**
	* Space between 3D stacked columns.
	* Default Value: 0
	*/
	protected double columnSpacing3D;
	/**
	* Relative width of columns. Value range is 0 - 1.
	* Default Value: 0.8
	*/
	protected double columnWidth;
	/**
	* Even if your chart parses dates, you can pass them as strings in your data – all you need to do is to set data date format and the chart will parse dates to date objects. Check this page for available formats.
	* Please note that two-digit years (YY) is NOT supported in this setting.
	* Default Value: 
	*/
	protected String dataDateFormat;
	/**
	* Read-only. If category axis parses dates endDate indicates date to which the chart is currently displayed.
	* Default Value: 
	*/
	protected Date endDate;
	/**
	* Read-only. Category index to which the chart is currently displayed.
	* Default Value: 
	*/
	protected double endIndex;
	/**
	* Maximum number of series allowed to select.
	* Default Value: 
	*/
	protected double maxSelectedSeries;
	/**
	* The longest time span allowed to select (in milliseconds) for example, 259200000 will limit selection to 3 days. Works if equalSpacing is set to false (default).
	* Default Value: 
	*/
	protected double maxSelectedTime;
	/**
	* The shortest time span allowed to select (in milliseconds) for example, 1000 will limit selection to 1 second. Works if equalSpacing is set to false (default).
	* Default Value: 0
	*/
	protected double minSelectedTime;
	/**
	* Specifies if scrolling of a chart with mouse wheel is enabled. If you press shift while rotating mouse wheel, the chart will zoom-in/out.
	* Default Value: FALSE
	*/
	protected boolean mouseWheelScrollEnabled;
	/**
	* Specifies if zooming of a chart with mouse wheel is enabled. If you press shift while rotating mouse wheel, the chart will scroll.
	* Default Value: FALSE
	*/
	protected boolean mouseWheelZoomEnabled;
	/**
	* If you set this to true, the chart will be rotated by 90 degrees (the columns will become bars).
	* Default Value: FALSE
	*/
	protected boolean rotate;
	/**
	* Read-only. If category axis parses dates startDate indicates date from which the chart is currently displayed.
	* Default Value: 
	*/
	protected Date startDate;
	/**
	* Read-only. Category index from which the chart is currently displayed.
	* Default Value: 
	*/
	protected double startIndex;
	/**
	* Specifies if chart should zoom-out when data is updated.
	* Default Value: TRUE
	*/
	protected boolean zoomOutOnDataUpdate;
	
	/*
	 * Constructors
	 */
	public AmSerialChart() {
		super();
	}
	
	
	/*
	 * Getters and Setters
	 */
	public String getBalloonDateFormat() {
		return balloonDateFormat;
	}
	public void setBalloonDateFormat(String balloonDateFormat) {
		this.balloonDateFormat = balloonDateFormat;
	}
	public CategoryAxis getCategoryAxis() {
		return categoryAxis;
	}
	public void setCategoryAxis(CategoryAxis categoryAxis) {
		this.categoryAxis = categoryAxis;
	}
	public String getCategoryField() {
		return categoryField;
	}
	public void setCategoryField(String categoryField) {
		this.categoryField = categoryField;
	}
	public double getColumnSpacing() {
		return columnSpacing;
	}
	public void setColumnSpacing(double columnSpacing) {
		this.columnSpacing = columnSpacing;
	}
	public double getColumnSpacing3D() {
		return columnSpacing3D;
	}
	public void setColumnSpacing3D(double columnSpacing3D) {
		this.columnSpacing3D = columnSpacing3D;
	}
	public double getColumnWidth() {
		return columnWidth;
	}
	public void setColumnWidth(double columnWidth) {
		this.columnWidth = columnWidth;
	}
	public String getDataDateFormat() {
		return dataDateFormat;
	}
	public void setDataDateFormat(String dataDateFormat) {
		this.dataDateFormat = dataDateFormat;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public double getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(double endIndex) {
		this.endIndex = endIndex;
	}
	public double getMaxSelectedSeries() {
		return maxSelectedSeries;
	}
	public void setMaxSelectedSeries(double maxSelectedSeries) {
		this.maxSelectedSeries = maxSelectedSeries;
	}
	public double getMaxSelectedTime() {
		return maxSelectedTime;
	}
	public void setMaxSelectedTime(double maxSelectedTime) {
		this.maxSelectedTime = maxSelectedTime;
	}
	public double getMinSelectedTime() {
		return minSelectedTime;
	}
	public void setMinSelectedTime(double minSelectedTime) {
		this.minSelectedTime = minSelectedTime;
	}
	public boolean isMouseWheelScrollEnabled() {
		return mouseWheelScrollEnabled;
	}
	public void setMouseWheelScrollEnabled(boolean mouseWheelScrollEnabled) {
		this.mouseWheelScrollEnabled = mouseWheelScrollEnabled;
	}
	public boolean isMouseWheelZoomEnabled() {
		return mouseWheelZoomEnabled;
	}
	public void setMouseWheelZoomEnabled(boolean mouseWheelZoomEnabled) {
		this.mouseWheelZoomEnabled = mouseWheelZoomEnabled;
	}
	public boolean isRotate() {
		return rotate;
	}
	public void setRotate(boolean rotate) {
		this.rotate = rotate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public double getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(double startIndex) {
		this.startIndex = startIndex;
	}
	public boolean isZoomOutOnDataUpdate() {
		return zoomOutOnDataUpdate;
	}
	public void setZoomOutOnDataUpdate(boolean zoomOutOnDataUpdate) {
		this.zoomOutOnDataUpdate = zoomOutOnDataUpdate;
	}

}

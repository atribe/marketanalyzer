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
	protected Double columnSpacing;
	/**
	* Space between 3D stacked columns.
	* Default Value: 0
	*/
	protected Double columnSpacing3D;
	/**
	* Relative width of columns. Value range is 0 - 1.
	* Default Value: 0.8
	*/
	protected Double columnWidth;
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
	protected Double endIndex;
	/**
	* Maximum number of series allowed to select.
	* Default Value: 
	*/
	protected Double maxSelectedSeries;
	/**
	* The longest time span allowed to select (in milliseconds) for example, 259200000 will limit selection to 3 days. Works if equalSpacing is set to false (default).
	* Default Value: 
	*/
	protected Double maxSelectedTime;
	/**
	* The shortest time span allowed to select (in milliseconds) for example, 1000 will limit selection to 1 second. Works if equalSpacing is set to false (default).
	* Default Value: 0
	*/
	protected Double minSelectedTime;
	/**
	* Specifies if scrolling of a chart with mouse wheel is enabled. If you press shift while rotating mouse wheel, the chart will zoom-in/out.
	* Default Value: FALSE
	*/
	protected Boolean mouseWheelScrollEnabled;
	/**
	* Specifies if zooming of a chart with mouse wheel is enabled. If you press shift while rotating mouse wheel, the chart will scroll.
	* Default Value: FALSE
	*/
	protected Boolean mouseWheelZoomEnabled;
	/**
	* If you set this to true, the chart will be rotated by 90 degrees (the columns will become bars).
	* Default Value: FALSE
	*/
	protected Boolean rotate;
	/**
	* Read-only. If category axis parses dates startDate indicates date from which the chart is currently displayed.
	* Default Value: 
	*/
	protected Date startDate;
	/**
	* Read-only. Category index from which the chart is currently displayed.
	* Default Value: 
	*/
	protected Double startIndex;
	/**
	* Specifies if chart should zoom-out when data is updated.
	* Default Value: TRUE
	*/
	protected Boolean zoomOutOnDataUpdate;
	
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
	public Double getColumnSpacing() {
		return columnSpacing;
	}
	public void setColumnSpacing(Double columnSpacing) {
		this.columnSpacing = columnSpacing;
	}
	public Double getColumnSpacing3D() {
		return columnSpacing3D;
	}
	public void setColumnSpacing3D(Double columnSpacing3D) {
		this.columnSpacing3D = columnSpacing3D;
	}
	public Double getColumnWidth() {
		return columnWidth;
	}
	public void setColumnWidth(Double columnWidth) {
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
	public Double getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Double endIndex) {
		this.endIndex = endIndex;
	}
	public Double getMaxSelectedSeries() {
		return maxSelectedSeries;
	}
	public void setMaxSelectedSeries(Double maxSelectedSeries) {
		this.maxSelectedSeries = maxSelectedSeries;
	}
	public Double getMaxSelectedTime() {
		return maxSelectedTime;
	}
	public void setMaxSelectedTime(Double maxSelectedTime) {
		this.maxSelectedTime = maxSelectedTime;
	}
	public Double getMinSelectedTime() {
		return minSelectedTime;
	}
	public void setMinSelectedTime(Double minSelectedTime) {
		this.minSelectedTime = minSelectedTime;
	}
	public Boolean isMouseWheelScrollEnabled() {
		return mouseWheelScrollEnabled;
	}
	public void setMouseWheelScrollEnabled(Boolean mouseWheelScrollEnabled) {
		this.mouseWheelScrollEnabled = mouseWheelScrollEnabled;
	}
	public Boolean isMouseWheelZoomEnabled() {
		return mouseWheelZoomEnabled;
	}
	public void setMouseWheelZoomEnabled(Boolean mouseWheelZoomEnabled) {
		this.mouseWheelZoomEnabled = mouseWheelZoomEnabled;
	}
	public Boolean isRotate() {
		return rotate;
	}
	public void setRotate(Boolean rotate) {
		this.rotate = rotate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Double getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Double startIndex) {
		this.startIndex = startIndex;
	}
	public Boolean isZoomOutOnDataUpdate() {
		return zoomOutOnDataUpdate;
	}
	public void setZoomOutOnDataUpdate(Boolean zoomOutOnDataUpdate) {
		this.zoomOutOnDataUpdate = zoomOutOnDataUpdate;
	}

}

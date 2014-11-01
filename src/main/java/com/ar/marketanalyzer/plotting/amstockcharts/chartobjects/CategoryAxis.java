package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.DateFormat;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Period;

public class CategoryAxis extends AxisBase{

	/**
	* Angle of label rotation, if the number of series exceeds autoRotateCount and parseDates is set to false.
	* Default Value: 
	*/
	private double autoRotateAngle;
	/**
	* If the number of category axis items will exceed the autoRotateCount, the labels will be rotated by autoRotateAngle degree. Works only if parseDates is false.
	* Default Value: 
	*/
	private double autoRotateCount;
	/**
	* Specifies if axis labels (only when it is horizontal) should be wrapped if they don't fit in the allocated space. Wrapping won't work for rotated axis labels.
	* Default Value: FALSE
	*/
	private boolean autoWrap;
	/**
	* Specifies if axis labels should be bold or not.
	* Default Value: FALSE
	*/
	private boolean boldLabels;
	/**
	* When parse dates is on for the category axis, the chart will try to highlight the beginning of the periods, like month, in bold. Set this to false to disable the functionality.
	* Default Value: TRUE
	*/
	private boolean boldPeriodBeginning;
	/**
	* specifies a method that returns the value that should be used as categoryValue for current item. If this property is set, the return value of the custom data function takes precedence over categoryField. When a chart calls this method, it passes category value, data item from chart's data provider and reference to categoryAxis: categoryFunction(category, dataItem, categoryAxis); This method can be used both when category axis parses dates and when it doesn't. If axis parses dates, your
	* categoryFunction should return Date object. For example, if you have date strings in your data, you can use this method to convert these strings into Date objects.
	* Default Value: 
	*/
	private String categoryFunction;
	/**
	* This setting works only when parseDates is set to true and equalSpacing is set to false. In case you set it to false,
	* labels will never be centered between grid lines.
	* Default Value: TRUE
	*/
	private boolean centerLabelOnFullPeriod;
	/**
	* Date formats of different periods. Possible period values: fff - milliseconds, ss - seconds, mm - minutes, hh - hours, DD - days, MM - months, WW - weeks, YYYY - years. Check this page for date formatting strings.
	* Default Value: [{period:'fff',format:'JJ:NN:SS'},{period:'ss',format:'JJ:NN:SS'},{period:'mm',format:'JJ:NN'},{period:'hh',format:'JJ:NN'},{period:'DD',format:'MMM DD'},{period:'WW',format:'MMM DD'},{period:'MM',format:'MMM'},{period:'YYYY',format:'YYYY'}]
	*/
	private List<DateFormat> dateFormats;
	/**
	* In case your category axis values are Date objects and parseDates is set to true, the chart will parse dates and will place your data points at irregular intervals. However if you want dates to be parsed (displayed on the axis, baloons, etc), but data points to be placed at equal intervals (omiting dates with no data), set equalSpacing to true.
	* Default Value: FALSE
	*/
	private boolean equalSpacing;
	/**
	* Sets first day of the week. 0 is Sunday, 1 is Monday, etc.
	* Default Value: 1
	*/
	private double firstDayOfWeek;
	/**
	* Field in data provider which specifies if the category value should always be shown. For example: categoryAxis.forceShowField = forceShow;
	* And in data:
	*      {category:one, forceShow:true, value:100}
	* Note, this works only when parseDates is set to false.
	* Default Value: 
	*/
	private String forceShowField;
	/**
	* Specifies if a grid line is placed on the center of a cell or on the beginning of a cell. Possible values are: start and middle This setting doesn't work if parseDates is set to true.
	* Default Value: middle
	*/
	private String gridPosition;
	/**
	* You can use it to set color of a axis label. Works only with non-date-based data.
	* Default Value: 
	*/
	private String labelColorField;
	/**
	* You can use this function to format Category axis labels. If this function is set, then it is called with the following parameters passed:
	* if dates are not parsed:
	*      labelFunction(valueText, serialDataItem, categoryAxis)
	* if dates are parsed:
	*      labelFunction(valueText, date, categoryAxis)
	* Your function should return string which will be displayed on the axis. 
	* Default Value:
	*/
	private String labelFunction;
	/**
	* If you set it to false, the start of longer periods won't use a different date format and won't be bold.
	* Default Value: TRUE
	*/
	private boolean markPeriodChange;
	/**
	* Specifies the shortest period of your data. This should be set only if parseDates is set to true. Possible period values: fff - milliseconds, ss - seconds, mm - minutes, hh - hours, DD - days, MM - months, YYYY - years.
	* It's also possible to supply a number for increments, i.e. 15mm which will instruct the chart that your data is supplied in 15 minute increments.
	* Default Value: DD
	*/
	private Period minPeriod;
	/**
	* In case your category axis values are Date objects, set this to true. In this case the chart will parse dates and will place your data points at irregular intervals. If you want dates to be parsed, but data points to be placed at equal intervals, set both parseDates and equalSpacing to true.
	* Default Value: FALSE
	*/
	private boolean parseDates;
	/**
	* Possible values are: top, bottom, left, right. If axis is vertical, default position is left. If axis is horizontal, default position is bottom.
	* Default Value: bottom
	*/
	private String position;
	/**
	* Specifies whether the graph should start on axis or not. In case you display columns, it is recommended to set this to false. If parseDates is set to true, startOnAxis will allways be false, unless equalSpacing is set to true.
	* Default Value: FALSE
	*/
	private boolean startOnAxis;
	/**
	* Position of a axis tick. Works only with non-date-based data.
	* Default Value: middle
	*/
	private String tickPosition;
	/**
	* Works only when parseDates is set to true and equalSpacing is false. If you set it to true, at the position where bigger period changes, category axis will display date strings of bot small and big period, in two rows.
	* Default Value: FALSE
	*/
	private boolean twoLineMode;
	
	public double getAutoRotateAngle() {
		return autoRotateAngle;
	}
	public void setAutoRotateAngle(double autoRotateAngle) {
		this.autoRotateAngle = autoRotateAngle;
	}
	public double getAutoRotateCount() {
		return autoRotateCount;
	}
	public void setAutoRotateCount(double autoRotateCount) {
		this.autoRotateCount = autoRotateCount;
	}
	public boolean isAutoWrap() {
		return autoWrap;
	}
	public void setAutoWrap(boolean autoWrap) {
		this.autoWrap = autoWrap;
	}
	public boolean isBoldLabels() {
		return boldLabels;
	}
	public void setBoldLabels(boolean boldLabels) {
		this.boldLabels = boldLabels;
	}
	public boolean isBoldPeriodBeginning() {
		return boldPeriodBeginning;
	}
	public void setBoldPeriodBeginning(boolean boldPeriodBeginning) {
		this.boldPeriodBeginning = boldPeriodBeginning;
	}
	public String getCategoryFunction() {
		return categoryFunction;
	}
	public void setCategoryFunction(String categoryFunction) {
		this.categoryFunction = categoryFunction;
	}
	public boolean isCenterLabelOnFullPeriod() {
		return centerLabelOnFullPeriod;
	}
	public void setCenterLabelOnFullPeriod(boolean centerLabelOnFullPeriod) {
		this.centerLabelOnFullPeriod = centerLabelOnFullPeriod;
	}
	public List<DateFormat> getDateFormats() {
		return dateFormats;
	}
	public void setDateFormats(List<DateFormat> dateFormats) {
		this.dateFormats = dateFormats;
	}
	public boolean isEqualSpacing() {
		return equalSpacing;
	}
	public void setEqualSpacing(boolean equalSpacing) {
		this.equalSpacing = equalSpacing;
	}
	public double getFirstDayOfWeek() {
		return firstDayOfWeek;
	}
	public void setFirstDayOfWeek(double firstDayOfWeek) {
		this.firstDayOfWeek = firstDayOfWeek;
	}
	public String getForceShowField() {
		return forceShowField;
	}
	public void setForceShowField(String forceShowField) {
		this.forceShowField = forceShowField;
	}
	public String getGridPosition() {
		return gridPosition;
	}
	public void setGridPosition(String gridPosition) {
		this.gridPosition = gridPosition;
	}
	public String getLabelColorField() {
		return labelColorField;
	}
	public void setLabelColorField(String labelColorField) {
		this.labelColorField = labelColorField;
	}
	public String getLabelFunction() {
		return labelFunction;
	}
	public void setLabelFunction(String labelFunction) {
		this.labelFunction = labelFunction;
	}
	public boolean isMarkPeriodChange() {
		return markPeriodChange;
	}
	public void setMarkPeriodChange(boolean markPeriodChange) {
		this.markPeriodChange = markPeriodChange;
	}
	public Period getMinPeriod() {
		return minPeriod;
	}
	public void setMinPeriod(Period minPeriod) {
		this.minPeriod = minPeriod;
	}
	public boolean isParseDates() {
		return parseDates;
	}
	public void setParseDates(boolean parseDates) {
		this.parseDates = parseDates;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public boolean isStartOnAxis() {
		return startOnAxis;
	}
	public void setStartOnAxis(boolean startOnAxis) {
		this.startOnAxis = startOnAxis;
	}
	public String getTickPosition() {
		return tickPosition;
	}
	public void setTickPosition(String tickPosition) {
		this.tickPosition = tickPosition;
	}
	public boolean isTwoLineMode() {
		return twoLineMode;
	}
	public void setTwoLineMode(boolean twoLineMode) {
		this.twoLineMode = twoLineMode;
	}
}

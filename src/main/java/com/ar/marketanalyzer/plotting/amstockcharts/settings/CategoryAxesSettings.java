package com.ar.marketanalyzer.plotting.amstockcharts.settings;

import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.DateFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class CategoryAxesSettings {

	/**
	* Specifies whether number of gridCount is specified automatically, according to the axis size.
	* Default Value: TRUE
	*/
	 private boolean autoGridCount;
	/**
	* Axis opacity.
	* Default Value: 0
	*/
	 private double axisAlpha;
	/**
	* Axis color.
	* Default Value: 
	*/
	 private Color axisColor;
	/**
	* Height of category axes. Set it to 0 if you set inside property to true.
	* Default Value: 28
	*/
	 private double axisHeight;
	/**
	* Thickness of the axis.
	* Default Value: 
	*/
	 private double axisThickness;
	/**
	* Text color.
	* Default Value: 
	*/
	 private Color color;
	/**
	* Length of a dash.
	* Default Value: 
	*/
	 private double dashLength;
	/**
	* Date formats of different periods. Possible period values: fff - milliseconds, ss - seconds, mm - minutes, hh - hours, DD - days, MM - months, WW - weeks, YYYY - years. Check this page for date formatting strings.
	* Default Value: 
	*/
	 private List<DateFormat> dateFormats;
	/**
	* If you want data points to be placed at equal intervals (omiting dates with no data), set equalSpacing to true.
	* Default Value: FALSE
	*/
	 private boolean equalSpacing;
	/**
	* Fill opacity. Every second space between grid lines can be filled with fillColor.
	* Default Value: 
	*/
	 private double fillAlpha;
	/**
	* Fill color. Every second space between grid lines can be filled with color. Set fillAlpha to a value greater than 0 to see the fills.
	* Default Value: 
	*/
	 private Color fillColor;
	/**
	* Text size.
	* Default Value: 
	*/
	 private double fontSize;
	/**
	* Opacity of grid lines.
	* Default Value: 
	*/
	 private double gridAlpha;
	/**
	* Color of grid lines.
	* Default Value: 
	*/
	 private Color gridColor;
	/**
	* Approximate number of grid lines. You should set autoGridCount to false in order this property not to be ignored.
	* Default Value: 10
	*/
	 private double gridCount;
	/**
	* Thickness of grid lines.
	* Default Value: 
	*/
	 private double gridThickness;
	/**
	* Periods to which data will be gruoped in case there are more data items in the selected period than specified in maxSeries property.
	* Default Value: [""ss"", ""10ss"", ""30ss"", ""mm"", ""10mm"", ""30mm"", ""hh"", ""DD"", ""WW"", ""MM"", ""YYYY""]
	*/
	 private List<String> groupToPeriods;
	/**
	* Specifies whether values should be placed inside or outside of plot area.
	* Default Value: FALSE
	*/
	 private boolean inside;
	/**
	* Rotation angle of a label.
	* Default Value: 
	*/
	 private double labelRotation;
	/**
	* Specifies whether axis displays category axis' labels and value axis' values.
	* Default Value: TRUE
	*/
	 private boolean labelsEnabled;
	/**
	* Specifies if period period should be marked with a different date format.
	* Default Value: TRUE
	*/
	 private boolean markPeriodChange;
	/**
	* Maximum series shown at a time. In case there are more data points in the selection than maxSeries, the chart will group data to longer periods, for example - you have 250 days in the selection, and maxSeries is 150 - the chart will group data to weeks.
	* Default Value: 150
	*/
	 private double maxSeries;
	/**
	* This property is used when calculating grid count. It specifies minimum cell width required for one span between grid lines.
	* Default Value: 75
	*/
	 private double minHorizontalGap;
	/**
	* Specifies the shortest period of your data. fff - millisecond, ss - second, mm - minute, hh - hour, DD - day, MM - month, YYYY - year.
	* It's also possible to supply a number for increments, i.e. ""15mm"" which will instruct the chart that your data is supplied in 15 minute increments.
	* Default Value: DD
	*/
	 private String minPeriod;
	/**
	* ""top"" or ""bottom"".
	* Default Value: 
	*/
	 private String position;
	/**
	* Specifies whether the graph should start on axis or not. In case you display columns, it is recommended to set this to false. startOnAxis can be set to true only if equalSpacing is set to true.
	* Default Value: FALSE
	*/
	 private boolean startOnAxis;
	/**
	* Tick length.
	* Default Value: 0
	*/
	 private double tickLength;
	/**
	* Works only when parseDates is set to true and equalSpacing is false. If you set it to true, at the position where bigger period changes, category axis will display date strings of bot small and big period, in two rows.
	* Default Value: FALSE
	*/
	 private boolean twoLineMode;
	public boolean isAutoGridCount() {
		return autoGridCount;
	}
	public void setAutoGridCount(boolean autoGridCount) {
		this.autoGridCount = autoGridCount;
	}
	public double getAxisAlpha() {
		return axisAlpha;
	}
	public void setAxisAlpha(double axisAlpha) {
		this.axisAlpha = axisAlpha;
	}
	public Color getAxisColor() {
		return axisColor;
	}
	public void setAxisColor(Color axisColor) {
		this.axisColor = axisColor;
	}
	public double getAxisHeight() {
		return axisHeight;
	}
	public void setAxisHeight(double axisHeight) {
		this.axisHeight = axisHeight;
	}
	public double getAxisThickness() {
		return axisThickness;
	}
	public void setAxisThickness(double axisThickness) {
		this.axisThickness = axisThickness;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getDashLength() {
		return dashLength;
	}
	public void setDashLength(double dashLength) {
		this.dashLength = dashLength;
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
	public double getFillAlpha() {
		return fillAlpha;
	}
	public void setFillAlpha(double fillAlpha) {
		this.fillAlpha = fillAlpha;
	}
	public Color getFillColor() {
		return fillColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	public double getFontSize() {
		return fontSize;
	}
	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
	}
	public double getGridAlpha() {
		return gridAlpha;
	}
	public void setGridAlpha(double gridAlpha) {
		this.gridAlpha = gridAlpha;
	}
	public Color getGridColor() {
		return gridColor;
	}
	public void setGridColor(Color gridColor) {
		this.gridColor = gridColor;
	}
	public double getGridCount() {
		return gridCount;
	}
	public void setGridCount(double gridCount) {
		this.gridCount = gridCount;
	}
	public double getGridThickness() {
		return gridThickness;
	}
	public void setGridThickness(double gridThickness) {
		this.gridThickness = gridThickness;
	}
	public List<String> getGroupToPeriods() {
		return groupToPeriods;
	}
	public void setGroupToPeriods(List<String> groupToPeriods) {
		this.groupToPeriods = groupToPeriods;
	}
	public boolean isInside() {
		return inside;
	}
	public void setInside(boolean inside) {
		this.inside = inside;
	}
	public double getLabelRotation() {
		return labelRotation;
	}
	public void setLabelRotation(double labelRotation) {
		this.labelRotation = labelRotation;
	}
	public boolean isLabelsEnabled() {
		return labelsEnabled;
	}
	public void setLabelsEnabled(boolean labelsEnabled) {
		this.labelsEnabled = labelsEnabled;
	}
	public boolean isMarkPeriodChange() {
		return markPeriodChange;
	}
	public void setMarkPeriodChange(boolean markPeriodChange) {
		this.markPeriodChange = markPeriodChange;
	}
	public double getMaxSeries() {
		return maxSeries;
	}
	public void setMaxSeries(double maxSeries) {
		this.maxSeries = maxSeries;
	}
	public double getMinHorizontalGap() {
		return minHorizontalGap;
	}
	public void setMinHorizontalGap(double minHorizontalGap) {
		this.minHorizontalGap = minHorizontalGap;
	}
	public String getMinPeriod() {
		return minPeriod;
	}
	public void setMinPeriod(String minPeriod) {
		this.minPeriod = minPeriod;
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
	public double getTickLength() {
		return tickLength;
	}
	public void setTickLength(double tickLength) {
		this.tickLength = tickLength;
	}
	public boolean isTwoLineMode() {
		return twoLineMode;
	}
	public void setTwoLineMode(boolean twoLineMode) {
		this.twoLineMode = twoLineMode;
	}

}

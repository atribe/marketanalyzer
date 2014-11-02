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
	 private Boolean autoGridCount;
	/**
	* Axis opacity.
	* Default Value: 0
	*/
	 private Double axisAlpha;
	/**
	* Axis color.
	* Default Value: 
	*/
	 private Color axisColor;
	/**
	* Height of category axes. Set it to 0 if you set inside property to true.
	* Default Value: 28
	*/
	 private Double axisHeight;
	/**
	* Thickness of the axis.
	* Default Value: 
	*/
	 private Double axisThickness;
	/**
	* Text color.
	* Default Value: 
	*/
	 private Color color;
	/**
	* Length of a dash.
	* Default Value: 
	*/
	 private Double dashLength;
	/**
	* Date formats of different periods. Possible period values: fff - milliseconds, ss - seconds, mm - minutes, hh - hours, DD - days, MM - months, WW - weeks, YYYY - years. Check this page for date formatting strings.
	* Default Value: 
	*/
	 private List<DateFormat> dateFormats;
	/**
	* If you want data points to be placed at equal intervals (omiting dates with no data), set equalSpacing to true.
	* Default Value: FALSE
	*/
	 private Boolean equalSpacing;
	/**
	* Fill opacity. Every second space between grid lines can be filled with fillColor.
	* Default Value: 
	*/
	 private Double fillAlpha;
	/**
	* Fill color. Every second space between grid lines can be filled with color. Set fillAlpha to a value greater than 0 to see the fills.
	* Default Value: 
	*/
	 private Color fillColor;
	/**
	* Text size.
	* Default Value: 
	*/
	 private Double fontSize;
	/**
	* Opacity of grid lines.
	* Default Value: 
	*/
	 private Double gridAlpha;
	/**
	* Color of grid lines.
	* Default Value: 
	*/
	 private Color gridColor;
	/**
	* Approximate number of grid lines. You should set autoGridCount to false in order this property not to be ignored.
	* Default Value: 10
	*/
	 private Double gridCount;
	/**
	* Thickness of grid lines.
	* Default Value: 
	*/
	 private Double gridThickness;
	/**
	* Periods to which data will be gruoped in case there are more data items in the selected period than specified in maxSeries property.
	* Default Value: [""ss"", ""10ss"", ""30ss"", ""mm"", ""10mm"", ""30mm"", ""hh"", ""DD"", ""WW"", ""MM"", ""YYYY""]
	*/
	 private List<String> groupToPeriods;
	/**
	* Specifies whether values should be placed inside or outside of plot area.
	* Default Value: FALSE
	*/
	 private Boolean inside;
	/**
	* Rotation angle of a label.
	* Default Value: 
	*/
	 private Double labelRotation;
	/**
	* Specifies whether axis displays category axis' labels and value axis' values.
	* Default Value: TRUE
	*/
	 private Boolean labelsEnabled;
	/**
	* Specifies if period period should be marked with a different date format.
	* Default Value: TRUE
	*/
	 private Boolean markPeriodChange;
	/**
	* Maximum series shown at a time. In case there are more data points in the selection than maxSeries, the chart will group data to longer periods, for example - you have 250 days in the selection, and maxSeries is 150 - the chart will group data to weeks.
	* Default Value: 150
	*/
	 private Double maxSeries;
	/**
	* This property is used when calculating grid count. It specifies minimum cell width required for one span between grid lines.
	* Default Value: 75
	*/
	 private Double minHorizontalGap;
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
	 private Boolean startOnAxis;
	/**
	* Tick length.
	* Default Value: 0
	*/
	 private Double tickLength;
	/**
	* Works only when parseDates is set to true and equalSpacing is false. If you set it to true, at the position where bigger period changes, category axis will display date strings of bot small and big period, in two rows.
	* Default Value: FALSE
	*/
	 private Boolean twoLineMode;
	public Boolean isAutoGridCount() {
		return autoGridCount;
	}
	public void setAutoGridCount(Boolean autoGridCount) {
		this.autoGridCount = autoGridCount;
	}
	public Double getAxisAlpha() {
		return axisAlpha;
	}
	public void setAxisAlpha(Double axisAlpha) {
		this.axisAlpha = axisAlpha;
	}
	public Color getAxisColor() {
		return axisColor;
	}
	public void setAxisColor(Color axisColor) {
		this.axisColor = axisColor;
	}
	public Double getAxisHeight() {
		return axisHeight;
	}
	public void setAxisHeight(Double axisHeight) {
		this.axisHeight = axisHeight;
	}
	public Double getAxisThickness() {
		return axisThickness;
	}
	public void setAxisThickness(Double axisThickness) {
		this.axisThickness = axisThickness;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Double getDashLength() {
		return dashLength;
	}
	public void setDashLength(Double dashLength) {
		this.dashLength = dashLength;
	}
	public List<DateFormat> getDateFormats() {
		return dateFormats;
	}
	public void setDateFormats(List<DateFormat> dateFormats) {
		this.dateFormats = dateFormats;
	}
	public Boolean isEqualSpacing() {
		return equalSpacing;
	}
	public void setEqualSpacing(Boolean equalSpacing) {
		this.equalSpacing = equalSpacing;
	}
	public Double getFillAlpha() {
		return fillAlpha;
	}
	public void setFillAlpha(Double fillAlpha) {
		this.fillAlpha = fillAlpha;
	}
	public Color getFillColor() {
		return fillColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	public Double getFontSize() {
		return fontSize;
	}
	public void setFontSize(Double fontSize) {
		this.fontSize = fontSize;
	}
	public Double getGridAlpha() {
		return gridAlpha;
	}
	public void setGridAlpha(Double gridAlpha) {
		this.gridAlpha = gridAlpha;
	}
	public Color getGridColor() {
		return gridColor;
	}
	public void setGridColor(Color gridColor) {
		this.gridColor = gridColor;
	}
	public Double getGridCount() {
		return gridCount;
	}
	public void setGridCount(Double gridCount) {
		this.gridCount = gridCount;
	}
	public Double getGridThickness() {
		return gridThickness;
	}
	public void setGridThickness(Double gridThickness) {
		this.gridThickness = gridThickness;
	}
	public List<String> getGroupToPeriods() {
		return groupToPeriods;
	}
	public void setGroupToPeriods(List<String> groupToPeriods) {
		this.groupToPeriods = groupToPeriods;
	}
	public Boolean isInside() {
		return inside;
	}
	public void setInside(Boolean inside) {
		this.inside = inside;
	}
	public Double getLabelRotation() {
		return labelRotation;
	}
	public void setLabelRotation(Double labelRotation) {
		this.labelRotation = labelRotation;
	}
	public Boolean isLabelsEnabled() {
		return labelsEnabled;
	}
	public void setLabelsEnabled(Boolean labelsEnabled) {
		this.labelsEnabled = labelsEnabled;
	}
	public Boolean isMarkPeriodChange() {
		return markPeriodChange;
	}
	public void setMarkPeriodChange(Boolean markPeriodChange) {
		this.markPeriodChange = markPeriodChange;
	}
	public Double getMaxSeries() {
		return maxSeries;
	}
	public void setMaxSeries(Double maxSeries) {
		this.maxSeries = maxSeries;
	}
	public Double getMinHorizontalGap() {
		return minHorizontalGap;
	}
	public void setMinHorizontalGap(Double minHorizontalGap) {
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
	public Boolean isStartOnAxis() {
		return startOnAxis;
	}
	public void setStartOnAxis(Boolean startOnAxis) {
		this.startOnAxis = startOnAxis;
	}
	public Double getTickLength() {
		return tickLength;
	}
	public void setTickLength(Double tickLength) {
		this.tickLength = tickLength;
	}
	public Boolean isTwoLineMode() {
		return twoLineMode;
	}
	public void setTwoLineMode(Boolean twoLineMode) {
		this.twoLineMode = twoLineMode;
	}

}

package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class GaugeAxis {

	/**
	* Axis opacity.
	* Default Value: 1
	*/
	private double axisAlpha;
	/**
	* Axis color.
	* Default Value: #000000
	*/
	private Color axisColor;
	/**
	* Thickness of the axis outline.
	* Default Value: 1
	*/
	private double axisThickness;
	/**
	* Opacity of band fills.
	* Default Value: 1
	*/
	private double bandAlpha;
	/**
	* Opacity of band outlines.
	* Default Value: 0
	*/
	private double bandOutlineAlpha;
	/**
	* Color of band outlines.
	* Default Value: #000000
	*/
	private Color bandOutlineColor;
	/**
	* Thickness of band outlines.
	* Default Value: 0
	*/
	private double bandOutlineThickness;
	/**
	* Array of bands - GaugeBand objects. Bands are used to draw color fills between specified values.
	* Default Value: 
	*/
	private List<GaugeBand> bands;
	
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
	public double getAxisThickness() {
		return axisThickness;
	}
	public void setAxisThickness(double axisThickness) {
		this.axisThickness = axisThickness;
	}
	public double getBandAlpha() {
		return bandAlpha;
	}
	public void setBandAlpha(double bandAlpha) {
		this.bandAlpha = bandAlpha;
	}
	public double getBandOutlineAlpha() {
		return bandOutlineAlpha;
	}
	public void setBandOutlineAlpha(double bandOutlineAlpha) {
		this.bandOutlineAlpha = bandOutlineAlpha;
	}
	public Color getBandOutlineColor() {
		return bandOutlineColor;
	}
	public void setBandOutlineColor(Color bandOutlineColor) {
		this.bandOutlineColor = bandOutlineColor;
	}
	public double getBandOutlineThickness() {
		return bandOutlineThickness;
	}
	public void setBandOutlineThickness(double bandOutlineThickness) {
		this.bandOutlineThickness = bandOutlineThickness;
	}
	public List<GaugeBand> getBands() {
		return bands;
	}
	public void setBands(List<GaugeBand> bands) {
		this.bands = bands;
	}
	public String getBottomText() {
		return bottomText;
	}
	public void setBottomText(String bottomText) {
		this.bottomText = bottomText;
	}
	public boolean isBottomTextBold() {
		return bottomTextBold;
	}
	public void setBottomTextBold(boolean bottomTextBold) {
		this.bottomTextBold = bottomTextBold;
	}
	public Color getBottomTextColor() {
		return bottomTextColor;
	}
	public void setBottomTextColor(Color bottomTextColor) {
		this.bottomTextColor = bottomTextColor;
	}
	public double getBottomTextFontSize() {
		return bottomTextFontSize;
	}
	public void setBottomTextFontSize(double bottomTextFontSize) {
		this.bottomTextFontSize = bottomTextFontSize;
	}
	public double getBottomTextYOffset() {
		return bottomTextYOffset;
	}
	public void setBottomTextYOffset(double bottomTextYOffset) {
		this.bottomTextYOffset = bottomTextYOffset;
	}
	public double getCenterX() {
		return centerX;
	}
	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}
	public double getCenterY() {
		return centerY;
	}
	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}
	public double getEndAngle() {
		return endAngle;
	}
	public void setEndAngle(double endAngle) {
		this.endAngle = endAngle;
	}
	public double getEndValue() {
		return endValue;
	}
	public void setEndValue(double endValue) {
		this.endValue = endValue;
	}
	public double getGridCount() {
		return gridCount;
	}
	public void setGridCount(double gridCount) {
		this.gridCount = gridCount;
	}
	public boolean isGridInside() {
		return gridInside;
	}
	public void setGridInside(boolean gridInside) {
		this.gridInside = gridInside;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isInside() {
		return inside;
	}
	public void setInside(boolean inside) {
		this.inside = inside;
	}
	public double getLabelFrequency() {
		return labelFrequency;
	}
	public void setLabelFrequency(double labelFrequency) {
		this.labelFrequency = labelFrequency;
	}
	public double getLabelOffset() {
		return labelOffset;
	}
	public void setLabelOffset(double labelOffset) {
		this.labelOffset = labelOffset;
	}
	public double getMinorTickInterval() {
		return minorTickInterval;
	}
	public void setMinorTickInterval(double minorTickInterval) {
		this.minorTickInterval = minorTickInterval;
	}
	public double getMinorTickLength() {
		return minorTickLength;
	}
	public void setMinorTickLength(double minorTickLength) {
		this.minorTickLength = minorTickLength;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public boolean isShowFirstLabel() {
		return showFirstLabel;
	}
	public void setShowFirstLabel(boolean showFirstLabel) {
		this.showFirstLabel = showFirstLabel;
	}
	public boolean isShowLastLabel() {
		return showLastLabel;
	}
	public void setShowLastLabel(boolean showLastLabel) {
		this.showLastLabel = showLastLabel;
	}
	public double getStartAngle() {
		return startAngle;
	}
	public void setStartAngle(double startAngle) {
		this.startAngle = startAngle;
	}
	public double getStartValue() {
		return startValue;
	}
	public void setStartValue(double startValue) {
		this.startValue = startValue;
	}
	public double getTickAlpha() {
		return tickAlpha;
	}
	public void setTickAlpha(double tickAlpha) {
		this.tickAlpha = tickAlpha;
	}
	public Color getTickColor() {
		return tickColor;
	}
	public void setTickColor(Color tickColor) {
		this.tickColor = tickColor;
	}
	public double getTickLength() {
		return tickLength;
	}
	public void setTickLength(double tickLength) {
		this.tickLength = tickLength;
	}
	public double getTickThickness() {
		return tickThickness;
	}
	public void setTickThickness(double tickThickness) {
		this.tickThickness = tickThickness;
	}
	public String getTopText() {
		return topText;
	}
	public void setTopText(String topText) {
		this.topText = topText;
	}
	public boolean isTopTextBold() {
		return topTextBold;
	}
	public void setTopTextBold(boolean topTextBold) {
		this.topTextBold = topTextBold;
	}
	public Color getTopTextColor() {
		return topTextColor;
	}
	public void setTopTextColor(Color topTextColor) {
		this.topTextColor = topTextColor;
	}
	public double getTopTextFontSize() {
		return topTextFontSize;
	}
	public void setTopTextFontSize(double topTextFontSize) {
		this.topTextFontSize = topTextFontSize;
	}
	public double getTopTextYOffset() {
		return topTextYOffset;
	}
	public void setTopTextYOffset(double topTextYOffset) {
		this.topTextYOffset = topTextYOffset;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitPosition() {
		return unitPosition;
	}
	public void setUnitPosition(String unitPosition) {
		this.unitPosition = unitPosition;
	}
	public boolean isUsePrefixes() {
		return usePrefixes;
	}
	public void setUsePrefixes(boolean usePrefixes) {
		this.usePrefixes = usePrefixes;
	}
	public double getValueInterval() {
		return valueInterval;
	}
	public void setValueInterval(double valueInterval) {
		this.valueInterval = valueInterval;
	}
	/**
	* Text displayed below the axis center.
	* Default Value: 
	*/
	private String bottomText;
	/**
	* Specifies if text should be bold.
	* Default Value: TRUE
	*/
	private boolean bottomTextBold;
	/**
	* Bottom text color.
	* Default Value: 
	*/
	private Color bottomTextColor;
	/**
	* Font size of bottom text.
	* Default Value: 
	*/
	private double bottomTextFontSize;
	/**
	* Y offset of bottom text.
	* Default Value: 0
	*/
	private double bottomTextYOffset;
	/**
	* X position of the axis, relative to the center of the gauge.
	* Default Value: 0
	*/
	private double centerX;
	/**
	* Y position of the axis, relative to the center of the gauge.
	* Default Value: 0
	*/
	private double centerY;
	/**
	* Axis end angle. Valid values are from - 180 to 180.
	* Default Value: 120
	*/
	private double endAngle;
	/**
	* Axis end (max) value
	* Default Value: 
	*/
	private double endValue;
	/**
	* Number of grid lines. Note, GaugeAxis doesn't adjust gridCount, so you should check your values and choose a proper gridCount which would result grids at round numbers.
	* Default Value: 5
	*/
	private double gridCount;
	/**
	* Specifies if grid should be drawn inside or outside the axis.
	* Default Value: TRUE
	*/
	private boolean gridInside;
	/**
	* Unique id of an axis.
	* Default Value: 
	*/
	private String id;
	/**
	* Specifies if labels should be placed inside or outside the axis.
	* Default Value: TRUE
	*/
	private boolean inside;
	/**
	* Frequency of labels.
	* Default Value: 1
	*/
	private double labelFrequency;
	/**
	* Distance from axis to the labels.
	* Default Value: 15
	*/
	private double labelOffset;
	/**
	* Interval, at which minor ticks should be placed.
	* Default Value: 
	*/
	private double minorTickInterval;
	/**
	* Length of a minor tick.
	* Default Value: 5
	*/
	private double minorTickLength;
	/**
	* Axis radius.
	* Default Value: 0.95
	*/
	private double radius;
	/**
	* Specifies if the first label should be shown.
	* Default Value: TRUE
	*/
	private boolean showFirstLabel;
	/**
	* Specifies if the last label should be shown.
	* Default Value: TRUE
	*/
	private boolean showLastLabel;
	/**
	* Axis start angle. Valid values are from - 180 to 180.
	* Default Value: -120
	*/
	private double startAngle;
	/**
	* Axis start (min) value.
	* Default Value: 0
	*/
	private double startValue;
	/**
	* Opacity of axis ticks.
	* Default Value: 1
	*/
	private double tickAlpha;
	/**
	* Color of axis ticks.
	* Default Value: #555555
	*/
	private Color tickColor;
	/**
	* Length of a major tick.
	* Default Value: 10
	*/
	private double tickLength;
	/**
	* Tick thickness.
	* Default Value: 1
	*/
	private double tickThickness;
	/**
	* Text displayed above the axis center.
	* Default Value: 
	*/
	private String topText;
	/**
	* Specifies if text should be bold.
	* Default Value: TRUE
	*/
	private boolean topTextBold;
	/**
	* Color of top text.
	* Default Value: 
	*/
	private Color topTextColor;
	/**
	* Font size of top text.
	* Default Value: 
	*/
	private double topTextFontSize;
	/**
	* Y offset of top text.
	* Default Value: 0
	*/
	private double topTextYOffset;
	/**
	* A string which can be placed next to axis labels.
	* Default Value: 
	*/
	private String unit;
	/**
	* Position of the unit.
	* Default Value: right
	*/
	private String unitPosition;
	/**
	* Specifies if small and big numbers should use prefixes to make them more readable.
	* Default Value: FALSE
	*/
	private boolean usePrefixes;
	/**
	* Interval, at which ticks with values should be placed.
	* Default Value: 
	*/
	private double valueInterval;

}

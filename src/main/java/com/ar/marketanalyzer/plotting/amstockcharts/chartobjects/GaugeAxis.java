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
	private Double axisAlpha;
	/**
	* Axis color.
	* Default Value: #000000
	*/
	private Color axisColor;
	/**
	* Thickness of the axis outline.
	* Default Value: 1
	*/
	private Double axisThickness;
	/**
	* Opacity of band fills.
	* Default Value: 1
	*/
	private Double bandAlpha;
	/**
	* Opacity of band outlines.
	* Default Value: 0
	*/
	private Double bandOutlineAlpha;
	/**
	* Color of band outlines.
	* Default Value: #000000
	*/
	private Color bandOutlineColor;
	/**
	* Thickness of band outlines.
	* Default Value: 0
	*/
	private Double bandOutlineThickness;
	/**
	* Array of bands - GaugeBand objects. Bands are used to draw color fills between specified values.
	* Default Value: 
	*/
	private List<GaugeBand> bands;
	
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
	public Double getAxisThickness() {
		return axisThickness;
	}
	public void setAxisThickness(Double axisThickness) {
		this.axisThickness = axisThickness;
	}
	public Double getBandAlpha() {
		return bandAlpha;
	}
	public void setBandAlpha(Double bandAlpha) {
		this.bandAlpha = bandAlpha;
	}
	public Double getBandOutlineAlpha() {
		return bandOutlineAlpha;
	}
	public void setBandOutlineAlpha(Double bandOutlineAlpha) {
		this.bandOutlineAlpha = bandOutlineAlpha;
	}
	public Color getBandOutlineColor() {
		return bandOutlineColor;
	}
	public void setBandOutlineColor(Color bandOutlineColor) {
		this.bandOutlineColor = bandOutlineColor;
	}
	public Double getBandOutlineThickness() {
		return bandOutlineThickness;
	}
	public void setBandOutlineThickness(Double bandOutlineThickness) {
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
	public Boolean isBottomTextBold() {
		return bottomTextBold;
	}
	public void setBottomTextBold(Boolean bottomTextBold) {
		this.bottomTextBold = bottomTextBold;
	}
	public Color getBottomTextColor() {
		return bottomTextColor;
	}
	public void setBottomTextColor(Color bottomTextColor) {
		this.bottomTextColor = bottomTextColor;
	}
	public Double getBottomTextFontSize() {
		return bottomTextFontSize;
	}
	public void setBottomTextFontSize(Double bottomTextFontSize) {
		this.bottomTextFontSize = bottomTextFontSize;
	}
	public Double getBottomTextYOffset() {
		return bottomTextYOffset;
	}
	public void setBottomTextYOffset(Double bottomTextYOffset) {
		this.bottomTextYOffset = bottomTextYOffset;
	}
	public Double getCenterX() {
		return centerX;
	}
	public void setCenterX(Double centerX) {
		this.centerX = centerX;
	}
	public Double getCenterY() {
		return centerY;
	}
	public void setCenterY(Double centerY) {
		this.centerY = centerY;
	}
	public Double getEndAngle() {
		return endAngle;
	}
	public void setEndAngle(Double endAngle) {
		this.endAngle = endAngle;
	}
	public Double getEndValue() {
		return endValue;
	}
	public void setEndValue(Double endValue) {
		this.endValue = endValue;
	}
	public Double getGridCount() {
		return gridCount;
	}
	public void setGridCount(Double gridCount) {
		this.gridCount = gridCount;
	}
	public Boolean isGridInside() {
		return gridInside;
	}
	public void setGridInside(Boolean gridInside) {
		this.gridInside = gridInside;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean isInside() {
		return inside;
	}
	public void setInside(Boolean inside) {
		this.inside = inside;
	}
	public Double getLabelFrequency() {
		return labelFrequency;
	}
	public void setLabelFrequency(Double labelFrequency) {
		this.labelFrequency = labelFrequency;
	}
	public Double getLabelOffset() {
		return labelOffset;
	}
	public void setLabelOffset(Double labelOffset) {
		this.labelOffset = labelOffset;
	}
	public Double getMinorTickInterval() {
		return minorTickInterval;
	}
	public void setMinorTickInterval(Double minorTickInterval) {
		this.minorTickInterval = minorTickInterval;
	}
	public Double getMinorTickLength() {
		return minorTickLength;
	}
	public void setMinorTickLength(Double minorTickLength) {
		this.minorTickLength = minorTickLength;
	}
	public Double getRadius() {
		return radius;
	}
	public void setRadius(Double radius) {
		this.radius = radius;
	}
	public Boolean isShowFirstLabel() {
		return showFirstLabel;
	}
	public void setShowFirstLabel(Boolean showFirstLabel) {
		this.showFirstLabel = showFirstLabel;
	}
	public Boolean isShowLastLabel() {
		return showLastLabel;
	}
	public void setShowLastLabel(Boolean showLastLabel) {
		this.showLastLabel = showLastLabel;
	}
	public Double getStartAngle() {
		return startAngle;
	}
	public void setStartAngle(Double startAngle) {
		this.startAngle = startAngle;
	}
	public Double getStartValue() {
		return startValue;
	}
	public void setStartValue(Double startValue) {
		this.startValue = startValue;
	}
	public Double getTickAlpha() {
		return tickAlpha;
	}
	public void setTickAlpha(Double tickAlpha) {
		this.tickAlpha = tickAlpha;
	}
	public Color getTickColor() {
		return tickColor;
	}
	public void setTickColor(Color tickColor) {
		this.tickColor = tickColor;
	}
	public Double getTickLength() {
		return tickLength;
	}
	public void setTickLength(Double tickLength) {
		this.tickLength = tickLength;
	}
	public Double getTickThickness() {
		return tickThickness;
	}
	public void setTickThickness(Double tickThickness) {
		this.tickThickness = tickThickness;
	}
	public String getTopText() {
		return topText;
	}
	public void setTopText(String topText) {
		this.topText = topText;
	}
	public Boolean isTopTextBold() {
		return topTextBold;
	}
	public void setTopTextBold(Boolean topTextBold) {
		this.topTextBold = topTextBold;
	}
	public Color getTopTextColor() {
		return topTextColor;
	}
	public void setTopTextColor(Color topTextColor) {
		this.topTextColor = topTextColor;
	}
	public Double getTopTextFontSize() {
		return topTextFontSize;
	}
	public void setTopTextFontSize(Double topTextFontSize) {
		this.topTextFontSize = topTextFontSize;
	}
	public Double getTopTextYOffset() {
		return topTextYOffset;
	}
	public void setTopTextYOffset(Double topTextYOffset) {
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
	public Boolean isUsePrefixes() {
		return usePrefixes;
	}
	public void setUsePrefixes(Boolean usePrefixes) {
		this.usePrefixes = usePrefixes;
	}
	public Double getValueInterval() {
		return valueInterval;
	}
	public void setValueInterval(Double valueInterval) {
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
	private Boolean bottomTextBold;
	/**
	* Bottom text color.
	* Default Value: 
	*/
	private Color bottomTextColor;
	/**
	* Font size of bottom text.
	* Default Value: 
	*/
	private Double bottomTextFontSize;
	/**
	* Y offset of bottom text.
	* Default Value: 0
	*/
	private Double bottomTextYOffset;
	/**
	* X position of the axis, relative to the center of the gauge.
	* Default Value: 0
	*/
	private Double centerX;
	/**
	* Y position of the axis, relative to the center of the gauge.
	* Default Value: 0
	*/
	private Double centerY;
	/**
	* Axis end angle. Valid values are from - 180 to 180.
	* Default Value: 120
	*/
	private Double endAngle;
	/**
	* Axis end (max) value
	* Default Value: 
	*/
	private Double endValue;
	/**
	* Number of grid lines. Note, GaugeAxis doesn't adjust gridCount, so you should check your values and choose a proper gridCount which would result grids at round numbers.
	* Default Value: 5
	*/
	private Double gridCount;
	/**
	* Specifies if grid should be drawn inside or outside the axis.
	* Default Value: TRUE
	*/
	private Boolean gridInside;
	/**
	* Unique id of an axis.
	* Default Value: 
	*/
	private String id;
	/**
	* Specifies if labels should be placed inside or outside the axis.
	* Default Value: TRUE
	*/
	private Boolean inside;
	/**
	* Frequency of labels.
	* Default Value: 1
	*/
	private Double labelFrequency;
	/**
	* Distance from axis to the labels.
	* Default Value: 15
	*/
	private Double labelOffset;
	/**
	* Interval, at which minor ticks should be placed.
	* Default Value: 
	*/
	private Double minorTickInterval;
	/**
	* Length of a minor tick.
	* Default Value: 5
	*/
	private Double minorTickLength;
	/**
	* Axis radius.
	* Default Value: 0.95
	*/
	private Double radius;
	/**
	* Specifies if the first label should be shown.
	* Default Value: TRUE
	*/
	private Boolean showFirstLabel;
	/**
	* Specifies if the last label should be shown.
	* Default Value: TRUE
	*/
	private Boolean showLastLabel;
	/**
	* Axis start angle. Valid values are from - 180 to 180.
	* Default Value: -120
	*/
	private Double startAngle;
	/**
	* Axis start (min) value.
	* Default Value: 0
	*/
	private Double startValue;
	/**
	* Opacity of axis ticks.
	* Default Value: 1
	*/
	private Double tickAlpha;
	/**
	* Color of axis ticks.
	* Default Value: #555555
	*/
	private Color tickColor;
	/**
	* Length of a major tick.
	* Default Value: 10
	*/
	private Double tickLength;
	/**
	* Tick thickness.
	* Default Value: 1
	*/
	private Double tickThickness;
	/**
	* Text displayed above the axis center.
	* Default Value: 
	*/
	private String topText;
	/**
	* Specifies if text should be bold.
	* Default Value: TRUE
	*/
	private Boolean topTextBold;
	/**
	* Color of top text.
	* Default Value: 
	*/
	private Color topTextColor;
	/**
	* Font size of top text.
	* Default Value: 
	*/
	private Double topTextFontSize;
	/**
	* Y offset of top text.
	* Default Value: 0
	*/
	private Double topTextYOffset;
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
	private Boolean usePrefixes;
	/**
	* Interval, at which ticks with values should be placed.
	* Default Value: 
	*/
	private Double valueInterval;

}

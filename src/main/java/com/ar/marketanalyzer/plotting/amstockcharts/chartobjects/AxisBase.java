package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class AxisBase {

	/**
	* Specifies whether number of gridCount is specified automatically, acoarding to the axis size.
	* Default Value: TRUE
	*/
	protected boolean autoGridCount;
	/**
	* Axis opacity. Value range is 0 - 1.
	* Default Value: 1
	*/
	protected double axisAlpha;
	/**
	* Axis color.
	* Default Value: #000000
	*/
	protected Color axisColor;
	/**
	* Thickness of the axis.
	* Default Value: 1
	*/
	protected double axisThickness;
	/**
	* Read-only. Returns x coordinate of the axis.
	* Default Value: 
	*/
	protected double axisX;
	/**
	* Read-only. Returns y coordinate of the axis.
	* Default Value: 
	*/
	protected double axisY;
	/**
	* Specifies if axis labels should be bold or not.
	* Default Value: FALSE
	*/
	protected boolean boldLabels;
	/**
	* Color of axis value labels. Will use chart's color if not set.
	* Default Value: 
	*/
	protected Color color;
	/**
	* Length of a dash. 0 means line is not dashed.
	* Default Value: 0
	*/
	protected double dashLength;
	/**
	* Fill opacity. Every second space between grid lines can be filled with color. Set fillAlpha to a value greater than 0 to see the fills.
	* Default Value: 0
	*/
	protected double fillAlpha;
	/**
	* Fill color. Every second space between grid lines can be filled with color. Set fillAlpha to a value greater than 0 to see the fills.
	* Default Value: #FFFFFF
	*/
	protected Color fillColor;
	/**
	* Size of value labels text. Will use chart's fontSize if not set.
	* Default Value: 
	*/
	protected double fontSize;
	/**
	* Opacity of grid lines.
	* Default Value: 0.15
	*/
	protected double gridAlpha;
	/**
	* Color of grid lines.
	* Default Value: #000000
	*/
	protected Color gridColor;
	/**
	* Number of grid lines. In case this is value axis, or your categoryAxis parses dates, the number is approximate. The default value is 5. If you set autoGridCount to true, this property is ignored.
	* Default Value: 5
	*/
	protected double gridCount;
	/**
	* Thickness of grid lines.
	* Default Value: 1
	*/
	protected double gridThickness;
	/**
	* The array of guides belonging to this axis.
	* Default Value: []
	*/
	protected List<Guide> guides;
	/**
	* If autoMargins of a chart is set to true, but you want this axis not to be measured when calculating margin, set ignoreAxisWidth to true.
	* Default Value: FALSE
	*/
	protected boolean ignoreAxisWidth;
	/**
	* Specifies whether values should be placed inside or outside plot area.
	* Default Value: FALSE
	*/
	protected boolean inside;
	/**
	* Frequency at which labels should be placed. Doesn't work for CategoryAxis if parseDates is set to true.
	* Default Value: 1
	*/
	protected double labelFrequency;
	/**
	* You can use it to adjust position of axes labels. Works both withCategoryAxis and ValueAxis.
	* Default Value: 0
	*/
	protected double labelOffset;
	/**
	* Rotation angle of a label. Only horizontal axis' values can be rotated. If you set this for vertical axis, the setting will be ignored. Possible values from -90 to 90.
	* Default Value: 0
	*/
	protected double labelRotation;
	/**
	* Specifies whether axis displays category axis' labels and value axis' values.
	* Default Value: TRUE
	*/
	protected boolean labelsEnabled;
	/**
	* This property is used when calculating grid count (when autoGridCount is true). It specifies minimum cell width required for one span between grid lines.
	* Default Value: 75
	*/
	protected double minHorizontalGap;
	/**
	* Opacity of minor grid. In order minor to be visible, you should set minorGridEnabled to true.
	* Default Value: 0.07
	*/
	protected double minorGridAlpha;
	/**
	* Specifies if minor grid should be displayed.
	* Default Value: FALSE
	*/
	protected boolean minorGridEnabled;
	/**
	* This property is used when calculating grid count (when autoGridCount is true). It specifies minimum cell height required for one span between grid lines.
	* Default Value: 35
	*/
	protected double minVerticalGap;
	/**
	* The distance of the axis to the plot area, in pixels. Negative values can also be used.
	* Default Value: 0
	*/
	protected double offset;
	/**
	* Whether to show first axis label or not. This works properly only on ValueAxis. WithCategoryAxis it wont work 100%, it depends on the period, zooming, etc. There is no guaranteed way to force category axis to show or hide first label.
	* Default Value: TRUE
	*/
	protected boolean showFirstLabel;
	/**
	* Whether to show last axis label or not. This works properly only on ValueAxis. WithCategoryAxis it wont work 100%, it depends on the period, zooming, etc. There is no guaranteed way to force category axis to show or hide last label.
	* Default Value: TRUE
	*/
	protected boolean showLastLabel;
	/**
	* Length of the tick marks.
	* Default Value: 5
	*/
	protected double tickLength;
	/**
	* Title of the axis.
	* Default Value: 
	*/
	protected String title;
	/**
	* Specifies if title should be bold or not.
	* Default Value: TRUE
	*/
	protected boolean titleBold;
	/**
	* Color of axis title. Will use text color of chart if not set any.
	* Default Value: 
	*/
	protected Color titleColor;
	/**
	* Font size of axis title. Will use font size of chart plus two pixels if not set any.
	* Default Value: 
	*/
	protected double titleFontSize;
	/**
	* Axis opacity. Value range is 0 - 1.
	* Default Value: 1
	*/
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
	public double getAxisThickness() {
		return axisThickness;
	}
	public void setAxisThickness(double axisThickness) {
		this.axisThickness = axisThickness;
	}
	public double getAxisX() {
		return axisX;
	}
	public void setAxisX(double axisX) {
		this.axisX = axisX;
	}
	public double getAxisY() {
		return axisY;
	}
	public void setAxisY(double axisY) {
		this.axisY = axisY;
	}
	public boolean isBoldLabels() {
		return boldLabels;
	}
	public void setBoldLabels(boolean boldLabels) {
		this.boldLabels = boldLabels;
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
	public List<Guide> getGuides() {
		return guides;
	}
	public void setGuides(List<Guide> guides) {
		this.guides = guides;
	}
	public boolean isIgnoreAxisWidth() {
		return ignoreAxisWidth;
	}
	public void setIgnoreAxisWidth(boolean ignoreAxisWidth) {
		this.ignoreAxisWidth = ignoreAxisWidth;
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
	public double getMinHorizontalGap() {
		return minHorizontalGap;
	}
	public void setMinHorizontalGap(double minHorizontalGap) {
		this.minHorizontalGap = minHorizontalGap;
	}
	public double getMinorGridAlpha() {
		return minorGridAlpha;
	}
	public void setMinorGridAlpha(double minorGridAlpha) {
		this.minorGridAlpha = minorGridAlpha;
	}
	public boolean isMinorGridEnabled() {
		return minorGridEnabled;
	}
	public void setMinorGridEnabled(boolean minorGridEnabled) {
		this.minorGridEnabled = minorGridEnabled;
	}
	public double getMinVerticalGap() {
		return minVerticalGap;
	}
	public void setMinVerticalGap(double minVerticalGap) {
		this.minVerticalGap = minVerticalGap;
	}
	public double getOffset() {
		return offset;
	}
	public void setOffset(double offset) {
		this.offset = offset;
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
	public double getTickLength() {
		return tickLength;
	}
	public void setTickLength(double tickLength) {
		this.tickLength = tickLength;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isTitleBold() {
		return titleBold;
	}
	public void setTitleBold(boolean titleBold) {
		this.titleBold = titleBold;
	}
	public Color getTitleColor() {
		return titleColor;
	}
	public void setTitleColor(Color titleColor) {
		this.titleColor = titleColor;
	}
	public double getTitleFontSize() {
		return titleFontSize;
	}
	public void setTitleFontSize(double titleFontSize) {
		this.titleFontSize = titleFontSize;
	}	
}

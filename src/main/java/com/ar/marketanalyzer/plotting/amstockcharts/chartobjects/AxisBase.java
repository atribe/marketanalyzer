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
	protected Boolean autoGridCount;
	/**
	* Axis opacity. Value range is 0 - 1.
	* Default Value: 1
	*/
	protected Double axisAlpha;
	/**
	* Axis color.
	* Default Value: #000000
	*/
	protected Color axisColor;
	/**
	* Thickness of the axis.
	* Default Value: 1
	*/
	protected Double axisThickness;
	/**
	* Read-only. Returns x coordinate of the axis.
	* Default Value: 
	*/
	protected Double axisX;
	/**
	* Read-only. Returns y coordinate of the axis.
	* Default Value: 
	*/
	protected Double axisY;
	/**
	* Specifies if axis labels should be bold or not.
	* Default Value: FALSE
	*/
	protected Boolean boldLabels;
	/**
	* Color of axis value labels. Will use chart's color if not set.
	* Default Value: 
	*/
	protected Color color;
	/**
	* Length of a dash. 0 means line is not dashed.
	* Default Value: 0
	*/
	protected Double dashLength;
	/**
	* Fill opacity. Every second space between grid lines can be filled with color. Set fillAlpha to a value greater than 0 to see the fills.
	* Default Value: 0
	*/
	protected Double fillAlpha;
	/**
	* Fill color. Every second space between grid lines can be filled with color. Set fillAlpha to a value greater than 0 to see the fills.
	* Default Value: #FFFFFF
	*/
	protected Color fillColor;
	/**
	* Size of value labels text. Will use chart's fontSize if not set.
	* Default Value: 
	*/
	protected Double fontSize;
	/**
	* Opacity of grid lines.
	* Default Value: 0.15
	*/
	protected Double gridAlpha;
	/**
	* Color of grid lines.
	* Default Value: #000000
	*/
	protected Color gridColor;
	/**
	* Number of grid lines. In case this is value axis, or your categoryAxis parses dates, the number is approximate. The default value is 5. If you set autoGridCount to true, this property is ignored.
	* Default Value: 5
	*/
	protected Double gridCount;
	/**
	* Thickness of grid lines.
	* Default Value: 1
	*/
	protected Double gridThickness;
	/**
	* The array of guides belonging to this axis.
	* Default Value: []
	*/
	protected List<Guide> guides;
	/**
	* If autoMargins of a chart is set to true, but you want this axis not to be measured when calculating margin, set ignoreAxisWidth to true.
	* Default Value: FALSE
	*/
	protected Boolean ignoreAxisWidth;
	/**
	* Specifies whether values should be placed inside or outside plot area.
	* Default Value: FALSE
	*/
	protected Boolean inside;
	/**
	* Frequency at which labels should be placed. Doesn't work for CategoryAxis if parseDates is set to true.
	* Default Value: 1
	*/
	protected Double labelFrequency;
	/**
	* You can use it to adjust position of axes labels. Works both withCategoryAxis and ValueAxis.
	* Default Value: 0
	*/
	protected Double labelOffset;
	/**
	* Rotation angle of a label. Only horizontal axis' values can be rotated. If you set this for vertical axis, the setting will be ignored. Possible values from -90 to 90.
	* Default Value: 0
	*/
	protected Double labelRotation;
	/**
	* Specifies whether axis displays category axis' labels and value axis' values.
	* Default Value: TRUE
	*/
	protected Boolean labelsEnabled;
	/**
	* This property is used when calculating grid count (when autoGridCount is true). It specifies minimum cell width required for one span between grid lines.
	* Default Value: 75
	*/
	protected Double minHorizontalGap;
	/**
	* Opacity of minor grid. In order minor to be visible, you should set minorGridEnabled to true.
	* Default Value: 0.07
	*/
	protected Double minorGridAlpha;
	/**
	* Specifies if minor grid should be displayed.
	* Default Value: FALSE
	*/
	protected Boolean minorGridEnabled;
	/**
	* This property is used when calculating grid count (when autoGridCount is true). It specifies minimum cell height required for one span between grid lines.
	* Default Value: 35
	*/
	protected Double minVerticalGap;
	/**
	* The distance of the axis to the plot area, in pixels. Negative values can also be used.
	* Default Value: 0
	*/
	protected Double offset;
	/**
	* Whether to show first axis label or not. This works properly only on ValueAxis. WithCategoryAxis it wont work 100%, it depends on the period, zooming, etc. There is no guaranteed way to force category axis to show or hide first label.
	* Default Value: TRUE
	*/
	protected Boolean showFirstLabel;
	/**
	* Whether to show last axis label or not. This works properly only on ValueAxis. WithCategoryAxis it wont work 100%, it depends on the period, zooming, etc. There is no guaranteed way to force category axis to show or hide last label.
	* Default Value: TRUE
	*/
	protected Boolean showLastLabel;
	/**
	* Length of the tick marks.
	* Default Value: 5
	*/
	protected Double tickLength;
	/**
	* Title of the axis.
	* Default Value: 
	*/
	protected String title;
	/**
	* Specifies if title should be bold or not.
	* Default Value: TRUE
	*/
	protected Boolean titleBold;
	/**
	* Color of axis title. Will use text color of chart if not set any.
	* Default Value: 
	*/
	protected Color titleColor;
	/**
	* Font size of axis title. Will use font size of chart plus two pixels if not set any.
	* Default Value: 
	*/
	protected Double titleFontSize;
	/**
	* Axis opacity. Value range is 0 - 1.
	* Default Value: 1
	*/
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
	public Double getAxisThickness() {
		return axisThickness;
	}
	public void setAxisThickness(Double axisThickness) {
		this.axisThickness = axisThickness;
	}
	public Double getAxisX() {
		return axisX;
	}
	public void setAxisX(Double axisX) {
		this.axisX = axisX;
	}
	public Double getAxisY() {
		return axisY;
	}
	public void setAxisY(Double axisY) {
		this.axisY = axisY;
	}
	public Boolean isBoldLabels() {
		return boldLabels;
	}
	public void setBoldLabels(Boolean boldLabels) {
		this.boldLabels = boldLabels;
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
	public List<Guide> getGuides() {
		return guides;
	}
	public void setGuides(List<Guide> guides) {
		this.guides = guides;
	}
	public Boolean isIgnoreAxisWidth() {
		return ignoreAxisWidth;
	}
	public void setIgnoreAxisWidth(Boolean ignoreAxisWidth) {
		this.ignoreAxisWidth = ignoreAxisWidth;
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
	public Double getMinHorizontalGap() {
		return minHorizontalGap;
	}
	public void setMinHorizontalGap(Double minHorizontalGap) {
		this.minHorizontalGap = minHorizontalGap;
	}
	public Double getMinorGridAlpha() {
		return minorGridAlpha;
	}
	public void setMinorGridAlpha(Double minorGridAlpha) {
		this.minorGridAlpha = minorGridAlpha;
	}
	public Boolean isMinorGridEnabled() {
		return minorGridEnabled;
	}
	public void setMinorGridEnabled(Boolean minorGridEnabled) {
		this.minorGridEnabled = minorGridEnabled;
	}
	public Double getMinVerticalGap() {
		return minVerticalGap;
	}
	public void setMinVerticalGap(Double minVerticalGap) {
		this.minVerticalGap = minVerticalGap;
	}
	public Double getOffset() {
		return offset;
	}
	public void setOffset(Double offset) {
		this.offset = offset;
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
	public Double getTickLength() {
		return tickLength;
	}
	public void setTickLength(Double tickLength) {
		this.tickLength = tickLength;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean isTitleBold() {
		return titleBold;
	}
	public void setTitleBold(Boolean titleBold) {
		this.titleBold = titleBold;
	}
	public Color getTitleColor() {
		return titleColor;
	}
	public void setTitleColor(Color titleColor) {
		this.titleColor = titleColor;
	}
	public Double getTitleFontSize() {
		return titleFontSize;
	}
	public void setTitleFontSize(Double titleFontSize) {
		this.titleFontSize = titleFontSize;
	}	
}

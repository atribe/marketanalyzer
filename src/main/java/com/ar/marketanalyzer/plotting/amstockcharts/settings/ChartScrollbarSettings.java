package com.ar.marketanalyzer.plotting.amstockcharts.settings;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.GraphType;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.PeriodEnum;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.PositionVertical;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ChartScrollbarSettings {
	/**
	* Specifies whether number of gridCount is specified automatically, according to the axis size.
	* Default Value: TRUE
	*/
	private Boolean autoGridCount;
	/**
	* Background opacity.
	* Default Value: 
	*/
	private Double backgroundAlpha;
	/**
	* Background color of the scrollbar.
	* Default Value: 
	*/
	private Color backgroundColor;
	/**
	* Text color.
	* Default Value: 
	*/
	private Color color;
	/**
	* Height of resize grip image. Note, you should also update the image in amcharts/images folder if you don't want it to be distorted because of resizing.
	* Default Value: 18
	*/
	private Double dragIconHeight;
	/**
	* Width of resize grip image. Note, you should also update the image in amcharts/images folder if you don't want it to be distorted because of resizing.
	* Default Value: 11
	*/
	private Double dragIconWidth;
	/**
	* Set false if you don't need scrollbar.
	* Default Value: TRUE
	*/
	private Boolean enabled;
	/**
	* Font size.
	* Default Value: 
	*/
	private Double fontSize;
	/**
	* Specifies which graph will be displayed in the scrollbar.
	* This is the graph ID.
	* Default Value: 
	*/
	private String graph;
	/**
	* Graph fill opacity.
	* Default Value: 
	*/
	private Double graphFillAlpha;
	/**
	* Graph fill color.
	* Default Value: 
	*/
	private Color graphFillColor;
	/**
	* Graph line opacity.
	* Default Value: 
	*/
	private Double graphLineAlpha;
	/**
	* Graph line color.
	* Default Value: 
	*/
	private Color graphLineColor;
	/**
	* Type of chart scrollbar's graph. By default the graph type is the same as the original graph's type, however in case of candlestick or ohlc you might want to show line graph in the scrollbar. Possible values are: line, column, step, smoothedLine, candlestick, ohlc.
	* Default Value: 
	*/
	private GraphType graphType;
	/**
	* Grid opacity.
	* Default Value: 
	*/
	private Double gridAlpha;
	/**
	* Grid color.
	* Default Value: 
	*/
	private Color gridColor;
	/**
	* Grid count. You should set autoGridCount to false in order this property to work.
	* Default Value: 
	*/
	private Double gridCount;
	/**
	* Height of scrollbar, in pixels.
	* Default Value: 40
	*/
	private Double height;
	/**
	* Specifies whether resize grips are hidden when mouse is away from the scrollbar.
	* Default Value: FALSE
	*/
	private Boolean hideResizeGrips;
	/**
	* Specifies if category axis of scrollbar should mark period change with a different date format.
	* Default Value: 
	*/
	private Boolean markPeriodChange;
	/**
	* Position of a scrollbar. Possible values are top and bottom.
	* Default Value: bottom
	*/
	private PositionVertical position;
	/**
	* Duration of scrolling, when the user clicks on scrollbar's background, in seconds. Note, updateOnReleaseOnly should be set to false in order animation to happen.
	* Default Value: 
	*/
	private Double scrollDuration;
	/**
	* Selected background opacity.
	* Default Value: 
	*/
	private Double selectedBackgroundAlpha;
	/**
	* Selected background color.
	* Default Value: 
	*/
	private Color selectedBackgroundColor;
	/**
	* Selected graph'sfill opacity.
	* Default Value: 
	*/
	private Double selectedGraphFillAlpha;
	/**
	* Selected graph'sfill color.
	* Default Value: 
	*/
	private Color selectedGraphFillColor;
	/**
	* Selected graph'sline opacity.
	* Default Value: 
	*/
	private Double selectedGraphLineAlpha;
	/**
	* Selected graph's line color.
	* Default Value: 
	*/
	private Color selectedGraphLineColor;
	/**
	* Specifies if the chart should be updated while dragging/resizing the scrollbar or only at the moment when user releases mouse button. Usefull when working with large data sets.
	* Default Value: TRUE
	*/
	private Boolean updateOnReleaseOnly;
	/**
	* This is very important feature for those, who work with large data sets. 
	* You can tell ChartScrollbarwhat period it should use for it's graph and save
	* a lot of time for rendering of this graph. For example, if your minPeriod
	* is DD (days), set usePeriod = WW (weeks) and you will have 7 times less data
	* points in scrollbar's graph. Note, the period you specify here should be set
	* inCategoryAxesSettings.groupToPeriods.
	* Default Value: 
	*/
	private PeriodEnum usePeriod;
	
	/*
	 * Constructors
	 */
	public ChartScrollbarSettings() {
	}
	
	/*
	 * Getters and Setters
	 */
	public Boolean isAutoGridCount() {
		return autoGridCount;
	}
	public void setAutoGridCount(Boolean autoGridCount) {
		this.autoGridCount = autoGridCount;
	}
	public Double getBackgroundAlpha() {
		return backgroundAlpha;
	}
	public void setBackgroundAlpha(Double backgroundAlpha) {
		this.backgroundAlpha = backgroundAlpha;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Double getDragIconHeight() {
		return dragIconHeight;
	}
	public void setDragIconHeight(Double dragIconHeight) {
		this.dragIconHeight = dragIconHeight;
	}
	public Double getDragIconWidth() {
		return dragIconWidth;
	}
	public void setDragIconWidth(Double dragIconWidth) {
		this.dragIconWidth = dragIconWidth;
	}
	public Boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Double getFontSize() {
		return fontSize;
	}
	public void setFontSize(Double fontSize) {
		this.fontSize = fontSize;
	}
	public String getGraph() {
		return graph;
	}
	public void setGraph(String graph) {
		this.graph = graph;
	}
	public Double getGraphFillAlpha() {
		return graphFillAlpha;
	}
	public void setGraphFillAlpha(Double graphFillAlpha) {
		this.graphFillAlpha = graphFillAlpha;
	}
	public Color getGraphFillColor() {
		return graphFillColor;
	}
	public void setGraphFillColor(Color graphFillColor) {
		this.graphFillColor = graphFillColor;
	}
	public Double getGraphLineAlpha() {
		return graphLineAlpha;
	}
	public void setGraphLineAlpha(Double graphLineAlpha) {
		this.graphLineAlpha = graphLineAlpha;
	}
	public Color getGraphLineColor() {
		return graphLineColor;
	}
	public void setGraphLineColor(Color graphLineColor) {
		this.graphLineColor = graphLineColor;
	}
	public GraphType getGraphType() {
		return graphType;
	}
	public void setGraphType(GraphType graphType) {
		this.graphType = graphType;
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
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Boolean isHideResizeGrips() {
		return hideResizeGrips;
	}
	public void setHideResizeGrips(Boolean hideResizeGrips) {
		this.hideResizeGrips = hideResizeGrips;
	}
	public Boolean isMarkPeriodChange() {
		return markPeriodChange;
	}
	public void setMarkPeriodChange(Boolean markPeriodChange) {
		this.markPeriodChange = markPeriodChange;
	}
	public PositionVertical getPosition() {
		return position;
	}
	public void setPosition(PositionVertical position) {
		this.position = position;
	}
	public Double getScrollDuration() {
		return scrollDuration;
	}
	public void setScrollDuration(Double scrollDuration) {
		this.scrollDuration = scrollDuration;
	}
	public Double getSelectedBackgroundAlpha() {
		return selectedBackgroundAlpha;
	}
	public void setSelectedBackgroundAlpha(Double selectedBackgroundAlpha) {
		this.selectedBackgroundAlpha = selectedBackgroundAlpha;
	}
	public Color getSelectedBackgroundColor() {
		return selectedBackgroundColor;
	}
	public void setSelectedBackgroundColor(Color selectedBackgroundColor) {
		this.selectedBackgroundColor = selectedBackgroundColor;
	}
	public Double getSelectedGraphFillAlpha() {
		return selectedGraphFillAlpha;
	}
	public void setSelectedGraphFillAlpha(Double selectedGraphFillAlpha) {
		this.selectedGraphFillAlpha = selectedGraphFillAlpha;
	}
	public Color getSelectedGraphFillColor() {
		return selectedGraphFillColor;
	}
	public void setSelectedGraphFillColor(Color selectedGraphFillColor) {
		this.selectedGraphFillColor = selectedGraphFillColor;
	}
	public Double getSelectedGraphLineAlpha() {
		return selectedGraphLineAlpha;
	}
	public void setSelectedGraphLineAlpha(Double selectedGraphLineAlpha) {
		this.selectedGraphLineAlpha = selectedGraphLineAlpha;
	}
	public Color getSelectedGraphLineColor() {
		return selectedGraphLineColor;
	}
	public void setSelectedGraphLineColor(Color selectedGraphLineColor) {
		this.selectedGraphLineColor = selectedGraphLineColor;
	}
	public Boolean isUpdateOnReleaseOnly() {
		return updateOnReleaseOnly;
	}
	public void setUpdateOnReleaseOnly(Boolean updateOnReleaseOnly) {
		this.updateOnReleaseOnly = updateOnReleaseOnly;
	}
	public PeriodEnum getUsePeriod() {
		return usePeriod;
	}
	public void setUsePeriod(PeriodEnum usePeriod) {
		this.usePeriod = usePeriod;
	}

}

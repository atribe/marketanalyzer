package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.GraphType;

public class ChartScrollbar {

	/**
	* Specifies whether number of gridCount is specified automatically, according to the axis size.
	* Default Value: FALSE
	*/
	 private boolean autoGridCount;
	/**
	* Background opacity.
	* Default Value: 1
	*/
	 private double backgroundAlpha;
	/**
	* Background color of the scrollbar.
	* Default Value: #D4D4D4
	*/
	 private Color backgroundColor;
	/**
	* Read-only. Category axis of the scrollbar.
	* Default Value: 
	*/
	 private CategoryAxis categoryAxis;
	/**
	* Text color.
	* Default Value: #FFFFFF
	*/
	 private Color color;
	/**
	* Height of resize grip image. Note, you should also update the image in amcharts/images folder if you don't want it to be distorted because of resizing.
	* Default Value: 25
	*/
	 private double dragIconHeight;
	/**
	* Width of resize grip image. Note, you should also update the image in amcharts/images folder if you don't want it to be distorted because of resizing.
	* Default Value: 18
	*/
	 private double dragIconWidth;
	/**
	* Specifies which graph will be displayed in the scrollbar. Only Serial chart's scrollbar can display a graph.
	* Default Value: 
	*/
	 private AmGraph graph;
	/**
	* Graph fill opacity. Value range is 0 - 1.
	* Default Value: 1
	*/
	 private double graphFillAlpha;
	/**
	* Graph fill color.
	* Default Value: #BBBBBB
	*/
	 private Color graphFillColor;
	/**
	* Graph line opacity. Value range is 0 - 1.
	* Default Value: 0
	*/
	 private double graphLineAlpha;
	/**
	* Graph line color.
	* Default Value: #BBBBBB
	*/
	 private Color graphLineColor;
	/**
	* by default the graph type is the same as the original graph's type, however in case of candlestick or ohlc you might want to show line graph in the scrollbar. Possible values are: line, column, step, smoothedLine, candlestick, ohlc
	* Default Value: 
	*/
	 private GraphType graphType;
	/**
	* Grid opacity. Value range is 0 - 1.
	* Default Value: 0.7
	*/
	 private double gridAlpha;
	/**
	* Grid color.
	* Default Value: #FFFFFF
	*/
	 private Color gridColor;
	/**
	* The number of grid lines.
	* Default Value: 0
	*/
	 private double gridCount;
	/**
	* Specifies whether resize grips are hidden when mouse is away from the scrollbar.
	* Default Value: FALSE
	*/
	 private boolean hideResizeGrips;
	/**
	* Maximum value of ValueAxis of ChartScrollbar. Calculated automatically, if not set.
	* Default Value: 
	*/
	 private double maximum;
	/**
	* Minimum value of ValueAxis of ChartScrollbar. Calculated automatically, if not set.
	* Default Value: 
	*/
	 private double minimum;
	/**
	* Distance from plot area to scrollbar, in pixels.
	* Default Value: 0
	*/
	 private double offset;
	/**
	* Specifies whether scrollbar has a resize feature.
	* Default Value: TRUE
	*/
	 private boolean resizeEnabled;
	/**
	* Height (width, if chart is rotated) of a scrollbar.
	* Default Value: 20
	*/
	 private double scrollbarHeight;
	/**
	* Duration of scrolling, when the user clicks on scrollbar's background, in seconds. Note, updateOnReleaseOnly should be set to false in order animation to happen.
	* Default Value: 1
	*/
	 private double scrollDuration;
	/**
	* Selected backround opacity.
	* Default Value: 1
	*/
	 private double selectedBackgroundAlpha;
	/**
	* Selected background color.
	* Default Value: #EFEFEF
	*/
	 private Color selectedBackgroundColor;
	/**
	* Selected graph's fill opacity. Value range is 0 - 1.
	* Default Value: 1
	*/
	 private double selectedGraphFillAlpha;
	/**
	* Selected graph's fill color.
	* Default Value: #888888
	*/
	 private Color selectedGraphFillColor;
	/**
	* Selected graph's line opacity. Value range is 0 - 1.
	* Default Value: 0
	*/
	 private double selectedGraphLineAlpha;
	/**
	* Selected graph's line color.
	* Default Value: #888888
	*/
	 private Color selectedGraphLineColor;
	/**
	* Specifies if the chart should be updated while dragging/resizing the scrollbar or only at the moment when user releases mouse button.
	* Default Value: FALSE
	*/
	 private boolean updateOnReleaseOnly;
	public boolean isAutoGridCount() {
		return autoGridCount;
	}
	public void setAutoGridCount(boolean autoGridCount) {
		this.autoGridCount = autoGridCount;
	}
	public double getBackgroundAlpha() {
		return backgroundAlpha;
	}
	public void setBackgroundAlpha(double backgroundAlpha) {
		this.backgroundAlpha = backgroundAlpha;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public CategoryAxis getCategoryAxis() {
		return categoryAxis;
	}
	public void setCategoryAxis(CategoryAxis categoryAxis) {
		this.categoryAxis = categoryAxis;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getDragIconHeight() {
		return dragIconHeight;
	}
	public void setDragIconHeight(double dragIconHeight) {
		this.dragIconHeight = dragIconHeight;
	}
	public double getDragIconWidth() {
		return dragIconWidth;
	}
	public void setDragIconWidth(double dragIconWidth) {
		this.dragIconWidth = dragIconWidth;
	}
	public AmGraph getGraph() {
		return graph;
	}
	public void setGraph(AmGraph graph) {
		this.graph = graph;
	}
	public double getGraphFillAlpha() {
		return graphFillAlpha;
	}
	public void setGraphFillAlpha(double graphFillAlpha) {
		this.graphFillAlpha = graphFillAlpha;
	}
	public Color getGraphFillColor() {
		return graphFillColor;
	}
	public void setGraphFillColor(Color graphFillColor) {
		this.graphFillColor = graphFillColor;
	}
	public double getGraphLineAlpha() {
		return graphLineAlpha;
	}
	public void setGraphLineAlpha(double graphLineAlpha) {
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
	public boolean isHideResizeGrips() {
		return hideResizeGrips;
	}
	public void setHideResizeGrips(boolean hideResizeGrips) {
		this.hideResizeGrips = hideResizeGrips;
	}
	public double getMaximum() {
		return maximum;
	}
	public void setMaximum(double maximum) {
		this.maximum = maximum;
	}
	public double getMinimum() {
		return minimum;
	}
	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}
	public double getOffset() {
		return offset;
	}
	public void setOffset(double offset) {
		this.offset = offset;
	}
	public boolean isResizeEnabled() {
		return resizeEnabled;
	}
	public void setResizeEnabled(boolean resizeEnabled) {
		this.resizeEnabled = resizeEnabled;
	}
	public double getScrollbarHeight() {
		return scrollbarHeight;
	}
	public void setScrollbarHeight(double scrollbarHeight) {
		this.scrollbarHeight = scrollbarHeight;
	}
	public double getScrollDuration() {
		return scrollDuration;
	}
	public void setScrollDuration(double scrollDuration) {
		this.scrollDuration = scrollDuration;
	}
	public double getSelectedBackgroundAlpha() {
		return selectedBackgroundAlpha;
	}
	public void setSelectedBackgroundAlpha(double selectedBackgroundAlpha) {
		this.selectedBackgroundAlpha = selectedBackgroundAlpha;
	}
	public Color getSelectedBackgroundColor() {
		return selectedBackgroundColor;
	}
	public void setSelectedBackgroundColor(Color selectedBackgroundColor) {
		this.selectedBackgroundColor = selectedBackgroundColor;
	}
	public double getSelectedGraphFillAlpha() {
		return selectedGraphFillAlpha;
	}
	public void setSelectedGraphFillAlpha(double selectedGraphFillAlpha) {
		this.selectedGraphFillAlpha = selectedGraphFillAlpha;
	}
	public Color getSelectedGraphFillColor() {
		return selectedGraphFillColor;
	}
	public void setSelectedGraphFillColor(Color selectedGraphFillColor) {
		this.selectedGraphFillColor = selectedGraphFillColor;
	}
	public double getSelectedGraphLineAlpha() {
		return selectedGraphLineAlpha;
	}
	public void setSelectedGraphLineAlpha(double selectedGraphLineAlpha) {
		this.selectedGraphLineAlpha = selectedGraphLineAlpha;
	}
	public Color getSelectedGraphLineColor() {
		return selectedGraphLineColor;
	}
	public void setSelectedGraphLineColor(Color selectedGraphLineColor) {
		this.selectedGraphLineColor = selectedGraphLineColor;
	}
	public boolean isUpdateOnReleaseOnly() {
		return updateOnReleaseOnly;
	}
	public void setUpdateOnReleaseOnly(boolean updateOnReleaseOnly) {
		this.updateOnReleaseOnly = updateOnReleaseOnly;
	}

}

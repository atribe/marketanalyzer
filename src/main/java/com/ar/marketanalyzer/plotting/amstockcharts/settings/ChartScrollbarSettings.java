package com.ar.marketanalyzer.plotting.amstockcharts.settings;

import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.AmGraph;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.GraphType;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.PositionVertical;

public class ChartScrollbarSettings {
	/**
	* Specifies whether number of gridCount is specified automatically, according to the axis size.
	* Default Value: TRUE
	*/
	private boolean autoGridCount;
	/**
	* Background opacity.
	* Default Value: 
	*/
	private double backgroundAlpha;
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
	private double dragIconHeight;
	/**
	* Width of resize grip image. Note, you should also update the image in amcharts/images folder if you don't want it to be distorted because of resizing.
	* Default Value: 11
	*/
	private double dragIconWidth;
	/**
	* Set false if you don't need scrollbar.
	* Default Value: TRUE
	*/
	private boolean enabled;
	/**
	* Font size.
	* Default Value: 
	*/
	private double fontSize;
	/**
	* Specifies which graph will be displayed in the scrollbar.
	* Default Value: 
	*/
	private AmGraph graph;
	/**
	* Graph fill opacity.
	* Default Value: 
	*/
	private double graphFillAlpha;
	/**
	* Graph fill color.
	* Default Value: 
	*/
	private Color graphFillColor;
	/**
	* Graph line opacity.
	* Default Value: 
	*/
	private double graphLineAlpha;
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
	private double gridAlpha;
	/**
	* Grid color.
	* Default Value: 
	*/
	private Color gridColor;
	/**
	* Grid count. You should set autoGridCount to false in order this property to work.
	* Default Value: 
	*/
	private double gridCount;
	/**
	* Height of scrollbar, in pixels.
	* Default Value: 40
	*/
	private double height;
	/**
	* Specifies whether resize grips are hidden when mouse is away from the scrollbar.
	* Default Value: FALSE
	*/
	private boolean hideResizeGrips;
	/**
	* Specifies if category axis of scrollbar should mark period change with a different date format.
	* Default Value: 
	*/
	private boolean markPeriodChange;
	/**
	* Position of a scrollbar. Possible values are top and bottom.
	* Default Value: bottom
	*/
	private PositionVertical position;
	/**
	* Duration of scrolling, when the user clicks on scrollbar's background, in seconds. Note, updateOnReleaseOnly should be set to false in order animation to happen.
	* Default Value: 
	*/
	private double scrollDuration;
	/**
	* Selected background opacity.
	* Default Value: 
	*/
	private double selectedBackgroundAlpha;
	/**
	* Selected background color.
	* Default Value: 
	*/
	private Color selectedBackgroundColor;
	/**
	* Selected graph'sfill opacity.
	* Default Value: 
	*/
	private double selectedGraphFillAlpha;
	/**
	* Selected graph'sfill color.
	* Default Value: 
	*/
	private Color selectedGraphFillColor;
	/**
	* Selected graph'sline opacity.
	* Default Value: 
	*/
	private double selectedGraphLineAlpha;
	/**
	* Selected graph's line color.
	* Default Value: 
	*/
	private Color selectedGraphLineColor;
	/**
	* Specifies if the chart should be updated while dragging/resizing the scrollbar or only at the moment when user releases mouse button. Usefull when working with large data sets.
	* Default Value: TRUE
	*/
	private boolean updateOnReleaseOnly;
	/**
	* This is very important feature for those, who work with large data sets. You can tell ChartScrollbarwhat period it should use for it's graph and save a lot of time for rendering of this graph. For example, if your minPeriod is DD (days), set usePeriod = WW (weeks) and you will have 7 times less data points in scrollbar's graph. Note, the period you specify here should be set inCategoryAxesSettings.groupToPeriods.
	* Default Value: 
	*/
	private String usePeriod;
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public double getFontSize() {
		return fontSize;
	}
	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
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
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public boolean isHideResizeGrips() {
		return hideResizeGrips;
	}
	public void setHideResizeGrips(boolean hideResizeGrips) {
		this.hideResizeGrips = hideResizeGrips;
	}
	public boolean isMarkPeriodChange() {
		return markPeriodChange;
	}
	public void setMarkPeriodChange(boolean markPeriodChange) {
		this.markPeriodChange = markPeriodChange;
	}
	public PositionVertical getPosition() {
		return position;
	}
	public void setPosition(PositionVertical position) {
		this.position = position;
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
	public String getUsePeriod() {
		return usePeriod;
	}
	public void setUsePeriod(String usePeriod) {
		this.usePeriod = usePeriod;
	}

}

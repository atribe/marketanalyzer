package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.BulletType;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.CompareGraphType;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.PeriodValue;

public class StockGraph extends AmGraph{

	/**
	* Specifies whether this graph will be compared if some data set is selected for comparing.
	* Default Value: FALSE
	*/
	private boolean comparable;
	/**
	* Specifies a field to be used to generate comparing graph. Note, this field is not the one used in your dataProvider, but toField from FieldMapping object.
	* Default Value: 
	*/
	private String compareField;
	/**
	* If you set it to true, when data sets are compared, the graphs will use first value as a base value instead of using the first value of selected period.
	* Default Value: FALSE
	*/
	private boolean compareFromStart;
	/**
	* Balloon color of comparing graph.
	* Default Value: 
	*/
	private Color compareGraphBalloonColor;
	/**
	* If you set some function, the graph will call it and pass GraphDataItem and AmGraph object to it. This function should return a string which will be displayed in a balloon. This will be used for graphs from compared data sets only. Use balloonFunction for main data set's graphs.
	* Default Value: 
	*/
	private String compareGraphBalloonFunction;
	/**
	* Balloon text of comparing graph.
	* Default Value: 
	*/
	private String compareGraphBalloonText;
	/**
	* Bullet of comparing graph. Possible values are: round, square, diamond, triangleUp, triangleDown, triangleLeft, triangleRight, bubble
	* Default Value: 
	*/
	private BulletType compareGraphBullet;
	/**
	* Opacity of bullet border of comparing graph.
	* Default Value: 
	*/
	private double compareGraphBulletBorderAlpha;
	/**
	* Color of bullet border of comparing graph.
	* Default Value: 
	*/
	private Color compareGraphBulletBorderColor;
	/**
	* Thickness of bullet border of comparing graph.
	* Default Value: 
	*/
	private double compareGraphBulletBorderThickness;
	/**
	* Color of compared graphs' bullets.
	* Default Value: 
	*/
	private Color compareGraphBulletColor;
	/**
	* Bullet size of comparing graph.
	* Default Value: 
	*/
	private double compareGraphBulletSize;
	/**
	* Corner radius of comparing graph (if type is column).
	* Default Value: 
	*/
	private double compareGraphCornerRadiusTop;
	/**
	* Dash length of compare graph.
	* Default Value: 
	*/
	private double compareGraphDashLength;
	/**
	* Fill alpha of comparing graph.
	* Default Value: 
	*/
	private double compareGraphFillAlphas;
	/**
	* Fill color of comparing graph.
	* Default Value: 
	*/
	private Color compareGraphFillColors;
	/**
	* Opacity of comparing graph line.
	* Default Value: 
	*/
	private double compareGraphLineAlpha;
	/**
	* Thickness of compare graph.
	* Default Value: 
	*/
	private double compareGraphLineThickness;
	/**
	* Type of comparing graph. Possible values are: line, column, step, smoothedLine.
	* Default Value: line
	*/
	private CompareGraphType compareGraphType;
	/**
	* Specifies if compare graph is visible in legend.
	* Default Value: TRUE
	*/
	private boolean compareGraphVisibleInLegend;
	/**
	* When data is grouped to periods, the graph must know which period value should be used. Possible values are: Open, Low, High, Close, Average and Sum.
	* Default Value: Close
	*/
	private PeriodValue periodValue;
	/**
	* Specifies if events of compared graphs should be shown.
	* Default Value: FALSE
	*/
	private boolean showEventsOnComparedGraphs;
	/**
	* Specifies whether data set color should be used as this graph's lineColor.
	* Default Value: TRUE
	*/
	private boolean useDataSetColors;
	public boolean isComparable() {
		return comparable;
	}
	public void setComparable(boolean comparable) {
		this.comparable = comparable;
	}
	public String getCompareField() {
		return compareField;
	}
	public void setCompareField(String compareField) {
		this.compareField = compareField;
	}
	public boolean isCompareFromStart() {
		return compareFromStart;
	}
	public void setCompareFromStart(boolean compareFromStart) {
		this.compareFromStart = compareFromStart;
	}
	public Color getCompareGraphBalloonColor() {
		return compareGraphBalloonColor;
	}
	public void setCompareGraphBalloonColor(Color compareGraphBalloonColor) {
		this.compareGraphBalloonColor = compareGraphBalloonColor;
	}
	public String getCompareGraphBalloonFunction() {
		return compareGraphBalloonFunction;
	}
	public void setCompareGraphBalloonFunction(String compareGraphBalloonFunction) {
		this.compareGraphBalloonFunction = compareGraphBalloonFunction;
	}
	public String getCompareGraphBalloonText() {
		return compareGraphBalloonText;
	}
	public void setCompareGraphBalloonText(String compareGraphBalloonText) {
		this.compareGraphBalloonText = compareGraphBalloonText;
	}
	public BulletType getCompareGraphBullet() {
		return compareGraphBullet;
	}
	public void setCompareGraphBullet(BulletType compareGraphBullet) {
		this.compareGraphBullet = compareGraphBullet;
	}
	public double getCompareGraphBulletBorderAlpha() {
		return compareGraphBulletBorderAlpha;
	}
	public void setCompareGraphBulletBorderAlpha(
			double compareGraphBulletBorderAlpha) {
		this.compareGraphBulletBorderAlpha = compareGraphBulletBorderAlpha;
	}
	public Color getCompareGraphBulletBorderColor() {
		return compareGraphBulletBorderColor;
	}
	public void setCompareGraphBulletBorderColor(Color compareGraphBulletBorderColor) {
		this.compareGraphBulletBorderColor = compareGraphBulletBorderColor;
	}
	public double getCompareGraphBulletBorderThickness() {
		return compareGraphBulletBorderThickness;
	}
	public void setCompareGraphBulletBorderThickness(
			double compareGraphBulletBorderThickness) {
		this.compareGraphBulletBorderThickness = compareGraphBulletBorderThickness;
	}
	public Color getCompareGraphBulletColor() {
		return compareGraphBulletColor;
	}
	public void setCompareGraphBulletColor(Color compareGraphBulletColor) {
		this.compareGraphBulletColor = compareGraphBulletColor;
	}
	public double getCompareGraphBulletSize() {
		return compareGraphBulletSize;
	}
	public void setCompareGraphBulletSize(double compareGraphBulletSize) {
		this.compareGraphBulletSize = compareGraphBulletSize;
	}
	public double getCompareGraphCornerRadiusTop() {
		return compareGraphCornerRadiusTop;
	}
	public void setCompareGraphCornerRadiusTop(double compareGraphCornerRadiusTop) {
		this.compareGraphCornerRadiusTop = compareGraphCornerRadiusTop;
	}
	public double getCompareGraphDashLength() {
		return compareGraphDashLength;
	}
	public void setCompareGraphDashLength(double compareGraphDashLength) {
		this.compareGraphDashLength = compareGraphDashLength;
	}
	public double getCompareGraphFillAlphas() {
		return compareGraphFillAlphas;
	}
	public void setCompareGraphFillAlphas(double compareGraphFillAlphas) {
		this.compareGraphFillAlphas = compareGraphFillAlphas;
	}
	public Color getCompareGraphFillColors() {
		return compareGraphFillColors;
	}
	public void setCompareGraphFillColors(Color compareGraphFillColors) {
		this.compareGraphFillColors = compareGraphFillColors;
	}
	public double getCompareGraphLineAlpha() {
		return compareGraphLineAlpha;
	}
	public void setCompareGraphLineAlpha(double compareGraphLineAlpha) {
		this.compareGraphLineAlpha = compareGraphLineAlpha;
	}
	public double getCompareGraphLineThickness() {
		return compareGraphLineThickness;
	}
	public void setCompareGraphLineThickness(double compareGraphLineThickness) {
		this.compareGraphLineThickness = compareGraphLineThickness;
	}
	public CompareGraphType getCompareGraphType() {
		return compareGraphType;
	}
	public void setCompareGraphType(CompareGraphType compareGraphType) {
		this.compareGraphType = compareGraphType;
	}
	public boolean isCompareGraphVisibleInLegend() {
		return compareGraphVisibleInLegend;
	}
	public void setCompareGraphVisibleInLegend(boolean compareGraphVisibleInLegend) {
		this.compareGraphVisibleInLegend = compareGraphVisibleInLegend;
	}
	public PeriodValue getPeriodValue() {
		return periodValue;
	}
	public void setPeriodValue(PeriodValue periodValue) {
		this.periodValue = periodValue;
	}
	public boolean isShowEventsOnComparedGraphs() {
		return showEventsOnComparedGraphs;
	}
	public void setShowEventsOnComparedGraphs(boolean showEventsOnComparedGraphs) {
		this.showEventsOnComparedGraphs = showEventsOnComparedGraphs;
	}
	public boolean isUseDataSetColors() {
		return useDataSetColors;
	}
	public void setUseDataSetColors(boolean useDataSetColors) {
		this.useDataSetColors = useDataSetColors;
	}

}

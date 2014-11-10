package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.BulletType;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.CompareGraphType;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.GraphType;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.PeriodValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class StockGraph extends AmGraph{

	/**
	* Specifies whether this graph will be compared if some data set is selected for comparing.
	* Default Value: FALSE
	*/
	private Boolean comparable;
	/**
	* Specifies a field to be used to generate comparing graph. Note, this field is not the one used in your dataProvider, but toField from FieldMapping object.
	* Default Value: 
	*/
	private String compareField;
	/**
	* If you set it to true, when data sets are compared, the graphs will use first value as a base value instead of using the first value of selected period.
	* Default Value: FALSE
	*/
	private Boolean compareFromStart;
	/**
	* Balloon color of comparing graph.
	* Default Value: 
	*/
	private Color compareGraphBalloonColor;
	/**
	* If you set some function, the graph will call it and pass�GraphDataItem�and�AmGraph�object to it. This function should return a string which will be displayed in a balloon. This will be used for graphs from compared data sets only. Use balloonFunction for main data set's graphs.
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
	private Double compareGraphBulletBorderAlpha;
	/**
	* Color of bullet border of comparing graph.
	* Default Value: 
	*/
	private Color compareGraphBulletBorderColor;
	/**
	* Thickness of bullet border of comparing graph.
	* Default Value: 
	*/
	private Double compareGraphBulletBorderThickness;
	/**
	* Color of compared graphs' bullets.
	* Default Value: 
	*/
	private Color compareGraphBulletColor;
	/**
	* Bullet size of comparing graph.
	* Default Value: 
	*/
	private Double compareGraphBulletSize;
	/**
	* Corner radius of comparing graph (if type is column).
	* Default Value: 
	*/
	private Double compareGraphCornerRadiusTop;
	/**
	* Dash length of compare graph.
	* Default Value: 
	*/
	private Double compareGraphDashLength;
	/**
	* Fill alpha of comparing graph.
	* Default Value: 
	*/
	private Double compareGraphFillAlphas;
	/**
	* Fill color of comparing graph.
	* Default Value: 
	*/
	private Color compareGraphFillColors;
	/**
	* Opacity of comparing graph line.
	* Default Value: 
	*/
	private Double compareGraphLineAlpha;
	/**
	* Thickness of compare graph.
	* Default Value: 
	*/
	private Double compareGraphLineThickness;
	/**
	* Type of comparing graph. Possible values are: line, column, step, smoothedLine.
	* Default Value: line
	*/
	private CompareGraphType compareGraphType;
	/**
	* Specifies if compare graph is visible in legend.
	* Default Value: TRUE
	*/
	private Boolean compareGraphVisibleInLegend;
	/**
	* When data is grouped to periods, the graph must know which period value should be used. Possible values are: Open, Low, High, Close, Average and Sum.
	* Default Value: Close
	*/
	private PeriodValue periodValue;
	/**
	* Specifies if events of compared graphs should be shown.
	* Default Value: FALSE
	*/
	private Boolean showEventsOnComparedGraphs;
	/**
	* Specifies whether data set color should be used as this graph's lineColor.
	* Default Value: TRUE
	*/
	private Boolean useDataSetColors;
	
	/*
	 * Constructors
	 */
	public StockGraph() {
		
	}
	public StockGraph(GraphType graphType, String id) {
		this();
		
		this.type = graphType;
		this.id = id;
	}
	public StockGraph(String id) {
		this();
		
		this.id = id;
	}
	public StockGraph(GraphType graphType) {
		this();
		
		this.type = graphType;
	}
	
	/*
	 * Helper Functions
	 */
	public void setValueGraphSettings() {
		this.openField = "open";
		this.closeField = "close";
		this.highField = "high";
		this.lowField = "low";
		this.valueField = "close";
		
		Color slateBlue = new Color("7F8DA9");
		this.lineColor = slateBlue;
		this.fillColors = slateBlue;
		
		Color slateRed = new Color("DB4C3C");
		this.negativeLineColor = slateRed;
		this.negativeFillColors = slateRed;
		
		this.fillAlphas = 1.0;
		
		this.useDataSetColors = false;
		this.comparable = true;
		this.compareField = "value";
		
		this.showBalloon = false;
		
		this.proCandlesticks = true;
	}
	public void setVolumeGraphSettings() {
		this.valueField = "volume";
		this.showBalloon = false;
		this.fillAlphas = 1.0;
	}
	
	
	@Override
	public String toString() {
		return id;
	}
	
	/*
	 * Getters and Setters
	 */
	public Boolean isComparable() {
		return comparable;
	}
	public void setComparable(Boolean comparable) {
		this.comparable = comparable;
	}
	public String getCompareField() {
		return compareField;
	}
	public void setCompareField(String compareField) {
		this.compareField = compareField;
	}
	public Boolean isCompareFromStart() {
		return compareFromStart;
	}
	public void setCompareFromStart(Boolean compareFromStart) {
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
	public Double getCompareGraphBulletBorderAlpha() {
		return compareGraphBulletBorderAlpha;
	}
	public void setCompareGraphBulletBorderAlpha(
			Double compareGraphBulletBorderAlpha) {
		this.compareGraphBulletBorderAlpha = compareGraphBulletBorderAlpha;
	}
	public Color getCompareGraphBulletBorderColor() {
		return compareGraphBulletBorderColor;
	}
	public void setCompareGraphBulletBorderColor(Color compareGraphBulletBorderColor) {
		this.compareGraphBulletBorderColor = compareGraphBulletBorderColor;
	}
	public Double getCompareGraphBulletBorderThickness() {
		return compareGraphBulletBorderThickness;
	}
	public void setCompareGraphBulletBorderThickness(
			Double compareGraphBulletBorderThickness) {
		this.compareGraphBulletBorderThickness = compareGraphBulletBorderThickness;
	}
	public Color getCompareGraphBulletColor() {
		return compareGraphBulletColor;
	}
	public void setCompareGraphBulletColor(Color compareGraphBulletColor) {
		this.compareGraphBulletColor = compareGraphBulletColor;
	}
	public Double getCompareGraphBulletSize() {
		return compareGraphBulletSize;
	}
	public void setCompareGraphBulletSize(Double compareGraphBulletSize) {
		this.compareGraphBulletSize = compareGraphBulletSize;
	}
	public Double getCompareGraphCornerRadiusTop() {
		return compareGraphCornerRadiusTop;
	}
	public void setCompareGraphCornerRadiusTop(Double compareGraphCornerRadiusTop) {
		this.compareGraphCornerRadiusTop = compareGraphCornerRadiusTop;
	}
	public Double getCompareGraphDashLength() {
		return compareGraphDashLength;
	}
	public void setCompareGraphDashLength(Double compareGraphDashLength) {
		this.compareGraphDashLength = compareGraphDashLength;
	}
	public Double getCompareGraphFillAlphas() {
		return compareGraphFillAlphas;
	}
	public void setCompareGraphFillAlphas(Double compareGraphFillAlphas) {
		this.compareGraphFillAlphas = compareGraphFillAlphas;
	}
	public Color getCompareGraphFillColors() {
		return compareGraphFillColors;
	}
	public void setCompareGraphFillColors(Color compareGraphFillColors) {
		this.compareGraphFillColors = compareGraphFillColors;
	}
	public Double getCompareGraphLineAlpha() {
		return compareGraphLineAlpha;
	}
	public void setCompareGraphLineAlpha(Double compareGraphLineAlpha) {
		this.compareGraphLineAlpha = compareGraphLineAlpha;
	}
	public Double getCompareGraphLineThickness() {
		return compareGraphLineThickness;
	}
	public void setCompareGraphLineThickness(Double compareGraphLineThickness) {
		this.compareGraphLineThickness = compareGraphLineThickness;
	}
	public CompareGraphType getCompareGraphType() {
		return compareGraphType;
	}
	public void setCompareGraphType(CompareGraphType compareGraphType) {
		this.compareGraphType = compareGraphType;
	}
	public Boolean isCompareGraphVisibleInLegend() {
		return compareGraphVisibleInLegend;
	}
	public void setCompareGraphVisibleInLegend(Boolean compareGraphVisibleInLegend) {
		this.compareGraphVisibleInLegend = compareGraphVisibleInLegend;
	}
	public PeriodValue getPeriodValue() {
		return periodValue;
	}
	public void setPeriodValue(PeriodValue periodValue) {
		this.periodValue = periodValue;
	}
	public Boolean isShowEventsOnComparedGraphs() {
		return showEventsOnComparedGraphs;
	}
	public void setShowEventsOnComparedGraphs(Boolean showEventsOnComparedGraphs) {
		this.showEventsOnComparedGraphs = showEventsOnComparedGraphs;
	}
	public Boolean isUseDataSetColors() {
		return useDataSetColors;
	}
	public void setUseDataSetColors(Boolean useDataSetColors) {
		this.useDataSetColors = useDataSetColors;
	}

}

package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.CategoryAxis;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.Label;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.StockGraph;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.StockLegend;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.ValueAxis;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.ChartTypeAm;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.RecalculateToPercents;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class StockPanel extends AmSerialChart{

	/**
	* Array of Labels. Example of label object, with all possible properties:
	{x: 20, y: 20, text: this is label, align: left, size: 12, color: #CC0000, alpha: 1, rotation: 0, bold: true, url: http://www.amcharts.com}
	* Default Value: []
	*/
	private List<Label> allLabels;
	/**
	* Specifies whether x button will be displayed near the panel. This button allows turning panel off.
	* Default Value: FALSE
	*/
	private Boolean allowTurningOff;
	/**
	* If true, drawing icons will be displayed in top-right corner.
	* Default Value: FALSE
	*/
	private Boolean drawingIconsEnabled;
	/**
	* Specifies on which value axis user can draw trend lines. Set drawingIconsEnabled to true if you want drawing icons to be visible. First value axis will be used if not set here. You can use a reference to the value axis object or id of value axis.
	* Default Value: 
	*/
	private ValueAxis drawOnAxis;
	/**
	* Specifies if all trend lines should be erased when erase button is clicked. If false, trend lines can be erased one by one.
	* Default Value: FALSE
	*/
	private Boolean eraseAll;
	/**
	* Size of trend line drawing icons. If you change this size, you should update icon images if you want them to look properly.
	* Default Value: 18
	*/
	private Double iconSize;
	/**
	* Relative height of panel. Possible values 0 - 100.
	* Default Value: 
	*/
	private Double percentHeight;
	/**
	* Specifies from which date's value should be used as base when recalculating values to percent.
	* Default Value: 
	*/
	private Date recalculateFromDate;
	/**
	* Specifies when values should be recalculated to percents. Possible values are: never, always, whenComparing.
	* Default Value: whenComparing
	*/
	private RecalculateToPercents recalculateToPercents;
	/**
	* Specifies whether this panel will show category axis.
	* Default Value: TRUE
	*/
	private Boolean showCategoryAxis;
	/**
	* Specifies if compared graphs should be shown above or behind the main graph.
	* Default Value: TRUE
	*/
	private Boolean showComparedOnTop;
	/**
	* Array of stock graphs.
	* Default Value: 
	*/
	private List<StockGraph> stockGraphs = new ArrayList<StockGraph>();
	/**
	* Stock chart legend.
	* Default Value: 
	*/
	private StockLegend stockLegend;
	/**
	* A title of a panel. Note,StockLegend should be added in order title to be displayed.
	* Default Value: 
	*/
	private String title;
	/**
	* Trend line opacity.
	* Default Value: 1
	*/
	private Double trendLineAlpha;
	/**
	* Trend line color.
	* Default Value: #00CC00
	*/
	private Color trendLineColor;
	/**
	* Trend line dash length.
	* Default Value: 0
	*/
	private Double trendLineDashLength;
	/**
	* Trend line thickness.
	* Default Value: 2
	*/
	private Double trendLineThickness;
	
	/*
	 * Constructors
	 */
	public StockPanel() {
		title = "Value";
		showCategoryAxis = false;
		percentHeight = 70.0;
		
		valueAxes = new ArrayList<ValueAxis>();
		valueAxes.add( new ValueAxis("v1"));
		
		categoryAxis = new CategoryAxis();
		
		stockLegend = new StockLegend();
	}
	public StockPanel(ChartTypeAm type) {
		this();
		
		StockGraph g1 = new StockGraph(type, "plot1");
		stockGraphs.add(g1);
	}
	
	
	/*
	 * Getters and Setters
	 */
	public List<Label> getAllLabels() {
		return allLabels;
	}
	public void setAllLabels(List<Label> allLabels) {
		this.allLabels = allLabels;
	}
	public Boolean isAllowTurningOff() {
		return allowTurningOff;
	}
	public void setAllowTurningOff(Boolean allowTurningOff) {
		this.allowTurningOff = allowTurningOff;
	}
	public Boolean isDrawingIconsEnabled() {
		return drawingIconsEnabled;
	}
	public void setDrawingIconsEnabled(Boolean drawingIconsEnabled) {
		this.drawingIconsEnabled = drawingIconsEnabled;
	}
	public ValueAxis getDrawOnAxis() {
		return drawOnAxis;
	}
	public void setDrawOnAxis(ValueAxis drawOnAxis) {
		this.drawOnAxis = drawOnAxis;
	}
	public Boolean isEraseAll() {
		return eraseAll;
	}
	public void setEraseAll(Boolean eraseAll) {
		this.eraseAll = eraseAll;
	}
	public Double getIconSize() {
		return iconSize;
	}
	public void setIconSize(Double iconSize) {
		this.iconSize = iconSize;
	}
	public Double getPercentHeight() {
		return percentHeight;
	}
	public void setPercentHeight(Double percentHeight) {
		this.percentHeight = percentHeight;
	}
	public Date getRecalculateFromDate() {
		return recalculateFromDate;
	}
	public void setRecalculateFromDate(Date recalculateFromDate) {
		this.recalculateFromDate = recalculateFromDate;
	}
	public RecalculateToPercents getRecalculateToPercents() {
		return recalculateToPercents;
	}
	public void setRecalculateToPercents(RecalculateToPercents recalculateToPercents) {
		this.recalculateToPercents = recalculateToPercents;
	}
	public Boolean isShowCategoryAxis() {
		return showCategoryAxis;
	}
	public void setShowCategoryAxis(Boolean showCategoryAxis) {
		this.showCategoryAxis = showCategoryAxis;
	}
	public Boolean isShowComparedOnTop() {
		return showComparedOnTop;
	}
	public void setShowComparedOnTop(Boolean showComparedOnTop) {
		this.showComparedOnTop = showComparedOnTop;
	}
	public List<StockGraph> getStockGraphs() {
		return stockGraphs;
	}
	public void setStockGraphs(List<StockGraph> stockGraphs) {
		this.stockGraphs = stockGraphs;
	}
	public StockLegend getStockLegend() {
		return stockLegend;
	}
	public void setStockLegend(StockLegend stockLegend) {
		this.stockLegend = stockLegend;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getTrendLineAlpha() {
		return trendLineAlpha;
	}
	public void setTrendLineAlpha(Double trendLineAlpha) {
		this.trendLineAlpha = trendLineAlpha;
	}
	public Color getTrendLineColor() {
		return trendLineColor;
	}
	public void setTrendLineColor(Color trendLineColor) {
		this.trendLineColor = trendLineColor;
	}
	public Double getTrendLineDashLength() {
		return trendLineDashLength;
	}
	public void setTrendLineDashLength(Double trendLineDashLength) {
		this.trendLineDashLength = trendLineDashLength;
	}
	public Double getTrendLineThickness() {
		return trendLineThickness;
	}
	public void setTrendLineThickness(Double trendLineThickness) {
		this.trendLineThickness = trendLineThickness;
	}

}

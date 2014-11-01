package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import java.sql.Date;
import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.Label;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.StockGraph;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.StockLegend;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.ValueAxis;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.RecalculateToPercents;

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
	private boolean allowTurningOff;
	/**
	* If true, drawing icons will be displayed in top-right corner.
	* Default Value: FALSE
	*/
	private boolean drawingIconsEnabled;
	/**
	* Specifies on which value axis user can draw trend lines. Set drawingIconsEnabled to true if you want drawing icons to be visible. First value axis will be used if not set here. You can use a reference to the value axis object or id of value axis.
	* Default Value: 
	*/
	private ValueAxis drawOnAxis;
	/**
	* Specifies if all trend lines should be erased when erase button is clicked. If false, trend lines can be erased one by one.
	* Default Value: FALSE
	*/
	private boolean eraseAll;
	/**
	* Size of trend line drawing icons. If you change this size, you should update icon images if you want them to look properly.
	* Default Value: 18
	*/
	private double iconSize;
	/**
	* Relative height of panel. Possible values 0 - 100.
	* Default Value: 
	*/
	private double percentHeight;
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
	private boolean showCategoryAxis;
	/**
	* Specifies if compared graphs should be shown above or behind the main graph.
	* Default Value: TRUE
	*/
	private boolean showComparedOnTop;
	/**
	* Array of stock graphs.
	* Default Value: 
	*/
	private List<StockGraph> stockGraphs;
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
	private double trendLineAlpha;
	/**
	* Trend line color.
	* Default Value: #00CC00
	*/
	private Color trendLineColor;
	/**
	* Trend line dash length.
	* Default Value: 0
	*/
	private double trendLineDashLength;
	/**
	* Trend line thickness.
	* Default Value: 2
	*/
	private double trendLineThickness;
	public List<Label> getAllLabels() {
		return allLabels;
	}
	public void setAllLabels(List<Label> allLabels) {
		this.allLabels = allLabels;
	}
	public boolean isAllowTurningOff() {
		return allowTurningOff;
	}
	public void setAllowTurningOff(boolean allowTurningOff) {
		this.allowTurningOff = allowTurningOff;
	}
	public boolean isDrawingIconsEnabled() {
		return drawingIconsEnabled;
	}
	public void setDrawingIconsEnabled(boolean drawingIconsEnabled) {
		this.drawingIconsEnabled = drawingIconsEnabled;
	}
	public ValueAxis getDrawOnAxis() {
		return drawOnAxis;
	}
	public void setDrawOnAxis(ValueAxis drawOnAxis) {
		this.drawOnAxis = drawOnAxis;
	}
	public boolean isEraseAll() {
		return eraseAll;
	}
	public void setEraseAll(boolean eraseAll) {
		this.eraseAll = eraseAll;
	}
	public double getIconSize() {
		return iconSize;
	}
	public void setIconSize(double iconSize) {
		this.iconSize = iconSize;
	}
	public double getPercentHeight() {
		return percentHeight;
	}
	public void setPercentHeight(double percentHeight) {
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
	public boolean isShowCategoryAxis() {
		return showCategoryAxis;
	}
	public void setShowCategoryAxis(boolean showCategoryAxis) {
		this.showCategoryAxis = showCategoryAxis;
	}
	public boolean isShowComparedOnTop() {
		return showComparedOnTop;
	}
	public void setShowComparedOnTop(boolean showComparedOnTop) {
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
	public double getTrendLineAlpha() {
		return trendLineAlpha;
	}
	public void setTrendLineAlpha(double trendLineAlpha) {
		this.trendLineAlpha = trendLineAlpha;
	}
	public Color getTrendLineColor() {
		return trendLineColor;
	}
	public void setTrendLineColor(Color trendLineColor) {
		this.trendLineColor = trendLineColor;
	}
	public double getTrendLineDashLength() {
		return trendLineDashLength;
	}
	public void setTrendLineDashLength(double trendLineDashLength) {
		this.trendLineDashLength = trendLineDashLength;
	}
	public double getTrendLineThickness() {
		return trendLineThickness;
	}
	public void setTrendLineThickness(double trendLineThickness) {
		this.trendLineThickness = trendLineThickness;
	}

}

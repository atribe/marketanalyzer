package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.AmGraph;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.Guide;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.ValueAxis;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.StartEffect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmCoordinateChart extends AmChart{
	
	/**
	* Read-only. Array, holding processed chart's data.
	* Default Value: 
	*/
	protected List<Object> chartData;
	/**
	* Text color.
	* Default Value: #000000
	*/
	protected List<Color> colors;
	/**
	* The array of graphs belonging to this chart.
	* Default Value: 
	*/
	protected List<AmGraph> graphs;
	/**
	* Specifies if grid should be drawn above the graphs or below. Will not work properly with 3D charts.
	* Default Value: FALSE
	*/
	protected boolean gridAboveGraphs;
	/**
	* Instead of adding guides to the axes, you can push all of them to this array. In case guide has category or date defined, it will automatically will be assigned to the category axis. Otherwise to first value axis, unless you specify a different valueAxis for the guide.
	* Default Value: []
	*/
	protected List<Guide> guides;
	/**
	* Specifies whether the animation should be sequenced or all objects should appear at once.
	* Default Value: TRUE
	*/
	protected boolean sequencedAnimation;
	/**
	* The initial opacity of the column/line. If you set startDuration to a value higher than 0, the columns/lines will fade in from startAlpha. Value range is 0 - 1.
	* Default Value: 1
	*/
	protected double startAlpha;
	/**
	* Duration of the animation, in seconds.
	* Default Value: 0
	*/
	protected double startDuration;
	/**
	* Animation effect. Possible values are: easeOutSine, easeInSine, elastic, bounce
	* Default Value: elastic
	*/
	protected StartEffect startEffect;
	/**
	* Target of url.
	* Default Value: _self
	*/
	protected String urlTarget;
	/**
	* The array of value axes. Chart creates one value axis automatically, so if you need only one value axis, you don't need to create it.
	* Default Value: ValueAxis
	*/
	protected List<ValueAxis> valueAxes;
	
	public List<Object> getChartData() {
		return chartData;
	}
	public void setChartData(List<Object> chartData) {
		this.chartData = chartData;
	}
	public List<Color> getColors() {
		return colors;
	}
	public void setColors(List<Color> colors) {
		this.colors = colors;
	}
	public List<AmGraph> getGraphs() {
		return graphs;
	}
	public void setGraphs(List<AmGraph> graphs) {
		this.graphs = graphs;
	}
	public boolean isGridAboveGraphs() {
		return gridAboveGraphs;
	}
	public void setGridAboveGraphs(boolean gridAboveGraphs) {
		this.gridAboveGraphs = gridAboveGraphs;
	}
	public List<Guide> getGuides() {
		return guides;
	}
	public void setGuides(List<Guide> guides) {
		this.guides = guides;
	}
	public boolean isSequencedAnimation() {
		return sequencedAnimation;
	}
	public void setSequencedAnimation(boolean sequencedAnimation) {
		this.sequencedAnimation = sequencedAnimation;
	}
	public double getStartAlpha() {
		return startAlpha;
	}
	public void setStartAlpha(double startAlpha) {
		this.startAlpha = startAlpha;
	}
	public double getStartDuration() {
		return startDuration;
	}
	public void setStartDuration(double startDuration) {
		this.startDuration = startDuration;
	}
	public StartEffect getStartEffect() {
		return startEffect;
	}
	public void setStartEffect(StartEffect startEffect) {
		this.startEffect = startEffect;
	}
	public String getUrlTarget() {
		return urlTarget;
	}
	public void setUrlTarget(String urlTarget) {
		this.urlTarget = urlTarget;
	}
	public List<ValueAxis> getValueAxes() {
		return valueAxes;
	}
	public void setValueAxes(List<ValueAxis> valueAxes) {
		this.valueAxes = valueAxes;
	}


}

package com.ar.marketanalyzer.plotting.amstockcharts.buildingblock;

import com.ar.marketanalyzer.plotting.amcharts.buildingblock.AmLegend;

public class StockLegend extends AmLegend{

	private static final long serialVersionUID = 4326841233558448131L;
	
	/*
	 * http://docs.amcharts.com/3/javascriptstockchart/StockLegend
	 */
	
	/**
	* The text which will be displayed in the value portion of the legend when user is not hovering above any data point and the data sets are compared. The tags should be made out of two parts - the name of a field (value / open / close / high / low) and the value of the period you want to be show - open / close / high / low / sum / average / count. For example: [[value.sum]] means that sum of all data points of value field in the selected period will be displayed. In case you want to display percent values, you should add ""percent"" string in front of a tag, for example: [[percents.value.close]] means that last percent value of a period will be displayed.
	* Default Value: 
	*/
	 private String periodValueTextComparing;
	/**
	* The text which will be displayed in the value portion of the legend when user is not hovering above any data point. The tags should be made out of two parts - the name of a field (value / open / close / high / low) and the value of the period you want to be show - open / close / high / low / sum / average / count. For example: [[value.sum]] means that sum of all data points of value field in the selected period will be displayed.
	* Default Value: 
	*/
	 private String periodValueTextRegular;

	/**
	* The text which will be displayed in the value portion of the legend when graph is comparable and at least one dataSet is selected for comparing. You can use tags like [[value]], [[open]], [[high]], [[low]], [[close]], [[percents.value/open/close/low/high]], [[description]].
	* Default Value: [[percents.value]]%
	*/
	 private String valueTextComparing;
	/**
	* The text which will be displayed in the value portion of the legend. You can use tags like [[value]], [[open]], [[high]], [[low]], [[close]], [[percents]], [[description]].
	* Default Value: [[value]]
	*/
	private String valueTextRegular;
	public String getPeriodValueTextComparing() {
		return periodValueTextComparing;
	}
	public void setPeriodValueTextComparing(String periodValueTextComparing) {
		this.periodValueTextComparing = periodValueTextComparing;
	}
	public String getPeriodValueTextRegular() {
		return periodValueTextRegular;
	}
	public void setPeriodValueTextRegular(String periodValueTextRegular) {
		this.periodValueTextRegular = periodValueTextRegular;
	}
	public String getValueTextComparing() {
		return valueTextComparing;
	}
	public void setValueTextComparing(String valueTextComparing) {
		this.valueTextComparing = valueTextComparing;
	}
	public String getValueTextRegular() {
		return valueTextRegular;
	}
	public void setValueTextRegular(String valueTextRegular) {
		this.valueTextRegular = valueTextRegular;
	}

	
}

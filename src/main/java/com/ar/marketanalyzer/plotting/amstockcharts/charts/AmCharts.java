package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmCharts {
	/**
	* Set it to true if you have base href set for your page. This will fix rendering problems in Firefox caused by base href.
	* Default Value: FALSE
	*/
	 private boolean baseHref;
	/**
	* Array of day names, used when formatting dates (if categoryAxis.parseDates is set to true)
	* Default Value: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
	*/
	 private List<String> dayNames;
	/**
	* Array of month names, used when formatting dates (if categoryAxis.parseDates is set to true)
	* Default Value: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']
	*/
	 private List<String> monthNames;
	/**
	* Delay in ms at which each chart on the page should be rendered. This is very handy if you have a lot of charts on the page and do not want to overload the device CPU.
	* Default Value: 0
	*/
	 private double processDelay;
	/**
	* Array of short versions of day names, used when formatting dates (if categoryAxis.parseDates is set to true)
	* Default Value: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
	*/
	 private List<String> shortDayNames;
	/**
	* Array of short versions of month names, used when formatting dates (if categoryAxis.parseDates is set to true)
	* Default Value: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
	*/
	 private List<String> shortMonthNames;
	/**
	* You can set theme for all the charts on your page by setting: AmCharts.theme =AmCharts.themes.light; // or some other theme. If you are creating charts using JavaScript API, not JSON, then this is quite a comfortable way, as you won't need to pass theme to each object you create. Note, you should set theme before write method is called. There is no way to change theme of already created chart, you have to create chart's instance once more if you want to change theme.
	* Default Value: 
	*/
	 private String theme;
	/**
	* Set it to true if you want UTC time to be used instead of local time.
	* Default Value: FALSE
	*/
	 private boolean useUTC;
	public boolean isBaseHref() {
		return baseHref;
	}
	public void setBaseHref(boolean baseHref) {
		this.baseHref = baseHref;
	}
	public List<String> getDayNames() {
		return dayNames;
	}
	public void setDayNames(List<String> dayNames) {
		this.dayNames = dayNames;
	}
	public List<String> getMonthNames() {
		return monthNames;
	}
	public void setMonthNames(List<String> monthNames) {
		this.monthNames = monthNames;
	}
	public double getProcessDelay() {
		return processDelay;
	}
	public void setProcessDelay(double processDelay) {
		this.processDelay = processDelay;
	}
	public List<String> getShortDayNames() {
		return shortDayNames;
	}
	public void setShortDayNames(List<String> shortDayNames) {
		this.shortDayNames = shortDayNames;
	}
	public List<String> getShortMonthNames() {
		return shortMonthNames;
	}
	public void setShortMonthNames(List<String> shortMonthNames) {
		this.shortMonthNames = shortMonthNames;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public boolean isUseUTC() {
		return useUTC;
	}
	public void setUseUTC(boolean useUTC) {
		this.useUTC = useUTC;
	}

}

package com.ar.marketanalyzer.plotting.amstockcharts.charts;

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
	 private Array[String] dayNames;
	/**
	* Array of month names, used when formatting dates (if categoryAxis.parseDates is set to true)
	* Default Value: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']
	*/
	 private Array[String] monthNames;
	/**
	* Delay in ms at which each chart on the page should be rendered. This is very handy if you have a lot of charts on the page and do not want to overload the device CPU.
	* Default Value: 0
	*/
	 private double processDelay;
	/**
	* Array of short versions of day names, used when formatting dates (if categoryAxis.parseDates is set to true)
	* Default Value: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
	*/
	 private Array[String] shortDayNames;
	/**
	* Array of short versions of month names, used when formatting dates (if categoryAxis.parseDates is set to true)
	* Default Value: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
	*/
	 private Array[String] shortMonthNames;
	/**
	* Array of short versions of month names, used when formatting dates (if categoryAxis.parseDates is set to true)
	* Default Value: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
	*/
	 private Array[String] shortMonthNames;
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

}

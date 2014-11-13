/**
 * 
 */

AmCharts.ready(
	function(){

		$.getJSON("http://localhost:8080/marketanalyzer/json/amchart/test", 
			function(json) {
				console.log(json); // this will show the info it in firebug console
				var chartJson = eval(json);
				var chart = new AmCharts.makeChart("OHLCChart", chartJson);
			}
		);
	}
);

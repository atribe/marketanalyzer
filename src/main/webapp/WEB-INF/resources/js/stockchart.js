/**
 * 
 */
AmCharts.ready(
	function(){
	   //alert('page loaded');
		$.getJSON("http://localhost:8080/marketanalyzer/json/amchart/test", 
			function(data) {
				var chart = new AmCharts.makeChart("OHLCChart", data);
			}
		);
	}
);
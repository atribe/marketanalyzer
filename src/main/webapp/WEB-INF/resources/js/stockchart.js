/**
 * 
 */
AmCharts.ready(function(){
	   //alert('page loaded');
		$.getJSON("http://localhost:8080/marketanalyzer/json/amchart/test", function(data) {
			var chart = new AmCharts.makeChart("OHLCChart", data);
			
			chart.addListener("rendered", zoomChart);
			zoomChart();
			
			// this method is called when chart is first inited as we listen for "dataUpdated" event
			function zoomChart() {
			    // different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
			    chart.zoomToIndexes(chart.dataProvider.length - 50, chart.dataProvider.length - 1);
			}
		});
	});
/**
 * 
 */

$(function() {
    console.log( "ready!" );
    $.getJSON("http://localhost:8080/marketanalyzer/json/highstockohlcv/test",
    	function(json) {
    	
    	var chartJson = eval(json);
    	
    	$('#highchart').highcharts('StockChart', chartJson);
    })
});
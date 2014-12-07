/**
 * 
 */

$(function() {
    console.log( "ready!" );
    $.getJSON("/json/highstockohlcv/test",
    	function(json) {
    	
    	var chartJson = eval(json);
    	
    	$('#highchart').highcharts('StockChart', chartJson);
    })
});
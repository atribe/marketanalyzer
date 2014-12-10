/**
 * 
 */

$(function() {
    console.log( "stockchart.js ready!" );
    //alert(document.URL);
    
    var url = "/json/highstockohlcv/test";
    var addtourl = "/marketanalyzer";
    
    if(document.URL == "http://localhost:8080/marketanalyzer/") {
    	url = addtourl.concat(url);
    }
    
    $.getJSON(url,
    	function(json) {
    	
    	var chartJson = eval(json);
    	
    	$('#highchart').highcharts('StockChart', chartJson);
    })
});
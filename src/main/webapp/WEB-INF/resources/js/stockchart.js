/**
 * 
 */

$(function() {
    console.log( "stockchart.js ready!" );
    //alert(document.URL);
    var symbol = $('#highchart').innerHTML;
    var url = "/json/stock/"+symbol;
    var addtourl = "/marketanalyzer";
    
    alert(symbol);
    
    if(document.URL == "http://localhost:8080/marketanalyzer/") {
    	url = addtourl.concat(url);
    }
    
    $.getJSON(url,
    	function(json) {
    	
    	var chartJson = eval(json);
    	
    	$('#highchart').highcharts('StockChart', chartJson);
    })
});
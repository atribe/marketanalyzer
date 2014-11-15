AmCharts.ready(
	function(){
		if(window.location.href.indexOf('/dday')!==-1) {
			$.getJSON("http://localhost:8080/marketanalyzer/json/amchart/dday/test", 
					function(json) {
						console.log(json); // this will show the info it in firebug console
						var chartJson = eval(json);
						var chart = new AmCharts.makeChart("am-stock-chart", chartJson);
					}
				);
	    } else if(window.location.href.indexOf('/combined')!==-1) {
			$.getJSON("http://localhost:8080/marketanalyzer/json/amchart/combined/test", 
				function(json) {
					console.log(json); // this will show the info it in firebug console
					var chartJson = eval(json);
					var chart = new AmCharts.makeChart("am-stock-chart", chartJson);
				}
			);
	    } else {
			$.getJSON("http://localhost:8080/marketanalyzer/json/amchart/ohlcv/test", 
					function(json) {
						console.log(json); // this will show the info it in firebug console
						var chartJson = eval(json);
						var chart = new AmCharts.makeChart("am-stock-chart", chartJson);
					}
				);
		    }
	}
);

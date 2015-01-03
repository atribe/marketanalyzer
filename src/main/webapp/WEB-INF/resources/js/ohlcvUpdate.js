/**
 * 
 */

$(document).ready(function() {
        $('.update').click(function() {
            var id = $(this).attr("value");
            
            var httpPrefix = "http://";
            var url = "/REST/ohlcv/update/"+id;
            var addtourl = "/marketanalyzer";
            var host = $(location).attr('host');
            var href = $(location).attr('href');
            
            if(href.indexOf("http://localhost:8080/marketanalyzer/") >= 0) {
            	url = host.concat(addtourl).concat(url);
            } else if(href.indexOf("http://localhost:100080/ROOT/") >= 0) {
            	url = host.concat(addtourl).concat("ROOT");
            }
            url = httpPrefix.concat(url);
            
            console.log("The AJAX url:" + url);
            
            $.ajax({
            	url: url,
            	type: "GET",
    			beforeSend: function(xhr) {
    	            message("started", "OHLCV Update has Started for Symbol ID: " + id);
    	        },
    	        dataType: "plain",
            	success: function(ohlcvCount) {
            		$("#"+id+"_count").html(ohlcvCount+1);
            		message("success", "OHLCV Update has Succeeded for Symbol ID: " + id + " with a count of " + ohlcvCount);
            	},
            	error: function() {
            		message("fail", "OHLCV Update has Failed for Symbol ID: " + id);
            	}
            	});
        });
});

//		url: "/ohlcvmanager/update/"+id,

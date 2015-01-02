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
    	            xhr.setRequestHeader("Accept", "application/json");
    	            xhr.setRequestHeader("Content-Type", "application/json");
    	        },
            	success: function(ohlcvCount) {
            		$("#"+id+"_count").html(ohlcvCount+1);
            		alert(ohclvCount);
            	},
            	error: function() {
            		alert("error");
            	}
            	});
        });
});

//		url: "/ohlcvmanager/update/"+id,

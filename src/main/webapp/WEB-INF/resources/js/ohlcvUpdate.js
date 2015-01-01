/**
 * 
 */

$(document).ready(function() {
        $('.update').click(function() {
            var id = $(this).attr("value");
            
            var pathname=$(location).attr('pathname');
            
            var parts = pathname.split("/");
            var result = parts[parts.length - 1]; // Or parts.pop();
            
            var url = result+"/ohlcvmanager/update/"+id;
            
            $.ajax({
            	url: url,
            	//data: {id : id},
            	success: alert( "Success:")
            	});
        });
});

//		url: "/ohlcvmanager/update/"+id,

/**
 * 
 */

$(function ohlcvUpdate(id) {
	$.ajax({
		type: "POST",
		url: "/ohlcvmanager/update/"+id,
		data: "{}",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function (msg) {
			$('#myDiv').text(msg.d);
		}
	});
});
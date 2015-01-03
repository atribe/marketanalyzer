/**
 * 
 */

function message(status, message) {
	var messageDiv = document.getElementById("message");
	messageDiv.className = status;
	$(messageDiv).children('h2').replaceWith("<h2>"+message+"</h2>");

}
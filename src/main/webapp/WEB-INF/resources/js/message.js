/**
 * 
 */

function message(status, message) {
	var messageDiv = document.getElementById("message");
	messageDiv.className = status;
	var messageHeader = $(messageDiv).children('h2')[0];
	var node = document.createTextNode(message);
	messageHeader.appendChild(node);
}
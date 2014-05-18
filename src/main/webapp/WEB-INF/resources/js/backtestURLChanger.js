/**
 * 
 */
$(document).ready(function(){
	
	var trans = document.forms['backtestForm'].elements['symbol'];
	
	for (var i=0, len=trans.length; i<len; i++) {
		trans[i].onchange = function() {
			var URL = document.URL;
			URL = URL.replace(URL.substr(URL.lastIndexOf('/') + 1), '');
			
			window.history.pushState(this.value, "What title should this be", URL + this.value)
			
		}
	}
});
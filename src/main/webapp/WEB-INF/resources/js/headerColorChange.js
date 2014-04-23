/**
 * 
 */
$(document).ready(function(){
	var pathArray = window.location.pathname.split( '/' );
	
	//alert(pathArray);
	if ( pathArray[pathArray.length-1] == "")
		{
		$( '#homeLink' ).addClass("currentPage");
		}
	else if( pathArray[pathArray.length-1] =="backtest") 
		{
			$( '#backtestLink' ).addClass("currentPage");
		} 
	else if ( pathArray[pathArray.length-1] == "contact")
		{
		$( '#contactLink' ).addClass("currentPage");
		}
	
});
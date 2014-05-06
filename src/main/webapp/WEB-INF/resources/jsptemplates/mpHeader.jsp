<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="header">
	<div id="banner">
		<h1>Market Predictor</h1>
	</div> <!-- close div banner -->
	<nav>
	  <ul>
	    <li><a id="homeLink" href="<c:url value="/" />">Home</a></li>
	    <li><a id="backtestLink" href="<c:url value="backtest/^IXIC" />">Backtest</a></li>
	    <li><a id="contactLink" href="<c:url value="contact" />">Contact</a></li>
	  </ul>
	</nav>
	<div class="clear"></div>
</div><!-- close div header-->

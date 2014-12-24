<%@tag description="Generic Header Tag" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
	<div class="pure-menu pure-menu-open pure-menu-horizontal">
		<a href="<c:url value="/" />" class="pure-menu-heading">Market Predictor</a>
	  <ul>
	    <li><a id="homeLink" href="<c:url value="/" />">Home</a></li>
	    <li><a id="backtestLink" href="${pageContext.request.contextPath}/<c:url value="backtest" />">Backtest</a></li>
	    <li><a id="contactLink" href="${pageContext.request.contextPath}/<c:url value="contact" />">Contact</a></li>
	  </ul>
	</div>
	
</div>
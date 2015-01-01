<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
	<jsp:attribute name="head"></jsp:attribute>
	<jsp:attribute name="foot"></jsp:attribute>
	<jsp:body>
		<div id="dayStatus">
			<h3>Today you should:</h3>
			<h2>SELL</h2>
		</div>
		<div id="highchart" class="chart"></div>
		<p>The context path is: ${pageContext.request.contextPath}.</p>
		<p>The requestURL is: ${pageContext.request.requestURL}.</p>
		<p>The requestURI is: ${pageContext.request.requestURI}.</p>
	</jsp:body>
</t:genericpage>
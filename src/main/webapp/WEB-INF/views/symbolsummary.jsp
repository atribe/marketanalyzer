<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>



<t:genericpage>
	<jsp:attribute name="head"></jsp:attribute>
	<jsp:attribute name="foot"></jsp:attribute>
	<jsp:attribute name="message">${message}</jsp:attribute>
	<jsp:attribute name="messagestatus">${messagestatus}</jsp:attribute>
	<jsp:body>
		<div id="dayStatus">
			<h3>Today you should:</h3>
			<h2>SELL</h2>
		</div>
		
	</jsp:body>
</t:genericpage>
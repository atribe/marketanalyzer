<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
		<form:form method="GET" modelAttribute="symbol" action="${pageContext.request.contextPath}/stocksummary" class="pure-form pure-form-aligned">
			<legend>Pick a Stock to See the Summary Info</legend>
			<fieldset>
				<div class="pure-control-group">
					<form:select path="symbol">
					   <form:option value="NONE" label="--- Select ---"/>
					   <form:options items="${symbols}" itemValue="symbol" itemLabel="symbol"/>
					</form:select>
				</div>
				<button type="submit" class="pure-button pure-button-primary">Submit</button>
			</fieldset>
		</form:form>
	</jsp:body>
</t:genericpage>
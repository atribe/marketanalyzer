<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page isELIgnored="false" %>

<html>
	<head>
		<title>Test MarketAnalyzer</title>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=320; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
		<link rel="stylesheet" type="text/css" href="<c:url value="css/style.css" />" />
	</head>

	<body><% System.out.println("Start of Body Tag"); %>
		<div id="container">
			<!-- Header Div (in mpHeader file)-->
				<jsp:include page="/WEB-INF/resources/jsptemplates/mpHeader.jsp" />
			<!-- End Header Div -->
			<div id="content">
				<div id="backtest">
					<p>Select the index to backtest.</p>
					<form:form method="POST" commandName="backtestModel" action="backtestResults">
					<div id="backtestIndexPicker">
						<ul>
							<form:radiobuttons element="li" path="index" items="${indexList}" />
						</ul>
					</div>
					<p>The model parameters for the selected index are:</p>
					<div id="backtestModelParameters">
						<ul>
							<form:input path="" value="" />
						</ul>
					</div>
					<input value="Submit" type="submit">
					</form:form>
				</div>

			<!-- Footer Div (in mpFooter file-->
				<jsp:include page="/WEB-INF/resources/jsptemplates/mpFooter.jsp" />
			<!-- End Footer Div -->
		</div> <!-- close div container -->
	</body>
</html>
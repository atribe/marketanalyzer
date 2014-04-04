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
							<form:radiobuttons element="li" path="symbol" items="${indexList}" />
						</ul>
					</div>
					<p>The model parameters for the selected index are:</p>
					<div id="backtestModelParameters">
						<ul>
							<li class="backtestLabel">Start Date</li>
							<li><form:input class="backtestInput" path="startDate" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">End Date</li>
							<li><form:input class="backtestInput" path="endDate" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">D Day Window</li>
							<li><form:input class="backtestInput" path="dDayWindow" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">D Day Parameter</li>
							<li><form:input class="backtestInput" path="dDayParam" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Churn Volume Range</li>
							<li><form:input class="backtestInput" path="churnVolRange" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Churn Price Range</li>
							<li><form:input class="backtestInput" path="churnPriceRange" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Churn Price Close Higher On</li>
							<li><form:input class="backtestInput" path="churnPriceCloseHigherOn" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Churn Average 50 On</li>
							<li><form:input class="backtestInput" path="churnAVG50On" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Churn Price Trend 35 On</li>
							<li><form:input class="backtestInput" path="churnPriceTrend35On" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Churn Price Trend 35</li>
							<li><form:input class="backtestInput" path="churnPriceTrend35" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Volume Volatility On</li>
							<li><form:input class="backtestInput" path="volVolatilityOn" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Volume Mult</li>
							<li><form:input class="backtestInput" path="volumeMult" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Volume Mult Top</li>
							<li><form:input class="backtestInput" path="volMultTop" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Volume Mult Bot</li>
							<li><form:input class="backtestInput" path="volMultBot" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Price Volatility On</li>
							<li><form:input class="backtestInput" path="priceVolatilityOn" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Price Mult</li>
							<li><form:input class="backtestInput" path="priceMult" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Price Mult Top</li>
							<li><form:input class="backtestInput" path="priceMultTop" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Price Mult Bot</li>
							<li><form:input class="backtestInput" path="priceMultBot" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">R Days Min</li>
							<li><form:input class="backtestInput" path="rDaysMin" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">R Days Max</li>
							<li><form:input class="backtestInput" path="rDaysMax" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">pivotTrent35On</li>
							<li><form:input class="backtestInput" path="pivotTrend35On" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">pivotTrent35</li>
							<li><form:input class="backtestInput" path="pivotTrend35" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Rally Volume Average 50 On</li>
							<li><form:input class="backtestInput" path="rallyVolAVG50On" /></li>
						</ul>
						<ul>
							<li class="backtestLabel">Rally Price High On</li>
							<li><form:input class="backtestInput" path="rallyPriceHighOn" /></li>
						</ul>
					</div>
					<div class="clear">
					<input class="submitButton" value="Submit" type="submit">
					</div>
					</form:form>
				</div>

			<!-- Footer Div (in mpFooter file-->
				<jsp:include page="/WEB-INF/resources/jsptemplates/mpFooter.jsp" />
			<!-- End Footer Div -->
		</div> <!-- close div container -->
	</body>
</html>
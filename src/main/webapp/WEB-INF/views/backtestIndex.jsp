<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page isELIgnored="false" %>
<jsp:include page="/WEB-INF/resources/jsptemplates/mpHead.jsp" />
<% System.out.println("Backtest.jsp has been served"); %>
	<body>
		<div id="container">
			<!-- Header Div (in mpHeader file)-->
				<jsp:include page="/WEB-INF/resources/jsptemplates/mpHeader.jsp" />
			<!-- End Header Div -->
			<div id="content">
				<div class="Index_Results">
					<div class="sectionHeader"><h2>Baseline and Current Results Section</h2></div>
						<div class="singleBacktestInfoBlock">
							<div class="singleBacktestTitle">
								<h3><a href="<c:url value="backtest/${backtest.symbol}" />" >
									<c:out value="${currentBacktest.symbol}">No name</c:out> Result
								</a></h3>
								<h5>Full Name of the symbol</h5>
							</div> <!-- End singleBacktestTitle div -->
							<div class="singleBacktestStats">
								<ul>
									<li>
										<h4>Result Time Period:</h4> 
										<p class="daterange">${currentBacktest.startDate} - ${currentBacktest.endDate}</p>
									</li>
									<li>
										<h4>Baseline Return:</h4>
										<p class="percentReturn"><fmt:formatNumber type="percent" minIntegerDigits="1" maxFractionDigits="2" value="${baseline.totalPercentReturn}" /></p>
									</li>
									<li>
										<h4>Current Model:</h4>
										<p class="percentReturn"><fmt:formatNumber type="percent" minIntegerDigits="1" maxFractionDigits="2" value="${currentBacktest.totalPercentReturn}" /></p>
									</li>
								</ul>
							</div> <!-- End singleBacktestStats div -->
						</div> <!-- End singleBacktestInfoBlock div -->
					<div id="ResultChart" class="chart">
						<c:if test="${not empty currentBacktest.symbol}">
							<img src="<c:url value="charts/backtest/${currentBacktest.symbol}" />" />
						</c:if>
					</div>
					<h2>Current Model Results for Each Index</h2>
					<table>
						<tr>
							<th>Type</th>
							<th>Index</th>
							<th>Results Start Date</th>
							<th>Results End Date</th>
							<th>Initial Investment</th>
							<th>Final Value</th>
							<th>% Return</th>
							<th>Number of Trades</th>
							<th>Number of Profitable Trades</th>
						</tr>
						<tr>
							<td>Baseline</td>
							<td>${baseline.symbol}</td>
							<td>${baseline.startDate}</td>
							<td>${baseline.endDate}</td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${baseline.costBasis}" type="currency"/></td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${baseline.finalValue}" type="currency"/></td>
							<td><fmt:formatNumber type="percent" maxIntegerDigits="3" value="${baseline.totalPercentReturn}" /></td>
						</tr>
						<tr>
							<td>Current</td>
							<td>${currentBacktest.symbol}</td>
							<td>${currentBacktest.startDate}</td>
							<td>${currentBacktest.endDate}</td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${currentBacktest.costBasis}" type="currency"/></td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${currentBacktest.finalValue}" type="currency"/></td>
							<td><fmt:formatNumber type="percent" maxIntegerDigits="3" value="${currentBacktest.totalPercentReturn}" /></td>
							<td>${currentBacktest.numberOfTrades}</td>
							<td>${currentBacktest.numberOfProfitableTrades}</td>
						</tr>
					</table>
				</div>
				<div id="backtest">
					<form:form method="POST" commandName="currentBacktest" action="backtest/" id="backtestForm">
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
							<li class="backtestLabel">D Day Price Drop</li>
							<li><form:input class="backtestInput" path="dDayPriceDrop" /></li>
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
			</div>
			<!-- Footer Div (in mpFooter file-->
				<jsp:include page="/WEB-INF/resources/jsptemplates/mpFooter.jsp" />
			<!-- End Footer Div -->
		</div> <!-- close div container -->
		<!-- 
		<c:set var="url">${pageContext.request.requestURL}</c:set>
    	<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
		<script type="text/javascript" src="<c:url value="js/backtestURLChanger.js" />" ></script>
		 -->
	</body>
</html>
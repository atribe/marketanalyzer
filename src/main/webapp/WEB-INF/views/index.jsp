<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page isELIgnored="false" %>

<html xmlns="http://www.w3.org/1999/xhtml">
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
				<div class="OHLCSection">
					<div class="sectionHeader"><h2>Index OHLC Section</h2></div>
					<div id="OHLCChart" class="chart">
						<img src="<c:url value="charts/OHLC" />" />
					</div>
				</div>
				<div class="D_Day_Table">
					<div class="sectionHeader"><h2>D Day Section</h2></div>
					<h3>Nasdaq Distribution Days in the Last 120 Days</h3>
					<div id="dDayChart" class="chart">
						<img src="<c:url value="charts/dday" />" />
					</div>
					
				<!-- D Day table stuff used for debugging
					<table class="DDays">
						<tr>
							<th>DB id</th>
							<th>Date</th>
							<th>Is a D Day?</th>
							<th>Churn or Regular</th>
							<th>D Day Count</th>
						</tr>
						<c:forEach items="${dDayList}" var="indexCalcs">
						<tr>
							<td>${indexCalcs.id}</td>
							<td>${indexCalcs.date}</td>
							<td>${indexCalcs.distributionDay}</td>
							<td>${indexCalcs.churnDay}</td>
							<td>${indexCalcs.distributionDayCounter}</td>
						</tr>
						</c:forEach>	
					</table>
				 -->
				</div>
				<div class="Index_Results">
					<div class="sectionHeader"><h2>Results Section</h2></div>
					<h2>Baseline Results for each Index</h2>
					<table>
						<tr>
							<th>Index</th>
							<th>Results Start Date</th>
							<th>Results End Date</th>
							<th>Initial Investment</th>
							<th>Final Value</th>
							<th>% Return</th>
						</tr>
						<tr>
							<td>${result1.symbol}</td>
							<td>${result1.startDate}</td>
							<td>${result1.endDate}</td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${initialInvestment1}" type="currency"/></td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${finalValue1}" type="currency"/></td>
							<td><fmt:formatNumber type="percent" maxIntegerDigits="3" value="${result1.totalPercentReturn}" /></td>
						</tr>
						<tr>
							<td>${result2.symbol}</td>
							<td>${result2.startDate}</td>
							<td>${result2.endDate}</td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${initialInvestment2}" type="currency"/></td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${finalValue2}" type="currency"/></td>
							<td><fmt:formatNumber type="percent" maxIntegerDigits="3" value="${result2.totalPercentReturn}" /></td>
						</tr>
						<tr>
							<td>${result3.symbol}</td>
							<td>${result3.startDate}</td>
							<td>${result3.endDate}</td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${initialInvestment3}" type="currency"/></td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${finalValue3}" type="currency"/></td>
							<td><fmt:formatNumber type="percent" maxIntegerDigits="3" value="${result3.totalPercentReturn}" /></td>
						</tr>
					</table>
					<h2>Current Model Results for Each Index</h2>
					<table>
						<tr>
							<th>Index</th>
							<th>Results Start Date</th>
							<th>Results End Date</th>
							<th>Initial Investment</th>
							<th>Final Value</th>
							<th>% Return</th>
						</tr>
						<tr>
							<td>${result1.symbol}</td>
							<td>${result1.startDate}</td>
							<td>${result1.endDate}</td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${initialInvestment1}" type="currency"/></td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${finalValue1}" type="currency"/></td>
							<td><fmt:formatNumber type="percent" maxIntegerDigits="3" value="${result1.totalPercentReturn}" /></td>
						</tr>
						<tr>
							<td>${result2.symbol}</td>
							<td>${result2.startDate}</td>
							<td>${result2.endDate}</td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${initialInvestment2}" type="currency"/></td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${finalValue2}" type="currency"/></td>
							<td><fmt:formatNumber type="percent" maxIntegerDigits="3" value="${result2.totalPercentReturn}" /></td>
						</tr>
						<tr>
							<td>${result3.symbol}</td>
							<td>${result3.startDate}</td>
							<td>${result3.endDate}</td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${initialInvestment3}" type="currency"/></td>
							<td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${finalValue3}" type="currency"/></td>
							<td><fmt:formatNumber type="percent" maxIntegerDigits="3" value="${result3.totalPercentReturn}" /></td>
						</tr>
					</table>
				</div>
			</div>
			<!-- Footer Div (in mpFooter file-->
				<jsp:include page="/WEB-INF/resources/jsptemplates/mpFooter.jsp" />
			<!-- End Footer Div -->
		</div> <!-- close div container -->
	</body>
</html>
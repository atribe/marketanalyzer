<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
				<div class="D_Day_Table">
					<h3>Nasdaq Distribution Days in the Last 120 Days</h3>
					<div id="dDayChart" class="chart">
						<img src="<c:url value="charts/dday" />" />
					</div>
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
				</div>
				<div id="demo">
					<h3>Demo jFreeChart</h3>	
					<img src="<c:url value="charts/piechart" />" />
				</div>
			</div>
			<!-- Footer Div (in mpFooter file-->
				<jsp:include page="/WEB-INF/resources/jsptemplates/mpFooter.jsp" />
			<!-- End Footer Div -->
		</div> <!-- close div container -->
	</body>
</html>
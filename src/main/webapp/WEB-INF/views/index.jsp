<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page isELIgnored="false" %>
<jsp:include page="/WEB-INF/resources/jsptemplates/mpHead.jsp" />
<% System.out.println("Index.jsp has been served"); %>
	<body>
		<div id="container">
			<!-- Header Div (in mpHeader file)-->
				<jsp:include page="/WEB-INF/resources/jsptemplates/mpHeader.jsp" />
			<!-- End Header Div -->
			<div id="content">
				<div class="OHLCSection">
					<div class="sectionHeader"><h2>Index OHLC Section</h2></div>
					<div id="highchart" class="chart">
						<!-- <img src="<c:url value="charts/OHLC" />" /> -->
					</div>
				</div>
				<div class="modelList">
					<table class="modelListTable">
						<thead>
						<tr>
							<th>Model ID</th>
							<th>Symbol</th>
							<th>Model Name</th>
							<th>Initial Investment</th>
							<th>Start Date</th>
							<th>End Date</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="backtestModel" items="${modelList}" >
						<tr>
							<td><c:out value="${backtestModel.id}"/></td>
							<td><c:out value="${backtestModel.symbol.symbol}"/></td>
							<td><c:out value="${backtestModel.modelName}"/></td>
							<td><c:out value="${backtestModel.initialInvestment}"/></td>
							<td><c:out value="${backtestModel.startDate}"/></td>
							<td><c:out value="${backtestModel.endDate}"/></td>
						</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			<!-- Footer Div (in mpFooter file-->
				<jsp:include page="/WEB-INF/resources/jsptemplates/mpFooter.jsp" />
			<!-- End Footer Div -->
		</div> <!-- close div container -->
	</body>
</html>
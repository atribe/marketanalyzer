<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
	<jsp:attribute name="head"></jsp:attribute>
	<jsp:attribute name="foot"></jsp:attribute>
	<jsp:body>
		<div id="dayStatus">
			<h3>Today you should:</h3>
			<h2>SELL</h2>
		</div>
		<div id="symbolmanager">
			<table class="symbolmanagerTable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Symbol</th>
						<th>Description</th>
						<th>Type</th>
						<th>Count of OHLCV data</th>
						<th>OHLC Auto Updating</th>
						<th>Manual Update OHLCV</th>
						<th>Plot</th>
						<th>Remove</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="symbol" items="${symbols}" >
					<tr>
						<td><c:out value="${symbol.id}"/></td>
						<td><c:out value="${symbol.symbol}"/></td>
						<td><c:out value="${symbol.name}"/></td>
						<td><c:out value="${symbol.type}"/></td>
						<td><c:out value="${fn:length(symbol.ohlcv)}"/></td>
						<td></td>
						<td></td>
						<td></td>
						<td><a href="<c:url value="stockmanager/delete/${symbol.id}" />">Delete</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</jsp:body>
</t:genericpage>
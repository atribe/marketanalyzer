<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
	<jsp:attribute name="head">
        <script src="<t:baseurl/><c:url value="/js/ohlcvUpdate.js" />" type="text/javascript"></script>
        <script src="<t:baseurl/><c:url value="/js/message.js" />" type="text/javascript"></script>
	</jsp:attribute>
	<jsp:attribute name="foot"></jsp:attribute>
	<jsp:attribute name="message">${message}</jsp:attribute>
	<jsp:attribute name="messagestatus">${messagestatus}</jsp:attribute>
	<jsp:body>
		<div id="dayStatus">
			<h3>Today you should:</h3>
			<h2>SELL</h2>
		</div>
		<div id="symbolmanager">
			<table class="pure-table pure-table-bordered">
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
						<th>Edit Entry</th>
						<th>Remove From DB</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="symbol" items="${symbols}" >
					<tr>
					
						<td id="<c:out value="id_${symbol.id}"/>"><c:out value="${symbol.id}"/></td>
						<td id="<c:out value="symbol_${symbol.id}"/>"><c:out value="${symbol.symbol}"/></td>
						<td id="<c:out value="name_${symbol.id}"/>"><c:out value="${symbol.name}"/></td>
						<td id="<c:out value="type_${symbol.id}"/>"><c:out value="${symbol.type}"/></td>
						<td id="<c:out value="count_${symbol.id}"/>"><c:out value="${fn:length(symbol.ohlcv)}"/></td>
						<td></td>
						<td><button class="pure-button update" type="button" value="${symbol.id}">Update</button></td>
						<td></td>
						<td><a class="pure-button edit" href="<t:baseurl/><c:url value="/stockmanager/edit/${symbol.id}" />">Edit</a></td>
						<td><a class="pure-button delete" href="<t:baseurl/><c:url value="/stockmanager/delete/${symbol.id}" />">Delete</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<div id="addsymbollink">
				<a class="pure-button" href="<c:url value="stockmanager/add" />">Add a symbol</a>
			</div>
		</div>
	</jsp:body>
</t:genericpage>
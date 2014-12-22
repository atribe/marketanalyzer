<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
	<jsp:attribute name="head"></jsp:attribute>
	<jsp:attribute name="foot"></jsp:attribute>
	<jsp:body>
		<form:form method="POST" modelAttribute="symbol" action="${pageContext.request.contextPath}/addprocess">
		<table>
			<tbody>
				<tr>
					<td>Symbol:</td>
					<td><form:input path="symbol" /></td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Type:</td>
					<td><form:input path="type" /></td>
				</tr>  
				<tr>
					<td><input type="submit" value="Add" /></td>
					<td></td>
				</tr>
			</tbody>
		</table>
		</form:form>
	</jsp:body>
</t:genericpage>
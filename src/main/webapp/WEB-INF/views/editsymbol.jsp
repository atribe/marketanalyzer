<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
	<jsp:attribute name="head"></jsp:attribute>
	<jsp:attribute name="foot"></jsp:attribute>
	<jsp:body>
		<form:form method="POST" modelAttribute="symbol" action="${pageContext.request.contextPath}/stockmanager/editprocess" class="pure-form pure-form-aligned">
			<legend>Add a Symbol to the Database</legend>
			<fieldset>
				<form:hidden path="id" />
				<div class="pure-control-group">
					<form:label path="symbol">Symbol:</form:label>
					<form:input path="symbol" />
				</div>
				
				<div class="pure-control-group">
					<form:label path="name">Description:</form:label>
					<form:input path="name" />
				</div>

				<div class="pure-control-group">
					<form:label path="type">Type:</form:label>
					<form:input path="type" />
				</div>
				<div class="pure-control-group">
					<form:label path="oldestDateInDb">Oldest Date in DB:</form:label>
					<form:checkbox path="oldestDateInDb" />
				</div>
				<button type="submit" class="pure-button pure-button-primary">Submit</button>
			</fieldset>
		</form:form>
	</jsp:body>
</t:genericpage>
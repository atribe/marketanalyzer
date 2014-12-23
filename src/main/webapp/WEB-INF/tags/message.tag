<%@tag description="Message from Program Tag" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@attribute name="message" fragment="true" %>
<%@attribute name="messagestatus" fragment="true" %>

<c:if test="${not empty message}">
	<div id="message" class="<jsp:invoke fragment="messagestatus"/>">
		<h2><jsp:invoke fragment="message"/></h2>
	</div>
</c:if>
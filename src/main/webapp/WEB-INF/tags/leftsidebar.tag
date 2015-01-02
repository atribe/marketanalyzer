<%@tag description="Left Side Bar" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="topofsidebar" fragment="true" %>
<%@attribute name="bottomofsidebar" fragment="true" %>
<div id="leftsidebar">
	<jsp:invoke fragment="topofsidebar"/>
		<div class="pure-menu pure-menu-open">
			<a class="pure-menu-heading">Stock Management</a>
		
		<ul>
			<li><a id="stockSummary" href="${pageContext.request.contextPath}/<c:url value="stocksummary" />">Stock Summary</a></li>
			<li><a id="stockManager" href="${pageContext.request.contextPath}/<c:url value="stockmanager" />">Stock Manager</a></li>
			<li><a id="modelSummary" href="${pageContext.request.contextPath}/<c:url value="modelsummary" />">Model Summary</a></li>
			<li><a id="modelManager" href="${pageContext.request.contextPath}/<c:url value="modelmanager" />">Model Manager</a></li>
		</ul>
		<jsp:invoke fragment="bottomofsidebar"/>
	</div>
</div>
<%@tag description="Left Side Bar" language="java" pageEncoding="UTF-8"%>
<%@attribute name="topofsidebar" fragment="true" %>
<%@attribute name="bottomofsidebar" fragment="true" %>
<div id="leftsidebar">
	<jsp:invoke fragment="topofsidebar"/>
	<ul>
		<li><a id="stockSummary" href="">Stock Summary</a></li>
		<li><a id="stockManager" href="">Stock Manager</a></li>
		<li><a id="modelSummary" href="">Model Summary</a></li>
		<li><a id="modelManager" href="">Model Manager</a></li>
	</ul>
	<jsp:invoke fragment="bottomofsidebar"/>
</div>
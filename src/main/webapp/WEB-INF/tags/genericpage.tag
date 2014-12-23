<%@tag description="Overall Page Template" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="foot" fragment="true" %>
<%//@attribute name="header" fragment="true" %>
<%//@attribute name="footer" fragment="true" %>
<!DOCTYPE html>
<html>
	<t:head>
		<jsp:invoke fragment="head"/>
	</t:head>
	<body>
		<div id="wrapper">
			<t:header>
			</t:header>
			<div id="container">
				<t:leftsidebar>
				</t:leftsidebar>
				<div id="content">
					<t:message/>
					<jsp:doBody/>
				</div>
			</div>
			<t:footer>
			</t:footer>
		</div>
	</body>
	<t:foot>
		<jsp:invoke fragment="foot"/>
	</t:foot>
</html>

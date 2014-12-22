<%@ tag description="Generic Head Tag" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@attribute name="head" fragment="true" %>

<c:set var="baseURL" value="${fn:replace(request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}" />
<c:set var="url" value="${pageContext.request.requestURL}" />
<c:set var="uri" value="${pageContext.request.requestURI}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${contextPath}/" />
<head>
	<title>Test MarketAnalyzer</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="shortcut icon" href="<c:url value="img/favicon.ico" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="css/style.css" />" />
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
	<script src="<c:url value="js/headerColorChange.js" />" type="text/javascript"></script>
	
	<!--
	<script src="<c:url value="js/highstock/highstock.src.js" />" type="text/javascript"></script>
	<script src="<c:url value="js/stockchart.js" />" type="text/javascript"></script>
	 -->	
	 
	<jsp:invoke fragment="head"/>
</head>
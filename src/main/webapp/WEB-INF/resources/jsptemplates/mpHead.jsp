<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<% 
		String url = pageContext.request.requestURL;
		String uri = pageContext.request.requestURI;
		String contextPath = pageContext.request.contextPath;
		%>
		<title>Test MarketAnalyzer</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=320; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
		<base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${contextPath}/" />
		<link rel="shortcut icon" href="<c:url value="img/favicon.ico" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="css/style.css" />" />
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
		<script src="<c:url value="js/headerColorChange.js" />" type="text/javascript"></script>
		
        <script src="<c:url value="js/highstock/highstock.src.js" />" type="text/javascript"></script>
        <script src="<c:url value="js/stockchart.js" />" type="text/javascript"></script>
	</head>

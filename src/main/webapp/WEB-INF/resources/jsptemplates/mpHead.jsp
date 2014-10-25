<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Test MarketAnalyzer</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=320; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
		<c:set var="url">${pageContext.request.requestURL}</c:set>
    	<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
		<link rel="shortcut icon" href="<c:url value="img/favicon.ico" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="css/style.css" />" />
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
		<script src="<c:url value="js/headerColorChange.js" />" type="text/javascript"></script>
		
		<script src="<c:url value="js/amcharts/amcharts.js" />" type="text/javascript"></script>
        <script src="<c:url value="js/amcharts/serial.js" />" type="text/javascript"></script>
        <script src="<c:url value="js/amcharts/amstock.js" />" type="text/javascript"></script>
        <link rel="stylesheet" href="<c:url value="css/amcharts/style.css" /> type="text/css">
        
        <script type="text/javascript">
        	
		</script>
	</head>

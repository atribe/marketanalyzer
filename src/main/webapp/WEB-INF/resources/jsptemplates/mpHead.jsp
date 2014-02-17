<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head><% System.out.println("Starting Head of Index.jsp"); %>
		<title><%-- <spring:message code="label.applicationTitle" /> --%>Test MarketAnalyzer</title>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=320; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
		
		<link rel="stylesheet" type="text/css" href="<c:url value="css/style.css" />" />
		
		<script src="http://code.createjs.com/easeljs-0.5.0.min.js"></script> 
		<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
		
		<script src="js/base64.js" type="text/javascript"></script>
		<script src="js/canvas2image.js" type="text/javascript"></script>
		
		<!-- Current Locale : ${pageContext.response.locale}  -->	
		<% System.out.println("End Head of Index.jsp"); %>
	</head>
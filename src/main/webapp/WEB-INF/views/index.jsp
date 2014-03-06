<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page isELIgnored="false" %>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head><% System.out.println("Starting Head of Index.jsp"); %>
		<title><%-- <spring:message code="label.applicationTitle" /> --%>Test MarketAnalyzer</title>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=320; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
		
		<link rel="stylesheet" type="text/css" href="<c:url value="css/style.css" />" />
		
		<!-- <script type="text/javascript" src="<c:url value="js/HumbleFinance.js" />"></script> -->
		
		<% System.out.println("End Head of Index.jsp"); %>
	</head>

	<body>
		<div id="container">
		<% System.out.println("Start of Body Tag"); %>
		<jsp:include page="/WEB-INF/resources/jsptemplates/mpHeader.jsp" />
	
		<div id="humbleFinance">
			<!--[if IE]>
		    <script type="text/javascript" src="/static/lib/FlashCanvas/bin/flashcanvas.js"></script>
		    <![endif]-->
		    <script type="text/javascript" src="<c:url value="js/flotr2.min.js"/>"></script>
		    <script type="text/javascript">
		      (function () {
		
		        var
		          container = document.getElementById('humbleFinance'),
		          start = (new Date).getTime(),
		          data, graph, offset, i;
		
		        // Draw a sine curve at time t
		        function animate (t) {
		
		          data = [];
		          offset = 2 * Math.PI * (t - start) / 10000;
		
		          // Sample the sine function
		          for (i = 0; i < 4 * Math.PI; i += 0.2) {
		            data.push([i, Math.sin(i - offset)]);
		          }
		
		          // Draw Graph
		          graph = Flotr.draw(container, [ data ], {
		            yaxis : {
		              max : 2,
		              min : -2
		            }
		          });
		
		          // Animate
		          setTimeout(function () {
		            animate((new Date).getTime());
		          }, 50);
		        }
		
		        animate(start);
		      })();
		    </script>
		</div>
		<div id="demo junk">
			<p>test. Hi Merritt!</p>
			
			<img src="<c:url value="charts/piechart" />" />
		</div>
		<jsp:include page="/WEB-INF/resources/jsptemplates/mpFooter.jsp" />
		</div> <!-- close div container -->
	</body>
</html>
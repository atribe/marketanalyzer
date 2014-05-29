<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% //Start JSTL tag includes %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page isELIgnored="false" %>
<% //End JSTL tag includes %>

<% //Start Spring tag includes %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<% //End Spring tag includes %>

<% System.out.println("Backtest.jsp is being served"); %>

<% //Start Head Include %>
	<jsp:include page="/WEB-INF/resources/jsptemplates/mpHead.jsp" />
<% //End Head Include %>

	<body>
		<div id="container">
			<% //Header Div (in mpHeader file) %>
				<jsp:include page="/WEB-INF/resources/jsptemplates/mpHeader.jsp" />
			<% //End Header Div %>
			<div id="content">
			<c:if test="${not empty backtests}">
				<div id="backtestSummaries">
				<c:forEach items="${backtests}" var="backtest" varStatus="backtestIterator">
					<div class="singleBacktestResult">
						<div class="singleBacktestInfoBlock">
							<div class="singleBacktestTitle">
								<h3><a href="<c:url value="backtest/${backtest.symbol}" />" >
									<c:out value="${backtest.symbol}">No name</c:out> Result
								</a></h3>
								<h5>Full Name of the symbol</h5>
							</div> <!-- End singleBacktestTitle div -->
							<div class="singleBacktestStats">
								<ul>
									<li>
										<h4>Result Time Period:</h4> 
										<p class="daterange">${backtest.startDate} - ${backtest.endDate}</p>
									</li>
									<li>
										<h4>Baseline Return:</h4>
										<p class="percentReturn"><fmt:formatNumber type="percent" minIntegerDigits="1" maxFractionDigits="2" value="${baselineList[backtestIterator.index].totalPercentReturn}" /></p>
									</li>
									<li>
										<h4>Current Model:</h4>
										<p class="percentReturn"><fmt:formatNumber type="percent" minIntegerDigits="1" maxFractionDigits="2" value="${backtest.totalPercentReturn}" /></p>
									</li>
								</ul>
							</div> <!-- End singleBacktestStats div -->
						</div> <!-- End singleBacktestInfoBlock div -->
						<div class="singleBacktestPlot">
							<a href="<c:url value="backtest/${backtest.symbol}" />" >
								<img src="<c:url value="charts/backtest/${backtest.symbol}" />" />
							</a>
						</div> <!-- End singleBacktestPlot div -->
						<div class="clear"></div>
					</div>
				</c:forEach>
				</div> <!-- End backtestSummaries div -->
			</c:if>
			<c:if test="${empty backtests}">
				<div class="modelRunning">
				<h2>Hold On A Second!</h2>
				<p>
				No need to be in such a rush!
				
				The model is currently running, results will momentarily be ready. Wait just a second more and refresh and you should be good.
				</p>
				</div>
			</c:if>
			</div> <!-- End content div -->
			<!-- Footer Div (in mpFooter file)-->
				<jsp:include page="/WEB-INF/resources/jsptemplates/mpFooter.jsp" />
			<!-- End Footer Div -->
		</div> <!-- close div container -->
	</body>
</html>
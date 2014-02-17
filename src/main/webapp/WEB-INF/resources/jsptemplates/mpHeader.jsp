<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="header">
	<div id="banner">
		<h1>Market Predictor</h1>
	</div> <!-- close div banner -->
	<nav>
	  <ul>
	    <li><a class="currentPage" href="<c:url value="" />">Home</a></li>
	    <li><a href="tempDisplay">Temp Display</a></li>
	    <li><a href="contact">Contact</a></li>
	  </ul>
	</nav>
	<div id="headerDate"><p>${dateOut}</p></div>
	<div class="clear"></div>
</div><!-- close div header-->

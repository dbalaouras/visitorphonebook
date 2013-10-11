<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />

<div class="container">
	<div class="hero-unit margin-top">
		<h2>
			<spring:message code="err.404.title" />
		</h2>
		<p>
			<spring:message code="err.404.message" />
		</p>
	</div>
</div>
<jsp:include page="footer.jsp" />
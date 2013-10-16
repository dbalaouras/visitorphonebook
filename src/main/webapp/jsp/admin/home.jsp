<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="../header.jsp" />
<div class="container">
	<div class="margin-top">
		<ul class="breadcrumb">
			<li><a href="home/"><spring:message code="menu.home" /></a><span
				class="divider">/</span></li>
			<li class="active"><spring:message code="menu.admin" /></li>
		</ul>
	</div>
	<h1 class="jetstrap-highlighted margin-top">
		<spring:message code="app.backend_name" />
	</h1>
	<p class="">
		<spring:message code="app.backend_home_intro" />
		<br>
	</p>
</div>
<!-- /container -->
<jsp:include page="../footer.jsp" />
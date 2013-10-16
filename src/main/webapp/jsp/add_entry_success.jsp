<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="current_page" value="add_entry" scope="request" />
<jsp:include page="header.jsp" />
<div class="container">
	<div style="margin-top: 20px">
		<div class="row">
			<div class="span6">
				<h3 class="text-success">
					<spring:message code="app.entry_saved" />
				</h3>
				<p class="margin-top">
					<a href="${baseurl}entries" class="btn  btn-primary"><spring:message
							code="app.continue_browsing" /></a> <a href="${baseurl}entries/add"
						class="btn btn-success"><spring:message code="app.add_more" /></a>
				</p>
				<p style="font-size: 40px;" class="margin-top">
					<i class="icon-thumbs-up-alt icon-4x"></i>
				</p>
			</div>
			<div class="span6">
				<c:if test="${ not empty entry.name}">
					<h2>
						<i class="icon-book icon-white"></i> ${entry.name}
					</h2>
				</c:if>
				<c:if test="${ not empty entry.emailAddress}">
					<h2>
						<i class="icon-phone-sign"></i> ${entry.emailAddress}
					</h2>
				</c:if>
				<c:if test="${ not empty entry.emailAddress}">
					<h2>
						<i class="icon-book icon-envelope"></i> ${entry.emailAddress}
					</h2>
				</c:if>
				<c:if test="${ not empty entry.website}">
					<h2>
						<i class="icon-book icon-globe"></i> ${entry.website}
					</h2>
				</c:if>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('form:first *:input[type!=hidden]:first').focus();
	});
</script>
<!-- /container -->
<jsp:include page="footer.jsp" />

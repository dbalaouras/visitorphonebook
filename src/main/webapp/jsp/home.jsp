<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

<div class="container">
	<div class="hero-unit margin-top" id="welcome">
		<h1 class="jetstrap-highlighted">
			<spring:message code="app.name" />
		</h1>
		<p class="lead">
			<spring:message code="app.intro" />
		</p>
		<p class="">
			<a href="entries" class="btn btn-large btn-primary"><spring:message
					code="butt.browse_online" /></a> <a href="entries.html" class=""><img
				src="assets/img/Google-Play-Badge.png" /></a>
		</p>
	</div>
</div>

<!-- /container -->
<jsp:include page="footer.jsp" />
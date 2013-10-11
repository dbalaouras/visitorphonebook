<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="twitter_url" scope="request">
	<spring:message code="app.twitter_url" />
</c:set>
<c:set var="github_url" scope="request">
	<spring:message code="app.github_url" />
</c:set>
<c:set var="facebook_url" scope="request">
	<spring:message code="app.facebook_url" />
</c:set>
<c:set var="linkedin_url" scope="request">
	<spring:message code="app.linkedin_url" />
</c:set>
<c:set var="developer_name" scope="request">
	<spring:message code="app.developer_name" />
</c:set>
<c:set var="developer_shortname" scope="request">
	<spring:message code="app.developer_shortname" />
</c:set>
<c:set var="developer_url" scope="request">
	<spring:message code="app.developer_url" />
</c:set>


</div>
<!-- End inner container -->

</div>
<!-- End Main container -->

<!-- FOOTER  -->
<footer class="footer">
	<!-- SOCIAL MODULE  -->
	<section id="social_module">
		<div class="container">
			<div class="row">
				<div class="span12">
					<ul>
						<li><a href="${linkedin_url}" target=_blank><i
								class="icon icon-linkedin"></i></a></li>
						<li><a href="${twitter_url}" target=_blank><i
								class="icon icon-twitter"></i></a></li>
						<li><a href="${github_url}" target=_blank><i
								class="icon icon-github"></i></a></li>
						<li><a href="${facebook_url}" target=_blank><i
								class="icon icon-facebook"></i></a></li>
						<li><a
							href="https://plus.google.com/100211800323347120261/posts"
							target=_blank><i class="icon icon-google-plus"></i></a></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<!-- END SOCIAL MODULE  -->

	<!-- COPYRIGHT  -->
	<div class="container" id="developer_info">
		<div class="row">
			<div class="span center">
				<a href="${developer_url}" target="_blank"><img
					alt="${developer_shortname}" src="assets/img/logo_small.png"></a>
			</div>
			<div class="">
				<p class="copyright text-right center">
					<a href="#aboutBox" data-keyboard="true" data-toggle="modal"><spring:message
							code="menu.about" /></a> | &copy; 2013 <a href="${developer_url}"
						target="_blank">${developer_name}</a>
				</p>
			</div>
		</div>
	</div>
	<!-- END COPYRIGHT  -->
</footer>
<!-- END FOOTER  -->
<jsp:include page="components/aboutbox.jsp" />
<jsp:include page="components/loginbox.jsp" />
<jsp:include page="components/confirm_delete_dialogbox.jsp" />
<c:if test="${slgb == true or param.slgb == true}">
	<script type="text/javascript">
		window.onload = function() {
			$('#loginBox').modal('show').on('shown', function() {
				$('#username_or_email').focus();
			});
		}
	</script>
</c:if>

<script src="assets/js/bootstrap.js"></script>
<script src="assets/js/phonebook.js"></script>
<c:if
	test="${ not empty appconfig['ga-tracker'] and not empty appconfig['ga-domain']}">
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');

		ga('create', '${appconfig["ga-tracker"]}', '${appconfig["ga-domain"]}');
		ga('send', 'pageview');
	</script>

</c:if>
<c:if test="${current_page eq \"admin\"}">
	<script src="assets/js/admin-phonebook.js"></script>
</c:if>

</body>
</html>
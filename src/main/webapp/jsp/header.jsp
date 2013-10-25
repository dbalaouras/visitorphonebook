<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<c:set var="available_languages"
	value="${fn:split(appconfig['available_languages'], ',')}"
	scope="application" />

<c:set var="developer_name" scope="request">
	<spring:message code="app.developer_name" />
</c:set>
<c:set var="developer_shortname" scope="request">
	<spring:message code="app.developer_shortname" />
</c:set>
<c:set var="developer_url" scope="request">
	<spring:message code="app.developer_url" />
</c:set>
<c:set var="twitter_share_hashtags" scope="request">
	<spring:message code="app.share.twitter.hashtags" />
</c:set>
<c:set var="twitter_share_via" scope="request">
	<spring:message code="app.share.twitter.via" />
</c:set>


<c:choose>
	<c:when test="${current_page eq \"list_entries\"}">
		<c:set var="list_entries_class" value="active" scope="request" />
	</c:when>
	<c:when test="${current_page eq \"admin\"}">
		<c:set var="admin_class" value="active" scope="request" />
	</c:when>
</c:choose>

<c:set var="retweet_message" scope="request">
	<spring:message code="app.retweet_message" />
</c:set>

<c:choose>
	<c:when test="${ not empty appconfig['baseurl']}">
		<c:set var="baseurl" value="${appconfig['baseurl']}" scope="request" />
	</c:when>
	<c:otherwise>
		<c:set var="baseurl" value="${pageContext.request.contextPath}/web"
			scope="request" />
	</c:otherwise>

</c:choose>

<sec:authentication property="principal" var="principal" />

<!DOCTYPE html>
<html lang="${pageContext.response.locale}">

<head>

<base href="${baseurl}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><spring:message code="app.title" /> <c:if
		test="${not empty page_title}"> -
${page_title} </c:if></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta http-equiv="content-language"
	content="${pageContext.response.locale}">
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="assets/css/bootstrap-responsive.min.css" rel="stylesheet" />


<link
	href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css"
	rel="stylesheet">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css"
	rel="stylesheet">
<link
	href="http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic"
	rel="stylesheet" />
<link href="assets/css/bootstrap-docs.css" rel="stylesheet" />
<link href="assets/css/phonebook.css" rel="stylesheet" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="assets/js/theme.js"></script>
<script>
	!function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/
				.test(d.location) ? 'http' : 'https';
		if (!d.getElementById(id)) {
			js = d.createElement(s);
			js.id = id;
			js.src = p + '://platform.twitter.com/widgets.js';
			fjs.parentNode.insertBefore(js, fjs);
		}
	}(document, 'script', 'twitter-wjs');

	var baseurl = "${baseurl}";
</script>
<!-- Le HTML5 shim, for IE6-8 support of HTML5
elements -->
<!--[if lt IE 9]> <script
src="http://html5shim.googlecode.com/svn/trunk/html5.js"> </script>
<![endif]-->
<link rel="shortcut icon" href="assets/ico/favicon.ico">
<link rel="icon" type="image/x-icon" href="assets/ico/favicon.ico" />

</head>
<body ${body_extra}>
	<div class="navbar navbar-inverse
navbar-fixed-top">
		<div class="navbar-inner">
			<div class="">
				<div class="github-banner hidden-phone hidden-tablet">
					<a href="https://github.com/dbalaouras/visitorphonebook"
						target="_blank"> <img
						style="position: absolute; top: 0; right: 0; border: 0; z-index: 100000"
						src="https://s3.amazonaws.com/github/ribbons/forkme_right_red_aa0000.png"
						alt="Fork me on GitHub" />
					</a>
				</div>
				<button type="button" class="btn
btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand" id="head_logo" href="home/"><spring:message
						code="app.title" /></a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<c:choose>
							<c:when test="${current_page eq \"list_entries\"}">
								<li class="active"><a href="javascript:void(0);"><spring:message
											code="menu.browse_phonebook" /></a></li>

							</c:when>
							<c:otherwise>
								<li><a href="entries"><spring:message
											code="menu.browse_phonebook" /></a></li>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${current_page eq \"add_entry\"}">
								<li class="active"><a href="javascript:void(0);"><spring:message
											code="menu.suggest_entry" /></a></li>

							</c:when>
							<c:otherwise>
								<li><a href="entries/add"><spring:message
											code="menu.suggest_entry" /></a></li>
							</c:otherwise>
						</c:choose>

						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<li class="dropdown ${admin_class}"><a
								data-toggle="dropdown" class="dropdown-toggle" href="#"><spring:message
										code="menu.admin" /> <b class="caret"></b> </a>
								<ul class="dropdown-menu">
									<li><a href="admin/entries"><spring:message
												code="menu.entries" /></a></li>
									<li><a href="admin/categories"><spring:message
												code="menu.entry_categories" /></a></li>
									<li><a href="admin/entries/add"><spring:message
												code="menu.add_entry" /></a></li>
									<li><a href="admin/categories/add"><spring:message
												code="menu.add_category" /></a></li>


								</ul></li>
						</sec:authorize>

						<c:choose>
							<c:when test="${pageContext['request'].userPrincipal !=
null}">
								<li><a href="logout"><spring:message code="menu.logout" />
										${principal.username}</a></li>
							</c:when>
							<c:otherwise>
								<li><a data-toggle="modal" data-keyboard="true"
									href="#loginBox"><spring:message code="menu.login" /></a></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${current_page eq \"contact\"}">
								<li class="active"><a data-toggle="modal"
									data-keyboard="true" href="javascript:void(0);"><spring:message
											code="menu.contact" /></a></li>

							</c:when>
							<c:otherwise>
								<li><a href="contact"><spring:message
											code="menu.contact" /></a></li>
							</c:otherwise>
						</c:choose>

						<li>
							<div class="btn-group" id="menu-language">
								<button data-toggle="dropdown"
									class="btn  btn-mini btn-primary dropdown-toggle">
									<spring:message code="language.${pageContext.response.locale}" />
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">

									<c:forEach var="lang" items="${available_languages}">
										<li><a href="?language=${lang}&d=1"><spring:message
													code="language.${lang}" /></a></li>
									</c:forEach>
								</ul>
							</div>
						</li>
						<li>
							<div id="promote-twitter">
								<a href="https://twitter.com/share" class="twitter-share-button"
									data-url="${baseurl}" data-text="${retweet_message}"
									data-via="${twitter_share_via}"
									data-related="${twitter_share_via}"
									data-hashtags="${twitter_share_hashtags}"><spring:message
										code="social.tweet" /></a>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="main_container">
		<div class="container" id="inner_container">
			<div class="container" id="messages">
				<c:if test="${not empty
page_header}">
					<h3>${page_header}</h3>

				</c:if>
				<c:if test="${not empty
page_subheader}">
					<p class="page_subhead">${page_subheader}</p>

				</c:if>
				<c:if test="${not empty
error_message}">
					<h4 class="error">${error_message}</h4>
				</c:if>
				<c:if test="${not empty info_message}">
					<h4 class="text-success">${info_message}</h4>
				</c:if>
			</div>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="current_page" value="list_entries" scope="request" />
<c:set var="body_extra"
	value="data-target=\".bs-docs-sidebar\" data-spy=\"scroll\"
	data-twttr-rendered=\"true\" "
	scope="request" />
<c:set var="page_title" scope="request">
	<spring:message code="title.browse_phonebook" />
</c:set>

<jsp:include page="header.jsp" />
<div class="container">
	<div class="row">
		<div class="span3 bs-docs-sidebar">
			<div data-spy="affix" data-offset-top="0px" id="list_org_menu">
				<ul class="nav nav-list bs-docs-sidenav">
					<c:forEach var="category" items="${entryCategories.categories}">
						<c:if test="${fn:length(category.entries) gt 0}">
							<li><a href="${request_path}#cat_${category.id}">${category.name}<i
									class="icon-chevron-right"></i></a></li>
						</c:if>
					</c:forEach>
				</ul>
				<p style="margin-top: 10px;">
					<a href="entries.html"><img
						src="assets/img/Google-Play-Badge.png"></img></a>
				</p>
			</div>
		</div>
		<div class="span9" id="entry_list">
			<c:forEach var="category" items="${entryCategories.categories}">
				<section id="cat_${category.id}">
					<c:if test="${fn:length(category.entries) gt 0}">
						<div class="page-header">
							<h1>${category.name}</h1>
						</div>
						<table class="table table-striped table-bordered table-hover">

							<c:forEach var="entry" items="${category.entries}">
								<tr>
									<td class="entry-list-cell"><c:choose>
											<c:when test="${not empty entry.website}">
												<a href="${entry.website}" target="_blank"><i
													class="icon-globe"></i> ${fn:escapeXml(entry.name)}</a>
											</c:when>
											<c:otherwise>${entry.name}</c:otherwise>
										</c:choose> <span class="pull-right">${entry.telephoneNumber}</span></td>

									<td width="20%"><a href="tel:${entry.telephoneNumber}"
										class="btn btn-info text-center"><i
											class="icon-white icon-phone"></i> <spring:message
												code="butt.call" /> </a> <c:choose>
											<c:when test="${not empty entry.emailAddress}">
												<a href="mailto:${fn:escapeXml(entry.emailAddress)}"
													class="btn btn-info">e-mail <i
													class="icon-white icon-envelope"></i>
												</a>
											</c:when>
										</c:choose></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</section>
			</c:forEach>
		</div>
	</div>

</div>
<!-- /container -->

<jsp:include page="footer.jsp" />
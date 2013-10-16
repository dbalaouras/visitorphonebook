<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="current_page" value="admin" scope="request" />
<c:set var="page_title" scope="request">
	<spring:message code="menu.entries" />
</c:set>

<jsp:include page="../header.jsp" />
<div class="container">
	<div class="breadcrumb-container">
		<ul class="breadcrumb">
			<li><a href="home/"><spring:message code="menu.home" /></a><span
				class="divider">/</span></li>
			<li><a href="admin/"><spring:message code="menu.admin" /></a><span
				class="divider">/</span></li>
			<li class="active"><spring:message code="menu.entries" /></li>
		</ul>
	</div>

	<div class="">
		<c:forEach var="category" items="${entryCategories.categories}">
			<h3>${category.name}</h3>
			<c:choose>
				<c:when test="${fn:length(category.entries) gt 0}">
					<table class="table">
						<tr>
							<th><spring:message code="form.name" /></th>
							<th><spring:message code="form.website" /></th>
							<th><spring:message code="form.email" /></th>
							<th><spring:message code="form.telephoneNumber" /></th>
							<th><spring:message code="form.actions" /></th>
						</tr>
						<c:forEach var="org" items="${category.allEntries}">
							<tr>
								<td>${fn:escapeXml(org.name)}</td>
								<td width="20%"><c:choose>
										<c:when test="${not empty org.website}">
											<a href="${org.website}">${org.website}</a>
										</c:when>
										<c:otherwise>-</c:otherwise>
									</c:choose></td>

								<td width="20%"><c:choose>
										<c:when test="${not empty org.emailAddress}">
											<a href="mailto:${org.emailAddress}">${org.emailAddress}</a>
										</c:when>
										<c:otherwise>-</c:otherwise>
									</c:choose></td>
								<td width="20%"><a href="tel:${org.telephoneNumber}">${org.telephoneNumber}</a></td>
								<td width="20%"><a href="javascript:void(0);"
									onclick="deleteEntry(${org.id});"><spring:message
											code="butt.delete" /></a> <c:if test="${org.status == 1}">
										 | <a href="javascript:void(0);"
											onclick="activateEntry(${org.id});"><spring:message
												code="butt.activate" /></a>
									</c:if> <c:if test="${org.status gt 1}">
										 | <a href="javascript:void(0);"
											onclick="deactivateEntry(${org.id});"><spring:message
												code="butt.deactivate" /></a>
									</c:if> | <a href="admin/entries/edit/${org.id}"><spring:message
											code="butt.edit" /></a></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<spring:message code="misc.nodata" />
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>

</div>
<!-- /container -->

<jsp:include page="../footer.jsp" />

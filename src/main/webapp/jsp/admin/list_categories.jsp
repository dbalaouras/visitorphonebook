<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="current_page" value="admin" scope="request" />
<c:set var="page_title" scope="request">
	<spring:message code="menu.entry_categories" />
</c:set>

<jsp:include page="../header.jsp" />
<div class="container">
	<div class="breadcrumb-container">
		<ul class="breadcrumb">
			<li><a href="home/"><spring:message code="menu.home" /></a><span
				class="divider">/</span></li>
			<li><a href="admin/"><spring:message code="menu.admin" /></a><span
				class="divider">/</span></li>
			<li class="active"><spring:message code="menu.entry_categories" /></li>
		</ul>
	</div>

	<div class="">

		<c:if test="${fn:length(entryCategories.categories) gt 0}">
			<table class="table">
				<tr>
					<th><spring:message code="form.name" /></th>
					<th><spring:message code="form.description" /></th>
					<th><spring:message code="form.entries" /></th>
					<th><spring:message code="form.actions" /></th>
				</tr>
				<c:forEach var="category" items="${entryCategories.categories}">
					<tr>
						<td>${category.name}</td>
						<td>${category.description}</td>
						<td>${fn:length(category.entries)}</td>
						<td width="20%"><a href="javascript:void(0);"
							onclick="deleteCategory(${category.id});"><spring:message
									code="butt.delete" /></a> | <a
							href="admin/categories/edit/${category.id}"><spring:message
									code="butt.edit" /></a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>

</div>
<!-- /container -->
<jsp:include page="../footer.jsp" />

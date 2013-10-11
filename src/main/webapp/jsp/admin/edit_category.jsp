<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="current_page" value="admin" scope="request" />

<jsp:include page="../header.jsp" />

<c:set var="page_title" scope="request">
	<c:choose>
		<c:when test="${not empty entryCategory.id}">
			<spring:message code="menu.edit_category" />
		</c:when>
		<c:otherwise>
			<spring:message code="menu.add_category" />
		</c:otherwise>
	</c:choose>
</c:set>


<div class="container">
	<div class="breadcrumb-container">
		<ul class="breadcrumb">
			<li><a href="home/"><spring:message code="menu.home" /></a><span
				class="divider">/</span></li>
			<li><a href="admin/"><spring:message code="menu.admin" /></a><span
				class="divider">/</span></li>
			<li><a href="admin/categories"><spring:message
						code="menu.entry_categories" /></a><span class="divider">/</span></li>
			<li class="active">${page_title}</li>
		</ul>
	</div>

	<div class="control-group">
		<span class="label label-success">${message}</span>
		<form:form commandName="entryCategory" action="${formaction}">
			<label class="control-label"> <spring:message
					code="form.name" />
			</label>
			<form:input path="name" placeholder="Add the category name" />
			<form:errors path="name" cssClass="error" element="span" />

			<label class="control-label"> <spring:message
					code="form.description" />
			</label>
			<form:input path="description"
				placeholder="Add the category description" />
			<form:errors path="description" cssClass="error" element="span" />

			<div class="controls">
				<button type="submit" class="btn btn-primary">
					<spring:message code="form.save" />
				</button>
				<form:hidden path="id" />
			</div>
		</form:form>

	</div>

	<h2>
		<spring:message code="categories.current_categories" />
	</h2>

	<div class="tagcloud">
		<c:forEach var="item" items="${entryCategories.categories}">
			<span class="label label-info">${item.name}</span>
		</c:forEach>
	</div>

</div>
<!-- /container -->

<jsp:include page="../footer.jsp" />

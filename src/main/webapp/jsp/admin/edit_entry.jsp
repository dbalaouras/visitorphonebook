<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="current_page" value="admin" scope="request" />

<c:set var="page_title" scope="request">
	<c:choose>
		<c:when test="${not empty entry.id}">
			<spring:message code="menu.edit_entry" />
		</c:when>
		<c:otherwise>
			<spring:message code="menu.add_entry" />
		</c:otherwise>
	</c:choose>
</c:set>

<jsp:include page="../header.jsp" />
<div class="container">

	<div class="breadcrumb-container">
		<ul class="breadcrumb">
			<li><a href="home/"><spring:message code="menu.home" /></a><span
				class="divider">/</span></li>
			<li><a href="admin/"><spring:message code="menu.admin" /></a><span
				class="divider">/</span></li>
			<li><a href="admin/entries"><spring:message
						code="menu.entries" /></a><span class="divider">/</span></li>
			<li class="active">${page_title}</li>
		</ul>
	</div>


	<form:form commandName="entry" action="${formaction}">
		<div class="control-group">

			<!-- Name Input -->
			<label class="control-label"> <spring:message
					code="form.name" />
			</label>
			<div class="controls">
				<div class="input-prepend">
					<span class="add-on"><i class="icon-book"></i></span>
					<form:input path="name" cssErrorClass="error" placeholder="" />
				</div>
				<form:errors path="name" cssClass="error" element="span" />
			</div>


			<!-- Status Input -->
			<label class="control-label"> <spring:message
					code="form.choose_status" />:
			</label>
			<div class="controls">
				<div class="input-prepend">
					<span class="add-on"><i class="icon-off"></i></span>
					<form:select path="status" items="${statuses}" />
				</div>
				<form:errors path="status" cssClass="error" element="span" />
			</div>

			<!-- Telephone Input -->
			<label class="control-label"> <spring:message
					code="form.telephoneNumber" />
			</label>
			<div class="controls">
				<div class="input-prepend">
					<span class="add-on"><i class="icon-phone"></i></span>
					<form:input path="telephoneNumber" cssErrorClass="error"
						placeholder="" />
				</div>
				<form:errors path="telephoneNumber" cssClass="error" element="span" />
			</div>

			<!-- Category Input -->
			<label class="control-label"> <spring:message
					code="form.choose_category" />:
			</label>
			<div class="controls">
				<div class="input-prepend">
					<span class="add-on"><i class="icon-tag"></i></span>
					<form:select path="entryCategory">
						<option value="">
							--
							<spring:message code="form.select_category" />
							--
						</option>
						<form:options items="${entryCategories}" itemValue="id"
							itemLabel="name" />
					</form:select>
				</div>
				<form:errors path="entryCategory" cssClass="error" element="span" />
			</div>

			<!-- Website Input -->
			<label class="control-label"> <spring:message
					code="form.website" /> <small class="muted">(<spring:message
						code="form.optional" />)
			</small>
			</label>
			<div class="controls">
				<div class="input-prepend">
					<span class="add-on"><i class="icon-globe"></i></span>
					<form:input path="website" cssErrorClass="error" placeholder="" />
				</div>
				<form:errors path="website" cssClass="error" element="span" />
			</div>

			<!-- E-mail input -->
			<label class="control-label"> <spring:message
					code="form.email" /> <small class="muted">(<spring:message
						code="form.optional" />)
			</small>
			</label>
			<div class="controls">
				<div class="input-prepend">
					<span class="add-on"><i class="icon-envelope"></i></span>
					<form:input path="emailAddress" cssErrorClass="error"
						placeholder="" />
				</div>

				<form:errors path="emailAddress" cssClass="error" element="span" />
			</div>

		</div>
		<div class="controls">
			<a href="admin/entries" class="btn btn-primary"> <spring:message
					code="form.cancel" />
			</a>
			<button type="submit" class="btn btn-danger">
				<spring:message code="form.save" />
			</button>
			<form:hidden path="id" />
			<c:if test="${not empty entry.id}">
				<a href="admin/entries/add" class="btn btn-success"> <spring:message
						code="butt.new" />
				</a>
			</c:if>
		</div>

	</form:form>


</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('form:first *:input[type!=hidden]:first').focus();
	});
</script>
<!-- /container -->
<jsp:include page="../footer.jsp" />
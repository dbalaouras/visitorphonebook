<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="page_header" scope="request">
	<spring:message code="menu.contact_us" />
</c:set>
<c:set var="page_title" scope="request">
	<spring:message code="menu.contact_us" />
</c:set>

<c:set var="current_page" value="contact" scope="request" />

<jsp:include page="header.jsp" />
<c:set var="your_name_tip" scope="request">
	<spring:message code="form.your_name_tip" />
</c:set>
<c:set var="your_email_tip" scope="request">
	<spring:message code="form.your_email_tip" />
</c:set>
<c:set var="your_message_tip" scope="request">
	<spring:message code="form.your_message_tip" />
</c:set>

<div class="container">
	<div class="row">
		<div class="span12" id="contact">

			<form:form commandName="emailMessage" action="${formaction}">

				<!-- Name Input -->
				<div class="controls">
					<label class="control-label"> <spring:message
							code="form.name" />
					</label>
					<div class="input-prepend">
						<span class="add-on"><i class="icon-male"></i></span>
						<form:input path="senderName" cssErrorClass="error"
							placeholder="${your_name_tip}" />
					</div>
					<form:errors path="senderName" cssClass="error" element="span" />
				</div>
				<div class="controls">
					<label class="control-label"> <spring:message
							code="form.email" />
					</label>
					<div class="input-prepend">
						<span class="add-on"><i class="icon-envelope"></i></span>
						<form:input path="senderEmail" cssErrorClass="error"
							placeholder="${your_email_tip}" />
					</div>
					<form:errors path="senderEmail" cssClass="error" element="span" />
				</div>

				<div class="controls">
					<label class="control-label"> <spring:message
							code="form.message" />
					</label>
					<div class="input-prepend">
						<span class="add-on"><i class="icon-pencil"></i></span>
						<form:textarea path="message" cssErrorClass="error" cols="12"
							rows="5" placeholder="${your_message_tip}"></form:textarea>
					</div>
					<form:errors path="message" cssClass="error" element="span" />
				</div>

				<button name="send" type="submit" class="button start"
					id="contact_submit">
					<i id="contact_loader" class="icon-envelope-alt icon-large"
						style="margin-left: 10px"></i>
				</button>
			</form:form>
		</div>

	</div>
</div>

<!-- /container -->
<jsp:include page="footer.jsp" />
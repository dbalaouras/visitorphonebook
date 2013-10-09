<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="loginBox" class="modal fade" aria-hidden="true"
	style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<form method="post" class="signin" action="login">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">
						<spring:message code="menu.login" />
					</h4>
				</div>
				<div class="modal-body">
					<c:if test="${param.lgerr eq 1}">
						<h4 class="error" id="login_error_msg">
							<spring:message code="app.login_error" />
						</h4>

					</c:if>

					<fieldset>
						<div class="input-group">
							<label for="username_or_email"><spring:message
									code="form.username_email" /></label>
							<div class="input-prepend">
								<span class="add-on"><i class="icon-envelope"></i></span> <input
									id="username_or_email" name="j_username" type="text"
									value="${username}" />
							</div>

							<label for="password"><spring:message
									code="form.username_email" /></label></label>

							<div class="input-prepend">
								<span class="add-on"><i class="icon-lock"></i></span> <input
									id="password" name="j_password" type="password" />
							</div>
						</div>

						<div class="checkbox">
							<label for="remember_me" class="inline"><input
								id="remember_me" name="_spring_security_remember_me"
								type="checkbox" /> <spring:message code="form.remember_me" /></label>
						</div>

					</fieldset>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal"
						onclick="$('#login_error_msg').html('')">
						<spring:message code="butt.close" />
					</button>
					<button type="submit" class="btn btn-primary">
						<spring:message code="menu.login" />
					</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


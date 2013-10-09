<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="aboutBox" class="modal fade" aria-hidden="true"
	style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<spring:message code="app.aboutbox_title" />
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<spring:message code="app.about" />
				</p>
				<p>
					<spring:message code="app.contribute" />
				</p>
			</div>
			<div class="modal-footer">
				<button id="about_box_close_btn" type="button" class="btn btn-danger" data-dismiss="modal">
					<spring:message code="butt.close" />

				</button>

			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


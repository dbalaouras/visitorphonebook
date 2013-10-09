<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="conf_delete_box" class="modal hide fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h3>Delete Confirmation</h3>
	</div>
	<div class="modal-body">
		<p>
			<spring:message code="admin.confirm_delete" />
		</p>
		<p>
			<spring:message code="admin.confirm_proceed" />
		</p>
		<p id="debug-url"></p>
	</div>
	<div class="modal-footer">
		<button id="conf_delete_box_butt_positive" type="button"
			class="btn btn-danger" data-dimsiss="modal"
			onclick="javascript:void(0);">
			<spring:message code="butt.yes" />
		</button>
		<button id="conf_delete_box_butt_negative" type="button"
			class="btn btn-primary" data-dismiss="modal"
			onclick="$('#conf_delete_box').modal('hide');">
			<spring:message code="butt.no" />
		</button>
	</div>
</div>



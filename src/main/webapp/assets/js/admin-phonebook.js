/*
 * Opens up a confirmation dialog
 */
confirmAction = function(confDialogId, redirUrl) {
	$('#' + confDialogId).modal('show');

	$('#' + confDialogId + '_butt_positive').on('click', function() {
		document.location = redirUrl;
	});

};

/*
 * Requests a confirmation before deleting a category
 */
deleteCategory = function(entryId) {
	confirmAction('conf_delete_box', baseurl + 'admin/categories/delete/'
			+ entryId);

};

/*
 * Requests a confirmation before deleting an entry
 */
deleteEntry = function(entryId) {
	confirmAction('conf_delete_box', baseurl + 'admin/entries/delete/'
			+ entryId);
};

/*
 * Calls the url that activates an entry
 */
activateEntry = function(entryId) {
	document.location = baseurl + 'admin/entries/activate/' + entryId;
};

/*
 * Calls the url that deactivates an entry
 */
deactivateEntry = function(entryId) {
	document.location = baseurl + 'admin/entries/deactivate/' + entryId;
};

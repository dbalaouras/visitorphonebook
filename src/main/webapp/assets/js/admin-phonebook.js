/*
 * Opens up a confirmation dialog
 */
confirmAction = function(confDialogId, positiveUrl) {
	$('#' + confDialogId).modal('show');

	$('#' + confDialogId + '_butt_positive').on('click', function() {
		document.location = positiveUrl;
	});

};

/*
 * Requests a confirmation before deleting a category
 */
deleteCategory = function(entryId) {
	confirmAction('conf_delete_box', '/admin/categories/delete/' + entryId);

};

/*
 * Requests a confirmation before deleting an entry
 */
deleteEntry = function(entryId) {
	confirmAction('conf_delete_box', '/admin/entries/delete/' + entryId);
};

/*
 * Calls the url that activates an entry
 */
activateEntry = function(entryId) {
	document.location = '/admin/entries/activate/' + entryId;
};

/*
 * Calls the url that deactivates an entry
 */
deactivateEntry = function(entryId) {
	document.location = '/admin/entries/deactivate/' + entryId;
};

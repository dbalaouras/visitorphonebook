package gr.bytecode.visitorphonebook.web.controllers;

import gr.bytecode.visitorphonebook.entities.Entry;
import gr.bytecode.visitorphonebook.entities.EntryCategory;
import gr.bytecode.visitorphonebook.model.EntryCategories;
import gr.bytecode.visitorphonebook.services.BackOfficeService;
import gr.bytecode.visitorphonebook.services.MessageLoader;
import gr.bytecode.visitorphonebook.web.exceptions.EntityExistsException;
import gr.bytecode.visitorphonebook.web.exceptions.InvalidDataException;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Spring MVC Controller class for the backoffice of the Phonebook project
 * 
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 */
@Controller
@RequestMapping(value = "/admin")
public class BackOfficeController {

	/**
	 * Injected BackOfficeService bean
	 */
	@Autowired
	BackOfficeService backOfficeService;

	/**
	 * Get a i18n message loader
	 */
	@Autowired
	private MessageLoader messages;

	/**
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/", "/home", "/index" })
	public String showHome(@QueryParam("debug") Boolean debug) throws Exception {

		return "admin/home";
	}

	/**
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/entries", method = RequestMethod.GET)
	public String showEntries(HttpSession session, Model model) {

		// retrieve the categories
		EntryCategories entryCategories = backOfficeService.getCategories(true);

		// add the categories (and entries) to the model
		model.addAttribute("entryCategories", entryCategories);

		return "admin/list_entries";
	}

	/**
	 * @param entryId
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/entries/delete/{entryId}", method = RequestMethod.GET)
	public String deleteEntry(@PathVariable Long entryId, HttpSession session,
			Model model) {

		// delete the entry
		try {

			backOfficeService.deleteEntry(entryId);
			model.addAttribute("info_message",
					messages.getString("inform.entry_deleted", null) + ": "
							+ entryId);

			// clear cache
			backOfficeService.clearHibernateCache();

		} catch (InvalidDataException e) {

			model.addAttribute("error_message", e.getMessage());
		}

		return showEntries(session, model);
	}

	/**
	 * @param entryId
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/entries/add" }, method = RequestMethod.GET)
	public String addEntry(HttpSession session, Model model) {

		return editEntry(null, session, model);

	}

	/**
	 * @param entryId
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/entries/edit/{entryId}", method = RequestMethod.GET)
	public String editEntry(@PathVariable Long entryId, HttpSession session,
			Model model) {

		Entry entry;

		// check if we were sent an entry id
		if (entryId != null) {

			entry = backOfficeService.getEntryById(entryId);

			if (entry == null) {
				model.addAttribute("error_message",
						messages.getString("error.record_not_found", null));

				return showEntries(session, model);

			}

		} else {

			// lets create a new one
			entry = new Entry();
		}

		return prepareEditEntryForm(entry, model);

	}

	/**
	 * @param entryId
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/entries/save", method = RequestMethod.POST)
	public String saveEntry(@Valid @ModelAttribute("entry") Entry entry,
			BindingResult result, Model model) {

		// check for errors
		if (!result.hasErrors()) {

			try {

				// check if this is a new record
				if (entry.getId() != null) {

					backOfficeService.updateEntry(entry);

					// add a flash message
					model.addAttribute("info_message",
							messages.getString("inform.entry_updated", null)
									+ ": " + entry);

				} else {

					// save the entity and update the local reference of it
					entry = backOfficeService.saveEntry(entry);

					// add a flash message
					model.addAttribute("info_message",
							messages.getString("inform.entry_saved", null)
									+ ": " + entry);

				}

				// clear cache
				backOfficeService.clearHibernateCache();

			} catch (Exception e) {

				// oops!
				model.addAttribute("error_message", e.getMessage());
			}

		}

		// get categories
		List<EntryCategory> entryCategories = backOfficeService
				.getCategoryList(true);

		model.addAttribute("entryCategories", entryCategories);

		return prepareEditEntryForm(entry, model);

	}

	/**
	 * Prepares the entry form
	 * 
	 * @param entry
	 * @param model
	 * @return
	 */
	private String prepareEditEntryForm(Entry entry, Model model) {

		// add the form action as a relative path
		model.addAttribute("formaction", "admin/entries/save");

		model.addAttribute(entry);

		// get categories
		List<EntryCategory> entryCategories = backOfficeService
				.getCategoryList(true);

		model.addAttribute("entryCategories", entryCategories);

		// create the status map
		Map<Integer, String> statuses = new LinkedHashMap<Integer, String>();

		statuses.put(0, messages.getString("form.deleted", null));
		statuses.put(1, messages.getString("form.inactive", null));
		statuses.put(2, messages.getString("form.active", null));

		model.addAttribute("statuses", statuses);

		return "admin/edit_entry";
	}

	/**
	 * Handles Entry activation requests
	 * 
	 * @param entryCategory
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/entries/activate/{entryId}", method = RequestMethod.GET)
	public String activateEntry(@PathVariable Long entryId,
			HttpSession session, Model model) {

		try {
			backOfficeService.updateEntryStatus(entryId, 2);

			model.addAttribute("info_message", "Entry activated: " + entryId);

			// clear cache
			backOfficeService.clearHibernateCache();

		} catch (InvalidDataException e) {

			model.addAttribute("error_message", e.getMessage());
		}

		return showEntries(session, model);
	}

	/**
	 * @param entryCategory
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/entries/deactivate/{entryId}", method = RequestMethod.GET)
	public String deactivateEntry(@PathVariable Long entryId,
			HttpSession session, Model model) {

		// try deactivating the entry
		try {

			backOfficeService.updateEntryStatus(entryId, 1);
			model.addAttribute("info_message", "Entry deactivated: " + entryId);

			// clear cache
			backOfficeService.clearHibernateCache();

		} catch (InvalidDataException e) {

			model.addAttribute("error_message", e.getMessage());
		}

		return showEntries(session, model);
	}

	/**
	 * @param entryCategory
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String showCategories(HttpSession session, Model model) {

		EntryCategories entryCategories = backOfficeService.getCategories(true);

		model.addAttribute("entryCategories", entryCategories);

		return "admin/list_categories";
	}

	/**
	 * Show the form for adding a new category
	 * 
	 * @param entryCategory
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/categories/add", method = RequestMethod.GET)
	public String addCategory(
			@ModelAttribute("entryCategory") EntryCategory entryCategory,
			HttpSession session, Model model) {

		return editCategory(null, session, model);
	}

	/**
	 * Show a form for updating a new category
	 * 
	 * @param entryCategory
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/categories/edit/{categoryId}", method = RequestMethod.GET)
	public String editCategory(@PathVariable Long categoryId,
			HttpSession session, Model model) {

		EntryCategory category;

		// check if we were sent an entry id
		if (categoryId != null) {

			category = backOfficeService.getEntryCategoryById(categoryId);

			// check if the category exists in the database
			if (category == null) {
				model.addAttribute("error_message",
						messages.getString("error.record_not_found", null));

				return showCategories(session, model);

			}

		} else {

			// lets create a new one
			category = new EntryCategory();
		}

		// add the form action as a relative path
		model.addAttribute("formaction", "admin/categories/save");

		// add the category
		model.addAttribute(category);

		// append the categories
		EntryCategories entryCategories = backOfficeService.getCategories(true);

		model.addAttribute("entryCategories", entryCategories);

		return "admin/edit_category";
	}

	/**
	 * @param entryCategory
	 * @param result
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/categories/save", method = RequestMethod.POST)
	public String saveCategory(
			@Valid @ModelAttribute("entryCategory") EntryCategory entryCategory,
			BindingResult result, Model model, HttpSession session,
			RedirectAttributes redirectAttrs) {

		// append the categories
		EntryCategories entryCategories = backOfficeService.getCategories(true);

		model.addAttribute("entryCategories", entryCategories);

		// if there were errors, load the edit form
		if (!result.hasErrors()) {
			// set the changed to now
			entryCategory.setChanged(new Date());

			try {

				if (entryCategory.getId() != null) {

					entryCategory = backOfficeService
							.updateEntryCategory(entryCategory);

					// add a flash message
					model.addAttribute("info_message",
							messages.getString("inform.category_updated", null)
									+ ": " + entryCategory);

				} else {

					// save the entry
					entryCategory = backOfficeService
							.saveEntryCategory(entryCategory);

					// add a flash message
					model.addAttribute("info_message",
							messages.getString("inform.category_saved", null)
									+ ": " + entryCategory);

				}

				// clear cache
				backOfficeService.clearHibernateCache();

			} catch (EntityExistsException e) {

				// add an error message
				redirectAttrs
						.addFlashAttribute("error_message", e.getMessage());

			}
		}

		// always return the edit category page
		return "admin/edit_category";
	}

	/**
	 * @param entryCategory
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/categories/delete/{categoryId}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable Long categoryId,
			HttpSession session, Model model) {

		// delete the category
		try {

			// delete the category
			backOfficeService.deleteCategory(categoryId);

			// inform the user for the successful deletion
			model.addAttribute("info_message",
					messages.getString("inform.category_deleted", null) + ":"
							+ categoryId);

			// clear cache
			backOfficeService.clearHibernateCache();

		} catch (InvalidDataException e) {

			model.addAttribute("error_message", e.getMessage());
		}

		return showCategories(session, model);
	}

	/**
	 * Creates a Binder object that binds an id of entryCategory to a
	 * EntryCategory object.
	 * 
	 * @param request
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {

		binder.registerCustomEditor(EntryCategory.class, "entryCategory",
				new PropertyEditorSupport() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * java.beans.PropertyEditorSupport#setAsText(java.lang.
					 * String)
					 */
					@Override
					public void setAsText(String id) {
						EntryCategory category = backOfficeService
								.getEntryCategoryById(Long.parseLong(id));

						setValue(category);
					}
				});
	}
}

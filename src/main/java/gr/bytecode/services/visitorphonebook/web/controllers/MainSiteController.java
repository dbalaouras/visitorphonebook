package gr.bytecode.services.visitorphonebook.web.controllers;

import gr.bytecode.services.visitorphonebook.entities.EmailMessage;
import gr.bytecode.services.visitorphonebook.entities.Entry;
import gr.bytecode.services.visitorphonebook.entities.EntryCategory;
import gr.bytecode.services.visitorphonebook.model.EntryCategories;
import gr.bytecode.services.visitorphonebook.services.BackOfficeService;
import gr.bytecode.services.visitorphonebook.services.MessageLoader;

import java.beans.PropertyEditorSupport;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
@RequestMapping(value = "/")
public class MainSiteController {

	/**
	 * Injected BackOfficeService bean
	 */
	@Autowired
	BackOfficeService backOfficeService;

	/**
	 * Localized Message loader
	 */
	@Autowired
	private MessageLoader messages;

	@Value("${baseurl}")
	private String baseurl;

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

	/**
	 * Show the Contact page
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String postMessage(HttpServletRequest request, Model model,
			HttpSession session,
			@Valid @ModelAttribute("emailMessage") EmailMessage emailMessage,
			BindingResult result) throws Exception {

		// check if there were errors
		if (!result.hasErrors()) { // no errors

			// send the message via the backoffice service
			backOfficeService.sendContactMessage(emailMessage);

			model.addAttribute("info_message",
					messages.getString("inform.message_sent", null));

			// clear the message
			emailMessage.setMessage("");

		} else { // something went wrong

			model.addAttribute("error_message",
					messages.getString("error.message_sent_error", null));
		}

		// set the form action
		model.addAttribute("formaction", "contact");

		return "/contact";
	}

	/**
	 * @param entryCategory
	 * @param result
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/entries/add", method = RequestMethod.POST)
	public String saveNewEntry(@Valid @ModelAttribute("entry") Entry entry,
			BindingResult result, Model model, HttpSession session) {

		if (!result.hasErrors()) {

			try {
				// force the status to inactive
				entry.setStatus(1);

				backOfficeService.saveEntry(entry);

				// clear cache
				backOfficeService.clearHibernateCache();

				// show a successful message
				return "/add_entry_success";

			} catch (Exception e) {

				// oops!
				model.addAttribute("error_message", e.getMessage());
			}

		}

		// get categories
		List<EntryCategory> entryCategories = backOfficeService
				.getCategoryList(true);

		model.addAttribute("entryCategories", entryCategories);

		// add the form action as a relative path
		model.addAttribute("formaction", "entries/add");

		return "/add_entry";

	}

	/**
	 * Show the Contact page
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String showContact(Model model, HttpSession session)
			throws Exception {

		// create a blank message
		EmailMessage emailMessage = new EmailMessage();

		// add it to the model
		model.addAttribute("emailMessage", emailMessage);

		// set the form action
		model.addAttribute("formaction", "contact");

		return "/contact";
	}

	/**
	 * Presents the Phonebook as a list of entries and their data
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/entries" }, method = RequestMethod.GET)
	public String showEntries(HttpSession session, Model model) {

		String message = (String) session.getAttribute("message");

		if (message != null && !message.equals("")) {
			session.removeAttribute("message");
			model.addAttribute("message", message);
		} else {
			model.addAttribute("message", "");
		}

		EntryCategories entryCategories = backOfficeService.getCategories(true);

		model.addAttribute("entryCategories", entryCategories);

		model.addAttribute("request_path", "entries");

		return "/list_entries";
	}

	/**
	 * Show the Homepage
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "", "/", "/home", "/index", "/home/", "/index/" })
	public String showHome() throws Exception {

		return "home";
	}

	/**
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/loginp", method = RequestMethod.GET)
	public String showLogin(Model model, HttpSession session) {

		if (BackOfficeService.userHasAuthority("ROLE_ADMIN") == false) {

			// show the loginbox
			model.addAttribute("slgb", true);
		}

		return "/home";
	}

	/**
	 * Show the new entry form
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/entries/add", method = RequestMethod.GET)
	public String showNewEntryForm(Model model, HttpSession session) {

		// add an empty entry
		model.addAttribute(new Entry());

		// add the form action as a relative path
		model.addAttribute("formaction", "entries/add");

		// get categories
		List<EntryCategory> entryCategories = backOfficeService
				.getCategoryList(true);

		model.addAttribute("entryCategories", entryCategories);

		return "/add_entry";
	}

	/**
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String showSuccess(Model model, HttpSession session) {

		Entry entry = new Entry();
		entry.setName("Testing Name");
		entry.setTelephoneNumber("+306976990146");

		model.addAttribute(entry);

		// show a successful message
		return "/add_entry_success";

	}

}

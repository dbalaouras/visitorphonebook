package gr.bytecode.visitorphonebook.services;

import gr.bytecode.visitorphonebook.entities.EmailMessage;
import gr.bytecode.visitorphonebook.entities.Entry;
import gr.bytecode.visitorphonebook.entities.EntryCategory;
import gr.bytecode.visitorphonebook.model.EntryCategories;
import gr.bytecode.visitorphonebook.repositories.EntryCategoryRepository;
import gr.bytecode.visitorphonebook.repositories.EntryRepository;
import gr.bytecode.visitorphonebook.web.exceptions.EntityExistsException;
import gr.bytecode.visitorphonebook.web.exceptions.InvalidDataException;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

/**
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 */
@Service("backOfficeService")
public class BackOfficeService {

	/**
	 * The entity manager
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Injected concrete CategoryRepository
	 */
	@Autowired
	private EntryCategoryRepository entryCategoryRepository;

	/**
	 * Injected concrete EntryRepository
	 */
	@Autowired
	private EntryRepository entryRepository;

	/**
	 * Injected localized message loader
	 */
	@Autowired
	private MessageLoader messageLoader;

	/**
	 * Injected Mail service
	 */
	@Autowired
	Mailer mailer;

	/**
	 * The base url of the website (injected)
	 */
	@Value("${baseurl}")
	private String baseurl;

	/**
	 * The email of the site admin (injected)
	 */
	@Value("${admin-email}")
	private String adminEmail;

	/**
	 * Create a new entry
	 * 
	 * @param name
	 * @param categoryId
	 * @param telephoneNumber
	 * @param email
	 * @param latitude
	 * @param longitude
	 * @param streetAddress
	 * @param website
	 * @return
	 * @throws EntityExistsException
	 * @throws InvalidDataException
	 */
	public Entry createNewEntry(String name, Long categoryId,
			String telephoneNumber, String email, Long latitude,
			Long longitude, String streetAddress, String website)
			throws EntityExistsException, InvalidDataException {

		// create a new entry
		Entry entry = new Entry();

		// locate the category
		EntryCategory category = null;

		// try to locate the category
		if (categoryId != null) {
			category = entryCategoryRepository.findEntityById(categoryId);
		}

		// get current date
		Date now = new Date();

		// set the name
		entry.setName(name);

		// set the category
		entry.setEntryCategory(category);

		// set the telephone
		entry.setTelephoneNumber(telephoneNumber);

		// set the website
		entry.setWebsite(website);

		// set entered date
		entry.setEntered(now);

		// set changed date
		entry.setChanged(now);

		// this is a new entry
		entry.setStatus(3);

		// validate the entry
		validateEntry(entry);

		// save the entity
		entryRepository.saveEntity(entry);

		return entry;
	}

	/**
	 * Retrieve a list of categories
	 * 
	 * @return
	 */
	public EntryCategories getCategories(boolean useCache) {

		return entryCategoryRepository.getCategories(useCache);
	}

	/**
	 * Retrieve a list of categories
	 * 
	 * @return
	 */
	public List<EntryCategory> getCategoryList(boolean useCache) {

		return entryCategoryRepository.getCategoryList(useCache);
	}

	/**
	 * @param id
	 * @return
	 */
	public EntryCategory getEntryCategoryById(Long id) {

		return entryCategoryRepository.findEntityById(id);

	}

	/**
	 * @param id
	 * @return
	 */
	public Entry getEntryById(Long id) {

		return entryRepository.findEntityById(id);

	}

	/**
	 * Update an entry
	 * 
	 * @param entry
	 * @return
	 * @throws InvalidDataException
	 * @throws EntityExistsException
	 */
	@Transactional
	public Entry updateEntry(Entry entry) throws InvalidDataException,
			EntityExistsException {

		Date now = new Date();

		// validate the entry
		validateEntry(entry);

		// set the last changed date to now
		entry.setChanged(now);

		// save the entity
		entryRepository.updateEntity(entry);

		return entry;
	}

	/**
	 * Save an entry
	 * 
	 * @param entry
	 * @return
	 * @throws InvalidDataException
	 * @throws EntityExistsException
	 */
	@Transactional
	public Entry saveEntry(Entry entry) throws InvalidDataException,
			EntityExistsException {

		Date now = new Date();

		// validate the entry
		validateEntry(entry);

		// set the last changed date to now
		entry.setChanged(now);

		// set the entered date to now
		entry.setEntered(now);

		// save the entity
		entryRepository.saveEntity(entry);

		// send a notification email
		String emailBody = String.format(
				messageLoader.getString("mail.new_entry_notify.body", null),
				entry.getName(), entry.getTelephoneNumber());
		String emailTitle = messageLoader.getString(
				"mail.new_entry_notify.subject", null);

		// send the mail asynchronously
		mailer.sendMailAsync(adminEmail, emailTitle, emailBody);

		return entry;
	}

	/**
	 * @param emailMessage
	 */
	public Future<Boolean> sendContactMessage(EmailMessage emailMessage) {

		// get a title
		String title = messageLoader.getString("mail.contact_message.subject",
				null);

		// constract the body
		String body = String
				.format(messageLoader.getString(
						"mail.contact_message.body_tmpl", null), emailMessage
						.getMessage());

		// send the email via the mailer
		return mailer.sendMailAsync(emailMessage.getSenderEmail(), adminEmail,
				title, body);

	}

	/**
	 * @param entryCategory
	 * @return
	 * @throws EntityExistsException
	 */
	@Transactional
	public EntryCategory saveEntryCategory(EntryCategory entryCategory)
			throws EntityExistsException {

		EntryCategory savedCategory = null;

		try {

			savedCategory = entryCategoryRepository.findCategoryByName(
					entryCategory.getName(), false);

		} catch (NoResultException e) {
			// this is a clear proof that the category does not exist
		}

		if (savedCategory == null) {

			// save the entity
			entryCategory = entryCategoryRepository.saveEntity(entryCategory);

			return entryCategory;

		} else {

			throw new EntityExistsException(messageLoader.getString(
					"BackOfficeService.ERR_CATEGORY_EXISTS", null));//$NON-NLS-1$
		}

	}

	/**
	 * Find an entry given it's name, status and category name
	 * 
	 * @param name
	 * @param categoryId
	 * @return
	 */
	public Entry getEntryByNameAndCat(String name, Long categoryId) {
		return entryRepository.getEntryByNameAndCat(name, categoryId);
	}

	/**
	 * @param entryCategory
	 * @return
	 * @throws EntityExistsException
	 */
	@Transactional
	public EntryCategory updateEntryCategory(EntryCategory entryCategory) {

		// save the entity
		entryCategory = entryCategoryRepository.updateEntity(entryCategory);

		return entryCategory;
	}

	/**
	 * Set an entryCategory (mainly intended for tests, as the private field is
	 * autowired by spring)
	 * 
	 * @param entryCategoryRepository
	 *            the entryCategoryRepository to set
	 */
	public void setEntryCategoryRepository(
			EntryCategoryRepository entryCategoryRepository) {
		this.entryCategoryRepository = entryCategoryRepository;
	}

	/**
	 * @param entryRepository
	 *            the entryRepository to set
	 */
	public void setEntryRepository(EntryRepository entryRepository) {
		this.entryRepository = entryRepository;
	}

	/**
	 * @param entry
	 * @throws InvalidDataException
	 * @throws EntityExistsException
	 */
	private void validateEntry(Entry entry) throws InvalidDataException,
			EntityExistsException {

		// validate data
		if (entry.getEntryCategory() == null) {
			throw new InvalidDataException(messageLoader.getString(
					"BackOfficeService.ERR_CATEGORY_DEFINED", null));
		}

		// validate the name
		if (entry.getName() == null) {
			throw new InvalidDataException(messageLoader.getString(
					"BackOfficeService.ERR_CATEGORY_MANDATORY", null));
		}

		// validate the phone number
		if (entry.getTelephoneNumber() == null) {
			throw new InvalidDataException(messageLoader.getString(
					"BackOfficeService.ERR_TELEPHONE_MANDATORY", null));
		}

		if (entry.getId() == null) { // new entry?

			Entry savedEntry = null;

			// check if the category exists already
			try {
				savedEntry = entryRepository.getEntryByNameAndCat(
						entry.getName(), entry.getEntryCategory().getId());

			} catch (NoResultException e) {

				// this is expected
			}

			// check if the entry already exists
			if (savedEntry != null) {

				throw new EntityExistsException(messageLoader.getString(
						"BackOfficeService.ERR_ENTRY_EXISTS", null)); //$NON-NLS-1$
			}

		}

	}

	/**
	 * Checks if user has a given authority
	 * 
	 * @param authority
	 * @return
	 */
	public static boolean userHasAuthority(String authority) {

		// get the authorities
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			if (authority.equals(grantedAuthority.getAuthority())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @param categoryId
	 * @throws InvalidDataException
	 */
	@Transactional
	public void deleteCategory(Long categoryId) throws InvalidDataException {

		EntryCategory savedCategory = null;

		try {

			savedCategory = entryCategoryRepository.findEntityById(categoryId);

		} catch (NoResultException e) {
			// this is a clear proof that the category does not exist
		}

		// check if we found the category
		if (savedCategory != null) {

			entryCategoryRepository.deleteEntity(savedCategory);

		} else {
			throw new InvalidDataException(

			messageLoader.getString(
					"BackOfficeService.ERR_NO_CATEGORY_FOUND", null) //$NON-NLS-1$
					+ categoryId);
		}

	}

	/**
	 * @param entryId
	 * @throws InvalidDataException
	 */
	@Transactional
	public void deleteEntry(Long entryId) throws InvalidDataException {

		updateEntryStatus(entryId, 0);
	}

	/**
	 * Enables an entry given it's it
	 * 
	 * @param entryId
	 * @throws InvalidDataExceptions
	 */
	@Transactional
	public void activateEntry(Long entryId) throws InvalidDataException {
		updateEntryStatus(entryId, 2);
	}

	/**
	 * Enables an entry given it's it
	 * 
	 * @param entryId
	 * @throws InvalidDataException
	 */
	@Transactional
	public void deactivateEntry(Long entryId) throws InvalidDataException {

		updateEntryStatus(entryId, 1);
	}

	/**
	 * @param entryId
	 * @throws InvalidDataException
	 */
	@Transactional
	public void updateEntryStatus(Long entryId, int newStatus)
			throws InvalidDataException {

		Entry savedEntry = null;

		try {

			savedEntry = entryRepository.findEntityById(entryId);

		} catch (NoResultException e) {
			// this is a clear proof that the entry does not exist
		}

		// check if we found the category
		if (savedEntry != null) {

			savedEntry.setStatus(newStatus);
			entryRepository.updateEntity(savedEntry);

		} else {
			throw new InvalidDataException(messageLoader.getString(
					"BackOfficeService.ERR_NO_ENTRY_FOUND", new String[] {})); //$NON-NLS-1$
		}

	}

	/**
	 * Clear Hibernate cache
	 */
	public void clearHibernateCache() {

		Session s = (Session) em.getDelegate();
		Cache cache = s.getSessionFactory().getCache();

		cache.evictEntityRegions();
		cache.evictCollectionRegions();
		cache.evictDefaultQueryRegion();
		cache.evictQueryRegions();

		return;
	}

	/**
	 * Sets the message loader
	 * 
	 * @param messageLoader
	 */
	public void setMessageLoader(MessageLoader messageLoader) {
		this.messageLoader = messageLoader;
	}

	/**
	 * @return
	 */
	public String getBaseUrl() {

		return baseurl;
	}

	/**
	 * @param model
	 */
	public void addDebugMessage(Model model, String message) {

		model.addAttribute("debug", message);
	}
}

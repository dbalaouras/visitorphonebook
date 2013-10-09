package gr.bytecode.services.visitorphonebook.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;
import gr.bytecode.services.visitorphonebook.entities.Entry;
import gr.bytecode.services.visitorphonebook.entities.EntryCategory;
import gr.bytecode.services.visitorphonebook.model.EntryCategories;
import gr.bytecode.services.visitorphonebook.repositories.IEntryCategoryRepository;
import gr.bytecode.services.visitorphonebook.repositories.IEntryRepository;
import gr.bytecode.services.visitorphonebook.repositories.EntryCategoryRepository;
import gr.bytecode.services.visitorphonebook.repositories.EntryRepository;
import gr.bytecode.services.visitorphonebook.web.exceptions.EntityExistsException;
import gr.bytecode.services.visitorphonebook.web.exceptions.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Dimitrios Balaouras
 * @since Jul 11, 2013 - 10:08:37 AM
 * @Copyright CA Inc. 2013
 * 
 */
@Test(groups = { "business" })
public class BackOfficeServiceTest {

	/**
	 * Repository of organization categories
	 */
	private IEntryCategoryRepository organizationCategoryRepository;

	/**
	 * Repository of organization organizations
	 */
	private IEntryRepository organizationRepository;

	/**
	 * The tested object
	 */
	private BackOfficeService backOfficeService;

	/**
	 * setup the test
	 */
	@BeforeClass
	public void setUp() {

		// mock the repositories
		organizationCategoryRepository = mock(EntryCategoryRepository.class);
		organizationRepository = mock(EntryRepository.class);
		EntryCategories organizationCategories = mock(EntryCategories.class);
		EntryCategory organizationCategory = mock(EntryCategory.class);

		// mock the message loader
		MessageLoader messageLoader = mock(MessageLoader.class);
		when(messageLoader.getString(anyString(), any(String[].class)))
				.thenReturn("Mock String");

		// prepare the EntryCategories mock
		List<EntryCategory> categoriesList = new ArrayList<EntryCategory>();
		categoriesList.add(organizationCategory);
		when(organizationCategories.getCategories()).thenReturn(categoriesList);

		// setup the EntryCategoryRepository mock
		when(organizationCategoryRepository.getCategories(true)).thenReturn(
				organizationCategories);

		// instantiate the back office service
		backOfficeService = new BackOfficeService();

		// set the repositories to the backoffice service
		backOfficeService
				.setEntryCategoryRepository(organizationCategoryRepository);

		backOfficeService.setEntryRepository(organizationRepository);

		backOfficeService.setMessageLoader(messageLoader);
	}

	/**
	 * @throws InvalidDataException
	 * @throws EntityExistsException
	 * 
	 */
	@Test(expectedExceptions = InvalidDataException.class)
	public void createNewEntryInvalidData()
			throws EntityExistsException, InvalidDataException {

		backOfficeService.createNewEntry(null, null, null, null, null,
				null, null, null);
	}

	/**
	 * @throws InvalidDataException
	 * @throws EntityExistsException
	 * 
	 */
	@Test(expectedExceptions = EntityExistsException.class)
	public void createNewEntryWithExistingEntity()
			throws EntityExistsException, InvalidDataException {

		// set some common values to use
		String orgName = "dimi";
		Long id = 1L;

		// prepare the saved organization category
		EntryCategory savedEntryCategory = new EntryCategory();
		savedEntryCategory.setId(id);

		// prepare the saved organization
		Entry savedEntry = new Entry();
		savedEntry.setId(id);
		savedEntry.setEntryCategory(savedEntryCategory);

		// setup the mocks
		when(organizationRepository.findEntryByName(orgName))
				.thenReturn(savedEntry);
		when(organizationCategoryRepository.findEntityById(id)).thenReturn(
				savedEntryCategory);

		// now run it
		backOfficeService.createNewEntry(orgName, id.longValue(),
				"+306976990146", null, null, null, null, null);
	}

	/**
	 * 
	 */
	@Test
	public void getCategories() {

		EntryCategories categories = organizationCategoryRepository
				.getCategories(true);
		List<EntryCategory> organizations = categories.getCategories();

		Assert.assertEquals(organizations.size(), 1);
	}

	/**
	 * 
	 */
	@Test
	public void saveEntryCategory() {
		// TODO: implement this test
	}
}

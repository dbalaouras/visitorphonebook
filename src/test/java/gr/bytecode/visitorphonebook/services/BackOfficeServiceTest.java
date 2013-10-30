package gr.bytecode.visitorphonebook.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;
import gr.bytecode.visitorphonebook.entities.Entry;
import gr.bytecode.visitorphonebook.entities.EntryCategory;
import gr.bytecode.visitorphonebook.model.EntryCategories;
import gr.bytecode.visitorphonebook.repositories.BasicEntryCategoryRepository;
import gr.bytecode.visitorphonebook.repositories.BasicEntryRepository;
import gr.bytecode.visitorphonebook.repositories.EntryCategoryRepository;
import gr.bytecode.visitorphonebook.repositories.EntryRepository;
import gr.bytecode.visitorphonebook.services.BackOfficeService;
import gr.bytecode.visitorphonebook.services.MessageLoader;
import gr.bytecode.visitorphonebook.web.exceptions.EntityExistsException;
import gr.bytecode.visitorphonebook.web.exceptions.InvalidDataException;

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
	 * Repository of entry categories
	 */
	private EntryCategoryRepository entryCategoryRepository;

	/**
	 * Repository of entries
	 */
	private EntryRepository entryRepository;

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
		entryCategoryRepository = mock(BasicEntryCategoryRepository.class);
		entryRepository = mock(BasicEntryRepository.class);
		EntryCategories entryCategories = mock(EntryCategories.class);
		EntryCategory entryCategory = mock(EntryCategory.class);

		// mock the message loader
		MessageLoader messageLoader = mock(MessageLoader.class);
		when(messageLoader.getString(anyString(), any(String[].class)))
				.thenReturn("Mock String");

		// prepare the EntryCategories mock
		List<EntryCategory> categoriesList = new ArrayList<EntryCategory>();
		categoriesList.add(entryCategory);
		when(entryCategories.getCategories()).thenReturn(categoriesList);

		// setup the EntryCategoryRepository mock
		when(entryCategoryRepository.getCategories(true)).thenReturn(
				entryCategories);

		// instantiate the back office service
		backOfficeService = new BackOfficeService();

		// set the repositories to the backoffice service
		backOfficeService.setEntryCategoryRepository(entryCategoryRepository);

		backOfficeService.setEntryRepository(entryRepository);

		backOfficeService.setMessageLoader(messageLoader);
	}

	/**
	 * @throws InvalidDataException
	 * @throws EntityExistsException
	 * 
	 */
	@Test(expectedExceptions = InvalidDataException.class)
	public void createNewEntryInvalidData() throws EntityExistsException,
			InvalidDataException {

		backOfficeService.createNewEntry(null, null, null, null, null, null,
				null, null);
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

		// prepare the saved entry category
		EntryCategory savedEntryCategory = new EntryCategory();
		savedEntryCategory.setId(id);

		// prepare the saved entry
		Entry savedEntry = new Entry();
		savedEntry.setId(id);
		savedEntry.setEntryCategory(savedEntryCategory);

		// setup the mocks
		when(entryRepository.getEntryByNameAndCat(orgName, id)).thenReturn(
				savedEntry);
		when(entryCategoryRepository.findEntityById(id)).thenReturn(
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

		EntryCategories categories = entryCategoryRepository
				.getCategories(true);
		List<EntryCategory> entries = categories.getCategories();

		Assert.assertEquals(entries.size(), 1);
	}

	/**
	 * 
	 */
	@Test
	public void saveEntryCategory() {
		// TODO: implement this test
	}
}

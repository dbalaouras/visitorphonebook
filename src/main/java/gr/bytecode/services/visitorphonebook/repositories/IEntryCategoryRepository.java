package gr.bytecode.services.visitorphonebook.repositories;

import gr.bytecode.services.visitorphonebook.entities.EntryCategory;
import gr.bytecode.services.visitorphonebook.model.EntryCategories;

import java.util.List;

/**
 * @author Dimitrios Balaouras
 * @version %G%
 * @since %I%
 * @copyright Bytecode.gr 2013
 * 
 */
public interface IEntryCategoryRepository extends IRepository<EntryCategory> {

	/**
	 * Get a list of EntryCategory objects wrapped in a ServiceCategories model
	 * object
	 * 
	 * @return
	 */
	public EntryCategories getCategories(boolean useCache);

	/**
	 * Get a list of EntryCategory objects wrapped in a ServiceCategories model
	 * object
	 * 
	 * @return
	 */
	public List<EntryCategory> getCategoryList(boolean useCache);

	/**
	 * @return
	 */
	public EntryCategory findCategoryByName(String name, boolean useCache);

}

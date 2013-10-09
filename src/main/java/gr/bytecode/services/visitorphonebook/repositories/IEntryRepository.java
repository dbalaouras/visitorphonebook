package gr.bytecode.services.visitorphonebook.repositories;

import gr.bytecode.services.visitorphonebook.entities.Entry;
import gr.bytecode.services.visitorphonebook.model.Entries;

/**
 * @author Dimitrios Balaouras
 * @since Jun 14, 2013 - 1:00:01 AM
 * @Copyright CA Inc. 2013
 * 
 */
public interface IEntryRepository extends IRepository<Entry> {

	/**
	 * Find an organization given it's name
	 * 
	 * @return
	 */
	public abstract Entry findEntryByName(String name);

	/**
	 * Find an organization given it's name
	 * 
	 * @return
	 */
	public abstract Entry findEntryByName(String name, int status);

	/**
	 * Get all organizations of a category
	 * 
	 * @return
	 */
	public abstract Entries getEntries(Long categoryId, int status);

}

package gr.bytecode.services.visitorphonebook.repositories;

import gr.bytecode.services.visitorphonebook.entities.Entry;
import gr.bytecode.services.visitorphonebook.model.Entries;

/**
 * @author Dimitrios Balaouras
 * @version %G%
 * @since %I%
 * @copyright Bytecode.gr 2013
 * 
 */
public interface IEntryRepository extends IRepository<Entry> {

	/**
	 * Find an entry given it's name
	 * 
	 * @return
	 */
	public abstract Entry findEntryByName(String name);

	/**
	 * Find an entry given it's name
	 * 
	 * @return
	 */
	public abstract Entry findEntryByName(String name, int status);

	/**
	 * Get all entries of a category
	 * 
	 * @return
	 */
	public abstract Entries getEntries(Long categoryId, int status);

	/**
	 * Find an entry given it's name, status and category name
	 * 
	 * @param name
	 * @param categoryId
	 * @return
	 */
	public abstract Entry getEntryByNameAndCat(String name, Long categoryId);

}

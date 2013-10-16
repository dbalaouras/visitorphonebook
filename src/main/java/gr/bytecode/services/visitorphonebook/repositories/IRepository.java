package gr.bytecode.services.visitorphonebook.repositories;

/**
 * @author Dimitrios Balaouras
 * @version %G%
 * @since %I%
 * @copyright Bytecode.gr 2013
 * 
 * @param <E>
 */
public interface IRepository<E> {

	/**
	 * @return
	 */
	public E findEntityById(Long id);

	/**
	 * @return
	 */
	public void deleteEntity(E entity);

	/**
	 * @return
	 */
	public E saveEntity(E entity);

	/**
	 * @return
	 */
	public E updateEntity(E entity);
}

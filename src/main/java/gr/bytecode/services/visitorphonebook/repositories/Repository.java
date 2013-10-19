package gr.bytecode.services.visitorphonebook.repositories;

/**
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 * @param <E>
 */
public interface Repository<E> {

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

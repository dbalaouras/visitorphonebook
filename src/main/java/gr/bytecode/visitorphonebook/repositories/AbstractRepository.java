package gr.bytecode.visitorphonebook.repositories;

import gr.bytecode.visitorphonebook.entities.PhonebookEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 */
abstract public class AbstractRepository<E extends PhonebookEntity> implements
		Repository<E> {

	@PersistenceContext
	private EntityManager em;

	// the type of the managed entities
	private Class<E> entityType;

	/**
	 * Default constructor
	 * 
	 * @param entityType
	 */
	public AbstractRepository(Class<E> entityType) {
		this.entityType = entityType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gr.bytecode.visitorphonebook.repositories.Repository#findEntityById(java
	 * .lang.Long)
	 */
	@Override
	public E findEntityById(Long id) {

		return em.find(entityType, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gr.bytecode.visitorphonebook.repositories.Repository#deleteEntity(java
	 * .lang.Object)
	 */
	@Override
	public void deleteEntity(E entity) {

		// purge the entity
		em.remove(entity);

		// flush the manager
		em.flush();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gr.bytecode.visitorphonebook.repositories.Repository#saveEntity(java.
	 * lang.Object)
	 */
	@Override
	public E saveEntity(E entity) {
		// persist the entity
		em.persist(entity);

		// flush the manager
		em.flush();

		// return the entity
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gr.bytecode.visitorphonebook.repositories.Repository#updateEntity(java
	 * .lang.Object)
	 */
	@Override
	public E updateEntity(E entity) {

		// persist the entity
		em.merge(entity);

		// flush the manager
		em.flush();

		// return the entity
		return entity;
	}

}

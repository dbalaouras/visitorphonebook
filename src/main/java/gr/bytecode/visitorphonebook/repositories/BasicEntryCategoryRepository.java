package gr.bytecode.visitorphonebook.repositories;

import gr.bytecode.visitorphonebook.entities.EntryCategory;
import gr.bytecode.visitorphonebook.model.EntryCategories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 */
@Repository
public class BasicEntryCategoryRepository extends
		AbstractRepository<EntryCategory> implements EntryCategoryRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default arg-less constructor
	 */
	public BasicEntryCategoryRepository() {

		// tell the base class which entity we are managing
		super(EntryCategory.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.bytecode.visitorphonebook.repositories.EntryCategoryRepository#
	 * getCategories(boolean)
	 */
	public EntryCategories getCategories(boolean useCache) {

		// get a list of the entries
		Query queryAllCategories = em
				.createNamedQuery("EntryCategory.findAllCategories");

		// configure the cache
		queryAllCategories.setHint("org.hibernate.cacheable", useCache);

		@SuppressWarnings("unchecked")
		final List<EntryCategory> list = queryAllCategories.getResultList();

		EntryCategories categories = new EntryCategories();

		categories.setCategories(list);

		return categories;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.bytecode.visitorphonebook.repositories.EntryCategoryRepository#
	 * findCategoryByName(java.lang.String, boolean)
	 */
	@Override
	public EntryCategory findCategoryByName(String name, boolean useCache) {

		// get a list of the entries
		Query query = em.createNamedQuery("EntryCategory.findCategoryByName")
				.setParameter("categoryName", name);

		// configure the cache
		query.setHint("org.hibernate.cacheable", useCache);

		EntryCategory category = (EntryCategory) query.getSingleResult();

		return category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.bytecode.visitorphonebook.repositories.EntryCategoryRepository#
	 * getCategoryList(boolean)
	 */
	@Override
	public List<EntryCategory> getCategoryList(boolean useCache) {

		// get a list of the entries
		Query query = em
				.createNamedQuery("EntryCategory.findAllCategoriesNames");

		query.setHint("org.hibernate.cacheable", useCache);

		@SuppressWarnings("unchecked")
		List<EntryCategory> list = query.getResultList();

		return list;
	}

}

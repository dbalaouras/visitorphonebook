package gr.bytecode.services.visitorphonebook.repositories;

import gr.bytecode.services.visitorphonebook.entities.EntryCategory;
import gr.bytecode.services.visitorphonebook.model.EntryCategories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * @author Dimitrios Balaouras
 * @since Jun 14, 2013 - 1:00:59 AM
 * @Copyright CA Inc. 2013
 * 
 */
@Repository
public class EntryCategoryRepository extends BaseRepository<EntryCategory>
		implements IEntryCategoryRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default arg-less constructor
	 */
	public EntryCategoryRepository() {

		// tell the base class which entity we are managing
		super(EntryCategory.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.bytecode.services.visitorphonebook.repositories.
	 * IEntryCategoryRepository#getCategories()
	 */
	public EntryCategories getCategories(boolean useCache) {

		// get a list of the organizations
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
	 * @see gr.bytecode.services.visitorphonebook.repositories.
	 * IEntryCategoryRepository#findCategoryByName()
	 */
	@Override
	public EntryCategory findCategoryByName(String name, boolean useCache) {

		// get a list of the organizations
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
	 * @see gr.bytecode.services.visitorphonebook.repositories.
	 * IEntryCategoryRepository#getCategoriesNames()
	 */
	@Override
	public List<EntryCategory> getCategoryList(boolean useCache) {

		// get a list of the organizations
		Query query = em
				.createNamedQuery("EntryCategory.findAllCategoriesNames");

		query.setHint("org.hibernate.cacheable", useCache);

		@SuppressWarnings("unchecked")
		List<EntryCategory> list = query.getResultList();

		return list;
	}

}

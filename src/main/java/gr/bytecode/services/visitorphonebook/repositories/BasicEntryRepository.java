package gr.bytecode.services.visitorphonebook.repositories;

import gr.bytecode.services.visitorphonebook.entities.Entry;
import gr.bytecode.services.visitorphonebook.model.Entries;

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
public class BasicEntryRepository extends AbstractRepository<Entry> implements
		EntryRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default arg-less constructor
	 */
	public BasicEntryRepository() {

		// tell the base class which entity we are managing
		super(Entry.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.bytecode.services.visitorphonebook.repositories.IEntryRepository
	 * #findEntryByName(java.lang.String)
	 */
	@Override
	public Entry findEntryByName(String name) {
		// get a list of the entries
		Query queryCategory = em.createNamedQuery("Entry.findEntryByName")
				.setParameter("entryName", name).setParameter("entryStatus", 1);

		Entry category = (Entry) queryCategory.getSingleResult();

		return category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.bytecode.services.visitorphonebook.repositories.IEntryRepository
	 * #findEntryByName(java.lang.String, int)
	 */
	@Override
	public Entry findEntryByName(String name, int status) {

		// find the entry
		Query queryCategory = em.createNamedQuery("Entry.findEntryByName")
				.setParameter("entryName", name)
				.setParameter("entryStatus", status);

		Entry entry = (Entry) queryCategory.getSingleResult();

		return entry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.bytecode.services.visitorphonebook.repositories.IEntryRepository
	 * #getEntries(int, int)
	 */
	@Override
	public Entries getEntries(Long categoryId, int status) {

		// get a list of the entries
		Query queryEntries = em
				.createNamedQuery("Entry.findEntriesByCategoryId")
				.setParameter("categoryId", categoryId)
				.setParameter("entryStatus", status);

		@SuppressWarnings("unchecked")
		final List<Entry> list = queryEntries.getResultList();

		// create a new Entries model object
		Entries entries = new Entries(list);

		return entries;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.bytecode.services.visitorphonebook.repositories.IEntryRepository#
	 * getEntryByNameAndCat(java.lang.String, int)
	 */
	@Override
	public Entry getEntryByNameAndCat(String name, Long categoryId) {

		// find the entry
		Query queryCategory = em
				.createNamedQuery("Entry.findEntryByNameAndCat")
				.setParameter("entryName", name)
				.setParameter("categoryId", categoryId);

		Entry entry = (Entry) queryCategory.getSingleResult();

		return entry;
	}

}

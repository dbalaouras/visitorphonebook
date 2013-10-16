package gr.bytecode.services.visitorphonebook.web.rest;

import gr.bytecode.services.visitorphonebook.model.EntryCategories;
import gr.bytecode.services.visitorphonebook.repositories.IEntryCategoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dimitrios Balaouras
 * @version %G%
 * @since %I%
 * @copyright Bytecode.gr 2013
 * 
 */
@Service("categoriesService")
@Path("/entryCategories")
public class EntryCategoriesRS {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	IEntryCategoryRepository entryCategoryRepository;

	/**
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public CallResponse<EntryCategories> getCategories() {

		EntryCategories categories = entryCategoryRepository
				.getCategories(true);

		CallResponse<EntryCategories> response = new CallResponse<EntryCategories>(
				"Found " + categories.size() + " categories", categories);

		return response;
	}
}

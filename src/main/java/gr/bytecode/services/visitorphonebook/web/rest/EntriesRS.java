package gr.bytecode.services.visitorphonebook.web.rest;

import gr.bytecode.services.visitorphonebook.entities.Entry;
import gr.bytecode.services.visitorphonebook.model.Entries;
import gr.bytecode.services.visitorphonebook.repositories.IEntryRepository;
import gr.bytecode.services.visitorphonebook.services.BackOfficeService;
import gr.bytecode.services.visitorphonebook.web.exceptions.EntityExistsException;
import gr.bytecode.services.visitorphonebook.web.exceptions.InvalidDataException;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dimitrios Balaouras
 * @version %G%
 * @since %I%
 * @copyright Bytecode.gr 2013
 * 
 */
@Service("entriesService")
@Path("/entry")
public class EntriesRS {

	@Autowired
	BackOfficeService backOfficeService;

	@Autowired
	IEntryRepository entryRepository;

	/**
	 * @param name
	 * @param categoryId
	 * @param telephoneNumber
	 * @param webUrl
	 * @param headers
	 * @return
	 * @throws InvalidDataException
	 * @throws EntityExistsException
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Transactional
	public CallResponse<Long> createEntry(@QueryParam("name") String name,
			@QueryParam("categoryid") Long categoryId,
			@NotNull @QueryParam("telephoneNumber") String telephoneNumber,
			@NotNull @QueryParam("email") String email,
			@QueryParam("latitude") Long latitude,
			@QueryParam("longtitude") Long longitude,
			@QueryParam("streetAddress") String streetAddress,
			@QueryParam("website") String website, @Context HttpHeaders headers)
			throws EntityExistsException, InvalidDataException {

		// create a new entry
		Entry entry = new Entry();

		CallResponse<Long> response = new CallResponse<Long>(0,
				"Organizaton created: " + name, 0L);

		entry = backOfficeService.createNewEntry(name, categoryId,
				telephoneNumber, email, latitude, longitude, streetAddress,
				website);

		// save the entity
		response.setData(entry.getId());

		response.setMessage("New entry saved");

		return response;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Transactional
	public CallResponse<Entries> getEntry(
			@NotNull @QueryParam("categoryid") Long categoryId,
			@Context HttpHeaders headers) throws InvalidDataException {

		// validate the data
		if (categoryId == null) {
			throw new InvalidDataException("category Id is mandatory");
		}

		Entries entries = entryRepository.getEntries(categoryId, 2);

		CallResponse<Entries> response = new CallResponse<Entries>(0,
				"Entry found: " + entries.size(), entries);

		response.setMessage("Entry found: " + entries.size());

		return response;
	}

}

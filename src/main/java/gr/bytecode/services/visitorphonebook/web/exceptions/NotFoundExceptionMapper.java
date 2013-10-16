package gr.bytecode.services.visitorphonebook.web.exceptions;

import gr.bytecode.services.visitorphonebook.web.rest.CallResponse;

import java.util.Date;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Dimitrios Balaouras
 * @version %G%
 * @since %I%
 * @copyright Bytecode.gr 2013
 * 
 */
@Provider
public class NotFoundExceptionMapper implements
		ExceptionMapper<NotFoundException> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(NotFoundException exception) {

		// create a response object
		CallResponse<String> callResponse = new CallResponse<String>();
		callResponse.setCode(404);
		callResponse.setDate(new Date());
		callResponse.setMessage("Not Found");

		Response response = Response.status(404).type("application/json")
				.entity(callResponse).build();

		return response;
	}

}

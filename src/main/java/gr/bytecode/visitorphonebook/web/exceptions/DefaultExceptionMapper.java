package gr.bytecode.visitorphonebook.web.exceptions;

import gr.bytecode.visitorphonebook.web.rest.CallResponse;

import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 */
@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(Exception exception) {

		int errorCode = 500;

		// create a response object
		CallResponse<String> callResponse = new CallResponse<String>();
		callResponse.setCode(errorCode);
		callResponse.setDate(new Date());
		callResponse.setMessage(exception.getMessage());

		exception.printStackTrace();

		Response response = Response.status(errorCode).entity(callResponse)
				.build();

		return response;
	}

}

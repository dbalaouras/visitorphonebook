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
public class BaseExceptionMapper<E extends ServiceAbstractException> implements
		ExceptionMapper<E> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(E exception) {

		// get the http error code
		int httpErrorCode = exception.getHttpCode();

		// set a default error code if it's set to 0
		if (httpErrorCode == 0)
			httpErrorCode = 500;

		// create a response object
		CallResponse<String> callResponse = new CallResponse<String>();
		callResponse.setCode(exception.getErrorCode());
		callResponse.setDate(new Date());
		callResponse.setMessage(exception.getMessage());

		Response response = Response.status(httpErrorCode).entity(callResponse)
				.build();

		return response;
	}

}

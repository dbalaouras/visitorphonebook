package gr.bytecode.services.visitorphonebook.web.exceptions;

import gr.bytecode.services.visitorphonebook.web.rest.CallResponse;

import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Dimitrios Balaouras
 * @since Jul 9, 2013 - 12:36:25 AM
 * @Copyright CA Inc. 2013
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
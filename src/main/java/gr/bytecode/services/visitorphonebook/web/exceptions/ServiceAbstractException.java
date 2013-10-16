package gr.bytecode.services.visitorphonebook.web.exceptions;

/**
 * Abstract service exception
 * 
 * @author Dimitrios Balaouras
 * @version %G%
 * @since %I%
 * @copyright Bytecode.gr 2013
 * 
 */
public abstract class ServiceAbstractException extends Exception {

	/**
	 * Generated UUID
	 */
	private static final long serialVersionUID = -7774946336713723695L;

	/**
	 * A message to show to the client
	 */
	private String message;

	/**
	 * Get the error code of this exception (must be implemented by subclasses)
	 * 
	 * @return
	 */
	public abstract int getErrorCode();

	/**
	 * Get the HTTP error code
	 * 
	 * @return
	 */
	public int getHttpCode() {
		return 501;
	}

	/**
	 * @param message
	 * @param errorCode
	 * @param httpCode
	 */
	public ServiceAbstractException(String message) {
		super();
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}

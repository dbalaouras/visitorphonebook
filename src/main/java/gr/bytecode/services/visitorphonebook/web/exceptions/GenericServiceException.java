package gr.bytecode.services.visitorphonebook.web.exceptions;

/**
 * @author Dimitrios Balaouras
 * @since Jul 6, 2013 - 5:13:40 PM
 * @Copyright CA Inc. 2013
 * 
 */
public class GenericServiceException extends ServiceAbstractException {

	/**
	 * Generated serial UUID required by the Exception class
	 */
	private static final long serialVersionUID = -8772091388553962559L;

	/**
	 * Default constructor
	 * 
	 * @param message
	 */
	public GenericServiceException(String message) {
		super(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gr.bytecode.services.visitorphonebook.web.exceptions.ServiceAbstractException
	 * #getErrorCode()
	 */
	@Override
	public int getErrorCode() {
		return 6;
	}

}

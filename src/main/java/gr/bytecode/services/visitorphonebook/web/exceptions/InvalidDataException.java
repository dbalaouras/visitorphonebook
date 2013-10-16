package gr.bytecode.services.visitorphonebook.web.exceptions;

/**
 * @author Dimitrios Balaouras
 * @version %G%
 * @since %I%
 * @copyright Bytecode.gr 2013
 * 
 */
public class InvalidDataException extends ServiceAbstractException {

	/**
	 * Generated UUID
	 */
	private static final long serialVersionUID = -6670145023519765104L;

	/**
	 * Default constructor
	 * 
	 * @param message
	 */
	public InvalidDataException(String message) {
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
		return 13;
	}

}

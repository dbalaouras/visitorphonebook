package gr.bytecode.services.visitorphonebook.web.exceptions;

/**
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 */
public class EntityExistsException extends ServiceAbstractException {

	/**
	 * Default constructor
	 * 
	 * @param message
	 */
	public EntityExistsException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5939058887020817806L;

	@Override
	public int getErrorCode() {

		return 12;
	}

}

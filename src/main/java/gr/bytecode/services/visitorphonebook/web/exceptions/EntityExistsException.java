package gr.bytecode.services.visitorphonebook.web.exceptions;

/**
 * @author Dimitrios Balaouras
 * @since Jul 7, 2013 - 4:37:30 PM
 * @Copyright CA Inc. 2013
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

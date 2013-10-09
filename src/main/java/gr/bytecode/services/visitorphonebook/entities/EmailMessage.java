package gr.bytecode.services.visitorphonebook.entities;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Dimitrios Balaouras
 * @since Oct 1, 2013 - 12:56:57 AM
 * @Copyright CA Inc. 2013
 * 
 */
public class EmailMessage {

	@Value("${admin-email}")
	private String adminEmail;

	/**
	 * Name of the sender
	 */
	@NotEmpty
	private String senderName;

	/**
	 * Email of the sender
	 */
	@NotEmpty
	private String senderEmail;

	/**
	 * The message to send
	 */
	@NotEmpty
	private String message;

	/**
	 * @return the senderName
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * @param senderName
	 *            the senderName to set
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * @return the senderEmail
	 */
	@Email
	public String getSenderEmail() {
		return senderEmail;
	}

	/**
	 * @param senderEmail
	 *            the senderEmail to set
	 */
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return message;
	}

}

package gr.bytecode.services.visitorphonebook.web.rest;

import gr.bytecode.services.visitorphonebook.entities.Entry;
import gr.bytecode.services.visitorphonebook.entities.EntryCategory;
import gr.bytecode.services.visitorphonebook.model.Entries;
import gr.bytecode.services.visitorphonebook.model.EntryCategories;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Generic call response class
 * 
 * @author Dimitrios Balaouras
 * @version %G%
 * @since %I%
 * @copyright Bytecode.gr 2013
 * 
 */
@XmlRootElement(name = "result")
@XmlSeeAlso({ Entry.class, EntryCategory.class, EntryCategories.class,
		Entries.class })
public class CallResponse<E> {

	/**
	 * Response code
	 */
	private int code;

	/**
	 * Response message
	 */
	private String message;

	/**
	 * Current date
	 */
	private Date date;

	/**
	 * Additional date
	 */
	private E data;

	/**
	 * Default constructor
	 */
	public CallResponse() {
		super();
		this.code = 200;
		this.message = "";
		this.date = new Date();
		this.data = null;
	}

	/**
	 * @param code
	 * @param message
	 * @param date
	 * @param data
	 */
	public CallResponse(int code, String message, E data) {
		super();
		this.code = code;
		this.message = message;
		this.date = new Date();
		this.data = data;
	}

	/**
	 * @param message
	 * @param data
	 */
	public CallResponse(String message, E data) {
		super();
		this.message = message;
		this.data = data;
		this.code = 200;
		this.message = message;
		this.date = new Date();
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
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

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the data
	 */
	public E getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(E data) {
		this.data = data;
	}

}

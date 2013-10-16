package gr.bytecode.services.visitorphonebook.model;

import gr.bytecode.services.visitorphonebook.entities.Entry;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Dimitrios Balaouras
 * @version %G%
 * @since %I%
 * @copyright Bytecode.gr 2013
 * 
 */
@XmlRootElement(name = "entries")
public class Entries {

	/**
	 * A list of entries
	 */
	private List<Entry> entries;

	/**
	 * 
	 */
	public Entries() {
		super();
		this.entries = new ArrayList<Entry>();
	}

	/**
	 * @param entries
	 */
	public Entries(List<Entry> entries) {
		super();
		this.entries = entries;
	}

	/**
	 * @return
	 */
	@XmlElement(name = "entry")
	public List<Entry> getEntries() {
		return entries;
	}

	/**
	 * @param entries
	 */
	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	/**
	 * Get the size of the enclosed list
	 * 
	 * @return
	 */
	public int size() {
		return entries == null ? 0 : entries.size();
	}

}

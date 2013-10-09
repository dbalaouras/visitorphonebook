package gr.bytecode.services.visitorphonebook.model;

import gr.bytecode.services.visitorphonebook.entities.Entry;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Dimitrios Balaouras
 * @since May 11, 2013 - 5:33:42 PM
 * @Copyright ByteCode.gr 2013
 * 
 */
@XmlRootElement(name = "organizations")
public class Entries {

	/**
	 * A list of organizations
	 */
	private List<Entry> organizations;

	/**
	 * 
	 */
	public Entries() {
		super();
		this.organizations = new ArrayList<Entry>();
	}

	/**
	 * @param organizations
	 */
	public Entries(List<Entry> organizations) {
		super();
		this.organizations = organizations;
	}

	/**
	 * @return
	 */
	@XmlElement(name = "organization")
	public List<Entry> getEntries() {
		return organizations;
	}

	/**
	 * @param organizations
	 */
	public void setEntries(List<Entry> organizations) {
		this.organizations = organizations;
	}

	/**
	 * Get the size of the enclosed list
	 * 
	 * @return
	 */
	public int size() {
		return organizations == null ? 0 : organizations.size();
	}

}

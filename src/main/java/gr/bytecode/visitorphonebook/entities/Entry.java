package gr.bytecode.visitorphonebook.entities;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

/**
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 */
@Entity
@Table(name = "Entry")
@XmlRootElement(name = "entry")
@JsonIgnoreProperties({ "entryCategory", "entered", "changed" })
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
		@NamedQuery(name = "Entry.findAllEntries", query = "SELECT o FROM Entry o"),
		@NamedQuery(name = "Entry.findEntryByName", query = "SELECT o FROM Entry o WHERE o.name = :entryName AND o.status = :entryStatus"),
		@NamedQuery(name = "Entry.findEntryByNameAndCat", query = "SELECT o FROM Entry o WHERE o.name = :entryName AND o.entryCategory.id = :categoryId"),
		@NamedQuery(name = "Entry.findEntriesByCategoryId", query = "SELECT o FROM Entry o WHERE o.entryCategory.id = :categoryId AND o.status = :entryStatus") })
public class Entry implements PhonebookEntity {

	/**
	 * Primary key of the entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The name of the entry
	 */
	@NotEmpty
	private String name;

	/**
	 * The telephone number of the entry
	 */
	@NotEmpty
	@Pattern(regexp = "(^$|[0-9\\. ]{3,16}|\\+[0-9\\. ]{3,16})")
	private String telephoneNumber;

	/**
	 * The web site address of the entry
	 */
	@URL
	private String website;

	/**
	 * The email address of the entry
	 */
	private String emailAddress;

	/**
	 * The latitude of the entry's location
	 */
	private Long latitude = 0L;

	/**
	 * The longitude of the entry's location
	 */
	private Long longitude = 0L;

	/**
	 * Status of the entry (0: deleted, 1:inactive, 2:active, 3:new)
	 */
	private int status = 1;

	/**
	 * The category this entry belongs to
	 */
	@ManyToOne
	@JoinColumn(name = "categoryId")
	@NotNull
	@XmlTransient
	private EntryCategory entryCategory;

	/**
	 * Date that this entity was created
	 */
	private Date entered;

	/**
	 * Date that this entity was last changed
	 */
	private Date changed;

	/**
	 * Default arg-less constructor
	 */
	public Entry() {
		super();
	}

	/**
	 * Default constructor
	 * 
	 * @param id
	 * @param name
	 * @param telephoneNumber
	 * @param website
	 * @param emailAddress
	 * @param latitude
	 * @param longitude
	 * @param entered
	 * @param changed
	 */
	public Entry(Long id, String name, String telephoneNumber, String website,
			String emailAddress, Long latitude, Long longitude, Date entered,
			Date changed, int status) {
		super();
		this.id = id;
		this.name = name;
		this.telephoneNumber = telephoneNumber;
		this.website = website;
		this.emailAddress = emailAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.entered = entered;
		this.changed = changed;
		this.status = status;
	}

	/**
	 * @return the category
	 */
	@XmlTransient
	public EntryCategory getEntryCategory() {
		return entryCategory;
	}

	/**
	 * @return
	 */
	@XmlTransient
	public Date getChanged() {
		return changed;
	}

	/**
	 * @return
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @return
	 */
	@XmlTransient
	public Date getEntered() {
		return entered;
	}

	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return
	 */
	public Long getLatitude() {
		return latitude;
	}

	/**
	 * @return
	 */
	public Long getLongitude() {
		return longitude;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @return
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @return
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param entryCategory
	 *            the category to set
	 */
	public void setEntryCategory(EntryCategory entryCategory) {
		this.entryCategory = entryCategory;
	}

	/**
	 * @param changed
	 */
	public void setChanged(Date changed) {
		this.changed = changed;
	}

	/**
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @param entered
	 */
	public void setEntered(Date entered) {
		this.entered = entered;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param latitude
	 */
	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude
	 */
	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @param telephoneNumber
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @param website
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name + " (" + this.getId() + ")";
	}
}

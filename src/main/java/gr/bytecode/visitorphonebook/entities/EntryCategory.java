package gr.bytecode.visitorphonebook.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 */
@Entity
@Table(name = "EntryCategory")
@XmlRootElement(name = "category")
@JsonIgnoreProperties({ "entered", "changed", "deactivatedEntries" })
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
		@NamedQuery(name = "EntryCategory.findCatWithActiveOrgs", query = "SELECT oc FROM EntryCategory oc WHERE 2 = ANY (SELECT o.status FROM oc.entries o)"),
		@NamedQuery(name = "EntryCategory.findAllCategories", query = "SELECT oc FROM EntryCategory oc "),
		@NamedQuery(name = "EntryCategory.findAllCategoriesNames", query = "SELECT new gr.bytecode.visitorphonebook.entities.EntryCategory(oc.id, oc.name, oc.description) FROM EntryCategory oc"),
		@NamedQuery(name = "EntryCategory.findCategoryByName", query = "SELECT oc FROM EntryCategory oc WHERE oc.name = :categoryName") })
public class EntryCategory implements PhonebookEntity {

	/**
	 * Primary key of the entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	/**
	 * The name of the service category
	 */
	@Length(min = 4, max = 20)
	protected String name;

	/**
	 * The description of the service
	 */
	@NotEmpty
	protected String description;

	/**
	 * Date that this entity was created
	 */
	protected Date entered;

	/**
	 * Date that this entity was last changed
	 */
	protected Date changed;

	/**
	 * A list of entries belonging to this category
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "entryCategory")
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@Where(clause = "status > 1")
	@OrderBy("name ASC")
	protected List<Entry> entries;

	/**
	 * A list of entries belonging to this category
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "entryCategory")
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@Where(clause = "status = 1")
	@XmlTransient
	@OrderBy("name ASC")
	private List<Entry> deactivatedEntries;

	@Transient
	@OrderBy("name ASC")
	private List<Entry> allEntries;

	/**
	 * Default arg-less constructor
	 */
	public EntryCategory() {
		super();
	}

	/**
	 * Default constructor Note: any refactoring of this constructor will impact
	 * the named query findAllCategoriesNames)
	 * 
	 * @param id
	 * @param name
	 * @param description
	 */
	public EntryCategory(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	@XmlTransient
	public Date getEntered() {
		return entered;
	}

	/**
	 * @param entered
	 */
	public void setEntered(Date entered) {
		this.entered = entered;
	}

	/**
	 * @return
	 */
	@XmlTransient
	public Date getChanged() {
		return changed;
	}

	/**
	 * @param changed
	 */
	public void setChanged(Date changed) {
		this.changed = changed;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.name + " (" + this.getId() + ")";
	}

	/**
	 * @return the deactivatedEntries
	 */
	@XmlTransient
	public List<Entry> getDeactivatedEntries() {
		return deactivatedEntries;
	}

	/**
	 * @return the deactivatedEntries
	 */
	@XmlTransient
	public List<Entry> getAllEntries() {

		if (allEntries == null) {

			allEntries = new ArrayList<Entry>(deactivatedEntries);
			allEntries.addAll(entries);
		}

		return allEntries;
	}
}

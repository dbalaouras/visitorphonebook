package gr.bytecode.services.visitorphonebook.model;

import gr.bytecode.services.visitorphonebook.entities.EntryCategory;

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
@XmlRootElement(name = "categories")
public class EntryCategories {

	/**
	 * A list of categories
	 */
	private List<EntryCategory> categories;

	/**
	 * @return
	 */
	@XmlElement(name = "category")
	public List<EntryCategory> getCategories() {
		return categories;
	}

	/**
	 * @param restFullCategoryList
	 */
	public void setCategories(List<EntryCategory> restFullCategoryList) {
		this.categories = restFullCategoryList;
	}

	/**
	 * Get the size of the enclosed list
	 * 
	 * @return
	 */
	public int size() {
		return categories == null ? 0 : categories.size();
	}

}

/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.chartsearch.cache;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Patient;
import org.openmrs.User;

/**
 * Bookmarked search java object, takes up searched phrase and attached/selected filter categories
 */
@Entity
@Table(name = "chartsearch_bookmark")
public class ChartSearchBookmark implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookmark_id")
	private Integer bookmarkId;
	
	@Column(name = "uuid", unique = true, nullable = false, length = 38)
	private String uuid = UUID.randomUUID().toString();
	
	@Column(name = "bookmark_name", nullable = false)
	private String bookmarkName;
	
	@Column(name = "search_phrase", nullable = false)
	private String searchPhrase;
	
	/**
	 * A default search if selected by the user, is what is automatically searched whenever a
	 * specified user loads/opens the chartsearch page for any patient, otherwise the last
	 * {@link ChartSearchHistory} is searched or a search for all data if no stored searches are
	 * found in the database
	 */
	@Column(name = "default_search", nullable = false)
	private Boolean defaultSearch = false;
	
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User bookmarkOwner;
	
	/**
	 * Comma separated category names, use {@link #getSelectedCategoriesAsList()} and use; ', '
	 * separate to space the categories when saving
	 */
	@Column(name = "selected_categories")
	private String selectedCategories;
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public Integer getBookmarkId() {
		return bookmarkId;
	}
	
	public void setBookmarkId(Integer bookmarkId) {
		this.bookmarkId = bookmarkId;
	}
	
	public String getBookmarkName() {
		return bookmarkName;
	}
	
	public void setBookmarkName(String bookmarkName) {
		this.bookmarkName = bookmarkName;
	}
	
	public String getSearchPhrase() {
		return searchPhrase;
	}
	
	public void setSearchPhrase(String searchPhrase) {
		this.searchPhrase = searchPhrase;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public User getBookmarkOwner() {
		return bookmarkOwner;
	}
	
	public void setBookmarkOwner(User bookmarkOwner) {
		this.bookmarkOwner = bookmarkOwner;
	}
	
	public String getSelectedCategories() {
		return selectedCategories;
	}
	
	public List<String> getSelectedCategoriesAsList() {
		String categories = getSelectedCategories();
		
		if (StringUtils.isBlank(categories)) {
			return null;
		} else {
			String[] categoriesArr = categories.split(", ");
			
			return Arrays.asList(categoriesArr);
		}
	}
	
	public void setSelectedCategories(String selectedCategories) {
		this.selectedCategories = selectedCategories;
	}
	
	/**
	 * @see #defaultSearch
	 */
	public Boolean isDefaultSearch() {
		return defaultSearch;
	}
	
	public void setIsDefaultSearch(Boolean isDefaultSearch) {
		this.defaultSearch = isDefaultSearch;
	}
}

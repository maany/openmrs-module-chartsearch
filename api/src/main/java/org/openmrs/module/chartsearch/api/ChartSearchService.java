/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.chartsearch.api;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.solr.client.solrj.SolrServer;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.chartsearch.cache.ChartSearchBookmark;
import org.openmrs.module.chartsearch.cache.ChartSearchCategoryDisplayName;
import org.openmrs.module.chartsearch.cache.ChartSearchHistory;
import org.openmrs.module.chartsearch.cache.ChartSearchNote;
import org.openmrs.module.chartsearch.cache.ChartSearchPreference;
import org.openmrs.module.chartsearch.categories.CategoryFilter;
import org.openmrs.module.chartsearch.synonyms.Synonym;
import org.openmrs.module.chartsearch.synonyms.SynonymGroup;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured
 * in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(ChartSearchService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface ChartSearchService extends OpenmrsService {
	
	/**
	 * Retrieve synonym group by id
	 * 
	 * @param id - The group id.
	 * @return the synonym group that matches the given id.
	 * @should not return voided group
	 */
	SynonymGroup getSynonymGroupById(Integer id);
	
	/**
	 * Retrieve List of all synonym groups.
	 * 
	 * @return List of all synonym groups.
	 * @should not return voided group
	 */
	List<SynonymGroup> getAllSynonymGroups();
	
	/**
	 * Delete a synonym group.
	 * 
	 * @param synGroup - The group to delete.
	 * @should not return voided group
	 */
	void purgeSynonymGroup(SynonymGroup synGroup);
	
	/**
	 * Update a synonym group or save a new one.
	 * 
	 * @param synGroup - The group to save.
	 * @return the synonym group that has been saved.
	 * @should not return voided group
	 */
	SynonymGroup saveSynonymGroup(SynonymGroup synGroup) throws APIException;
	
	/**
	 * Retrieve synonym group by group name
	 * 
	 * @param groupName - The group name.
	 * @return the synonym group that matches the given groupName.
	 * @should not return voided group
	 */
	SynonymGroup getSynonymGroupByName(String groupName);
	
	/**
	 * Retrieve a list of synonym groups by is category.
	 * 
	 * @param isCategory - Determines if a group is a category.
	 * @return if isCategory == true => return list of synonym groups that are categories, else,
	 *         return list of groups that aren't categories.
	 * @should not return voided groups
	 */
	List<SynonymGroup> getSynonymGroupsIsCategory(boolean isCategory);
	
	/**
	 * Retrieve a count of synonym groups.
	 * 
	 * @param byIsCategory - Determine if a group is a category.
	 * @return if byIsCategory == false => return count of all groups, else, returns count for
	 *         categorized groups only.
	 * @should not return voided groups
	 */
	Integer getCountOfSynonymGroups(boolean byIsCategory);
	
	/**
	 * Retrive a synonym by its id.
	 * 
	 * @param id - id of the synonym.
	 * @return the synonym that matches the id.
	 * @should not return voided synonyms
	 */
	Synonym getSynonymById(Integer id);
	
	/**
	 * Retrieve all synonyms.
	 * 
	 * @return List of all the synonyms.
	 * @should not return voided synonyms
	 */
	List<Synonym> getAllSynonyms();
	
	/**
	 * Delete a synonym.
	 * 
	 * @param synonym - the synonym to delete.
	 * @should not return voided synonyms
	 */
	void purgeSynonym(Synonym synonym);
	
	/**
	 * Update a synonym or save a new one.
	 * 
	 * @param synonym - the synonym to save.
	 * @return the synonym that has been saved.
	 * @should not return voided synonyms
	 */
	Synonym saveSynonym(Synonym synonym) throws APIException;
	
	/**
	 * Retrieve synonyms by synonym group
	 * 
	 * @param synonymGroup - the synonym group.
	 * @return list of synonyms for the given group.
	 * @should not return voided synonyms
	 */
	List<Synonym> getSynonymsByGroup(SynonymGroup synonymGroup);
	
	/**
	 * Retrieve count of synonyms by synonym group
	 * 
	 * @param synonymGroup - the synonym group.
	 * @return count of synonyms for the given group.
	 * @should not return voided synonyms
	 */
	Integer getSynonymsCountByGroup(SynonymGroup synonymGroup);
	
	public CategoryFilter getACategoryFilterByItsId(Integer categoryFilterId);
	
	public List<CategoryFilter> getAllCategoryFilters();
	
	public void createACategoryFilter(CategoryFilter categoryFilter);
	
	public void updateACategoryFilter(CategoryFilter categoryFilter);
	
	public void deleteACategoryFilter(CategoryFilter categoryFilter);
	
	public CategoryFilter getACategoryFilterByItsUuid(String uuid);
	
	void addEncountersToJSONToReturn(JSONObject jsonToReturn, JSONObject jsonEncounters, JSONArray arr_of_encounters);
	
	void addFormsToJSONToReturn(JSONObject jsonToReturn, JSONObject jsonForms, JSONArray arr_of_forms);
	
	void addSingleObsToJSONToReturn(JSONObject jsonToReturn, JSONObject jsonObs, JSONArray arr_of_obs);
	
	void addObsGroupsToJSONToReturn(JSONObject jsonToReturn, JSONArray arr_of_groups);
	
	void addDatatypesToJSONToReturn(JSONObject jsonToReturn, JSONArray arr_of_datatypes);
	
	void addProvidersToJSONToReturn(JSONObject jsonToReturn, JSONArray arr_of_providers);
	
	void addLocationsToJSONToReturn(JSONObject jsonToReturn, JSONArray arr_of_locations);
	
	void indexAllPatientData(Integer numberOfResults, SolrServer solrServer, Class showProgressToClass);
	
	public List<String> getAllPossibleSearchSuggestions(Integer patientId);
	
	public ChartSearchHistory getSearchHistory(Integer searchId);
	
	public void saveSearchHistory(ChartSearchHistory searchHistory);
	
	public void deleteSearchHistory(ChartSearchHistory searchHistory);
	
	public ChartSearchHistory getSearchHistoryByUuid(String uuid);
	
	public List<ChartSearchHistory> getAllSearchHistory();
	
	public ChartSearchBookmark getSearchBookmark(Integer bookmarkId);
	
	public void saveSearchBookmark(ChartSearchBookmark bookmark);
	
	public void deleteSearchBookmark(ChartSearchBookmark bookmark);
	
	public List<ChartSearchBookmark> getAllSearchBookmarks();
	
	public ChartSearchBookmark getSearchBookmarkByUuid(String uuid);
	
	public void saveSearchNote(ChartSearchNote note);
	
	public void deleteSearchNote(ChartSearchNote note);
	
	public ChartSearchNote getSearchNote(Integer noteId);
	
	public ChartSearchNote getSearchNoteByUuid(String uuid);
	
	public List<ChartSearchNote> getAllSearchNotes();
	
	String[] getAllLocationsFromTheDB();
	
	String[] getAllProvidersFromTheDB();
	
	/**
	 * @return ifSaved boolean status
	 */
	public boolean saveANewChartSearchPreference(ChartSearchPreference preference);
	
	public void updateChartSearchPreference(ChartSearchPreference pref);
	
	public ChartSearchPreference getChartSearchPreference(Integer preferenceId);
	
	public void deleteChartSearchPreference(ChartSearchPreference preference);
	
	/**
	 * Fetches all preferences of all the users that have set it up
	 */
	public List<ChartSearchPreference> getAllChartSearchPreferences();
	
	public ChartSearchPreference getChartSearchPreferenceByUuid(String uuid);
	
	public ChartSearchPreference getChartSearchPreferenceOfAUser(Integer userId);
	
	public ChartSearchPreference getRightMatchedPreferences();
	
	public ChartSearchCategoryDisplayName getCategoryDisplayNameByUuid(String uuid);
	
	public List<ChartSearchCategoryDisplayName> getAllCategoryDisplayNames();
	
	public void saveChartSearchCategoryDisplayName(ChartSearchCategoryDisplayName displayName);
	
	public void deleteChartSearchCategoryDisplayName(ChartSearchCategoryDisplayName displayName);
}

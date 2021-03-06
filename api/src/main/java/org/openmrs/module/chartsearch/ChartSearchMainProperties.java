/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.chartsearch;

import org.openmrs.GlobalProperty;

/**
 * Primary global properties for this Solr based ChartSearch module used as
 * {@link GlobalProperty#getProperty()}
 */
public class ChartSearchMainProperties {
	
	public static final String MODULE_ID = "chartsearch";
	
	public static final String USE_DEDICATED_SOLR_SERVER = MODULE_ID + ".useDedicatedSolrServer";
	
	public static final String DEDICATED_SOLR_SERVER_URL = MODULE_ID + ".httpSolrUrl";
	
}

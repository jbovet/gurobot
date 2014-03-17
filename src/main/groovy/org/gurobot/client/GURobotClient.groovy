/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 
 */
package org.gurobot.client

import wslite.http.HTTPClientException;
import wslite.rest.RESTClient
import wslite.rest.Response;

/**
 * @author josebovet
 *
 */
class GURobotClient {

	static final DEFAULT_API = "http://api.uptimerobot.com";

	RESTClient restClient

	private GURobotClient() {
	}

	static GURobotClient instance(String apiUrl = DEFAULT_API){
		new GURobotClient(restClient: new RESTClient(apiUrl))
	}

	private Response call(String path) throws GURobotClientException {
		try {
			restClient.get(path: path)
		} catch (HTTPClientException hce) {
			throw new GURobotClientException("Problems communicating with: $path", hce)
		}
	}
	
	List getMonitors(){
		return new ArrayList()
	}
}

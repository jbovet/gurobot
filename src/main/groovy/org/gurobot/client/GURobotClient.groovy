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

import sun.security.krb5.internal.APOptions;
import wslite.http.HTTPClientException;
import wslite.json.JSONObject;
import wslite.rest.ContentType;
import wslite.rest.RESTClient
import wslite.rest.Response;
import wslite.rest.ResponseBuilder;

/**
 * 
 * @author josebovet
 *
 */
class GURobotClient {

	static final DEFAULT_API = "http://api.uptimerobot.com";

	RESTClient restClient

	static apikey = ""

	static LIST_SEPARATOR = "-"

	private GURobotClient() {
	}

	static GURobotClient instance(String apiUrl = DEFAULT_API, String apiKey){
		apikey = apiKey
		new GURobotClient(restClient: new RESTClient(apiUrl))
	}

	private Response call(String path) throws GURobotClientException {
		try {
			restClient.get(path: path)
		} catch (HTTPClientException hce) {
			throw new GURobotClientException("Problems communicating with: $path", hce)
		}
	}

	List<Monitor> getMonitors(){
		def monitors =[]
		def response =  call("getMonitors?apiKey=${apikey}&format=json&noJsonCallback=1").json
		def monitor = response.monitors.monitor
		monitor.each(){ data ->
			monitors << parseMonitor(data)
		}
		monitors
	}

	Monitor getMonitorFor(Integer id){
		def response =  call("getMonitors?apiKey=${apikey}&format=json&noJsonCallback=1&monitors=$id").json
		parseMonitor(response.monitors.monitor.first())
	}

	private Monitor parseMonitor(data){
		def monitor = new Monitor()
		monitor.with {
			id =  data.id?.isInteger()?data.id.toInteger():null
			friendlyname = data.friendlyname
			url = data.url
			type = data.type?.isInteger()?data.type.toInteger():null
			subtype = data.subtype?.isInteger()?data.subtype.toInteger():null
			keywordtype = data.keywordtype?.isInteger()?data.keywordtype.toInteger():null
			keywordvalue = data.keywordvalue
			httpusername = data.httpusername
			httppassword = data.httppassword
			port = data.port?.isInteger()?data.port.toInteger():null
			status = data.status?.isInteger()?data.status.toInteger():null
			alltimeuptimeratio = data.alltimeuptimeratio?.isFloat()?data.alltimeuptimeratio.toFloat():null
			customuptimeratio = data.customuptimeratio
		}
		monitor
	}

	List<AlertContact> getAlertContacts(){
		def data = call("getAlertContacts?apiKey=${apikey}&format=json&noJsonCallback=1")
		getAlerts(data)
	}

	AlertContact getAlertContactFor(String id){
		getAlertContactFor([id]).first()
	}

	List<AlertContact> getAlertContactFor(List ids){
		def list = ids.join('-')
		def data = call("getAlertContacts?apiKey=${apikey}&alertcontacts=$list&format=json&noJsonCallback=1")
		getAlerts(data)
	}

	private List getAlerts(data) {
		def alerts = []
		if(data.response.contentType?.startsWith('text/')){
			JSONObject json = new JSONObject(data.text)
			alerts = json.alertcontacts.alertcontact
		}else
			alerts = data.alertcontacts.alertcontact

		def alertcontacts = []
		alerts.each(){ alert ->
			alertcontacts << parseAlertContact(alert)
		}
		alertcontacts
	}

	private AlertContact parseAlertContact(alert){
		def alertcontact = new AlertContact()
		alertcontact.with {
			id = alert.id
			value = alert.value
			type = alert.type?.isInteger()?alert.type.toInteger():null
			status = alert.status?.isInteger()?alert.status.toInteger():null
		}
		alertcontact
	}
}

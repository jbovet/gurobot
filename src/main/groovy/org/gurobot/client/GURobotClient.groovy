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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.groovy.runtime.metaclass.MethodMetaProperty.GetMethodMetaProperty;
import org.gurobot.client.alerts.AlertContact;
import org.gurobot.client.alerts.AlertStatus;
import org.gurobot.client.alerts.AlertType;
import org.gurobot.client.logs.Log;
import org.gurobot.client.logs.LogType;
import org.gurobot.client.monitors.KeywordType;
import org.gurobot.client.monitors.Monitor;
import org.gurobot.client.monitors.MonitorType;
import org.gurobot.client.monitors.Status;
import org.gurobot.client.monitors.SubType;

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

	private Response call(path, Map query =[:]) throws GURobotClientException {
		try {
			query << ['format':'json','noJsonCallback':1]
			restClient.get(path: path, query:query)
		} catch (HTTPClientException hce) {
			throw new GURobotClientException("Problems communicating with: $path", hce)
		}
	}

	/***
	 * @param monitors monitor list
	 * @param log return log 
	 * @param alertcontacts return alert contacts
	 * @param responseTimes response time data 
	 * @param responseTimesAverage not yet implemented
	 * @param showMonitorAlertContacts not yet implemented
	 * @param showTimeZone not yet implemented
	 * @return
	 * @throws GURobotClientException
	 */
	List<Monitor> getMonitors(List monitors = [], boolean log, boolean alertcontacts, boolean responseTimes, boolean responseTimesAverage, boolean showMonitorAlertContacts, boolean showTimeZone) throws GURobotClientException {
		def monitorList =[]
		def params= [:]

		if(!monitors.isEmpty()){
			def list = monitors.join('-')
			params << ['monitors':list]
		}

		if(log) params << ['logs':log?1:0]

		if(alertcontacts && log) params << ['alertcontacts':alertcontacts?1:0]

		if(responseTimes) params << ['responseTimes':responseTimes?1:0]

		if(responseTimesAverage) params << ['responseTimesAverage':responseTimesAverage?1:0]

		if(showMonitorAlertContacts) params << ['showMonitorAlertContacts':showMonitorAlertContacts?1:0]

		if(showTimeZone) params << ['showTimeZone':showTimeZone?1:0]

		def response =  call("getMonitors?apiKey=${apikey}", params).json
		def monitor = response.monitors.monitor
		monitor.each(){ data ->
			monitorList << parseMonitor(data)
		}
		monitorList
	}

	/***
	 *  Get Logs, alerts contacts and response time data of each monitor
	 * @param monitors monitor list
	 * @param log return log
	 * @param alertcontacts return alert contacts
	 * @param responseTimes response time data 
	 * @return
	 * @throws GURobotClientException
	 */
	List<Monitor> getMonitors(List monitors =[], boolean log, boolean alertcontacts, boolean responseTimes) throws GURobotClientException {
		getMonitors(monitors, log, alertcontacts,responseTimes, false, false, false);
	}

	/***
	 * Get Logs and alerts contacts of each monitor 
	 * @param monitors monitor list
	 * @param alertcontacts false just will return log | true logs + alert contacts
	 * @return
	 * @throws GURobotClientException
	 */
	List<Monitor> getMonitors(List monitors = [], boolean alertcontacts) throws GURobotClientException {
		getMonitors(monitors, true, alertcontacts, false, false, false,false)
	}

	/***
	 * 
	 * Get Logs and alerts contacts for all monitors
	 * @return logs and alerts contacts
	 * @throws GURobotClientException
	 */
	List<Monitor> getMonitors() throws GURobotClientException {
		getMonitors([],true, true,false, false, false,false);
	}

	/***
	 * Get specific monitors by id list
	 * @param list of monitors
	 * @return logs and alerts contacts
	 * @throws GURobotClientException
	 */
	List<Monitor> getMonitors(List monitors) throws GURobotClientException {
		getMonitors(monitors,true, true,false, false, false,false);
	}


	/***
	 * Get monitor for Id
	 * @deprecated use {@link getMonitors(List ids)}
	 * @param id
	 * @return monitor
	 */
	@Deprecated
	Monitor getMonitorFor(Integer id){
		def response =  call("getMonitors?apiKey=${apikey}&monitors=$id").json
		parseMonitor(response.monitors.monitor.first())
	}

	private Monitor parseMonitor(data){
		def monitor = new Monitor()
		monitor.with {
			id =  data.id?.isInteger()?data.id.toInteger():null
			friendlyname = data.friendlyname
			url = data.url
			type = data.type?.isInteger()?MonitorType.byId(data.type.toInteger()):null
			subtype = data.subtype?.isInteger()?SubType.byId(data.subtype.toInteger()):null
			keywordtype = data.keywordtype?.isInteger()?KeywordType.byId(data.keywordtype.toInteger()):null
			keywordvalue = data.keywordvalue
			httpusername = data.httpusername
			httppassword = data.httppassword
			port = data.port?.isInteger()?data.port.toInteger():null
			status = data.status?.isInteger()?Status.byId(data.status.toInteger()):null
			alltimeuptimeratio = data.alltimeuptimeratio?.isFloat()?data.alltimeuptimeratio.toFloat():null
			customuptimeratio = data.customuptimeratio
			logs = parseLog(data.log)
		}
		monitor
	}

	private List<Log> parseLog(data){
		def logList = []
		def log
		data.each(){ l->
			log = new Log()
			log.with {
				type = l.type?.isInteger()?LogType.byId(l.type.toInteger()):null
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
				datetime = sdf.parse(l.datetime)
				alertcontact = parseAlertContactLogs(l.alertcontact)
			}
			logList << log
		}
		logList
	}

	List<AlertContact> getAlertContacts(){
		def data = call("getAlertContacts?apiKey=${apikey}")
		getAlerts(data)
	}

	AlertContact getAlertContactFor(String id){
		getAlertContactFor([id]).first()
	}

	List<AlertContact> getAlertContactFor(List ids){
		def list = ids.join('-')
		def data = call("getAlertContacts?apiKey=${apikey}&alertcontacts=$list")
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
			type = alert.type?.isInteger()?AlertType.byId(alert.type.toInteger()):null
			status = alert.status?.isInteger()?AlertStatus.byId(alert.status.toInteger()):null
		}
		alertcontact
	}

	private List<AlertContact> parseAlertContactLogs(data){
		def alertsContact = []
		def alert
		data.each(){ alrt ->
			alert = new AlertContact()
			alert.with {
				value = alrt.value
				type = alrt.type?.isInteger()?AlertType.byId(alrt.type.toInteger()):null
			}
			alertsContact << alert
		}
		alertsContact
	}
}
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

package org.gurobot.client.monitors;

import java.util.List;

public class EditMonitorParameter {

	/***
	 * required
	 */
	Integer monitorID;

	/***
	 * optional 
	 */
	Integer monitorStatus;

	/***
	 * optional
	 */
	String monitorFriendlyName;

	/***
	 * optional
	 */
	String monitorURL;

	/***
	 * required
	 */
	Integer monitorType;

	/***
	 * optional (required for port monitoring)
	 */
	Integer monitorSubType;

	/***
	 * optional (required for port monitoring)
	 */
	Integer monitorPort;

	/***
	 * optional (required for keyword monitoring)
	 */
	Integer monitorKeywordType;

	/***
	 * optional (required for keyword monitoring)
	 */
	String monitorKeywordValue;

	/***
	 * optional (in order to remove any previously added username, simply send the value empty
	 */
	String monitorHTTPUsername;
	/***
	 * optional (in order to remove any previously added password, simply send the value empty
	 */
	String monitorHTTPPassword;

	/***
	 * optional Multiple alertContactIDs can be sent like monitorAlertContacts(457,373,8956), in order to remove any
	 * previously added alert contacts, simply send the value empty like.
	 */
	List<String> monitorAlertContacts;

	public static class EditMonitorParameterBuilder {
		private Integer monitorID;

		/***
		 * When used with the editMonitor method 0 (to pause) or 1 (to start) can be sent. 
		 */
		private Status monitorStatus;
		
		private String monitorFriendlyName;
		private String monitorURL;
		private MonitorType monitorType;
		private SubType monitorSubType;
		private Integer monitorPort;
		private KeywordType monitorKeywordType;
		private String monitorKeywordValue;
		private String monitorHTTPUsername;
		private String monitorHTTPPassword;
		private List<String> monitorAlertContacts;

		public EditMonitorParameterBuilder monitorID(Integer monitorID) {
			this.monitorID = monitorID;
			return this;
		}

		public EditMonitorParameterBuilder monitorStatus(Status monitorStatus = null) {
			this.monitorStatus = monitorStatus;
			return this;
		}

		public EditMonitorParameterBuilder monitorFriendlyName(String monitorFriendlyName) {
			this.monitorFriendlyName = monitorFriendlyName;
			return this;
		}

		public EditMonitorParameterBuilder monitorURL(String monitorURL) {
			this.monitorURL = monitorURL;
			return this;
		}

		public EditMonitorParameterBuilder monitorType(MonitorType monitorType = null) {
			this.monitorType = monitorType;
			return this;
		}

		public EditMonitorParameterBuilder monitorSubType(SubType monitorSubType = null) {
			this.monitorSubType = monitorSubType;
			return this;
		}

		public EditMonitorParameterBuilder monitorPort(Integer monitorPort) {
			this.monitorPort = monitorPort;
			return this;
		}

		public EditMonitorParameterBuilder monitorKeywordType(KeywordType monitorKeywordType = null) {
			this.monitorKeywordType = monitorKeywordType;
			return this;
		}

		public EditMonitorParameterBuilder monitorKeywordValue(String monitorKeywordValue) {
			this.monitorKeywordValue = monitorKeywordValue;
			return this;
		}

		public EditMonitorParameterBuilder monitorHTTPUsername(String monitorHTTPUsername) {
			this.monitorHTTPUsername = monitorHTTPUsername;
			return this;
		}

		public EditMonitorParameterBuilder monitorHTTPPassword(String monitorHTTPPassword) {
			this.monitorHTTPPassword = monitorHTTPPassword;
			return this;
		}

		public EditMonitorParameterBuilder monitorAlertContacts(List<String> monitorAlertContacts) {
			this.monitorAlertContacts = monitorAlertContacts;
			return this;
		}

		public EditMonitorParameter build() {
			return new EditMonitorParameter(this);
		}
	}

	private EditMonitorParameter(EditMonitorParameterBuilder builder) {
		this.monitorID = builder.monitorID;
		this.monitorStatus = builder.monitorStatus?builder.monitorStatus.value():null
		this.monitorFriendlyName = builder.monitorFriendlyName;
		this.monitorURL = builder.monitorURL;
		this.monitorType =   builder.monitorType?builder.monitorType.value():null
		this.monitorSubType = builder.monitorSubType?builder.monitorSubType.value():null
		this.monitorPort = builder.monitorPort
		this.monitorKeywordType = builder.monitorKeywordType?builder.monitorKeywordType.value():null
		this.monitorKeywordValue = builder.monitorKeywordValue;
		this.monitorHTTPUsername = builder.monitorHTTPUsername;
		this.monitorHTTPPassword = builder.monitorHTTPPassword;
		this.monitorAlertContacts = builder.monitorAlertContacts;
	}


	def asMap(){
		def map =[:]
		def filtered = [
			'class',
			'active',
			'metaClass'
		]
		this.properties.collect{it}.findAll{
			if(it.value !=null){
				!filtered.contains(it.key)
			}
		}.each {
			map.putAt(it.key, it.value)
		}
		map
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EditMonitorParameter [monitorID=");
		builder.append(monitorID);
		builder.append(", monitorStatus=");
		builder.append(monitorStatus);
		builder.append(", monitorFriendlyName=");
		builder.append(monitorFriendlyName);
		builder.append(", monitorURL=");
		builder.append(monitorURL);
		builder.append(", monitorType=");
		builder.append(monitorType);
		builder.append(", monitorSubType=");
		builder.append(monitorSubType);
		builder.append(", monitorPort=");
		builder.append(monitorPort);
		builder.append(", monitorKeywordType=");
		builder.append(monitorKeywordType);
		builder.append(", monitorKeywordValue=");
		builder.append(monitorKeywordValue);
		builder.append(", monitorHTTPUsername=");
		builder.append(monitorHTTPUsername);
		builder.append(", monitorHTTPPassword=");
		builder.append(monitorHTTPPassword);
		builder.append(", monitorAlertContacts=");
		builder.append(monitorAlertContacts);
		builder.append("]");
		return builder.toString();
	}
}

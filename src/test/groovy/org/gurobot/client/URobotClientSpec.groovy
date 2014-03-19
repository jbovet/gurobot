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

import spock.lang.Specification
import org.gurobot.client.GURobotClient
import org.gurobot.client.alerts.AlertType;
import org.gurobot.client.monitors.MonitorType;

/**
 * @author josebovet
 *
 */
class URobotClientSpec extends Specification {

	GURobotClient gurobotClient

	def apiKey = ""

	void setup(){
		def api = "http://api.uptimerobot.com"
		gurobotClient = GURobotClient.instance(api,apiKey)
	}


	void "should retrieve list of monitors"() {
		when:
		def monitors = gurobotClient.getMonitors()

		then:
		monitors.size() == 3
	}

	void "should retrieve monitor"() {
		when:
		def monitor = gurobotClient.getMonitorFor(776017935)
		print monitor

		then:
		monitor.friendlyname == "cletasrobadas"
		monitor.type == MonitorType.HTTP
	}

	void "should retrieve list of alert contacts"() {
		when:
		def alerts = gurobotClient.getAlertContacts()

		then:
		alerts.size() == 2
	}

	void "should retrieve one alert contacts"() {
		when:
		def alert = gurobotClient.getAlertContactFor("2253041")

		then:
		alert.value == 'josebovet'
		alert.type == AlertType.TWITTER
	}

	void "should retrieve two alert contacts"() {
		when:
		def ids = ["2253041", "0121322"]
		def alerts = gurobotClient.getAlertContactFor(ids)

		then:
		alerts.size() == 2
	}
}

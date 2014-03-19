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

/**
 * @author josebovet
 *
 */
class URobotClientSpec extends Specification {

	GURobotClient gurobotClient

	def apiKey = "u121322-021b5cd9f36e49a637df719e"

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

		then:
		monitor.friendlyname == "cletasrobadas"
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
		print alert
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

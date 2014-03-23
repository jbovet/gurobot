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

import org.gurobot.client.alerts.AlertStatus
import org.gurobot.client.alerts.AlertType
import org.gurobot.client.monitors.KeywordType;
import org.gurobot.client.monitors.MonitorParameter
import org.gurobot.client.monitors.MonitorType
import org.gurobot.client.monitors.MonitorParameter.MonitorParameterBuilder
import org.gurobot.client.monitors.SubType;

import spock.lang.Specification

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

	void "should retrieve list of monitors with logs"() {
		when:
		def monitors = gurobotClient.getMonitors(false)

		then:
		monitors.logs.size() > 0
	}


	void "should retrieve list of monitors with logs and alert contacts"() {
		when:
		def monitors = gurobotClient.getMonitors()

		then:
		monitors.logs.alertcontact.size() > 0
	}

	void "should retrieve specific list of monitors with logs and alert contacts"() {
		when:
		def monitors = gurobotClient.getMonitors([776025715, 776102414])

		then:
		monitors.size() > 0
		monitors.logs.size() > 0
		monitors.logs.alertcontact.size() > 0
	}

	//	void "should retrieve list of monitors with logs, alert contacts and response time data"() {
	//		when:
	//		def monitors = gurobotClient.getMonitors(true, true, true)
	//
	//		then:
	//		monitors.logs..size() > 0
	//	}


	void "should retrieve monitor"() {
		when:
		def monitor = gurobotClient.getMonitorFor(776025715)
		def logs = monitor.logs

		then:
		monitor.type in MonitorType.values()
	}

	void "should add new monitor"(){
		MonitorParameter m = new MonitorParameterBuilder()
				.monitorFriendlyName("api-test9")
				.monitorURL("http://duoc.cl")
				.monitorType(MonitorType.HTTP)
				.monitorSubType(SubType.HTTPS)
				.monitorKeywordType(KeywordType.NOT_EXISTS)
				.monitorKeywordValue("blah!")
				.monitorHTTPUsername("pepe")
				.monitorHTTPPassword("pepe".reverse())
				.build()

		when:
		def id  = gurobotClient.newMonitor(m)

		then:
		id > 0
	}

	void "should add new monitor with alerts contacts"() {
		MonitorParameter m = new MonitorParameterBuilder()
				.monitorFriendlyName("api-test10")
				.monitorURL("http://www.duoc.cl")
				.monitorType(MonitorType.HTTP)
				.monitorAlertContacts(["2253041","0121322"])
				.build()

		when:
		def id  = gurobotClient.newMonitor(m)

		then:
		id > 0
	}




	void "should retrieve list of alert contacts"() {
		when:
		def alerts = gurobotClient.getAlertContacts()

		then:
		alerts.size() == 2
	}

	void "should retrieve one alert contacts"() {
		when:
		def alert = gurobotClient.getAlertContacts([2253041]).first()

		then:
		alert.status == AlertStatus.ACTIVE
		alert.type == AlertType.TWITTER
	}

	void "should retrieve two alert contacts"() {
		when:
		def ids = ["2253041", "0121322"]
		def alerts = gurobotClient.getAlertContacts(ids)

		then:
		alerts.size() == 2
	}
}

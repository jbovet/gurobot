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
import org.gurobot.client.monitors.EditMonitorParameter.EditMonitorParameterBuilder;
import org.gurobot.client.monitors.KeywordType;
import org.gurobot.client.monitors.MonitorParameter
import org.gurobot.client.monitors.MonitorType
import org.gurobot.client.monitors.MonitorParameter.MonitorParameterBuilder
import org.gurobot.client.monitors.Status;
import org.gurobot.client.monitors.SubType;

import spock.lang.Specification

/**
 * @author josebovet
 *
 */
class URobotClientSpec extends Specification {

	GURobotClient gurobotClient

	def apiKey = ''

	void setup(){
		gurobotClient = GURobotClient.instance(apiKey)
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

	def createMonitor(){
		new MonitorParameterBuilder()
				.monitorType(MonitorType.HTTP)
				.monitorHTTPUsername("pepe")
				.monitorHTTPPassword("pepe".reverse())
	}

	void "should add and remove monitor"(){
		def m = createMonitor().monitorFriendlyName("api-test104").monitorURL("http://www.inacap.cl").build()
		when:
		def id  = gurobotClient.newMonitor(m)
		def monitor = gurobotClient.deleteMonitor(id)

		then:
		monitor > 0

	}

	void "should retrieve a GURobotClientException when delete a monitor"(){
		when:
		gurobotClient.deleteMonitor(123)

		then:
		def e = thrown (GURobotClientException)
		e.cause

	}

	void "should retrieve list of alert contacts"() {
		when:
		def alerts = gurobotClient.getAlertContacts()

		then:
		alerts.size() > 0
	}

	void "should create and delete alert contact"(){
		when:
		def id = gurobotClient.newAlertContact(AlertType.EMAIL, "jb@mail.com")
		def deletedAlertContact = gurobotClient.deleteAlertContact(id)

		then:
		deletedAlertContact == id
	}

	void "should edit monitor"(){
		def m = new EditMonitorParameterBuilder()
				.monitorID(776025715)
				.monitorFriendlyName("JIRAC")
				.monitorStatus(Status.NOT_CHECKED)
				.build()

		when:
		def id = gurobotClient.editMonitor(m)

		then:
		id > 0

	}
	void "should retrieve a GURobotClientException when edit and put invalid status monitor"(){
		def m = new EditMonitorParameterBuilder()
				.monitorID(776025715)
				.monitorStatus(Status.UP)
				.build()

		when:
		def id = gurobotClient.editMonitor(m)

		then:
		def e = thrown (GURobotClientException)
		e
	}


}

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
package org.gurobot.client.logs

import org.gurobot.client.alerts.AlertContact;

/**
 * @author josebovet
 *
 */
class Log {

	LogType type

	/***
	 * dd/MM/yyyy HH:mm:ss
	 */
	Date datetime

	AlertContact alertcontact

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Log [type=").append(type).append(", datetime=").append(datetime).append(", alertcontact=")
				.append(alertcontact).append("]");
		return builder.toString();
	}
}

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
package org.gurobot.client.alerts

/**
 * SMS(1),EMAI(2),TWITTER(3),BOXCAR(4),WEBHOOK(5)
 * @author josebovet
 *
 */
enum AlertType {

	SMS(1),EMAIL(2),TWITTER(3),BOXCAR(4),WEBHOOK(5)

	AlertType(int value) {
		this.value = value
	}

	private int value

	public int value() {
		return value
	}

	static AlertType byId(int id) {
		values().find { it.value == id }
	}
}

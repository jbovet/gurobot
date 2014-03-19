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
package org.gurobot.client.monitors

/**
 * PAUSED, NOT_CHECKED, UP, SEEMS_DOWN, DOWN
 * @author josebovet
 *
 */
enum Status {

	PAUSED(0), NOT_CHECKED(1), UP(2), SEEMS_DOWN(8), DOWN(9)

	Status(int value) {
		this.value = value
	}

	private int value

	public int value() {
		return value
	}

	static Status byId(int id) {
		values().find { it.value == id }
	}
}

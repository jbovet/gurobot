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
 * HTTP, HTTPS ,FTP ,SMTP ,POP3 ,IMAP, CUSTOM
 * @author josebovet
 *
 */
enum SubType {

	HTTP(1),HTTPS(2),FTP(3),SMTP(4),POP3(5),IMAP(6),CUSTOM(99)

	SubType(int value) {
		this.value = value
	}

	private int value

	public int value() {
		return value
	}

	static SubType byId(int id) {
		values().find { it.value == id }
	}
}

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
package org.gurobot.client.monitors

/**
 * @author josebovet
 *
 */
class Monitor {

	Integer id
	
	String friendlyname
	
	String url
	
	Integer type
	
	Integer subtype;
	
	Integer keywordtype;
	
	String keywordvalue
	
	String httpusername
	
	String httppassword
	
	Integer port
	
	Integer status
	
	Float alltimeuptimeratio
	
	String customuptimeratio
	 	
	String log

		@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Monitor [id=");
		builder.append(id);
		builder.append(", friendlyname=");
		builder.append(friendlyname);
		builder.append(", url=");
		builder.append(url);
		builder.append(", type=");
		builder.append(type);
		builder.append(", subtype=");
		builder.append(subtype);
		builder.append(", keywordtype=");
		builder.append(keywordtype);
		builder.append(", keywordvalue=");
		builder.append(keywordvalue);
		builder.append(", httpusername=");
		builder.append(httpusername);
		builder.append(", httppassword=");
		builder.append(httppassword);
		builder.append(", port=");
		builder.append(port);
		builder.append(", status=");
		builder.append(status);
		builder.append(", alltimeuptimeratio=");
		builder.append(alltimeuptimeratio);
		builder.append(", customuptimeratio=");
		builder.append(customuptimeratio);
		builder.append(", log=");
		builder.append(log);
		builder.append("]");
		return builder.toString();
	}

}

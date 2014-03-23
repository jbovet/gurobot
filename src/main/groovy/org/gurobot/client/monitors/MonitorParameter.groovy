package org.gurobot.client.monitors;


/***
 * 
 * @author josebovet
 *
 */
class MonitorParameter {
	/***
	 * required
	 */
	String monitorFriendlyName;

	/***
	 * required
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
	 *  optional (required for port monitoring)
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
	 *  optional
	 */
	String monitorHTTPUsername;
	/***
	 *  optional
	 */
	String monitorHTTPPassword;

	/***
	 * optional Multiple alertContactIDs can be sent like monitorAlertContacts(457,373,8956)
	 */
	List<String> monitorAlertContacts;

	public static class MonitorParameterBuilder {
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

		public MonitorParameterBuilder monitorFriendlyName(String monitorFriendlyName) {
			this.monitorFriendlyName = monitorFriendlyName;
			return this;
		}

		public MonitorParameterBuilder monitorURL(String monitorURL) {
			this.monitorURL = monitorURL;
			return this;
		}

		public MonitorParameterBuilder monitorType(MonitorType monitorType) {
			this.monitorType = monitorType;
			return this;
		}

		public MonitorParameterBuilder monitorSubType(SubType monitorSubType = null) {
			this.monitorSubType = monitorSubType;
			return this;
		}

		public MonitorParameterBuilder monitorPort(Integer monitorPort = null) {
			this.monitorPort = monitorPort;
			return this;
		}

		public MonitorParameterBuilder monitorKeywordType(KeywordType monitorKeywordType = null) {
			this.monitorKeywordType = monitorKeywordType;
			return this;
		}

		public MonitorParameterBuilder monitorKeywordValue(String monitorKeywordValue = null) {
			this.monitorKeywordValue = monitorKeywordValue;
			return this;
		}

		public MonitorParameterBuilder monitorHTTPUsername(String monitorHTTPUsername = null) {
			this.monitorHTTPUsername = monitorHTTPUsername;
			return this;
		}

		public MonitorParameterBuilder monitorHTTPPassword(String monitorHTTPPassword = null) {
			this.monitorHTTPPassword = monitorHTTPPassword;
			return this;
		}

		public MonitorParameterBuilder monitorAlertContacts(List<String> monitorAlertContacts = null) {
			this.monitorAlertContacts = monitorAlertContacts;
			return this;
		}

		public MonitorParameter build() {
			return new MonitorParameter(this);
		}
	}

	private MonitorParameter(MonitorParameterBuilder builder) {
		this.monitorFriendlyName = builder.monitorFriendlyName
		this.monitorURL = builder.monitorURL
		this.monitorType =   builder.monitorType?builder.monitorType.value():null
		this.monitorSubType = builder.monitorSubType?builder.monitorSubType.value():null
		this.monitorPort = builder.monitorPort
		this.monitorKeywordType = builder.monitorKeywordType?builder.monitorKeywordType.value():null
		this.monitorKeywordValue = builder.monitorKeywordValue
		this.monitorHTTPUsername = builder.monitorHTTPUsername
		this.monitorHTTPPassword = builder.monitorHTTPPassword
		this.monitorAlertContacts = builder.monitorAlertContacts
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
		builder.append("MonitorParameter [monitorFriendlyName=");
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

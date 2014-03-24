
###GURobot - Groovy Uptime Robot API Client

Groovy Uptime Robot http://uptimerobot.com client for groovy/java, it wraps some api calls to uptime robot.

Prerequisites

	groovy-all:2.2.2
	groovy-wslite:0.8.0	

Installation

	download jar version from: https://bintray.com/josebovet/GURobot/GURobot/

Usage:

    import org.gurobot.client.GURobotClient
    
    def gurobotClient = GURobotClient.instance('apiKey')
	
	monitors = gurobotClient.getMonitors(...)

Contribute


###GURobot - Groovy Uptime Robot API Client

Groovy Uptime Robot http://uptimerobot.com client for groovy/java, it wraps some api calls from uptimerobot.com

Prerequisites:

	groovy-all:2.3.0
	groovy-wslite:0.8.0	

Installation:

Download from Bintray site[ ![Download](https://api.bintray.com/packages/josebovet/GURobot/GURobot/images/download.png) ](https://bintray.com/josebovet/GURobot/GURobot/_latestVersion)

gurobot in https://bintray.com/josebovet/GURobot/GURobot/
	 

Usage:

    import org.gurobot.client.GURobotClient
    
    def gurobotClient = GURobotClient.instance('apiKey')
	
	monitors = gurobotClient.getMonitors(...)

Contribute
;)

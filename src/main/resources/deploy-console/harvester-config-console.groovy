/*******************************************************************************
 *Copyright (C) 2014 Queensland Cyber Infrastructure Foundation (http://www.qcif.edu.au/)
 *
 *This program is free software: you can redistribute it and/or modify
 *it under the terms of the GNU General Public License as published by
 *the Free Software Foundation; either version 2 of the License, or
 *(at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License along
 *with this program; if not, write to the Free Software Foundation, Inc.,
 *51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 ******************************************************************************/
/**
 * Sample Dataset JDBC harvester configuration for console execution only.
 * 
 * @author Shilo Banihit
 *
 */
// Environment specific config below...
environments {
	ide {
		client {
			harvesterId = "armsXmlHarvesterConsole" // the unique harvester name, can be dynamic or static. Console only clients likely won't need this to be dynamic.
			description = "ARMS 1A - ARMS 2A XML Harvester-Console"
			base = "target/client/" // optional base directory. 
			autoStart = true // whether the Harvester Manager will start this harvester upon start up otherwise, it will be manually started by an administrator 
			siPath = "src/main/resources/resources/deploy-console/applicationContext-SI-harvester-console.xml" // the path used when starting this harvester
			classPathEntries = [""] // entries that will be added to the class path, by default everything in resources/lib/ will be included in the classpath
			inboundAdapter = "inboundAdapter" // the name of the main SI Endpoint the framework will ".stop()" 
		}
		file {
			runtimePath = client.base+"runtime/harvester-config-console.groovy"
			customPath = client.base+"custom/harvester-config-console.groovy"
			ignoreEntriesOnSave = ["runtime"]
		}
		harvest {			
			xmlfile {
				directory = "target/input"				
				output {					
					directory = "target/output/other"
					deletesource = "true"					
				}
				xsltTemplate = "src/main/resources/resources/armsTransformer.xslt"
				xmlToMapScript = "src/main/resources/resources/scripts/xmltomap.groovy"
			}
			pollRate = "120000" // poll every x milliseconds
			pollTimeout = "60000" // each poll should complete within these milliseconds 
			scripts {
				scriptBase = "src/main/resources/resources/scripts/"
				//             "script path" : "configuration path" - pass in an emtpy string config path if you do not want to override the script's default config lookup behavior.
				preBuild = [] // executed after a successful, but prior to building the JSON String, no data is passed
				preAssemble = [["mapToArms.groovy":""],["merge.groovy":""]] // executed prior to building the JSON string, each resultset (map) of the JDBC poll is passed as 'data'
//				postBuild = [["update_last_harvest_ts.groovy":""],["saveconfig.groovy":""]] // executed after the data is processed, but prior to ending the poll
			}
		}
		activemq {
			url = "tcp://localhost:9101"
		}
	}
	development {
		client {
			harvesterId = "armsXmlHarvesterConsole" // the unique harvester name, can be dynamic or static. Console only clients likely won't need this to be dynamic.
			description = "ARMS 1A - ARMS 2A XML Harvester-Console"
			base = "" // optional base directory.
			autoStart = true // whether the Harvester Manager will start this harvester upon start up otherwise, it will be manually started by an administrator
			siPath = "applicationContext-SI-harvester-console.xml" // the path used when starting this harvester
			classPathEntries = [""] // entries that will be added to the class path, by default everything in resources/lib/ will be included in the classpath
			inboundAdapter = "inboundAdapter" // the name of the main SI Endpoint the framework will ".stop()"
		}
		file {
			runtimePath = client.base+"config/runtime/harvester-config-console.groovy"
			customPath = client.base+"config/custom/harvester-config-console.groovy"
			ignoreEntriesOnSave = ["runtime"]
		}
		harvest {
			xmlfile {
				directory = "input"
				output {
					directory = "output/other"
					deletesource = "true"
				}
				xsltTemplate = "resources/armsTransformer.xslt"
				xmlToMapScript = "resources/scripts/xmltomap.groovy"
			}
			pollRate = "120000" // poll every x milliseconds
			pollTimeout = "60000" // each poll should complete within these milliseconds
			scripts {
				scriptBase = "resources/scripts/"
				//             "script path" : "configuration path" - pass in an emtpy string config path if you do not want to override the script's default config lookup behavior.
				preBuild = [] // executed after a successful, but prior to building the JSON String, no data is passed
				preAssemble = [["mapToArms.groovy":""],["merge.groovy":""]] // executed prior to building the JSON string, each resultset (map) of the JDBC poll is passed as 'data'
			}
		}
		activemq {
			url = "tcp://localhost:9101"
		}
	}
}
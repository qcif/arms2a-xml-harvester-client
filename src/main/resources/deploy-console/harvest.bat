REM Running the harvester client... 
java -Denvironment=development -Dlog4j.debug=true -Dlog4j.configuration=file:///%CD%\log4j.xml -cp "resources/lib/*;*" au.com.redboxresearchdata.harvester.json.client.Main harvester-config-console.groovy
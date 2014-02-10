def stringdata = payload
def recordPathResult = new XmlSlurper().parseText(stringdata)
def records = recordPathResult.record

def theData = []
records.each { record->
    def theMap = [:]
    record.children().each {        
        theMap[it.name()] = it.text()
    }
    theData << theMap            
}
return theData  
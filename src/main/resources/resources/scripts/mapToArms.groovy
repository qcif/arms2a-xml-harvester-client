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

/*
 * Script for transforming a flat map into an ARMS-specific data map. This script will primarily produce a map that will be used with the merge.groovy script.
 * 
 *    @author: Shilo Banihit
 *    
 */
log.debug "Executing mapToArms script..............."
def inputMap = [:]

data.each {
	def newKey = it.key.replace("_", ":")
	inputMap[newKey] = it.value
}
def outputMap = [:]

setIfNotExist(inputMap, "requestID", inputMap["Provision-QRIS-ID"])
setIfNotExist(inputMap, "title", inputMap["Provision-Project-Title"])

outputMap["main_tfpackage"] = inputMap
outputMap["datasetId"] = inputMap["Provision-QRIS-ID"]
data = outputMap

def setIfNotExist(inputMap, fieldName, fieldValue) {
	if (!inputMap[fieldName] && inputMap[fieldName] != "") {
		inputMap[fieldName] = fieldValue
	}
}
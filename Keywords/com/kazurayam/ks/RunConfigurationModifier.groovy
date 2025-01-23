package com.kazurayam.ks

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration

public class RunConfigurationModifier {

	@Keyword
	public static void implementPrettyPrintExecutionSetting() {
		RunConfiguration.metaClass.'static'.prettyPrintExecutionSetting << {
			->
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter()
			String prettyJson = writer.writeValueAsString(localExecutionSettingMapStorage)
			println "localExecutionSettingMapStorage:"
			println prettyJson
		}
	}
	
	@Keyword
	public static void updateTimeOut(int seconds) {
		implementPrettyPrintExecutionSetting()
		def update = ["execution": ["general": ["timeout": seconds ]]]
		RunConfiguration.setExecutionSetting(update)
	}
}

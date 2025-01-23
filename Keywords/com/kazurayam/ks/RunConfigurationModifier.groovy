package com.kazurayam.ks

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.kms.katalon.core.configuration.RunConfiguration

public class RunConfigurationModifier {

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
}

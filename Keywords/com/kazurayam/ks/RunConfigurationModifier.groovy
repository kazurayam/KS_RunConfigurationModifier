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
			return prettyJson
		}
	}

	@Keyword
	public static void implementSetTimeOut() {
		RunConfiguration.metaClass.'static'.setTimeOut << { int seconds ->
			localExecutionSettingMapStorage.get('execution').get('general').put('timeout', seconds)
		}
	}

	@Keyword
	public static void implementGetLogTestSteps() {
		RunConfiguration.metaClass.'static'.getLogTestSteps << {
			->
			return localExecutionSettingMapStorage.get('execution').get('logTestSteps')
		}
	}

	@Keyword
	public static void implementSetLogTestSteps() {
		RunConfiguration.metaClass.'static'.setLogTestSteps << { boolean b ->
			localExecutionSettingMapStorage.get('execution').put('logTestSteps', b)
		}
	}
}

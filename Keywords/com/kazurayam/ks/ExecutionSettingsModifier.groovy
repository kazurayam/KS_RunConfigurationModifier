package com.kazurayam.ks

import com.kms.katalon.core.configuration.RunConfiguration
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

public class ExecutionSettingsModifier {

	static void modifyExecutionDefaultTimeout(int timeout) {
		;
	}

	static int foo(int value) {
		return value
	}
	
	static Path getExecutionPropertiesFile() {
		Path projectDir = Paths.get(RunConfiguration.getProjectDir())
		Path propertiesFile = projectDir.resolve("settings/internal/com.kms.katalon.execution.properties")
		assert Files.exists(propertiesFile)
		return propertiesFile
	}
	
	static Properties getExecutionProperties() {
		Properties props = new Properties()
		props.load(Files.newInputStream(ExecutionSettingsModifier.getExecutionPropertiesFile()))
		return props
	}
	
	static void modifyExecutionProperty(String key, String value) {
		Properties props = ExecutionSettingsModifier.getExecutionProperties()
		props.setProperty(key, value)
		Path propertiesFile = ExecutionSettingsModifier.getExecutionPropertiesFile()
		OutputStream os = Files.newOutputStream(propertiesFile)
		props.store(os, "")
	}
	
}

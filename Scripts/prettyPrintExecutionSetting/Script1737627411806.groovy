// Test Cases/prettyPrintExecutionSetting

import com.kms.katalon.core.configuration.RunConfiguration
import com.kazurayam.ks.RunConfigurationModifier

RunConfigurationModifier.implementPrettyPrintExecutionSetting()

String prettyJson = RunConfiguration.prettyPrintExecutionSetting()

println "localExecutionSettingMapStorage:"
println prettyJson
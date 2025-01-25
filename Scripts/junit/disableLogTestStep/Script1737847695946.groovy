import com.kazurayam.ks.RunConfigurationModifier
import com.kms.katalon.core.configuration.RunConfiguration

//RunConfigurationModifier.setLogTestStep(false)
RunConfigurationModifier.implementPrettyPrintExecutionSetting()
String json = RunConfiguration.prettyPrintExecutionSetting()
println json
import com.kazurayam.ks.RunConfigurationModifier
import com.kms.katalon.core.configuration.RunConfiguration

RunConfigurationModifier.implementGetLogTestSteps()
RunConfigurationModifier.implementSetLogTestSteps()

println "RunConfiguration.getLogTestSteps(): "+ RunConfiguration.getLogTestSteps()

RunConfiguration.setLogTestSteps(false)

println "RunConfiguration.getLogTestSteps(): "+ RunConfiguration.getLogTestSteps()

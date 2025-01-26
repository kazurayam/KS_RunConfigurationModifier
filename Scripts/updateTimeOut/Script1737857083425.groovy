// Test Cases/updateTimeOut

import com.kms.katalon.core.configuration.RunConfiguration
import com.kazurayam.ks.RunConfigurationModifier

println "Before updating: RunConfiguration.getTimeOut()=" + RunConfiguration.getTimeOut()
assert RunConfiguration.getTimeOut() == 30

RunConfigurationModifier.implementSetTimeOut()
RunConfiguration.setTimeOut(8)

println "After  updating: RunConfiguration.getTimeOut()=" + RunConfiguration.getTimeOut()
assert RunConfiguration.getTimeOut() == 8

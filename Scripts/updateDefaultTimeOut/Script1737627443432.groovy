// Test Cases/updateDefaultTimeOut

import com.kms.katalon.core.configuration.RunConfiguration
import com.kazurayam.ks.RunConfigurationModifier

println "Before modifying: RunConfiguration.getTimeOut()=" + RunConfiguration.getTimeOut()
assert RunConfiguration.getTimeOut() == 30


RunConfigurationModifier.updateTimeOut(8)

// the above line is the same as the following line
/*
RunConfiguration.setExecutionSetting(["execution": ["general": ["timeout": 8 ]]])
 */

println "After  modifying: RunConfiguration.getTimeOut()=" + RunConfiguration.getTimeOut()
assert RunConfiguration.getTimeOut() == 8
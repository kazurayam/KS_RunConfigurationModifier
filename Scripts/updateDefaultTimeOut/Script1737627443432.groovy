import com.kms.katalon.core.configuration.RunConfiguration
import com.kazurayam.ks.RunConfigurationModifier

println "Before modifying: RunConfiguration.getTimeOut()=" + RunConfiguration.getTimeOut()
assert RunConfiguration.getTimeOut() == 30


RunConfigurationModifier.updateTimeOut(8)
// the above line is the same as the following 2 lines
/*
def update = ["execution": ["general": ["timeout": 8 ]]]
RunConfiguration.setExecutionSetting(update)
 */

println "After modifying:  RunConfiguration.getTimeOut()=" + RunConfiguration.getTimeOut()
assert RunConfiguration.getTimeOut() == 8
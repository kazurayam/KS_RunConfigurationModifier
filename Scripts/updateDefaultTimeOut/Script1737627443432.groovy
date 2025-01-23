import com.kms.katalon.core.configuration.RunConfiguration
import com.kazurayam.ks.RunConfigurationModifier

println "Before modifying: RunConfiguration.getTimeOut()=" + RunConfiguration.getTimeOut()
assert RunConfiguration.getTimeOut() == 30

def update = ["execution": ["general": ["timeout": 8 ]]]
RunConfiguration.setExecutionSetting(update)

println "After modifying:  RunConfiguration.getTimeOut()=" + RunConfiguration.getTimeOut()
assert RunConfiguration.getTimeOut() == 8
import com.kms.katalon.core.configuration.RunConfiguration
import com.kazurayam.ks.ExecutionSettingsModifier as ESM

println "Before modification: RunConfiguration.getTimeOut()=${RunConfiguration.getTimeOut()}"

int newTimeout = 19
ESM.modifyExecutionDefaultTimeout(newTimeout)

println "After modification: RunConfiguration.getTimeOut()=${RunConfiguration.getTimeOut()}"

assert newTimeout == RunConfiguration.getTimeOut()
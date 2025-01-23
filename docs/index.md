# A utility class that modifies the RunConfiguration in Katalon Studio project

## Problem to solve

All Katalon Studio users should be familiar with the "Project Setting &gt; Excecution" dialog of the Katalon Studio GUI as follows:

![ProjectSettings Execution](https://kazurayam.gighub.io/KS_RunConfigurationModifier/images/ProjectSettings_Execution.png)

## Adding a method into RunConfiguration class that pretty-prints the Execution Setting

    // Test Cases/prettyPrintExecutionSetting

    import com.kms.katalon.core.configuration.RunConfiguration
    import com.kazurayam.ks.RunConfigurationModifier

    RunConfigurationModifier.implementPrettyPrintExecutionSetting()

    RunConfiguration.prettyPrintExecutionSetting()

When I ran this, I got the following output in the console:

    1月 23, 2025 8:50:59 午後 com.kms.katalon.core.logging.KeywordLogger startTest
    情報: --------------------
    1月 23, 2025 8:50:59 午後 com.kms.katalon.core.logging.KeywordLogger startTest
    情報: START Test Cases/prettyPrintExecutionSetting
    localExecutionSettingMapStorage:
    {
      "runningMode" : "GUI",
      "allowCustomizeRequestResponseSizeLimit" : false,
      "source" : "/Users/kazurayam/katalon-workspace/KS_RunConfigurationModifier/Test Cases/prettyPrintExecutionSetting.tc",
      "katalon.versionNumber" : "10.0.1",
      "Name" : "Chrome",
      "host" : {
        "hostName" : "kazurayam - localhost",
        "os" : "Mac OS X 64bit",
        "hostPort" : 54160.0,
        "hostAddress" : "127.0.0.1"
      },
      "katalon.buildNumber" : "223",
      "id" : "Test Cases/prettyPrintExecutionSetting",
      "allowCustomizeRequestTimeout" : false,
      "executedEntity" : "TestCase",
      "projectDir" : "/Users/kazurayam/katalon-workspace/KS_RunConfigurationModifier",
      "isDebugLaunchMode" : false,
      "logbackConfigFileLocation" : "/Applications/Katalon Studio.app/Contents/Eclipse/configuration/org.eclipse.osgi/86/0/.cp/resources/logback/logback-console.xml",
      "name" : "prettyPrintExecutionSetting",
      "allowUsingSelfHealing" : false,
      "allowUsingTimeCapsule" : true,
      "pluginTestListeners" : [ "KatalonReportListener" ],
      "sessionServer.host" : "localhost",
      "description" : "",
      "testops" : { },
      "execution" : {
        "general" : {
          "autoApplyNeighborXpaths" : false,
          "ignorePageLoadTimeoutException" : false,
          "timeCapsuleEnabled" : false,
          "executionProfile" : "default",
          "excludeKeywords" : [ "verifyElementPresent", "verifyElementNotPresent" ],
          "xpathsPriority" : [ {
            "left" : "xpath:attributes",
            "right" : true
          }, {
            "left" : "xpath:idRelative",
            "right" : true
          }, {
            "left" : "dom:name",
            "right" : true
          }, {
            "left" : "xpath:link",
            "right" : true
          }, {
            "left" : "xpath:neighbor",
            "right" : true
          }, {
            "left" : "xpath:href",
            "right" : true
          }, {
            "left" : "xpath:img",
            "right" : true
          }, {
            "left" : "xpath:position",
            "right" : true
          }, {
            "left" : "xpath:customAttributes",
            "right" : true
          } ],
          "timeout" : 30.0,
          "actionDelay" : 0.0,
          "methodsPriorityOrder" : [ {
            "left" : "XPATH",
            "right" : true
          }, {
            "left" : "SMART_LOCATOR",
            "right" : true
          }, {
            "left" : "BASIC",
            "right" : true
          }, {
            "left" : "CSS",
            "right" : true
          }, {
            "left" : "IMAGE",
            "right" : true
          } ],
          "proxy" : "{\"proxyOption\":\"NO_PROXY\",\"proxyServerType\":\"HTTP\",\"username\":\"\",\"password\":\"\",\"proxyServerAddress\":\"\",\"proxyServerPort\":0,\"exceptionList\":\"\",\"applyToDesiredCapabilities\":true}",
          "defaultFailureHandling" : "STOP_ON_FAILURE",
          "terminateDriverAfterTestCase" : false,
          "defaultPageLoadTimeout" : 30.0,
          "report" : {
            "videoRecorderOption" : {
              "enable" : false,
              "useBrowserRecorder" : true,
              "videoFormat" : "AVI",
              "videoQuality" : "LOW",
              "recordAllTestCases" : false,
              "allowedRecordIfFailed" : true,
              "allowedRecordIfPassed" : false
            },
            "takeScreenshotSettings" : {
              "enable" : true
            },
            "screenCaptureOption" : true,
            "reportFolder" : "/var/folders/7m/lm7d6nx51kj0kbtnsskz6r3m0000gn/T/Katalon/Test Cases/prettyPrintExecutionSetting/20250123_205054"
          },
          "enablePageLoadTimeout" : false,
          "terminateDriverAfterTestSuite" : true,
          "useActionDelayInSecond" : "SECONDS",
          "testDataInfo" : { },
          "selfHealingEnabled" : false
        },
        "drivers" : {
          "system" : {
            "WebUI" : {
              "chromeDriverPath" : "/Applications/Katalon Studio.app/Contents/Eclipse/configuration/resources/drivers/chromedriver_mac/chromedriver",
              "browserType" : "CHROME_DRIVER"
            }
          },
          "preferences" : {
            "WebUI" : { }
          }
        },
        "globalSmartWaitEnabled" : true,
        "smartLocatorEnabled" : false,
        "smartLocatorSettingDefaultEnabled" : true,
        "logTestSteps" : true,
        "hideHostname" : false,
        "current_testcase" : "Test Cases/prettyPrintExecutionSetting"
      },
      "sessionServer.port" : 53788.0,
      "maxFailedTests" : -1.0
    }
    1月 23, 2025 8:51:00 午後 com.kms.katalon.core.logging.KeywordLogger endTest
    情報: END Test Cases/prettyPrintExecutionSetting

The call to `RunConfigurationModifier.implementPrettyPrintExecutionSetting()` dynamically injects a static method `prettyPrintExecutionSetting` in the RunConfiguration class.

The call to `RunConfiguration.prettyPrintExecutionSetting()` prints the content of the `localExecutionSettingMapStorage` variable inside the `RunConfiguration` object, which contains the values set in the `Project Settings > Execution` GUI window of Katalon Studio.

## Updating the `Project Settings > Execution > Default wait for element timeout (in secods)` on the fly

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

    println "After modifying:  RunConfiguration.getTimeOut()=" + RunConfiguration.getTimeOut()
    assert RunConfiguration.getTimeOut() == 8

When I ran this I got the following output in the console:

    1月 23, 2025 8:56:34 午後 com.kms.katalon.core.logging.KeywordLogger startTest
    情報: --------------------
    1月 23, 2025 8:56:34 午後 com.kms.katalon.core.logging.KeywordLogger startTest
    情報: START Test Cases/updateDefaultTimeOut
    Before modifying: RunConfiguration.getTimeOut()=30
    After modifying:  RunConfiguration.getTimeOut()=8
    1月 23, 2025 8:56:35 午後 com.kms.katalon.core.logging.KeywordLogger endTest
    情報: END Test Cases/updateDefaultTimeOut

## How is it implemented?

Please read the source of `com.kazurayam.ks.RunConfigurationModifier` classs. Here I would quote it:

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
                println "localExecutionSettingMapStorage:"
                println prettyJson
            }
        }

        @Keyword
        public static void updateTimeOut(int seconds) {
            implementPrettyPrintExecutionSetting()
            def update = ["execution": ["general": ["timeout": seconds ]]]
            RunConfiguration.setExecutionSetting(update)
        }
    }

In order to understand this, you also need to read the source code of `com.kms.katalon.core.configuration.RunConfiguration` class. The source code is distributed bundled in the Katalon Studio. You can find it in the `<Katalon Studio installation folder>/Contents/Eclipse/configuration/resources/source/com.kms.katalon.core/com.kms.katalon.core-sources.jar`.

In the `implementPrettyPrintExecutionSetting()` method, I used the [Groovy’s Meta-programming technique](https://www.groovy-lang.org/metaprogramming.html#metaprogramming_emc). With this technique, the method dynamically injects the `prettyPrintExecutionSetting` method, which converts the `localExecutionSettingMap` variable of Groovy `Map` type into a pretty-formatted JSON String, and println it into the console.

The JSON tells you the internal data struction in the RunConfiguration object.

You should read the source of the `com.kms.katalon.core.configuration.RunConfiguration` and find the `setExecutionSetting(Map)` method is provided. Using this method, it is possible to update the content of the exectuion setting contained in the `RunConfiguration` object. the `updateTimeOut` method ofhte `RunConfigurationModifier` is a mere example how to make use of this capability.

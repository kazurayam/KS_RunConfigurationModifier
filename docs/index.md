# A utility class that modifies the RunConfiguration class in Katalon Studio project

back to [the project repository](https://github.com/kazurayam/KS_RunConfigurationModifier)

## Problem to solve

All Katalon Studio users would be familiar with the `Project Setting > Excecution` dialog of the Katalon Studio GUI as follows:

![ProjectSettings Execution](https://kazurayam.github.io/KS_RunConfigurationModifier/images/ProjectSettings_Execution.png)

Let’s look at the `Default wait for element timeout (in seconds)`. Of course, you can change the value during the authoring time before you run your Test Cases. Now I want to update the timeout value programatically runtime. Is it possbile?

Katalon API includes a class `com.kms.katalon.core.configuration.RunConfiguration` which implements a method `int getTimeOut()`

-   <https://api-docs.katalon.com/com/kms/katalon/core/configuration/RunConfiguration.html#getTimeOut()>

The value of `Default wait for element timeout (in seconds)` is made accessible as the return of `int getTimeOut()` method.

Howerver, `RunConfiguration` class has no `setTimeOut(int)` method. Therefore it seems that Katalon does not support a capability to update the timeout value by user codes. --- But I want to know how to hack it.

## Solution

You can read the source code of `com.kms.katalon.core.configuration.RunConfiguration` class. The source code is bundled in the Katalon Studio distribution. You can find it in the `<Katalon Studio installation folder>/Contents/Eclipse/configuration/resources/source/com.kms.katalon.core/com.kms.katalon.core-sources.jar`.

By a deep study in the source code, I found a way of hacking. See the following solution described.

## Description

### Adding a method into RunConfiguration class that pretty-prints the Execution Setting

    // Test Cases/prettyPrintExecutionSetting

    import com.kms.katalon.core.configuration.RunConfiguration
    import com.kazurayam.ks.RunConfigurationModifier

    RunConfigurationModifier.implementPrettyPrintExecutionSetting()

    String prettyJson = RunConfiguration.prettyPrintExecutionSetting()

    println "localExecutionSettingMapStorage:"
    println prettyJson

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

### Updating `Project Settings > Execution > Default wait for element timeout`

    // Test Cases/updateTimeOut

    import com.kms.katalon.core.configuration.RunConfiguration
    import com.kazurayam.ks.RunConfigurationModifier

    println "Before updating: RunConfiguration.getTimeOut()=" + RunConfiguration.getTimeOut()
    assert RunConfiguration.getTimeOut() == 30

    RunConfigurationModifier.implementSetTimeOut()
    RunConfiguration.setTimeOut(8)

    println "After  updating: RunConfiguration.getTimeOut()=" + RunConfiguration.getTimeOut()
    assert RunConfiguration.getTimeOut() == 8

When I ran this I got the following output in the console:

    1月 23, 2025 8:56:34 午後 com.kms.katalon.core.logging.KeywordLogger startTest
    情報: --------------------
    1月 23, 2025 8:56:34 午後 com.kms.katalon.core.logging.KeywordLogger startTest
    情報: START Test Cases/updateDefaultTimeOut
    Before modifying: RunConfiguration.getTimeOut()=30
    After  modifying: RunConfiguration.getTimeOut()=8
    1月 23, 2025 8:56:35 午後 com.kms.katalon.core.logging.KeywordLogger endTest
    情報: END Test Cases/updateDefaultTimeOut

### How is it implemented?

Please read the source of `com.kazurayam.ks.RunConfigurationModifier` classs. Here I would quote it:

    package com.kazurayam.ks

    import com.fasterxml.jackson.databind.ObjectMapper
    import com.fasterxml.jackson.databind.ObjectWriter
    import com.fasterxml.jackson.databind.SerializationFeature
    import com.kms.katalon.core.annotation.Keyword
    import com.kms.katalon.core.configuration.RunConfiguration

    public class RunConfigurationModifier {

        @Keyword
        public static void implementPrettyPrintExecutionSetting() {
            RunConfiguration.metaClass.'static'.prettyPrintExecutionSetting << {
                ->
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter()
                String prettyJson = writer.writeValueAsString(localExecutionSettingMapStorage)
                return prettyJson
            }
        }

        @Keyword
        public static void implementSetTimeOut() {
            RunConfiguration.metaClass.'static'.setTimeOut << { int seconds ->
                localExecutionSettingMapStorage.get('execution').get('general').put('timeout', seconds)
            }
        }

        @Keyword
        public static void implementGetLogTestSteps() {
            RunConfiguration.metaClass.'static'.getLogTestSteps << {
                ->
                return localExecutionSettingMapStorage.get('execution').get('logTestSteps')
            }
        }

        @Keyword
        public static void implementSetLogTestSteps() {
            RunConfiguration.metaClass.'static'.setLogTestSteps << { boolean b ->
                localExecutionSettingMapStorage.get('execution').put('logTestSteps', b)
            }
        }
    }

In the `RunConfigurationModifier` class, I used the [Groovy’s Meta-programming technique](https://www.groovy-lang.org/metaprogramming.html#metaprogramming_emc). With this technique, the `prettyPrintExecutionSetting` method and others are added to the `RunConfiguration` class.

The `implementSetTimeOut` method of the `RunConfigurationModifier` is adds the `setTimeOut(int)` method that is missing in the original `RunConfiguration` class. If you would like, you would be able to add more methods that update other items in the `Project Setting > Execution`. Please try it yourself.

## Warning: this does not work with Katalon Studio v9

I developed this project using Katalon Studio Free v10.0.1. I tried to run the `Test Cases/prettyPrintExecutionSetting` using v9.0.0 and it hanged with the following message:

    2025-01-26 12:38:40.524 INFO  c.k.katalon.core.main.TestCaseExecutor   - --------------------
    2025-01-26 12:38:40.557 INFO  c.k.katalon.core.main.TestCaseExecutor   - START Test Cases/prettyPrintExecutionSetting
    2025-01-26 12:38:41.571 DEBUG testcase.prettyPrintExecutionSetting     - 1: RunConfigurationModifier.implementPrettyPrintExecutionSetting()
    2025-01-26 12:38:41.750 DEBUG testcase.prettyPrintExecutionSetting     - 2: prettyJson = prettyPrintExecutionSetting()
    2025-01-26 12:38:42.755 ERROR c.k.katalon.core.main.TestCaseExecutor   - ❌ Test Cases/prettyPrintExecutionSetting FAILED.
    Reason:
    com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class com.kms.katalon.core.configuration.RunConfiguration$1 and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
        at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1306)
        at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.failForEmpty(UnknownSerializer.java:53)
        at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.serialize(UnknownSerializer.java:30)
        at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        at com.fasterxml.jackson.databind.ObjectWriter$Prefetch.serialize(ObjectWriter.java:1572)
        at com.fasterxml.jackson.databind.ObjectWriter._writeValueAndClose(ObjectWriter.java:1273)
        at com.fasterxml.jackson.databind.ObjectWriter.writeValueAsString(ObjectWriter.java:1140)
        at com.kazurayam.ks.RunConfigurationModifier$_implementPrettyPrintExecutionSetting_closure1.doCall(RunConfigurationModifier.groovy:16)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at prettyPrintExecutionSetting.run(prettyPrintExecutionSetting:8)
        at com.kms.katalon.core.main.ScriptEngine.run(ScriptEngine.java:194)
        at com.kms.katalon.core.main.ScriptEngine.runScriptAsRawText(ScriptEngine.java:119)
        at com.kms.katalon.core.main.TestCaseExecutor.runScript(TestCaseExecutor.java:448)
        at com.kms.katalon.core.main.TestCaseExecutor.doExecute(TestCaseExecutor.java:439)
        at com.kms.katalon.core.main.TestCaseExecutor.processExecutionPhase(TestCaseExecutor.java:418)
        at com.kms.katalon.core.main.TestCaseExecutor.accessMainPhase(TestCaseExecutor.java:410)
        at com.kms.katalon.core.main.TestCaseExecutor.execute(TestCaseExecutor.java:285)
        at com.kms.katalon.core.main.TestCaseMain.runTestCase(TestCaseMain.java:144)
        at com.kms.katalon.core.main.TestCaseMain.runTestCase(TestCaseMain.java:135)
        at TempTestCase1737862711294.run(TempTestCase1737862711294.groovy:25)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)

    2025-01-26 12:38:42.864 ERROR c.k.katalon.core.main.TestCaseExecutor   - ❌ Test Cases/prettyPrintExecutionSetting FAILED.
    Reason:
    com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class com.kms.katalon.core.configuration.RunConfiguration$1 and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
        at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1306)
        at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.failForEmpty(UnknownSerializer.java:53)
        at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.serialize(UnknownSerializer.java:30)
        at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        at com.fasterxml.jackson.databind.ObjectWriter$Prefetch.serialize(ObjectWriter.java:1572)
        at com.fasterxml.jackson.databind.ObjectWriter._writeValueAndClose(ObjectWriter.java:1273)
        at com.fasterxml.jackson.databind.ObjectWriter.writeValueAsString(ObjectWriter.java:1140)
        at com.kazurayam.ks.RunConfigurationModifier$_implementPrettyPrintExecutionSetting_closure1.doCall(RunConfigurationModifier.groovy:16)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at prettyPrintExecutionSetting.run(prettyPrintExecutionSetting:8)
        at com.kms.katalon.core.main.ScriptEngine.run(ScriptEngine.java:194)
        at com.kms.katalon.core.main.ScriptEngine.runScriptAsRawText(ScriptEngine.java:119)
        at com.kms.katalon.core.main.TestCaseExecutor.runScript(TestCaseExecutor.java:448)
        at com.kms.katalon.core.main.TestCaseExecutor.doExecute(TestCaseExecutor.java:439)
        at com.kms.katalon.core.main.TestCaseExecutor.processExecutionPhase(TestCaseExecutor.java:418)
        at com.kms.katalon.core.main.TestCaseExecutor.accessMainPhase(TestCaseExecutor.java:410)
        at com.kms.katalon.core.main.TestCaseExecutor.execute(TestCaseExecutor.java:285)
        at com.kms.katalon.core.main.TestCaseMain.runTestCase(TestCaseMain.java:144)
        at com.kms.katalon.core.main.TestCaseMain.runTestCase(TestCaseMain.java:135)
        at TempTestCase1737862711294.run(TempTestCase1737862711294.groovy:25)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)

    2025-01-26 12:38:42.954 INFO  c.k.katalon.core.main.TestCaseExecutor   - END Test Cases/prettyPrintExecutionSetting

I guess that the RunConfiguration class of v9 has some fault that I can not manage. I wouldn’t dive into this issue any more.

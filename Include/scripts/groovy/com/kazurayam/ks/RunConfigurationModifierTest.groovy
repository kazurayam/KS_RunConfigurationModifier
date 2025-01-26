package com.kazurayam.ks

import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.nio.file.Files
import java.nio.file.Path
import com.kms.katalon.core.configuration.RunConfiguration

@RunWith(JUnit4.class)
public class RunConfigurationModifierTest {

	@Before
	void setup() {
		RunConfigurationModifier.implementPrettyPrintExecutionSetting()
	}

	@Test
	void test_implementSetTimeOut() {
		RunConfigurationModifier.implementSetTimeOut()
		def expected = 8
		RunConfiguration.setTimeOut(expected)
		def actual = RunConfiguration.getTimeOut()
		assertThat(actual, is(expected))
	}

	@Test
	void test_implementGetAndSetLogTestSteps() {
		RunConfigurationModifier.implementGetLogTestSteps()
		println "logTestSteps: " + RunConfiguration.getLogTestSteps()
		assertTrue(RunConfiguration.getLogTestSteps())
		//
		RunConfigurationModifier.implementSetLogTestSteps()
		RunConfiguration.setLogTestSteps(false)
		println "logTestSteps: " + RunConfiguration.getLogTestSteps();
		assertFalse(RunConfiguration.getLogTestSteps())
	}
}

package com.kazurayam.ks

import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.nio.file.Files
import java.nio.file.Path

@RunWith(JUnit4.class)
public class ExecutionSettingsModifierTest {

	@Test
	void test_getExecutionPropertiesFile() {
		Path file = ExecutionSettingsModifier.getExecutionPropertiesFile()
		assertTrue(Files.exists(file))	
	}
	
	@Test
	void test_getExecutionProperties() {
		Properties props = ExecutionSettingsModifier.getExecutionProperties()
		assertNotNull(props)
		assertEquals('25', props.get("execution.default.timeout"))	
	}
	
	@Test
	void test_modifyExecutionProerty() {
		ExecutionSettingsModifier.modifyExecutionProperty("execution.default.timeout", "13")
		//
		Properties props = ExecutionSettingsModifier.getExecutionProperties()
		assertEquals('13', props.get("execution.default.timeout"))
	}
	
	@Test
	void testFoo() {
		int expected = 21
		int actual = ExecutionSettingsModifier.foo(21)
		assertThat(actual, is(expected))
	}
}

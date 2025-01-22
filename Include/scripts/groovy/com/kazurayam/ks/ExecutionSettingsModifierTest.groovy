package com.kazurayam.ks

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
public class ExecutionSettingsModifierTest {

	@Test
	void testFoo() {
		int expected = 21
		int actual = ExecutionSettingsModifier.foo(21)
		assertThat(actual, is(expected))
	}
}

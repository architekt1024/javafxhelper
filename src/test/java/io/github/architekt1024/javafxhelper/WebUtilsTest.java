/*
 * All rights reserved.
 */
package io.github.architekt1024.javafxhelper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebUtilsTest {

//	@BeforeEach
//	void setUp() {
//
//	}
//
//	@AfterEach
//	void tearDown() {
//	}

	@Test
	public void prepareSimpleScriptTest() {
		String result = WebUtils.prepareScript("test", "bb", "cc");
		assertEquals("test('bb', 'cc');", result);
	}

	@Test
	public void prepareNoArgsScriptTest() {
		String result = WebUtils.prepareScript("test");
		assertEquals("test();", result);
	}

	@Test
	public void testEscape() {
		String result = WebUtils.prepareScript("test", "a'b");
		assertEquals("test('a\\'b');", result);
	}
}
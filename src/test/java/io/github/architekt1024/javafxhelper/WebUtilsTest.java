/*
 * Copyright 2020 architekt1024
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.architekt1024.javafxhelper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebUtilsTest {
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

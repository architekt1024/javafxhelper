package io.github.architekt1024.javafxhelper.utils;

import java.net.URL;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertiesUtilsTest {
	private URL empty;

	@BeforeEach
	void setUp() {
		empty = this.getClass().getClassLoader().getResource("empty");
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testLoadNullProperties() throws Exception {
		PropertiesUtils.loadProperties((String) null);
	}


	@Test
	void loadEmptyProperties() throws Exception {
		Properties properties = PropertiesUtils.loadProperties(empty.getPath());
		assertEquals(0, properties.size());
	}

	@Test
	void testLoadProperties() throws Exception {
		URL example = this.getClass().getClassLoader().getResource("example.txt");
		Properties properties = PropertiesUtils.loadProperties(example.getPath());

		assertEquals(5, properties.size());
		assertEquals("10", properties.getProperty("a"));
		assertEquals("test", properties.getProperty("b"));
		assertEquals("inne znaki", properties.getProperty("c"));
		assertEquals("zażółć gęślą jaźń", properties.getProperty("pl"));
		assertEquals("test_\uD83D\uDD25\uD83E\uDDE8⚡", properties.getProperty("emoji"));
	}

	@Test
	void loadPropertiesXML() {
		//TODO
	}

	@Test
	void testLoadPropertiesXML() {
		//TODO
	}

	@Test
	void saveProperties() {
		//TODO
	}

	@Test
	void testSaveProperties() {
		//TODO
	}

	@Test
	void savePropertiesXML() {
		//TODO
	}

	@Test
	void testSavePropertiesXML() {
		//TODO
	}
}

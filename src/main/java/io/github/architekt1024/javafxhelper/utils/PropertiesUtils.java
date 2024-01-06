/*
 * Copyright 2023 architekt1024
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

package io.github.architekt1024.javafxhelper.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Utils to load and save {@link Properties} using file
 *
 * @author architekt1024
 * @since 0.1.11
 */
public final class PropertiesUtils {

	private PropertiesUtils() {
	}

	/**
	 * Load properties from file. When file not exits, return empty properties.
	 *
	 * @param fileName filename
	 *
	 * @return
	 *
	 * @throws IOException error read from file
	 * @since 0.1.11
	 */
	public static Properties loadProperties(String fileName) throws IOException {
		File file = new File(fileName);
		Properties properties = new Properties();
		if (file.exists()) {
			try (FileReader fileReader = new FileReader(file)) {
				properties.load(fileReader);
			}
		}
		return properties;
	}

	/**
	 * Save properties to file
	 *
	 * @param fileName   file name
	 * @param properties
	 *
	 * @throws IOException error save to file
	 * @since 0.1.11
	 */
	public static void saveProperties(String fileName, Properties properties) throws IOException {
		try (FileWriter fileWriter = new FileWriter(fileName)) {
			properties.store(fileWriter, null);
		}
	}

	/**
	 * Load properties to xml file. When file not exits, return empty properties.
	 *
	 * @param fileName xml file name
	 *
	 * @return
	 *
	 * @throws IOException error read from file
	 * @since 0.1.11
	 */
	public static Properties loadPropertiesXML(String fileName) throws IOException {
		File file = new File(fileName);
		Properties properties = new Properties();
		if (file.exists()) {
			try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
				properties.loadFromXML(fileInputStream);
			}
		}
		return properties;
	}

	/**
	 * Save properties to xml file
	 *
	 * @param fileName   xml file name
	 * @param properties
	 *
	 * @throws IOException error read from file
	 * @since 0.1.11
	 */
	public static void savePropertiesXML(String fileName, Properties properties) throws IOException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
			properties.storeToXML(fileOutputStream, null);
		}
	}
}

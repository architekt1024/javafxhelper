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

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Utils to load and save {@link Properties} file using UTF-8 charset.
 *
 * @author architekt1024
 * @since 0.1.11
 */
@SuppressWarnings("unused")
public final class PropertiesUtils {
	private static final Charset CHARSET = StandardCharsets.UTF_8;

	private PropertiesUtils() {
	}

	/**
	 * Load properties from file. When file not exits, return empty properties.
	 *
	 * @param fileName filename
	 *
	 * @return loaded properties
	 *
	 * @throws IOException error read from file
	 * @since 0.1.11
	 */
	public static Properties loadProperties(@Nullable String fileName) throws IOException {
		if (fileName == null || fileName.isBlank()) {
			return new Properties();
		}
		return loadProperties(Path.of(fileName));
	}

	/**
	 * Load properties from file. When file not exits, return empty properties.
	 *
	 * @param path properties path
	 *
	 * @return loaded properties
	 *
	 * @throws IOException error read from file
	 * @since 0.1.12
	 */
	public static Properties loadProperties(@NotNull Path path) throws IOException {
		Properties properties = new Properties();

		if (Files.isReadable(path)) {
			try (var reader = Files.newBufferedReader(path, CHARSET)) {
				properties.load(reader);
			}
		}
		return properties;
	}

	/**
	 * Load properties from XML file. When file not exits, return empty properties.
	 *
	 * @param fileName XML file name
	 *
	 * @return loaded properties
	 *
	 * @throws IOException error read from file
	 * @since 0.1.11
	 */
	public static Properties loadPropertiesXML(@Nullable String fileName) throws IOException {
		if (fileName == null || fileName.isBlank()) {
			return new Properties();
		}
		return loadPropertiesXML(Path.of(fileName));
	}

	/**
	 * Load properties from XML file. When file not exits, return empty properties.
	 *
	 * @param path properties path
	 *
	 * @return loaded properties
	 *
	 * @throws IOException error read from file
	 * @since 0.1.12
	 */
	public static Properties loadPropertiesXML(@Nullable Path path) throws IOException {
		Properties properties = new Properties();
		if (path != null && Files.isReadable(path)) {
			try (InputStream fileInputStream = Files.newInputStream(path)) {
				properties.loadFromXML(fileInputStream);
			}
		}
		return properties;
	}

	/**
	 * Save properties to XML file
	 *
	 * @param fileName   XML file name
	 * @param properties properties to save
	 *
	 * @throws IOException error read from file
	 * @since 0.1.11
	 */
	public static void saveProperties(@Nullable String fileName, @NotNull Properties properties) throws IOException {
		if (fileName == null || fileName.isBlank()) {
			return;
		}
		saveProperties(Path.of(fileName), properties);
	}

	/**
	 * Save properties to XML file
	 *
	 * @param path       XML path
	 * @param properties properties to save
	 *
	 * @throws IOException error read from file
	 * @since 0.1.12
	 */
	public static void saveProperties(@NotNull Path path, @NotNull Properties properties) throws IOException {
		try (var writer = Files.newBufferedWriter(path, CHARSET)) {
			properties.store(writer, null);
		}
	}

	/**
	 * Save properties to XML file
	 *
	 * @param fileName   XML file name
	 * @param properties properties to save
	 *
	 * @throws IOException error read from file
	 * @since 0.1.11
	 */
	public static void savePropertiesXML(@NotNull String fileName, @NotNull Properties properties) throws IOException {
		savePropertiesXML(Path.of(fileName), properties);
	}

	/**
	 * Save properties to XML file
	 *
	 * @param path       XML path
	 * @param properties properties to save
	 *
	 * @throws IOException error read from file
	 * @since 0.1.12
	 */
	public static void savePropertiesXML(@NotNull Path path, @NotNull Properties properties) throws IOException {
		try (var out = Files.newOutputStream(path)) {
			properties.storeToXML(out, null, CHARSET);
		}
	}
}

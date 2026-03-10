/*
 * Copyright 2020-2026 architekt1024
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

package io.github.architekt1024.javafxhelper.listview;

import java.util.function.Function;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A {@link javafx.util.StringConverter} implementation based on functional converters.
 *
 * <p>This class extends {@link ReadOnlyStringConverter} by adding support for
 * reverse conversion using a function provided at construction time.</p>
 *
 * <p>The conversion logic is delegated to two {@link Function} instances:</p>
 * <ul>
 *   <li>a function converting a value to its string representation</li>
 *   <li>a function converting a string back to a value</li>
 * </ul>
 * <p>Example usage</p>
 * <pre>{@code myList.setConverter(new SimpleStringConverter<>(SomeObject::getName, s -> new SomeObject(s)));}</pre>
 *
 * @param <T> the type being converted
 *
 * @author architekt1024
 * @since 0.1.10
 */
public class SimpleStringConverter<T> extends ReadOnlyStringConverter<T> {
	private final Function<String, T> fromString;

	/**
	 * Creates a converter using the provided conversion functions.
	 *
	 * @param toString   function used to convert a value to its string representation
	 * @param fromString function used to convert a string back to a value
	 *
	 * @since 0.1.10
	 */
	public SimpleStringConverter(@NotNull Function<T, String> toString, @NotNull Function<String, T> fromString) {
		super(toString);
		this.fromString = fromString;
	}

	/**
	 * Converts the given string to a value using the configured function.
	 *
	 * @param string the {@code String} to convert
	 *
	 * @return the result value
	 */
	@Override
	public T fromString(@Nullable String string) {
		return fromString.apply(string);
	}
}

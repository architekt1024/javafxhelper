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

import javafx.util.StringConverter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A {@link StringConverter} implementation intended for read-only conversions.
 *
 * <p>This converter supports conversion from an object to its string
 * representation via {@link #toString(Object)} but does not support
 * the reverse operation.</p>
 *
 * <p>Calling {@link #fromString(String)} will always throw an
 * {@link UnsupportedOperationException}.</p>
 *
 * <p>This class is useful in contexts where a {@link StringConverter}
 * is required by an API but the value is not intended to be edited
 * or parsed from text.</p>
 * <p>Example usage:</p>
 * <pre>{@code
 * myList.setConverter(new ReadOnlyStringConverter<>(SomeObject::getName));
 * }</pre>
 *
 * @param <T> the type being converted to a string
 *
 * @author architekt1024
 * @since 0.1.10
 */
public class ReadOnlyStringConverter<T> extends StringConverter<T> {
	/**
	 * TODO description
	 */
	protected final Function<T, String> converter;

	/**
	 * Creates a read-only converter using the given conversion function.
	 *
	 * @param converter function used to convert a value to its string representation
	 *
	 * @since 0.1.10
	 */
	public ReadOnlyStringConverter(@NotNull Function<T, String> converter) {
		this.converter = converter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(@Nullable T object) {
		if (object == null) {
			return null;
		}
		return converter.apply(object);
	}

	/**
	 * This operation is not supported.
	 *
	 * @throws UnsupportedOperationException always thrown
	 */
	@Override
	public T fromString(@Nullable String string) {
		throw new UnsupportedOperationException("Read-only converter");
	}
}

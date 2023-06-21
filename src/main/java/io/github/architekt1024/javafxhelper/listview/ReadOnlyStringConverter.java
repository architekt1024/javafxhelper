/*
 * Copyright 2020-2023 architekt1024
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

import javafx.util.StringConverter;

import java.util.function.Function;

/**
 * <p>TODO description</p>
 * Example use:
 * <pre>{@code
 * myList.setConverter(new ReadOnlyStringConverter<>(SomeObject::getName));
 * }</pre>
 *
 * @param <T>
 *
 * @author architekt1024
 * @since 0.1.10
 */
public class ReadOnlyStringConverter<T> extends StringConverter<T> {
	protected final Function<T, String> converter;

	/**
	 * TODO description
	 *
	 * @param converter
	 *
	 * @since 0.1.10
	 */
	public ReadOnlyStringConverter(Function<T, String> converter) {
		this.converter = converter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(T object) {
		if (object == null) {
			return null;
		}
		return converter.apply(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T fromString(String string) {
		throw new UnsupportedOperationException("Object is read-only");
	}
}

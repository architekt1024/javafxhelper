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
package io.github.architekt1024.javafxhelper.listview;

import java.util.Objects;
import java.util.function.Function;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import io.github.architekt1024.javafxhelper.annotation.Nonnull;

/**
 * Cell factory for {@link ListView} using String converter. Set null if item is empty or null, otherwise convert item to String using converter.
 * <p>
 * Example usage:<br>
 * {@code exampleList.setCellFactory(new SimpleListCellFactory<>(item -> item.getA() + " - " + item.getB()));}
 * </p>
 *
 * @param <T> result type
 *
 * @author architekt1024
 * @since 0.1.5
 */
public class SimpleListCellFactory<T> implements Callback<ListView<T>, ListCell<T>> {
	private final Function<T, String> converter;

	/**
	 * Create default simple {@link ListView} cell factory
	 *
	 * @param converter result to String converter, cannot be null
	 */
	public SimpleListCellFactory(Function<T, String> converter) {
		this.converter = Objects.requireNonNull(converter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListCell<T> call(ListView<T> param) {
		return new ListCell<>() {
			@Override
			protected void updateItem(T item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null) {
					setText(null);
				} else {
					setText(converter.apply(item));
				}
			}
		};
	}
}

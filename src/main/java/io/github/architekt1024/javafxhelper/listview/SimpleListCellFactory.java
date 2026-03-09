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

import java.util.Objects;
import java.util.function.Function;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link javafx.util.Callback} implementation creating {@link javafx.scene.control.ListCell} instances using a functional string
 * converter.
 *
 * <p>The cell text is produced by applying the provided {@link Function} to the cell item.</p>
 *
 * <p>This class is useful for quickly configuring {@link javafx.scene.control.ListView} or {@link javafx.scene.control.ComboBox} controls
 * without creating a custom {@link javafx.scene.control.ListCell} implementation.</p>
 *
 * <p><b>Example usage:</b></p>
 * <pre>{@code listView.setCellFactory(new SimpleListCellFactory<>(Person::getName));}</pre>
 * <pre>{@code exampleList.setCellFactory(new SimpleListCellFactory<>(item -> item.getA() + " - " + item.getB()));}</pre>
 *
 * @param <T> the type of items contained in the list
 */
@SuppressWarnings("ClassCanBeRecord")
public class SimpleListCellFactory<T> implements Callback<ListView<T>, ListCell<T>> {
	private final Function<T, String> converter;

	/**
	 * Creates a cell factory using the provided conversion function.
	 *
	 * @param converter function used to convert a cell item to its string representation
	 *
	 */
	public SimpleListCellFactory(@NotNull Function<T, String> converter) {
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

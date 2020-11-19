/*
 * All rights reserved.
 */
package io.github.architekt1024.javafxhelper.listview;

import java.util.function.Function;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import io.github.architekt1024.javafxhelper.annotation.Nonnull;

/**
 * Set null if item is empty or null, otherwise convert item to String using converter.
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
	 * @param converter result to String converter
	 */
	public SimpleListCellFactory(@Nonnull Function<T, String> converter) {
		this.converter = converter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListCell<T> call(ListView<T> param) {
		return new ListCell<T>() {
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

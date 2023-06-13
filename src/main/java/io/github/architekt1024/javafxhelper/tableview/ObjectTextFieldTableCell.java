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
package io.github.architekt1024.javafxhelper.tableview;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

/**
 * TODO description
 * Default String converters.
 * <p>TODO description</p>
 * <p>Example usage:</p>
 * <p>
 * {@code integerColumn.setCellFactory(ObjectTextFieldTableCell.forIntegerColumn());}<br>
 * {@code longColumn.setCellFactory(ObjectTextFieldTableCell.forLongColumn());}<br>
 * </p>
 * <p>In FXML:<br>
 * {@code
 * <cellFactory>
 *     <ObjectTextFieldTableCell fx:factory="forObjectColumn"/>
 * </cellFactory>
 * }
 * </p>
 *
 * @author architekt1024
 */
public final class ObjectTextFieldTableCell {
	private ObjectTextFieldTableCell() {
	}

	/**
	 * Create and return default CellFactory for String. Alias for {@link TextFieldTableCell#forTableColumn()}
	 *
	 * @param <S> model class for data in {@link javafx.scene.control.TableView}
	 *
	 * @return default CellFactory for Integer
	 */
	public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> forStringColumn() {
		return TextFieldTableCell.forTableColumn();
	}

	/**
	 * Create and return default CellFactory for Integer
	 *
	 * @param <S> model class for data in {@link javafx.scene.control.TableView}
	 *
	 * @return default CellFactory for Integer
	 */
	public static <S> Callback<TableColumn<S, Integer>, TableCell<S, Integer>> forIntegerColumn() {
		return TextFieldTableCell.forTableColumn(new IntegerStringConverter());
	}

	/**
	 * Create and return default CellFactory for Long
	 *
	 * @param <S> model class for data in {@link javafx.scene.control.TableView}
	 *
	 * @return default CellFactory for Long
	 */
	public static <S> Callback<TableColumn<S, Long>, TableCell<S, Long>> forLongColumn() {
		return TextFieldTableCell.forTableColumn(new LongStringConverter());
	}

	/**
	 * Create and return default CellFactory for Float
	 *
	 * @param <S> model class for data in {@link javafx.scene.control.TableView}
	 *
	 * @return default CellFactory for Float
	 */
	public static <S> Callback<TableColumn<S, Float>, TableCell<S, Float>> forFloatColumn() {
		return TextFieldTableCell.forTableColumn(new FloatStringConverter());
	}

	/**
	 * Create and return default CellFactory for Double
	 *
	 * @param <S> model class for data in {@link javafx.scene.control.TableView}
	 *
	 * @return default CellFactory for Double
	 */
	public static <S> Callback<TableColumn<S, Double>, TableCell<S, Double>> forDoubleColumn() {
		return TextFieldTableCell.forTableColumn(new DoubleStringConverter());
	}

	/**
	 * Create and return default CellFactory for Boolean
	 *
	 * @param <S> model class for data in {@link javafx.scene.control.TableView}
	 *
	 * @return default CellFactory for Boolean
	 */
	public static <S> Callback<TableColumn<S, Boolean>, TableCell<S, Boolean>> forBooleanColumn() {
		return TextFieldTableCell.forTableColumn(new BooleanStringConverter());
	}

	/**
	 * Create and return default CellFactory for Object. String converter base on {@link Object#toString()}
	 *
	 * @param <S> model class for data in {@link javafx.scene.control.TableView}
	 *
	 * @return default CellFactory for Boolean
	 *
	 * @since 0.1.6
	 */
	public static <S> Callback<TableColumn<S, Object>, TableCell<S, Object>> forObjectColumn() {
		return TextFieldTableCell.forTableColumn(new StringConverter<>() {
			@Override
			public String toString(Object object) {
				return object.toString();
			}

			@Override
			public Object fromString(String string) {
				return string;
			}
		});
	}
}

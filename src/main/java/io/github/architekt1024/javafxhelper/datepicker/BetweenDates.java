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
package io.github.architekt1024.javafxhelper.datepicker;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Function;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.DatePicker;

import org.jetbrains.annotations.NotNull;

/**
 * TODO description
 *
 * @author architekt1024
 * @since 0.1.6
 */
public final class BetweenDates {
	private BetweenDates() {
	}

	/**
	 * Allows you to select a date range. Introduces restrictions for {@link DatePicker} pair: end date cannot be earlier than start date.
	 *
	 * @param startDate {@link DatePicker}, cannot be null
	 * @param endDate   {@link DatePicker}, cannot be null
	 *
	 * @since 0.1.6
	 */
	public static void simple(@NotNull final DatePicker startDate, @NotNull final DatePicker endDate) {
		startDate.valueProperty().addListener(changeListener(Objects.requireNonNull(endDate), DateRestrictionCallback::minDate));
		endDate.valueProperty().addListener(changeListener(Objects.requireNonNull(startDate), DateRestrictionCallback::maxDate));
	}

	private static ChangeListener<LocalDate> changeListener(final DatePicker datePicker,
															final Function<LocalDate, DateRestrictionCallback> function) {
		return (observable, oldValue, newValue) -> {
			if (newValue != null) {
				datePicker.setDayCellFactory(function.apply(newValue));
			}
		};
	}

}

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
package io.github.architekt1024.javafxhelper.datepicker;

import java.time.LocalDate;
import java.util.function.Function;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.DatePicker;

import io.github.architekt1024.javafxhelper.annotation.Nonnull;

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
	 * TODO description
	 * <p>Example usage:<br>
	 * TODO
	 * </p>
	 *
	 * @param startDate {@link DatePicker}
	 * @param endDate   {@link DatePicker}
	 *
	 * @since 0.1.6
	 */
	public static void simple(@Nonnull final DatePicker startDate, @Nonnull final DatePicker endDate) {
		startDate.valueProperty().addListener(changeListener(endDate, DateRestrictionCallback::minDate));
		endDate.valueProperty().addListener(changeListener(startDate, DateRestrictionCallback::maxDate));
	}

	private static ChangeListener<LocalDate> changeListener(@Nonnull final DatePicker datePicker,
															@Nonnull final Function<LocalDate, DateRestrictionCallback> function) {
		return (observable, oldValue, newValue) -> {
			if (newValue != null) {
				datePicker.setDayCellFactory(function.apply(newValue));
			}
		};
	}

}

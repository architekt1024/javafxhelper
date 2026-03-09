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
package io.github.architekt1024.javafxhelper.datepicker;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Function;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.DatePicker;

import org.jetbrains.annotations.NotNull;

/**
 * Utility methods for configuring pairs of {@link DatePicker} controls.
 *
 * <p>Provides helpers for creating a date range selector from two {@link DatePicker} instances.</p>
 *
 * <p>The pickers are mutually constrained so that the start date is always strictly before the end date.</p>
 *
 * @author architekt1024
 * @since 0.1.6
 */
public final class BetweenDates {
	private BetweenDates() {
		throw new AssertionError("Utility class");
	}

	/**
	 * Configures two {@link DatePicker} controls to behave as a simple date range selector.
	 *
	 * <p>The pickers become mutually constrained so that the selected values always represent a valid interval.</p>
	 *
	 * <p><b>Invariant</b></p>
	 * <ul>
	 *   <li>{@code startDate < endDate}</li>
	 * </ul>
	 *
	 * <p>If one picker changes, the valid range of the other picker is automatically adjusted to preserve the invariant.</p>
	 *
	 * @param startDate the {@link DatePicker} representing the start of the range
	 * @param endDate   the {@link DatePicker} representing the end of the range
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

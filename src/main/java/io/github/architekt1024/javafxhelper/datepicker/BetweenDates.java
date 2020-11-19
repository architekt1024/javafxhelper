/*
 * All rights reserved.
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

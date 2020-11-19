/*
 * All rights reserved.
 */
package io.github.architekt1024.javafxhelper.datepicker;

import java.time.LocalDate;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import io.github.architekt1024.javafxhelper.annotation.Nullable;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Restrict {@link DatePicker} to choose a date range.
 *
 * @author architekt1024
 * @since 0.1.6
 */
public class DateRestrictionCallback implements Callback<DatePicker, DateCell> {
	private static final Logger LOG = LoggerFactory.getLogger(DateRestrictionCallback.class);

	private final LocalDate minDate;
	private final LocalDate maxDate;

	/**
	 * TODO description
	 *
	 * @param minDate start date limit
	 * @param maxDate end date limit
	 */
	public DateRestrictionCallback(@Nullable LocalDate minDate, @Nullable LocalDate maxDate) {
		if (!ObjectUtils.anyNotNull(minDate, maxDate)) {
			LOG.warn("minDate and maxDate is null, no restriction was set");
		}
		this.minDate = minDate;
		this.maxDate = maxDate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DateCell call(DatePicker param) {
		return new DateRestrictionCell();
	}

	/**
	 * Return minDate. For test only.
	 *
	 * @return min date
	 */
	LocalDate getMinDate() {
		return minDate;
	}

	/**
	 * Return maxDate. For test only.
	 *
	 * @return max date
	 */
	LocalDate getMaxDate() {
		return maxDate;
	}

	/**
	 * TODO description
	 *
	 * @param minDate start date limit
	 *
	 * @return
	 *
	 * @since 0.1.6
	 */
	public static DateRestrictionCallback minDate(@Nullable LocalDate minDate) {
		return new DateRestrictionCallback(minDate, null);
	}

	/**
	 * TODO description
	 *
	 * @param maxDate end date limit
	 *
	 * @return
	 *
	 * @since 0.1.6
	 */
	public static DateRestrictionCallback maxDate(@Nullable LocalDate maxDate) {
		return new DateRestrictionCallback(null, maxDate);
	}

	/**
	 * TODO description
	 *
	 * @return
	 *
	 * @since 0.1.6
	 */
	public static DateRestrictionCallback feature() {
		return new DateRestrictionCallback(LocalDate.now(), null);
	}

	/**
	 * TODO description
	 *
	 * @return
	 *
	 * @since 0.1.6
	 */
	public static DateRestrictionCallback past() {
		return new DateRestrictionCallback(null, LocalDate.now());
	}

	private class DateRestrictionCell extends DateCell {
		@Override
		public void updateItem(LocalDate item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty && item != null) {
				setDisable(validateDate(item));
			}
		}

		private boolean validateDate(LocalDate item) {
			if (maxDate != null && item.isAfter(maxDate)) {
				return true;
			}
			return minDate != null && item.isBefore(minDate);
		}
	}
}

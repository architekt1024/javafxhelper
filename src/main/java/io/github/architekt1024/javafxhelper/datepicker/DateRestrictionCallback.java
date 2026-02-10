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

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Restrict {@link DatePicker} to choose a date range.
 *
 * @author architekt1024
 * @since 0.1.6
 */
@SuppressWarnings("ClassCanBeRecord")
public class DateRestrictionCallback implements Callback<DatePicker, DateCell> {
	private static final Logger LOG = LoggerFactory.getLogger(DateRestrictionCallback.class);

	private final LocalDate minDate;
	private final LocalDate maxDate;

	/**
	 * TODO description
	 * If both {@code min} and {@code max} are {@code null}, this restriction has no effect.
	 *
	 * @param minDate start date limit, may be {@code null}
	 * @param maxDate end date limit, may be {@code null}
	 */
	public DateRestrictionCallback(@Nullable LocalDate minDate, @Nullable LocalDate maxDate) {
		if (ObjectUtils.allNull(minDate, maxDate)) {
			LOG.info("Date restriction has no effect because minDate and maxDate is null");
		}
		this.minDate = minDate;
		this.maxDate = maxDate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DateCell call(DatePicker param) {
		return new DateRestrictionCell(minDate, maxDate);
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
	 * @return date restriction callback
	 *
	 * @since 0.1.6
	 */
	public static DateRestrictionCallback minDate(LocalDate minDate) {
		return new DateRestrictionCallback(minDate, null);
	}

	/**
	 * TODO description
	 *
	 * @param maxDate end date limit
	 *
	 * @return date restriction callback
	 *
	 * @since 0.1.6
	 */
	public static DateRestrictionCallback maxDate(LocalDate maxDate) {
		return new DateRestrictionCallback(null, maxDate);
	}

	/**
	 * TODO description
	 *
	 * @return date restriction callback
	 *
	 * @since 0.1.6
	 */
	public static DateRestrictionCallback feature() {
		return new DateRestrictionCallback(LocalDate.now(), null);
	}

	/**
	 * TODO description
	 *
	 * @return date restriction callback
	 *
	 * @since 0.1.6
	 */
	public static DateRestrictionCallback past() {
		return new DateRestrictionCallback(null, LocalDate.now());
	}

	private static class DateRestrictionCell extends DateCell {
		private final LocalDate minDate;
		private final LocalDate maxDate;

		public DateRestrictionCell(LocalDate minDate, LocalDate maxDate) {
			this.minDate = minDate;
			this.maxDate = maxDate;
		}

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

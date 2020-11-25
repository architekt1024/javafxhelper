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
package io.github.architekt1024.javafxhelper.datepicker.dev;

import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.DatePicker;

import org.apache.commons.lang3.ObjectUtils;

//TODO
//public
class BetweenDates2 {
	private LocalDate startBefore;
	private LocalDate startAfter;
	private LocalDate endBefore;
	private LocalDate endAfter;

	public BetweenDates2 withStartBefore(LocalDate startBefore) {
		this.startBefore = startBefore;
		return this;
	}

	public BetweenDates2 withStartAfter(LocalDate startAfter) {
		this.startAfter = startAfter;
		return this;
	}

	public BetweenDates2 withEndBefore(LocalDate endBefore) {
		this.endBefore = endBefore;
		return this;
	}

	public BetweenDates2 withEndAfter(LocalDate endAfter) {
		this.endAfter = endAfter;
		return this;
	}

	public void run(DatePicker startDate, DatePicker endDate) {
		startDate.valueProperty().addListener(startDateChangeListener(endDate));
		endDate.valueProperty().addListener(endDateChangeListener(startDate));
	}

	private ChangeListener<LocalDate> startDateChangeListener(DatePicker endDate) {
		return (observable, oldValue, newValue) -> {
			if (ObjectUtils.allNotNull(endDate.getValue(), newValue)) {
				if (startBefore != null) {
					//TODO
				}
				if (startAfter != null) {
					//TODO
				}
				//TODO
//				endDate.setDayCellFactory(DateRestrictionCallback.minDate(newValue));
			}
		};
	}

	private ChangeListener<LocalDate> endDateChangeListener(DatePicker startDate) {
		return (observable, oldValue, newValue) -> {
			if (ObjectUtils.allNotNull(startDate.getValue(), newValue)) {
				if (endBefore != null) {
					//TODO
				}
				if (endAfter != null) {
					//TODO
				}
				//TODO
//				startDate.setDayCellFactory(DateRestrictionCallback.maxDate(newValue));
			}
		};
	}
}

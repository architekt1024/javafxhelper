/*
 * All rights reserved.
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

/*
 * All rights reserved.
 */
package io.github.architekt1024.javafxhelper.datepicker;

import java.time.LocalDate;
import java.util.stream.Stream;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import io.github.architekt1024.javafxhelper.JavafxViewTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.mock;

public class DateRestrictionCallbackTest extends JavafxViewTest {
	private static final LocalDate MIN_DATE = LocalDate.of(2016, 6, 5);
	private static final LocalDate MAX_DATE = LocalDate.of(2017, 10, 20);

	private DateRestrictionCallback dateRestrictionCallback;

	@BeforeEach
	public void setUp() {
		dateRestrictionCallback = new DateRestrictionCallback(MIN_DATE, MAX_DATE);
	}

	@ParameterizedTest
	@Disabled("problem with DatePicker mock")
	@MethodSource("provideValidDates")
	void callValidDates(LocalDate date) {
		// given
		DatePicker datePickerMock = mock(DatePicker.class); //TODO fix me
		DateCell cell = dateRestrictionCallback.call(datePickerMock);

		// when
		cell.updateItem(date, false);

		// then
		assertFalse(cell.disabledProperty().get());
	}

	private static Stream<Arguments> provideValidDates() {
		return Stream.of(
				Arguments.of(LocalDate.of(2016, 7, 1)),
				Arguments.of(LocalDate.of(2017, 1, 1)),
				Arguments.of(MIN_DATE),
				Arguments.of(MAX_DATE)
		);
	}

	@ParameterizedTest
	@Disabled("problem with DatePicker mock")
	@MethodSource("provideNotValidDates")
	void callNotValidDates(LocalDate date) {
		// given
		DatePicker datePickerMock = mock(DatePicker.class); //TODO fix me
		DateCell cell = dateRestrictionCallback.call(datePickerMock);

		// when
		cell.updateItem(date, false);

		// then
		assertTrue(cell.disabledProperty().get());
	}

	public static Stream<Arguments> provideNotValidDates() {
		return Stream.of(
				Arguments.of(LocalDate.of(2012, 7, 1)),
				Arguments.of(LocalDate.of(2021, 1, 1)),
				Arguments.of(MIN_DATE.minusDays(1)),
				Arguments.of(MAX_DATE.plusDays(1))
		);
	}

	@Test
	void minDate() {
		//when
		DateRestrictionCallback callback = DateRestrictionCallback.minDate(MIN_DATE);

		//then
		assertEquals(MIN_DATE, callback.getMinDate());
		assertNull(callback.getMaxDate());
	}

	@Test
	void maxDate() {
		//when
		DateRestrictionCallback callback = DateRestrictionCallback.maxDate(MAX_DATE);

		//then
		assertNull(callback.getMinDate());
		assertEquals(MAX_DATE, callback.getMaxDate());
	}

	@Test
	void feature() {
		//when
		DateRestrictionCallback callback = DateRestrictionCallback.feature();

		//then
		assertEquals(LocalDate.now(), callback.getMinDate());
		assertNull(callback.getMaxDate());
	}

	@Test
	void past() {
		//when
		DateRestrictionCallback callback = DateRestrictionCallback.past();

		//then
		assertNull(callback.getMinDate());
		assertEquals(LocalDate.now(), callback.getMaxDate());
	}
}

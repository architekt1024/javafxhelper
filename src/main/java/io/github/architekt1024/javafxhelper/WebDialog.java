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

package io.github.architekt1024.javafxhelper;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

import io.github.architekt1024.javafxhelper.annotation.Experimental;

/**
 * TODO description
 *
 * @author architekt1024
 * @see DialogFacade
 * @since 0.1.8
 */
@Experimental
public final class WebDialog {
	private WebDialog() {
	}

	/**
	 * Create and show alert with HTML. For null {@code type} will be used {@link javafx.scene.control.Alert.AlertType#NONE}
	 *
	 * @param type         alert type, for null will be used {@link Alert.AlertType#NONE}
	 * @param title        dialog title
	 * @param htmlText     HTML text to show in the dialog content area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 * @param buttons      types of buttons that the alert should contain
	 *
	 * @return button clicked by user
	 *
	 * @see DialogFacade#showDialog(Alert.AlertType, String, String, String, Window)
	 * @since 0.1.8
	 */
	@Experimental
	public static Optional<ButtonType> showHTMLAlert(Alert.AlertType type, String title, String htmlText, Window parentWindow, ButtonType... buttons) {
		final Alert htmlAlert = createHTMLAlert(type, title, htmlText, parentWindow, buttons);
		return htmlAlert.showAndWait();
	}

	/**
	 * Create new {@link Alert}. For null {@code type} must use {@code buttons} otherwise, the alert cannot be closed.
	 *
	 * @param type         alert type, for null will be used {@link Alert.AlertType#NONE}
	 * @param title        dialog title
	 * @param htmlText     HTML to show in the dialog content area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 * @param buttons      types of buttons that the alert should contain
	 *
	 * @return created alert
	 *
	 * @see DialogFacade#createAlert(Alert.AlertType, String, String, String, Window, ButtonType...)
	 * @since 0.1.8
	 */
	@Experimental
	public static Alert createHTMLAlert(Alert.AlertType type, String title, String htmlText, Window parentWindow, ButtonType... buttons) {
		return new AlertBuilder()
			.setAlertType(type)
			.setTitle(title)
			.setHtmlText(htmlText)
			.setParentWindow(parentWindow)
			.setButtons(buttons)
			.build();
	}
}

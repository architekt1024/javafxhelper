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

package io.github.architekt1024.javafxhelper.dialog;

import io.github.architekt1024.javafxhelper.AlertBuilder;
import io.github.architekt1024.javafxhelper.DialogFacade;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Window;

import java.util.Optional;

import static io.github.architekt1024.javafxhelper.DialogFacade.Buttons.YES_NO;
import static io.github.architekt1024.javafxhelper.DialogFacade.Buttons.YES_NO_CANCEL;


/**
 * Create and show predefined dialogs ({@link Alert}, {@link TextInputDialog}).
 *
 * @author architekt1024
 * @see DialogFacade
 * @see AlertBuilder
 * @since 0.1.10
 */
public class DialogService {
	private final Window parentWindow;
	private final String title;

	/**
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 * @param title        dialog title
	 *
	 * @since 0.1.10
	 */
	public DialogService(String title, Window parentWindow) {
		this.title = title;
		this.parentWindow = parentWindow;
	}

	/**
	 * Create and show alert. For null {@code type} will be used {@link javafx.scene.control.Alert.AlertType#NONE}
	 *
	 * @param type        alert type
	 * @param contentText text to show in the dialog content area
	 * @param headerText  text to show in the dialog header area
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<ButtonType> showDialog(Alert.AlertType type, String contentText, String headerText) {
		return DialogFacade.showDialog(type, title, contentText, headerText, parentWindow);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#INFORMATION} dialog.
	 *
	 * @param contentText text to show in the dialog content area
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<ButtonType> showInfoDialog(String contentText) {
		return showInfoDialog(contentText, null);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#INFORMATION} dialog.
	 *
	 * @param contentText text to show in the dialog content area
	 * @param headerText  text to show in the dialog header area
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<ButtonType> showInfoDialog(String contentText, String headerText) {
		return showDialog(Alert.AlertType.INFORMATION, contentText, headerText);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#WARNING} dialog.
	 *
	 * @param contentText text to show in the dialog content area
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<ButtonType> showWarningDialog(String contentText) {
		return showWarningDialog(contentText, null);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#WARNING} dialog.
	 *
	 * @param contentText text to show in the dialog content area
	 * @param headerText  text to show in the dialog header area
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<ButtonType> showWarningDialog(String contentText, String headerText) {
		return showDialog(Alert.AlertType.WARNING, contentText, headerText);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#ERROR} dialog.
	 *
	 * @param contentText text to show in the dialog content area
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<ButtonType> showErrorDialog(String contentText) {
		return showErrorDialog(contentText, null);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#ERROR} dialog.
	 *
	 * @param contentText text to show in the dialog content area
	 * @param headerText  text to show in the dialog header area
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<ButtonType> showErrorDialog(String contentText, String headerText) {
		return showDialog(Alert.AlertType.ERROR, contentText, headerText);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#CONFIRMATION} dialog with Yes/No buttons.
	 * <p></p>
	 * Example usage: <pre>{@code
	 * dialogService.showYesNoConfirmDialog(name).ifPresent(buttonType -> {
	 *     if (ButtonType.YES.equals(buttonType)) {
	 * 	     //action when user click YES
	 *     } else {
	 * 	     //action when user click NO
	 *     }
	 * });
	 * }</pre>
	 *
	 * @param contentText text to show in the dialog content area
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<ButtonType> showYesNoConfirmDialog(String contentText) {
		return showYesNoConfirmDialog(contentText, null);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#CONFIRMATION} dialog with Yes/No buttons.
	 * <p></p>
	 * Example usage: <pre>{@code
	 * dialogService.showYesNoConfirmDialog(name).ifPresent(buttonType -> {
	 *     if (ButtonType.YES.equals(buttonType)) {
	 * 	     //action when user click YES
	 *     } else {
	 * 	     //action when user click NO
	 *     }
	 * });
	 * }</pre>
	 *
	 * @param contentText text to show in the dialog content area
	 * @param headerText  text to show in the dialog header area
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<ButtonType> showYesNoConfirmDialog(String contentText, String headerText) {
		Alert alert = new AlertBuilder()
			.setAlertType(Alert.AlertType.CONFIRMATION)
			.setTitle(title)
			.setContentText(contentText)
			.setHeaderText(headerText)
			.setParentWindow(parentWindow)
			.setButtons(YES_NO)
			.build();
		return alert.showAndWait();
	}

	/**
	 * Create and show {@link TextInputDialog} dialog without default value
	 *
	 * @param headerText text to show in the dialog header area
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<String> showTextInputDialog(String headerText) {
		return showTextInputDialog("", headerText);
	}

	/**
	 * Create and show {@link TextInputDialog} dialog without default value
	 *
	 * @param contentText text to show in the dialog content area
	 * @param headerText  text to show in the dialog header area
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<String> showTextInputDialog(String contentText, String headerText) {
		return showTextInputDialog(contentText, headerText, "");
	}

	/**
	 * Create and show {@link TextInputDialog} dialog
	 *
	 * @param contentText  text to show in the dialog content area
	 * @param headerText   text to show in the dialog header area
	 * @param defaultValue default value entered into the dialog
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<String> showTextInputDialog(String contentText, String headerText, String defaultValue) {
		return DialogFacade.showTextInputDialog(title, contentText, headerText, parentWindow, defaultValue);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#CONFIRMATION} dialog with Yes, No, Cancel buttons.
	 *
	 * @param contentText text to show in the dialog content area
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<ButtonType> showYesNoCancelDialog(String contentText) {
		return showYesNoCancelDialog(contentText, null);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#CONFIRMATION} dialog with Yes, No, Cancel buttons.
	 *
	 * @param contentText text to show in the dialog content area
	 * @param headerText  text to show in the dialog header area
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public Optional<ButtonType> showYesNoCancelDialog(String contentText, String headerText) {
		return new AlertBuilder()
			.setAlertType(Alert.AlertType.CONFIRMATION)
			.setTitle(title)
			.setContentText(contentText)
			.setHeaderText(headerText)
			.setParentWindow(parentWindow)
			.setButtons(YES_NO_CANCEL)
			.buildAndShow();
	}
}

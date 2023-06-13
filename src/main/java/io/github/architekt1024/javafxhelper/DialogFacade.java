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

import java.util.Objects;
import java.util.Optional;

import io.github.architekt1024.javafxhelper.internal.InternalUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Window;

/**
 * Create and show predefined dialogs ({@link Alert}, {@link TextInputDialog}).
 *
 * @author architekt1024
 */
public final class DialogFacade {
	private DialogFacade() {
		InternalUtils.utilsClassConstructor();
	}

	/**
	 * Create new {@link Alert}. For null {@code type} must use {@code buttons} otherwise, the alert cannot be closed.
	 *
	 * @param type         alert type, for null will be used {@link javafx.scene.control.Alert.AlertType#NONE}
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param headerText   text to show in the dialog header area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 * @param buttons      types of buttons that the alert should contain
	 *
	 * @return created alert
	 */
	public static Alert createAlert(Alert.AlertType type, String title, String contentText, String headerText, Window parentWindow,
									ButtonType... buttons) {
		final Alert alert = new Alert(type, contentText, buttons);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.initOwner(parentWindow);
		return alert;
	}

	/**
	 * Create and show alert. For null {@code type} will be used {@link javafx.scene.control.Alert.AlertType#NONE}
	 *
	 * @param type         alert type
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param headerText   text to show in the dialog header area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return optional dialog result
	 */
	public static Optional<ButtonType> showDialog(Alert.AlertType type, String title, String contentText, String headerText, Window parentWindow) {

		Alert alert = createAlert(Objects.requireNonNullElse(type, Alert.AlertType.NONE), title, contentText, headerText, parentWindow);
		if (type == null || type == Alert.AlertType.NONE) {
			alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
		}
		alert.initOwner(parentWindow);
		return alert.showAndWait();
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#INFORMATION} dialog.
	 *
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return optional dialog result
	 */
	public static Optional<ButtonType> showInfoDialog(String title, String contentText, Window parentWindow) {
		return showInfoDialog(title, contentText, null, parentWindow);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#INFORMATION} dialog.
	 *
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param headerText   text to show in the dialog header area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return optional dialog result
	 */
	public static Optional<ButtonType> showInfoDialog(String title, String contentText, String headerText, Window parentWindow) {
		return showDialog(Alert.AlertType.INFORMATION, title, contentText, headerText, parentWindow);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#WARNING} dialog.
	 *
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return optional dialog result
	 */
	public static Optional<ButtonType> showWarningDialog(String title, String contentText, Window parentWindow) {
		return showWarningDialog(title, contentText, null, parentWindow);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#WARNING} dialog.
	 *
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param headerText   text to show in the dialog header area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return optional dialog result
	 */
	public static Optional<ButtonType> showWarningDialog(String title, String contentText, String headerText, Window parentWindow) {
		return showDialog(Alert.AlertType.WARNING, title, contentText, headerText, parentWindow);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#ERROR} dialog.
	 *
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return optional dialog result
	 */
	public static Optional<ButtonType> showErrorDialog(String title, String contentText, Window parentWindow) {
		return showErrorDialog(title, contentText, null, parentWindow);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#ERROR} dialog.
	 *
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param headerText   text to show in the dialog header area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return optional dialog result
	 */
	public static Optional<ButtonType> showErrorDialog(String title, String contentText, String headerText, Window parentWindow) {
		return showDialog(Alert.AlertType.ERROR, title, contentText, headerText, parentWindow);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#CONFIRMATION} dialog with Yes/No buttons.
	 *
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return optional dialog result
	 */
	public static Optional<ButtonType> showYesNoConfirmDialog(String title, String contentText, Window parentWindow) {
		return showYesNoConfirmDialog(title, contentText, null, parentWindow);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#CONFIRMATION} dialog with Yes/No buttons.
	 *
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param headerText   text to show in the dialog header area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return optional dialog result
	 */
	public static Optional<ButtonType> showYesNoConfirmDialog(String title, String contentText, String headerText, Window parentWindow) {
		Alert alert = createAlert(Alert.AlertType.CONFIRMATION, title, contentText, headerText, parentWindow, ButtonType.YES, ButtonType.NO);
		return alert.showAndWait();
	}

	/**
	 * Create and show {@link TextInputDialog} dialog
	 *
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param headerText   text to show in the dialog header area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 * @param defaultValue default value entered into the dialog
	 *
	 * @return optional dialog result
	 */
	public static Optional<String> showTextInputDialog(String title, String contentText, String headerText, Window parentWindow, String defaultValue) {
		TextInputDialog dialog = new TextInputDialog(defaultValue);
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.setContentText(contentText);
		dialog.initOwner(parentWindow);
		return dialog.showAndWait();
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#CONFIRMATION} dialog with Yes, No, Cancel buttons.
	 *
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return optional dialog result
	 *
	 * @since 0.1.4
	 */
	public static Optional<ButtonType> showYesNoCancelDialog(String title, String contentText, Window parentWindow) {
		return showYesNoCancelDialog(title, contentText, null, parentWindow);
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#CONFIRMATION} dialog with Yes, No, Cancel buttons.
	 *
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param headerText   text to show in the dialog header area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return optional dialog result
	 *
	 * @since 0.1.4
	 */
	public static Optional<ButtonType> showYesNoCancelDialog(String title, String contentText, String headerText,
															 Window parentWindow) {
		Alert alert = createAlert(Alert.AlertType.CONFIRMATION, title, contentText, headerText, parentWindow, ButtonType.YES, ButtonType.NO,
			ButtonType.CANCEL);
		return alert.showAndWait();
	}
}

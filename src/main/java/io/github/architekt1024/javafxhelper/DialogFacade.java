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
package io.github.architekt1024.javafxhelper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import io.github.architekt1024.javafxhelper.tableview.PairTableRecord;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Window;

import io.github.architekt1024.javafxhelper.dialog.DialogService;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * Create and show predefined dialogs ({@link Alert}, {@link TextInputDialog}).
 *
 * @author architekt1024
 * @see DialogService
 * @see AlertBuilder
 */
public final class DialogFacade {

	private DialogFacade() {
		throw new AssertionError("Utility class");
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
	 *
	 * @deprecated since 0.1.10, will be removed in 0.1.13. Use {{@link AlertBuilder}} instead.
	 */
	@ApiStatus.ScheduledForRemoval(inVersion = "0.1.13")
	@Deprecated(since = "0.1.10", forRemoval = true)
	public static Alert createAlert(Alert.AlertType type, String title, String contentText, String headerText, Window parentWindow,
									ButtonType... buttons) {
		return new AlertBuilder()
			.setAlertType(type)
			.setTitle(title)
			.setContentText(contentText)
			.setHeaderText(headerText)
			.setParentWindow(parentWindow)
			.setButtons(buttons)
			.build();
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
	 * @return An {@link Optional} that contains the dialog result
	 */
	public static Optional<ButtonType> showDialog(@Nullable Alert.AlertType type, String title, String contentText, String headerText, Window parentWindow) {
		Alert alert = new AlertBuilder(Objects.requireNonNullElse(type, Alert.AlertType.NONE))
			.setTitle(title)
			.setContentText(contentText)
			.setHeaderText(headerText)
			.setParentWindow(parentWindow)
			.build();
		if (type == null || type == Alert.AlertType.NONE) {
			alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
		}
		return alert.showAndWait();
	}

	/**
	 * Create and show {@link javafx.scene.control.Alert.AlertType#INFORMATION} dialog.
	 *
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return An {@link Optional} that contains the dialog result
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
	 * @return An {@link Optional} that contains the dialog result
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
	 * @return An {@link Optional} that contains the dialog result
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
	 * @return An {@link Optional} that contains the dialog result
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
	 * @return An {@link Optional} that contains the dialog result
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
	 * @return An {@link Optional} that contains the dialog result
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
	 * @return An {@link Optional} that contains the dialog result
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
	 * @return An {@link Optional} that contains the dialog result
	 */
	public static Optional<ButtonType> showYesNoConfirmDialog(String title, String contentText, String headerText, Window parentWindow) {
		return new AlertBuilder(Alert.AlertType.CONFIRMATION)
			.setTitle(title)
			.setContentText(contentText)
			.setHeaderText(headerText)
			.setParentWindow(parentWindow)
			.setButtons(DialogButtons.yesNo())
			.build()
			.showAndWait();
	}

	/**
	 * Create and show {@link TextInputDialog} dialog without default value
	 *
	 * @param title        dialog title
	 * @param headerText   text to show in the dialog header area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public static Optional<String> showTextInputDialog(String title, String headerText, Window parentWindow) {
		return showTextInputDialog(title, "", headerText, parentWindow);
	}

	/**
	 * Create and show {@link TextInputDialog} dialog without default value
	 *
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param headerText   text to show in the dialog header area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.10
	 */
	public static Optional<String> showTextInputDialog(String title, String contentText, String headerText, Window parentWindow) {
		return showTextInputDialog(title, contentText, headerText, parentWindow, "");
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
	 * @return An {@link Optional} that contains the dialog result
	 */
	public static Optional<String> showTextInputDialog(String title, String contentText, String headerText, Window parentWindow, String defaultValue) {
		TextInputDialog dialog = new TextInputDialog(defaultValue);
		dialog.setTitle(title);
		if (headerText != null) {
			dialog.setHeaderText(headerText);
		}
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
	 * @return An {@link Optional} that contains the dialog result
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
	 * @return An {@link Optional} that contains the dialog result
	 *
	 * @since 0.1.4
	 */
	public static Optional<ButtonType> showYesNoCancelDialog(String title, String contentText, String headerText, Window parentWindow) {
		return new AlertBuilder(Alert.AlertType.CONFIRMATION)
			.setTitle(title)
			.setContentText(contentText)
			.setHeaderText(headerText)
			.setParentWindow(parentWindow)
			.setButtons(DialogButtons.yesNoCancel())
			.buildAndShow();
	}

	/**
	 * A pre-defined {@link ButtonType} arrays
	 *
	 * @see DialogButtons
	 * @since 0.1.10
	 * @deprecated since 0.1.12. Will be removed in 0.1.14. Use {@link DialogButtons} instead.
	 */
	@ApiStatus.ScheduledForRemoval(inVersion = "0.1.14")
	@Deprecated(since = "0.1.12", forRemoval = true)
	public static class Buttons {
		/**
		 * Buttons {@link ButtonType#YES}, {@link ButtonType#NO}
		 */
		public static final ButtonType[] YES_NO = {ButtonType.YES, ButtonType.NO};
		/**
		 * Buttons {@link ButtonType#YES}, {@link ButtonType#NO}, {@link ButtonType#CANCEL}
		 */
		public static final ButtonType[] YES_NO_CANCEL = {ButtonType.YES, ButtonType.NO, ButtonType.CANCEL};
		/**
		 * Buttons {@link ButtonType#OK}, {@link ButtonType#CANCEL}
		 */
		public static final ButtonType[] OK_CANCEL = {ButtonType.OK, ButtonType.CANCEL};

		private Buttons() {
			throw new AssertionError("Utility class");
		}
	}

	/**
	 * Predefined button sets for JavaFX dialogs.
	 *
	 * @since 0.1.12
	 */
	public static final class DialogButtons {
		private static final ButtonType[] YES_NO = {ButtonType.YES, ButtonType.NO};
		private static final ButtonType[] YES_NO_CANCEL = {ButtonType.YES, ButtonType.NO, ButtonType.CANCEL};
		private static final ButtonType[] OK_CANCEL = {ButtonType.OK, ButtonType.CANCEL};

		/**
		 * Returns a button set containing {@link ButtonType#YES}, {@link ButtonType#NO}.
		 *
		 * @return a copy of the button set
		 */
		public static ButtonType[] yesNo() {
			return YES_NO.clone();
		}

		/**
		 * Returns a button set containing {@link ButtonType#YES}, {@link ButtonType#NO}, {@link ButtonType#CANCEL}.
		 *
		 * @return a copy of the button set
		 */
		public static ButtonType[] yesNoCancel() {
			return YES_NO_CANCEL.clone();
		}

		/**
		 * Returns a button set containing {@link ButtonType#OK}, {@link ButtonType#CANCEL}.
		 *
		 * @return a copy of the button set
		 */
		public static ButtonType[] okCancel() {
			return OK_CANCEL.clone();
		}

		private DialogButtons() {
			throw new AssertionError("Utility class");
		}
	}
}

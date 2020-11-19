/*
 * All rights reserved
 */
package io.github.architekt1024.javafxhelper;

import java.util.Objects;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Window;

import io.github.architekt1024.javafxhelper.annotation.Nonnull;
import io.github.architekt1024.javafxhelper.annotation.Nullable;

/**
 * Create and show predefined dialogs.
 *
 * @author architekt1024
 */
public final class DialogFacade {
	private DialogFacade() {
	}

	/**
	 * Create new {@link Alert}.
	 *
	 * @param type         alert type ({@link javafx.scene.control.Alert.AlertType#NONE} is not supported)
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param headerText   text to show in the dialog header area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 * @param buttons      types of buttons that the alert should contain
	 *
	 * @return created alert
	 */
	public static Alert createAlert(@Nullable Alert.AlertType type, @Nullable String title, @Nullable String contentText,
									@Nullable String headerText, @Nullable Window parentWindow, @Nullable ButtonType... buttons) {
		final Alert alert = new Alert(type, contentText, buttons);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.initOwner(parentWindow);
		return alert;
	}

	/**
	 * Create and show alert.
	 *
	 * @param type         alert type ({@link javafx.scene.control.Alert.AlertType#NONE} is not supported)
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param headerText   text to show in the dialog header area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 *
	 * @return optional dialog result
	 */
	public static Optional<ButtonType> showDialog(@Nonnull Alert.AlertType type, @Nullable String title, @Nullable String contentText,
												  @Nullable String headerText, @Nullable Window parentWindow) {
		if (type == Alert.AlertType.NONE) {
			throw new UnsupportedOperationException("AlertType.NONE is not supported");
		}
		Alert alert = createAlert(Objects.requireNonNull(type), title, contentText, headerText, parentWindow);
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
	public static Optional<ButtonType> showInfoDialog(@Nullable String title, @Nullable String contentText, @Nullable Window parentWindow) {
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
	public static Optional<ButtonType> showInfoDialog(@Nullable String title, @Nullable String contentText, @Nullable String headerText,
													  @Nullable Window parentWindow) {
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
	public static Optional<ButtonType> showWarningDialog(@Nullable String title, @Nullable String contentText, @Nullable Window parentWindow) {
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
	public static Optional<ButtonType> showWarningDialog(@Nullable String title, @Nullable String contentText, @Nullable String headerText,
														 @Nullable Window parentWindow) {
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
	public static Optional<ButtonType> showErrorDialog(@Nullable String title, @Nullable String contentText, @Nullable Window parentWindow) {
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
	public static Optional<ButtonType> showErrorDialog(@Nullable String title, @Nullable String contentText, @Nullable String headerText,
													   @Nullable Window parentWindow) {
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
	public static Optional<ButtonType> showYesNoConfirmDialog(@Nullable String title, @Nullable String contentText, @Nullable Window parentWindow) {
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
	public static Optional<ButtonType> showYesNoConfirmDialog(@Nullable String title, @Nullable String contentText, @Nullable String headerText,
															  @Nullable Window parentWindow) {
		Alert alert = createAlert(Alert.AlertType.CONFIRMATION, title, contentText, headerText, parentWindow, ButtonType.YES, ButtonType.NO);
		return alert.showAndWait();
	}

	/**
	 * @param title        dialog title
	 * @param contentText  text to show in the dialog content area
	 * @param headerText   text to show in the dialog header area
	 * @param parentWindow specifies the owner {@link Window} for this dialog, or null for a top-level, unowned dialog
	 * @param defaultValue default value entered into the dialog
	 *
	 * @return optional dialog result
	 */
	public static Optional<String> showTextInputDialog(@Nullable String title, @Nullable String contentText, @Nullable String headerText,
													   @Nullable Window parentWindow, @Nullable String defaultValue) {
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
	public static Optional<ButtonType> showYesNoCancelDialog(@Nullable String title, @Nullable String contentText, @Nullable Window parentWindow) {
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
	public static Optional<ButtonType> showYesNoCancelDialog(@Nullable String title, @Nullable String contentText, @Nullable String headerText,
															 @Nullable Window parentWindow) {
		Alert alert = createAlert(Alert.AlertType.CONFIRMATION, title, contentText, headerText, parentWindow, ButtonType.YES, ButtonType.NO,
				ButtonType.CANCEL);
		return alert.showAndWait();
	}
}

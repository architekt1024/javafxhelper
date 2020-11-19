/*
 * All rights reserved.
 */
package io.github.architekt1024.javafxhelper;

import java.io.File;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import io.github.architekt1024.javafxhelper.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Show open/save file dialogs.
 *
 * @author architekt1024
 * @since 0.1.6
 */
public final class FileDialog {
	private static final Logger LOG = LoggerFactory.getLogger(FileDialog.class);
	private static final String CANCEL_CLICKED_MSG = "cancel clicked";

	private FileDialog() {
	}

	/**
	 * Show open file dialog
	 *
	 * @param stage the owner window of the displayed file dialog
	 *
	 * @return selected file or null when user cancel
	 *
	 * @since 0.1.6
	 */
	public static File showOpenFileDialog(@Nullable Stage stage) {
		return showOpenFileDialog(stage, null, null);
	}

	/**
	 * Show open file dialog
	 *
	 * @param stage            the owner window of the displayed file dialog
	 * @param initialDirectory initial directory, null if not set
	 *
	 * @return selected file or null when user cancel
	 *
	 * @since 0.1.6
	 */
	public static File showOpenFileDialog(@Nullable Stage stage, @Nullable File initialDirectory) {
		FileChooser fileChooser = new FileChooser();
		if (initialDirectory != null) {
			fileChooser.setInitialDirectory(initialDirectory);
		}
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile == null) {
			LOG.debug(CANCEL_CLICKED_MSG);
		}
		return selectedFile;
	}

	/**
	 * Show open file dialog
	 *
	 * @param stage            the owner window of the displayed file dialog
	 * @param extensionFilters extension filters
	 *
	 * @return selected file or null when user cancel
	 *
	 * @since 0.1.6
	 */
	public static File showOpenFileDialog(@Nullable Stage stage, @Nullable FileChooser.ExtensionFilter... extensionFilters) {
		return showOpenFileDialog(stage, null, extensionFilters);
	}


	/**
	 * Show open file dialog
	 *
	 * @param stage            the owner window of the displayed file dialog
	 * @param initialDirectory initial directory, null if not set
	 * @param extensionFilters extension filters
	 *
	 * @return selected file or null when user cancel
	 *
	 * @since 0.1.6
	 */
	public static File showOpenFileDialog(@Nullable Stage stage, @Nullable File initialDirectory,
										  @Nullable FileChooser.ExtensionFilter... extensionFilters) {
		final FileChooser fileChooser = new FileChooser();
		if (initialDirectory != null) {
			fileChooser.setInitialDirectory(initialDirectory);
		}
		if (extensionFilters != null) {
			fileChooser.getExtensionFilters().addAll(extensionFilters);
		}
		final File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile == null) {
			LOG.debug(CANCEL_CLICKED_MSG);
		}
		return selectedFile;
	}

	/**
	 * Show open directory dialog
	 *
	 * @param stage            the owner window of the displayed file dialog
	 * @param initialDirectory initial directory, null if not set
	 *
	 * @return selected directory or null when user cancel
	 *
	 * @since 0.1.6
	 */
	public static File showOpenDirectoryDialog(@Nullable Stage stage, @Nullable File initialDirectory) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		if ((initialDirectory != null)) {
			directoryChooser.setInitialDirectory(initialDirectory);
		}

		File selectedDirectory = directoryChooser.showDialog(stage);
		if (selectedDirectory == null) {
			LOG.debug(CANCEL_CLICKED_MSG);
		}
		return selectedDirectory;
	}

	/**
	 * Show save dialog
	 *
	 * @param stage the owner window of the displayed file dialog
	 *
	 * @return selected file or null when user cancel
	 *
	 * @since 0.1.6
	 */
	public static File showSaveDialog(@Nullable Stage stage) {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showSaveDialog(stage);
		if (selectedFile == null) {
			LOG.debug(CANCEL_CLICKED_MSG);
		}
		return selectedFile;
	}

	/**
	 * Show save dialog
	 *
	 * @param stage           the owner window of the displayed file dialog
	 * @param initialFileName set initial file name
	 *
	 * @return selected file or null when user cancel
	 *
	 * @since 0.1.6
	 */
	public static File showSaveDialog(@Nullable Stage stage, @Nullable String initialFileName) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialFileName(initialFileName);
		File selectedFile = fileChooser.showSaveDialog(stage);
		if (selectedFile == null) {
			LOG.debug(CANCEL_CLICKED_MSG);
		}
		return selectedFile;
	}
}

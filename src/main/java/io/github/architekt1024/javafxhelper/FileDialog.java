/*
 * Copyright 2020-2025 architekt1024
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

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Show open/save file dialogs.
 *
 * @author architekt1024
 * @since 0.1.6
 * @deprecated 0.1.11 will be removed in 0.1.13, moved to {@link io.github.architekt1024.javafxhelper.dialog.FileDialog}
 */
@Deprecated(since = "0.1.11", forRemoval = true)
@ApiStatus.ScheduledForRemoval(inVersion = "0.1.13")
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
	public static File showOpenFileDialog(Stage stage) {
		return showOpenFileDialog(stage, (File) null);
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
	public static File showOpenFileDialog(Stage stage, File initialDirectory) {
		return showOpenFileDialog(stage, initialDirectory, (FileChooser.ExtensionFilter) null);
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
	public static File showOpenFileDialog(Stage stage, FileChooser.ExtensionFilter... extensionFilters) {
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
	public static File showOpenFileDialog(Stage stage, File initialDirectory, FileChooser.ExtensionFilter... extensionFilters) {
		final FileChooser fileChooser = new FileChooser();
		if (initialDirectory != null) {
			fileChooser.setInitialDirectory(initialDirectory);
		}
		if (extensionFilters != null) {
			fileChooser.getExtensionFilters().addAll(Arrays.stream(extensionFilters)
				.filter(Objects::nonNull)
				.collect(Collectors.toList()));
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
	public static File showOpenDirectoryDialog(Stage stage, File initialDirectory) {
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
	public static File showSaveDialog(Stage stage) {
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
	public static File showSaveDialog(Stage stage, String initialFileName) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialFileName(initialFileName);
		File selectedFile = fileChooser.showSaveDialog(stage);
		if (selectedFile == null) {
			LOG.debug(CANCEL_CLICKED_MSG);
		}
		return selectedFile;
	}
}

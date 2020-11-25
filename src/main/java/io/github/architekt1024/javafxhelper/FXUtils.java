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

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.function.Consumer;
import java.util.function.Function;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import io.github.architekt1024.javafxhelper.annotation.Nonnull;
import io.github.architekt1024.javafxhelper.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.architekt1024.javafxhelper.stage.FXMLController;
import io.github.architekt1024.javafxhelper.stage.StageUtils;

/**
 * Common tools.
 *
 * @author architekt1024
 */
public final class FXUtils {
	private static final Logger LOG = LoggerFactory.getLogger(FXUtils.class);

	private FXUtils() {
	}

	/**
	 * Add listener to spinner to update values after editing.
	 *
	 * @param spinnerArr spinner array to update
	 */
	public static void updateSpinnerValue(Spinner<?>... spinnerArr) {
		for (final Spinner<?> spinner : spinnerArr) {
			spinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
				if (!newValue) {
					spinner.increment(0);
				}
			});
		}
	}

	/**
	 * Change selection mode used in selection model
	 *
	 * @param selectionMode selection mode used in selection model
	 * @param views         list of {@link TreeView}
	 *
	 * @since 0.1.8
	 */
	public static void updateSelectionMode(@Nonnull SelectionMode selectionMode, @Nonnull TreeView<?>... views) {
		for (TreeView<?> treeView : views) {
			if (treeView.getSelectionModel() != null) {
				treeView.getSelectionModel().setSelectionMode(selectionMode);
			}
		}
	}

	/**
	 * Change selection mode used in selection model
	 *
	 * @param selectionMode selection mode used in selection model
	 * @param views         list of {@link ListView}
	 *
	 * @since 0.1.8
	 */
	public static void updateSelectionMode(@Nonnull SelectionMode selectionMode, @Nonnull ListView<?>... views) {
		for (ListView<?> listView : views) {
			if (listView.getSelectionModel() != null) {
				listView.getSelectionModel().setSelectionMode(selectionMode);
			}
		}
	}

	/**
	 * Change selection mode used in selection model
	 *
	 * @param selectionMode selection mode used in selection model
	 * @param views         list of {@link TableView}
	 *
	 * @since 0.1.8
	 */
	public static void updateSelectionMode(@Nonnull SelectionMode selectionMode, @Nonnull TableView<?>... views) {
		for (TableView<?> tableView : views) {
			if (tableView.getSelectionModel() != null) {
				tableView.getSelectionModel().setSelectionMode(selectionMode);
			}
		}
	}


	/**
	 * Load main stage. If controller implements {@link FXMLController}, it set stage.
	 *
	 * @param fxml        FXML {@link URL}
	 * @param stage       JavaFX stage
	 * @param title       window title
	 * @param stylesheets stylesheet file path
	 *
	 * @throws IOException fail load FXML file
	 * @deprecated deprecated from 0.1.6, removed in 0.1.9, you should use {@link StageUtils#loadMainStage(URL, Stage, String, String)}
	 */
	@Deprecated
	public static void loadMainStage(@Nonnull URL fxml, @Nonnull Stage stage, @Nonnull String title, @Nullable String stylesheets) throws IOException {
		StageUtils.loadMainStage(fxml, stage, title, stylesheets);
	}

	/**
	 * TODO description
	 * If controller implements {@link FXMLController}, it set stage.
	 *
	 * @param fxmlLoader   fxml loader instance
	 * @param title        window title
	 * @param parentWindow parent window
	 *
	 * @return created stage
	 *
	 * @throws IOException fail load FXML file
	 * @deprecated deprecated from 0.1.6, removed in 0.1.9, you should use {@link StageUtils#loadNonResizableStage(FXMLLoader, String, Window)}
	 */
	@Deprecated
	public static Stage loadNonResizableStage(@Nonnull FXMLLoader fxmlLoader, @Nonnull String title, @Nonnull Window parentWindow) throws IOException {
		return StageUtils.loadNonResizableStage(fxmlLoader, title, parentWindow);
	}

	/**
	 * Create modal utility window and return.
	 *
	 * @param fxmlLoader   fxml loader instance
	 * @param title        window title
	 * @param parentWindow parent window
	 *
	 * @return created stage
	 *
	 * @throws IOException fail load FXML file
	 * @deprecated deprecated from 0.1.6, removed in 0.1.9, you should use {@link StageUtils#prepareUtilityWindow(FXMLLoader, String, Window, boolean)}
	 */
	@Deprecated
	public static Stage prepareUtilityWindow(@Nonnull FXMLLoader fxmlLoader, @Nonnull String title, @Nonnull Window parentWindow) throws IOException {
		return StageUtils.prepareUtilityWindow(fxmlLoader, title, parentWindow, true);
	}

	/**
	 * Show open file dialog
	 *
	 * @param stage the owner window of the displayed file dialog
	 *
	 * @return selected file or null when user cancel
	 *
	 * @deprecated deprecated from 0.1.6, removed in 0.1.9, you should use {@link FileDialog#showOpenFileDialog(Stage)}
	 */
	@Deprecated
	public static File showOpenFileDialog(@Nullable Stage stage) {
		return FileDialog.showOpenFileDialog(stage);
	}

	/**
	 * Show open file dialog
	 *
	 * @param stage            the owner window of the displayed file dialog
	 * @param initialDirectory initial directory, null if not set
	 *
	 * @return selected file or null when user cancel
	 *
	 * @deprecated deprecated from 0.1.6, removed in 0.1.9, you should use {@link FileDialog#showOpenFileDialog(Stage, File)}
	 */
	@Deprecated
	public static File showOpenFileDialog(@Nullable Stage stage, @Nullable File initialDirectory) {
		return FileDialog.showOpenFileDialog(stage, initialDirectory);
	}


	/**
	 * Show open file dialog
	 *
	 * @param stage            the owner window of the displayed file dialog
	 * @param extensionFilters extension filters
	 *
	 * @return selected file or null when user cancel
	 *
	 * @since 0.1.5
	 * @deprecated deprecated from 0.1.6, removed in 0.1.9, you should use {@link FileDialog#showOpenFileDialog(Stage, FileChooser.ExtensionFilter...)}
	 */
	@Deprecated
	public static File showOpenFileDialog(@Nullable Stage stage, @Nullable FileChooser.ExtensionFilter... extensionFilters) {
		return FileDialog.showOpenFileDialog(stage, extensionFilters);
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
	 * @since 0.1.5
	 * @deprecated deprecated from 0.1.6, removed in 0.1.9, you should use {@link FileDialog#showOpenFileDialog(Stage, File, FileChooser.ExtensionFilter...)}
	 */
	@Deprecated
	public static File showOpenFileDialog(@Nullable Stage stage, @Nullable File initialDirectory,
										  @Nullable FileChooser.ExtensionFilter... extensionFilters) {
		return FileDialog.showOpenFileDialog(stage, initialDirectory, extensionFilters);
	}

	/**
	 * Show open directory dialog
	 *
	 * @param stage            the owner window of the displayed file dialog
	 * @param initialDirectory initial directory, null if not set
	 *
	 * @return selected directory or null when user cancel
	 *
	 * @deprecated deprecated from 0.1.6, removed in 0.1.9, you should use {@link FileDialog#showOpenDirectoryDialog(Stage, File)}
	 */
	@Deprecated
	public static File showOpenDirectoryDialog(@Nullable Stage stage, @Nullable File initialDirectory) {
		return FileDialog.showOpenDirectoryDialog(stage, initialDirectory);
	}

	/**
	 * Show save dialog
	 *
	 * @param stage the owner window of the displayed file dialog
	 *
	 * @return selected file or null when user cancel
	 *
	 * @deprecated deprecated from 0.1.6, removed in 0.1.9, you should use {@link FileDialog#showSaveDialog(Stage)}
	 */
	@Deprecated
	public static File showSaveDialog(@Nullable Stage stage) {
		return FileDialog.showSaveDialog(stage);
	}

	/**
	 * Show save dialog
	 *
	 * @param stage           the owner window of the displayed file dialog
	 * @param initialFileName set initial file name
	 *
	 * @return selected file or null when user cancel
	 *
	 * @deprecated deprecated from 0.1.6, removed in 0.1.9, you should use {@link FileDialog#showSaveDialog(Stage, String)}
	 */
	@Deprecated
	public static File showSaveDialog(@Nullable Stage stage, @Nullable String initialFileName) {
		return FileDialog.showSaveDialog(stage, initialFileName);
	}

	/**
	 * Load font. Return null if fileFont is null or empty. Return null if try load not supported font. For example TrueType collection (.ttc)
	 * is supported from Java 9.
	 * <br>
	 * See https://bugs.openjdk.java.net/browse/JDK-8139838
	 *
	 * @param fileFont font file path
	 * @param size     font size
	 *
	 * @return Loaded font or null if fileFont is blank
	 */
	public static Font loadFont(@Nullable String fileFont, double size) {
		if (StringUtils.isBlank(fileFont)) {
			return null;
		}
		try {
			String fontUrl = new File(fileFont).toURI().toURL().toString();
			/* Throws NullPointerException when loading a font with a .ttc extension (TrueType collection)
			 *	Exception in thread "JavaFX Application Thread" java.lang.NullPointerException
			 *		at com.sun.javafx.font.PrismFontFactory.createFontResource(PrismFontFactory.java:333)
			 *		at com.sun.javafx.font.PrismFontFactory.loadEmbeddedFont(PrismFontFactory.java:1607)
			 * Fixed in Java 9 https://bugs.openjdk.java.net/browse/JDK-8139838
			 */
			return Font.loadFont(fontUrl, size);
		} catch (MalformedURLException ex) {
			LOG.debug("", ex);
		}
		return null;
	}

	/**
	 * Run file in default application.
	 *
	 * @param file     path to run
	 * @param consumer exception handler
	 *
	 * @throws UnsupportedOperationException Desktop is not supported at this platform
	 * @since 0.1.5
	 */
	public static void runFile(@Nonnull final String file, @Nonnull final Consumer<Exception> consumer) {
		if (!Desktop.isDesktopSupported()) {
			throw new UnsupportedOperationException("Desktop is not supported");
		}

		Thread thread = new Thread(() -> {
			try {
				Desktop.getDesktop().open(new File(file));
			} catch (Exception e) {
				Platform.runLater(() -> consumer.accept(e));
			}
		});
		thread.start();
	}

	/**
	 * Open url in default browser.
	 *
	 * @param url               url address
	 * @param exceptionConsumer exceptions consumer
	 *
	 * @see #browse(String)
	 * @since 0.1.8
	 */
	public static void browse(@Nonnull String url, @Nonnull Consumer<Exception> exceptionConsumer) {
		try {
			browse(url);
		} catch (IOException | URISyntaxException | UnsupportedOperationException e) {
			exceptionConsumer.accept(e);
		}
	}

	/**
	 * Open url in default browser
	 *
	 * @param url url address
	 *
	 * @throws IOException                   fail to run default browser
	 * @throws MalformedURLException         malformed URL
	 * @throws URISyntaxException            URL cannot be parsed as {@link java.net.URI}
	 * @throws UnsupportedOperationException Desktop is not supported at this platform
	 * @since 0.1.5
	 */
	public static void browse(@Nonnull String url) throws IOException, URISyntaxException {
		if (!Desktop.isDesktopSupported()) {
			throw new UnsupportedOperationException("Desktop is not supported");
		}
		Desktop desktop = Desktop.getDesktop();
		if (!desktop.isSupported(Desktop.Action.BROWSE)) {
			throw new UnsupportedOperationException("Browse action is not supported");
		}
		desktop.browse(new URL(url).toURI());
	}
}

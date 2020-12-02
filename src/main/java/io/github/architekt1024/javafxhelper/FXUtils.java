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

import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.text.Font;

import io.github.architekt1024.javafxhelper.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JavaFX common tools.
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
	public static Font loadFont(String fileFont, double size) {
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

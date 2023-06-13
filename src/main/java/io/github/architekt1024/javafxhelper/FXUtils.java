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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.text.Font;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
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
	 * @param spinnerArr spinner array to update, any arguments cannot be null
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
	 * @param selectionMode selection mode used in selection model, should be not null
	 * @param views         list of {@link TreeView}
	 *
	 * @since 0.1.8
	 */
	public static void updateSelectionMode(SelectionMode selectionMode, TreeView<?>... views) {
		for (TreeView<?> treeView : views) {
			if (treeView != null && treeView.getSelectionModel() != null) {
				treeView.getSelectionModel().setSelectionMode(selectionMode);
			}
		}
	}

	/**
	 * Change selection mode used in selection model
	 *
	 * @param selectionMode selection mode used in selection model, should be not null
	 * @param views         list of {@link ListView}
	 *
	 * @since 0.1.8
	 */
	public static void updateSelectionMode(SelectionMode selectionMode, ListView<?>... views) {
		for (ListView<?> listView : views) {
			if (listView != null && listView.getSelectionModel() != null) {
				listView.getSelectionModel().setSelectionMode(selectionMode);
			}
		}
	}

	/**
	 * Change selection mode used in selection model
	 *
	 * @param selectionMode selection mode used in selection model, should be not null
	 * @param views         list of {@link TableView}
	 *
	 * @since 0.1.8
	 */
	public static void updateSelectionMode(SelectionMode selectionMode, TableView<?>... views) {
		for (TableView<?> tableView : views) {
			if (tableView != null && tableView.getSelectionModel() != null) {
				tableView.getSelectionModel().setSelectionMode(selectionMode);
			}
		}
	}


	/**
	 * Change selection mode used in selection model
	 *
	 * @param selectionMode selection mode used in selection model, should be not null
	 * @param views         list of {@link TreeView}, {@link TableView}, {@link ListView}
	 *
	 * @since 0.1.9
	 */
	public static void updateSelectionMode(SelectionMode selectionMode, Control... views) {
		for (Control view : views) {
			if (view != null) {
				try {
					final Method getSelectionModel = view.getClass().getDeclaredMethod("getSelectionModel");
					final MultipleSelectionModel<?> selectionModel = (MultipleSelectionModel<?>) getSelectionModel.invoke(view);
					selectionModel.setSelectionMode(selectionMode);
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					throw new IllegalArgumentException(e);
				}
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
	 * @param file     path to run, cannot be null
	 * @param consumer exception handler, cannot be null
	 *
	 * @throws UnsupportedOperationException Desktop is not supported at this platform
	 * @since 0.1.5
	 */
	public static void runFile(@NotNull final String file, @NotNull final Consumer<Exception> consumer) {
		if (!Desktop.isDesktopSupported()) {
			throw new UnsupportedOperationException("Desktop is not supported");
		}

		Thread thread = new Thread(() -> {
			try {
				Desktop.getDesktop().open(new File(Objects.requireNonNull(file)));
			} catch (Exception e) {
				Platform.runLater(() -> Objects.requireNonNull(consumer).accept(e));
			}
		});
		thread.start();
	}

	/**
	 * Open url in default browser.
	 *
	 * @param url               url address, cannot be blank
	 * @param exceptionConsumer exceptions consumer, cannot be null
	 *
	 * @see #browse(String)
	 * @since 0.1.8
	 */
	public static void browse(@NotNull String url, @NotNull Consumer<Exception> exceptionConsumer) {
		try {
			browse(url);
		} catch (IOException | URISyntaxException | UnsupportedOperationException | IllegalArgumentException e) {
			Objects.requireNonNull(exceptionConsumer, "exceptionConsumer cannot be null, can use method #browse(String)").accept(e);
		}
	}

	/**
	 * Open url in default browser
	 *
	 * @param url url address, cannot be blank
	 *
	 * @throws IOException                   fail to run default browser
	 * @throws MalformedURLException         malformed URL
	 * @throws URISyntaxException            URL cannot be parsed as {@link java.net.URI}
	 * @throws UnsupportedOperationException Desktop is not supported at this platform
	 * @throws IllegalArgumentException      URL is blank
	 * @since 0.1.5
	 */
	public static void browse(@NotNull String url) throws IOException, URISyntaxException {
		if (StringUtils.isBlank(url)) {
			throw new IllegalArgumentException("URL cannot be blank");
		}
		if (!Desktop.isDesktopSupported()) {
			throw new UnsupportedOperationException("Desktop is not supported");
		}
		Desktop desktop = Desktop.getDesktop();
		if (!desktop.isSupported(Desktop.Action.BROWSE)) {
			throw new UnsupportedOperationException("Browse action is not supported");
		}
		desktop.browse(new URL(Objects.requireNonNull(url)).toURI());
	}
}

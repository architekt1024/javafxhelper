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
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JavaFX common tools.
 *
 * @author architekt1024
 * @see io.github.architekt1024.javafxhelper.utils.DesktopUtils
 * @see io.github.architekt1024.javafxhelper.utils.ControlUtils
 * @deprecated deprecated in 0.1.12, will be removed in 0.2.0
 */
@Deprecated(since = "0.1.12")
@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
public final class FXUtils {
	private static final Logger LOG = LoggerFactory.getLogger(FXUtils.class);

	private FXUtils() {
	}

	/**
	 * Add listener to spinner to update values after editing.
	 *
	 * @param spinnerArr spinner array to update, any arguments cannot be null
	 *
	 * @see io.github.architekt1024.javafxhelper.utils.ControlUtils#updateSpinnerValue(Spinner[])
	 * @deprecated deprecated in 0.1.12, will be removed in 0.2.0
	 */
	@Deprecated(since = "0.1.12")
	@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
	public static void updateSpinnerValue(@NotNull Spinner<?>... spinnerArr) {
		for (final Spinner<?> spinner : spinnerArr) {
			spinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
				if (Boolean.FALSE.equals(newValue)) {
					spinner.increment(0);
				}
			});
		}
	}

	/**
	 * Change selection mode used in selection model
	 *
	 * @param selectionMode selection mode used in selection model, if {@code null}, a default value is used
	 * @param views         list of {@link TreeView}
	 *
	 * @see io.github.architekt1024.javafxhelper.utils.ControlUtils#updateSelectionMode(SelectionMode, TreeView[])
	 * @since 0.1.8
	 * @deprecated deprecated in 0.1.12, will be removed in 0.2.0
	 */
	@Deprecated(since = "0.1.12")
	@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
	public static void updateSelectionMode(@NotNull SelectionMode selectionMode, @Nullable TreeView<?>... views) {
		for (TreeView<?> treeView : views) {
			if (treeView != null && treeView.getSelectionModel() != null) {
				treeView.getSelectionModel().setSelectionMode(selectionMode);
			}
		}
	}

	/**
	 * Change selection mode used in selection model
	 *
	 * @param selectionMode selection mode used in selection model, if {@code null}, a default value is used
	 * @param views         list of {@link ListView}
	 *
	 * @since 0.1.8
	 * @deprecated deprecated in 0.1.12, will be removed in 0.2.0
	 */
	@Deprecated(since = "0.1.12")
	@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
	public static void updateSelectionMode(@Nullable SelectionMode selectionMode, @Nullable ListView<?>... views) {
		for (ListView<?> listView : views) {
			if (listView != null && listView.getSelectionModel() != null) {
				listView.getSelectionModel().setSelectionMode(selectionMode);
			}
		}
	}

	/**
	 * Change selection mode used in selection model
	 *
	 * @param selectionMode selection mode used in selection model, if {@code null}, a default value is used
	 * @param views         list of {@link TableView}
	 *
	 * @since 0.1.8
	 * @deprecated deprecated in 0.1.12, will be removed in 0.2.0
	 */
	@Deprecated(since = "0.1.12")
	@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
	public static void updateSelectionMode(@Nullable SelectionMode selectionMode, @Nullable TableView<?>... views) {
		for (TableView<?> tableView : views) {
			if (tableView != null && tableView.getSelectionModel() != null) {
				tableView.getSelectionModel().setSelectionMode(selectionMode);
			}
		}
	}


	/**
	 * Change selection mode used in selection model
	 *
	 * @param selectionMode selection mode used in selection model, if {@code null}, a default value is used
	 * @param views         list of {@link TreeView}, {@link TableView}, {@link ListView}
	 *
	 * @since 0.1.9
	 * @deprecated deprecated in 0.1.12, will be removed in 0.2.0
	 */
	@Deprecated(since = "0.1.12")
	@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
	public static void updateSelectionMode(@Nullable SelectionMode selectionMode, @Nullable Control... views) {
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
	 *
	 * @see io.github.architekt1024.javafxhelper.utils.ControlUtils#loadFont(String, double)
	 * @deprecated deprecated in 0.1.12, will be removed in 0.2.0
	 */
	@Deprecated(since = "0.1.12")
	@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
	public static Font loadFont(@NotNull String fileFont, double size) {
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
	 * @see io.github.architekt1024.javafxhelper.utils.DesktopUtils#openFile(String, Consumer)
	 * @since 0.1.5
	 * @deprecated 0.1.11, will be removed in 0.2.0
	 */
	@Deprecated(since = "0.1.11", forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
	public static void runFile(@NotNull final String file, @NotNull final Consumer<Exception> consumer) {
		openFile(file, consumer);
	}

	/**
	 * Run file in default application.
	 *
	 * @param file     path to run, cannot be null
	 * @param consumer exception handler, cannot be null
	 *
	 * @throws UnsupportedOperationException Desktop is not supported at this platform
	 * @see io.github.architekt1024.javafxhelper.utils.DesktopUtils#openFile(String, Consumer)
	 * @deprecated 0.1.11, will be removed in 0.2.0
	 */
	@Deprecated(since = "0.1.11", forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
	private static void openFile(@NotNull final String file, @NotNull final Consumer<Exception> consumer) {
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
	 * @see io.github.architekt1024.javafxhelper.utils.DesktopUtils#browse(String, Consumer)
	 * @since 0.1.8
	 * @deprecated 0.1.11, will be removed in 0.2.0
	 */
	@Deprecated(since = "0.1.11", forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
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
	 * @see #browse(String, Consumer)
	 * @see io.github.architekt1024.javafxhelper.utils.DesktopUtils#browse(String)
	 * @since 0.1.5
	 * @deprecated 0.1.11, will be removed in 0.2.0
	 */
	@Deprecated(since = "0.1.11", forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
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
		desktop.browse(new URL(url).toURI());
	}
}

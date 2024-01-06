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

package io.github.architekt1024.javafxhelper.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.function.Consumer;

import javafx.application.Platform;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * TODO description
 *
 * @author architekt1024
 * @since 0.1.11
 */
public final class DesktopUtils {
	private DesktopUtils() {
	}

	/**
	 * Open file in default application.
	 *
	 * @param file     path to run, cannot be null
	 * @param consumer exception handler, cannot be null
	 *
	 * @throws UnsupportedOperationException Desktop or desktop action is not supported at this platform
	 * @since 0.1.11
	 */
	public static void openFile(@NotNull final String file, @NotNull final Consumer<Exception> consumer) {
		checkDesktopAction(Desktop.Action.OPEN);

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
	 * @since 0.1.11
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
	 * @throws UnsupportedOperationException Desktop or desktop action is not supported at this platform
	 * @throws IllegalArgumentException      URL is blank
	 * @see #browse(String, Consumer)
	 * @since 0.1.11
	 */
	public static void browse(@NotNull String url) throws IOException, URISyntaxException {
		if (StringUtils.isBlank(url)) {
			throw new IllegalArgumentException("URL cannot be blank");
		}
		Desktop desktop = checkDesktopAction(Desktop.Action.BROWSE);
		desktop.browse(new URL(url).toURI());
	}

	private static Desktop checkDesktopAction(Desktop.Action action) {
		if (!Desktop.isDesktopSupported()) {
			throw new UnsupportedOperationException("Desktop is not supported");
		}
		Desktop desktop = Desktop.getDesktop();
		if (!desktop.isSupported(action)) {
			throw new UnsupportedOperationException(action.name() + " action is not supported");
		}
		return desktop;
	}
}

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

import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import org.apache.commons.text.StringEscapeUtils;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides utilities for {@link WebEngine}.
 *
 * @author architekt1024
 * @since 0.1.4
 * @deprecated 0.1.11 will be removed in 0.1.13, moved to {@link io.github.architekt1024.javafxhelper.utils.WebUtils}
 */
@Deprecated(since = "0.1.11")
@ApiStatus.ScheduledForRemoval(inVersion = "0.1.13")
public final class WebUtils {
	private static final Logger LOG = LoggerFactory.getLogger(WebUtils.class);

	private WebUtils() {
	}

	private static Object executeScript(@NotNull final WebEngine webEngine, @NotNull final String script) {
		LOG.debug(script);
		return webEngine.executeScript(script);
	}

	/**
	 * Run JavaScript function on web engine. Escape arguments.
	 *
	 * @param webEngine WebEngine instance, cannot be null
	 * @param function  JavaScript function name, cannot be null
	 * @param args      JavaScript function args, cannot be null
	 *
	 * @return result of {@link WebEngine#executeScript(String)}
	 */
	public static Object executeScript(@NotNull final WebEngine webEngine, @NotNull final String function, @NotNull final String... args) {
		return executeScript(webEngine, prepareScript(function, args));
	}

	static String prepareScript(@NotNull String function, @NotNull String... args) {
		String filtered = Arrays.stream(args)
			.map(StringEscapeUtils::escapeEcmaScript)
			.collect(Collectors.joining("', '"));
		if (!filtered.isEmpty()) {
			filtered = '\'' + filtered + '\'';
		}
		return function + "(" + filtered + ");";
	}

	/**
	 * Load HTML content from string
	 *
	 * @param htmlText   HTML to show in the dialog content area
	 * @param prefWidth  preferred width
	 * @param prefHeight preferred height
	 *
	 * @return {@link WebView} content
	 *
	 * @since 0.1.8
	 */
	public static WebView loadContent(@NotNull String htmlText, @Nullable Double prefWidth, @Nullable Double prefHeight) {
		WebView webView = new WebView();
		webView.getEngine().loadContent(htmlText);
		if (prefWidth != null) {
			webView.setPrefWidth(prefWidth);
		}
		if (prefHeight != null) {
			webView.setPrefHeight(prefHeight);
		}
		return webView;
	}
}

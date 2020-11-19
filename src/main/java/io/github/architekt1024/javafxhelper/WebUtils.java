/*
 * All rights reserved.
 */
package io.github.architekt1024.javafxhelper;

import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.scene.web.WebEngine;

import io.github.architekt1024.javafxhelper.annotation.Nonnull;

import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides utilities for {@link WebEngine}.
 *
 * @author architekt1024
 * @since 0.1.4
 */
public final class WebUtils {
	private static final Logger LOG = LoggerFactory.getLogger(WebUtils.class);

	private WebUtils() {
	}

	private static Object executeScript(@Nonnull final WebEngine webEngine, @Nonnull final String script) {
		LOG.debug(script);
		return webEngine.executeScript(script);
	}

	/**
	 * Run JavaScript function on web engine. Escape arguments.
	 *
	 * @param webEngine WebEngine instance
	 * @param function  JavaScript function name
	 * @param args      JavaScript function args
	 *
	 * @return result of {@link WebEngine#executeScript(String)}
	 */
	public static Object executeScript(@Nonnull final WebEngine webEngine, @Nonnull final String function, @Nonnull final String... args) {
		return executeScript(webEngine, prepareScript(function, args));
	}

	static String prepareScript(@Nonnull String function, @Nonnull String... args) {
		String filtered = Arrays.stream(args)
				.map(StringEscapeUtils::escapeEcmaScript)
				.collect(Collectors.joining("', '"));
		if (!filtered.isEmpty()) {
			filtered = '\'' + filtered + '\'';
		}
		return function + "(" + filtered + ");";
	}
}

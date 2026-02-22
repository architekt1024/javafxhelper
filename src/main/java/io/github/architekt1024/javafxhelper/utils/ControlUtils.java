package io.github.architekt1024.javafxhelper.utils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

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
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Common {@link Control} utils.
 *
 * @author architekt1024
 * @since 0.1.12
 */
public final class ControlUtils {
	private static final Logger LOG = LoggerFactory.getLogger(ControlUtils.class);

	private ControlUtils() {
	}

	/**
	 * Add listener to spinner to update values after editing.
	 *
	 * @param spinnerArr spinner array to update, any arguments cannot be null
	 *
	 * @since 0.1.12
	 */
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
	 * @since 0.1.12
	 */
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
	 * @since 0.1.12
	 */
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
	 * @since 0.1.12
	 */
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
	 * @since 0.1.12
	 */
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
	 *
	 * @param fileFont font file path
	 * @param size     font size
	 *
	 * @return Loaded font or null if fileFont is blank
	 *
	 * @since 0.1.12
	 */
	public static Font loadFont(@NotNull String fileFont, double size) {
		if (StringUtils.isBlank(fileFont)) {
			return null;
		}
		try {
			String fontUrl = new File(fileFont).toURI().toURL().toString();
			return Font.loadFont(fontUrl, size);
		} catch (MalformedURLException ex) {
			LOG.debug(ex.getMessage(), ex);
		}
		return null;
	}
}

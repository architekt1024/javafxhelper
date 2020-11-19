/*
 * All rights reserved
 */
package io.github.architekt1024.javafxhelper.treeview;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

import javafx.collections.FXCollections;
import javafx.scene.control.TreeItem;

import io.github.architekt1024.javafxhelper.annotation.Nonnull;

/**
 * Common utils for {@link javafx.scene.control.TreeView}
 *
 * @author architekt1024
 * @since 0.1.5
 */
public final class TreeUtils {
	private TreeUtils() {
	}

	/**
	 * Sort treeItem with children.
	 *
	 * @param treeItem   tree item to sorting
	 * @param comparator comparator used to sorting
	 * @param <V>        The type of the {@link TreeItem#getValue() value} property within {@link TreeItem}.
	 */
	public static <V> void sortChildren(@Nonnull TreeItem<V> treeItem, @Nonnull Comparator<TreeItem<V>> comparator) {
		Deque<TreeItem<V>> treeItemsDeque = new ArrayDeque<>();
		treeItemsDeque.push(treeItem);

		while (!treeItemsDeque.isEmpty()) {
			TreeItem<V> item = treeItemsDeque.pop();
			FXCollections.sort(item.getChildren(), comparator);
			for (TreeItem<V> child : item.getChildren()) {
				treeItemsDeque.push(child);
			}
		}
	}
}

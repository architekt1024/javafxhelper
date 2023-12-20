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
package io.github.architekt1024.javafxhelper.treeview;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

import javafx.collections.FXCollections;
import javafx.scene.control.TreeItem;

import org.jetbrains.annotations.NotNull;

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
	 * @param treeItem   tree item to sorting, cannot be null
	 * @param comparator comparator used to sorting, cannot be null
	 * @param <V>        The type of the {@link TreeItem#getValue() value} property within {@link TreeItem}.
	 */
	public static <V> void sortChildren(@NotNull TreeItem<V> treeItem, @NotNull Comparator<TreeItem<V>> comparator) {
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

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

import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.util.StringConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO description and example use.
 *
 * @param <T> Type of item contained in {@link TreeView}.
 *
 * @author architekt1024
 */
public abstract class AbstractRenameMenuTreeCell<T> extends TextFieldTreeCell<T> {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractRenameMenuTreeCell.class);

	protected final ContextMenu menu = new ContextMenu();
	protected final TreeView<T> treeView;

	/**
	 * @param converter string converter
	 * @param treeView tree view item
	 */
	protected AbstractRenameMenuTreeCell(StringConverter<T> converter, TreeView<T> treeView) {
		super(converter);
		this.treeView = treeView;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);
		if (!empty) {
			LOG.trace("update item {}", item);
		}
		if (!isEditing()) {
			setContextMenu(menu);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void commitEdit(T newValue) {
		LOG.trace("commitEdit {}", newValue);

		updateValue(newValue);
		super.commitEdit(getItem());
	}

	/**
	 * Update value after edit.
	 *
	 * @param newValue updated value
	 */
	protected abstract void updateValue(T newValue);

	/**
	 * Returns default value for new items
	 *
	 * @return default value for new items
	 */
	protected abstract T getNewDefault();

	/**
	 * Create "add" {@link MenuItem}.
	 *
	 * @param label display text
	 *
	 * @return "add" {@link MenuItem}.
	 */
	protected MenuItem createAddMenuItem(String label) {
		MenuItem addMenuItem = new MenuItem(label);
		addMenuItem.setOnAction((ActionEvent t) -> {
			if (getTreeItem() == null) {
				return;
			}
			TreeItem<T> newItem = createTreeItem(getNewDefault());
			TreeItem<T> parent = getTreeItem().getParent();
			if (parent != null) {
				parent.getChildren().add(newItem);
			}
		});
		return addMenuItem;
	}

	/**
	 * Create new {@link TreeItem} with value
	 *
	 * @param value TreeItem value
	 *
	 * @return {@link TreeItem} with value
	 *
	 * @since 0.1.6
	 */
	protected TreeItem<T> createTreeItem(final T value) {
		return new TreeItem<>(value);
	}

	/**
	 * Create "add child" {@link MenuItem}.
	 *
	 * @param label display text
	 *
	 * @return "add child" {@link MenuItem}.
	 */
	protected MenuItem createAddChildMenuItem(String label) {
		MenuItem addChildMenuItem = new MenuItem(label);
		addChildMenuItem.setOnAction((ActionEvent t) -> {
			if (getTreeItem() == null) {
				return;
			}
			TreeItem<T> newItem = createTreeItem(getNewDefault());
			getTreeItem().getChildren().add(newItem);
		});
		return addChildMenuItem;
	}

	/**
	 * Create "remove" {@link MenuItem}. Root element cannot be removed.
	 *
	 * @param label display text
	 *
	 * @return "remove" {@link MenuItem}
	 */
	protected MenuItem createRemoveMenuItem(String label) {
		MenuItem removeMenuItem = new MenuItem(label);
		removeMenuItem.setOnAction((ActionEvent t) -> {
			if (getTreeItem() == null) {
				return;
			}
			TreeItem<T> parent = getTreeItem().getParent();
			if (parent != null) {
				parent.getChildren().remove(getTreeItem());
			} else {
				//TODO remove root
			}
		});
		return removeMenuItem;
	}

	/**
	 * Create "rename" {@link MenuItem}
	 *
	 * @param label display text
	 *
	 * @return "rename" {@link MenuItem}
	 */
	protected MenuItem createRenameMenuItem(String label) {
		MenuItem renameMenuItem = new MenuItem(label);
		renameMenuItem.setOnAction(actionEvent -> startEdit());
		return renameMenuItem;
	}
}

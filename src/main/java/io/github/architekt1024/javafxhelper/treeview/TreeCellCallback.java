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

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

/**
 * TODO description. Syntactic sugar<br>
 * <br>
 * Create new class:
 * <pre>{@code
 * public class ElementTreeCellCallback implements TreeCellCallback<Item> {
 *  public TreeCell<Item> call(TreeView<Item> treeView) {
 * 	   return new ElementTreeCell(treeView);
 *   }
 * }
 * }</pre>
 * <br>
 * Class usage in FXML:
 * <pre>{@code
 * <TreeView fx:id="mainTree" editable="true" prefHeight="200.0" prefWidth="200.0">
 * 	<cellFactory>
 * 		<ElementTreeCellCallback/>
 * 	</cellFactory>
 * </TreeView>
 * }</pre>
 *
 * @param <T> Type of item contained in {@link TreeView}.
 *
 * @author architekt1024
 */
public interface TreeCellCallback<T> extends Callback<TreeView<T>, TreeCell<T>> {
	/**
	 * {@inheritDoc}
	 */
	@Override
	TreeCell<T> call(TreeView<T> treeView);
}

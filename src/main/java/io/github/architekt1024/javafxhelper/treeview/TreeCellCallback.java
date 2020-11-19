/*
 * All rights reserved
 */
package io.github.architekt1024.javafxhelper.treeview;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

/**
 * TODO description. Syntactic sugar<br>
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

/*
 * Copyright 2020 architekt1024
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeUtilsTest {
	private TreeItem<String> root;
	private Comparator<TreeItem<String>> comparator;

	@SuppressWarnings("unchecked")
	@BeforeEach
	void setUp() {

		comparator = (o1, o2) -> o1.getValue().compareToIgnoreCase(o2.getValue());

		TreeItem<String> item1 = new TreeItem<>("zz");
		item1.getChildren().addAll(create("500", "30", "1"));
		TreeItem<String> item2 = new TreeItem<>("aa");
		item2.getChildren().addAll(create("t", "4g", "s1"));
		TreeItem<String> item3 = new TreeItem<>("bv");
		item3.getChildren().addAll(create("a2", "b0", "c555"));

		root = new TreeItem<>("root");
		root.getChildren().addAll(item1, item2, item3);
	}

	@SuppressWarnings("unchecked")
	@SafeVarargs
	private static <T> TreeItem<T>[] create(T... values) {
		List<TreeItem<T>> result = new ArrayList<>();
		for (T value : values) {
			result.add(new TreeItem<>(value));
		}

		return (TreeItem<T>[]) result.toArray(new TreeItem[0]);
	}

	@Test
	void sortChildren() {
		TreeUtils.sortChildren(root, comparator);
		testResult(root.getChildren());
	}


	private static void testResult(ObservableList<TreeItem<String>> children) {
		assertEquals("aa", children.get(0).getValue());
		compareList(children.get(0), "4g", "s1", "t");
		assertEquals("bv", children.get(1).getValue());
		compareList(children.get(1), "a2", "b0", "c555");
		assertEquals("zz", children.get(2).getValue());
		compareList(children.get(2), "1", "30", "500");
	}

	@SafeVarargs
	private static <T> void compareList(TreeItem<T> item, T... expected) {
		ObservableList<TreeItem<T>> children = item.getChildren();
		assertEquals(expected.length, children.size());
		int i = 0;
		for (TreeItem<T> treeItem : children) {
			assertEquals(expected[i++], treeItem.getValue());
		}
	}

}

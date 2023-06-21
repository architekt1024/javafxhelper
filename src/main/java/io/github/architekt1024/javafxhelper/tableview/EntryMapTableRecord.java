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

package io.github.architekt1024.javafxhelper.tableview;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.ApiStatus;

/**
 * TODO description<br>
 * <p>Example usage</p>
 * <p>In FXML:</p>
 * <pre>{@code
 * <TableView layoutX="52.0" layoutY="49.0" prefHeight="311.0" prefWidth="353.0" editable="true">
 * 	<items>
 * 		<FXCollections fx:id="tableItems" fx:factory="observableArrayList"/>
 * 	</items>
 * 	<columns>
 * 		<TableColumn prefWidth="110.0" text="Name">
 * 			<cellValueFactory>
 * 				<PropertyValueFactory property="key"/>
 * 			</cellValueFactory>
 * 			<cellFactory>
 * 				<TextFieldTableCell fx:factory="forTableColumn"/>
 * 			</cellFactory>
 * 		</TableColumn>
 * 		<TableColumn prefWidth="110.0" text="Value">
 * 			<cellValueFactory>
 * 				<PropertyValueFactory property="value"/>
 * 			</cellValueFactory>
 * 			<cellFactory>
 * 				<TextFieldTableCell fx:factory="forTableColumn"/>
 * 			</cellFactory>
 * 		</TableColumn>
 * 	</columns>
 * </TableView>
 * }</pre>
 * <p>In controller:</p>
 *
 * <pre>
 * {@literal @FXML}
 * private ObservableList&lt;EntryMapTableRecord&gt; tableItems;
 * </pre>
 *
 * @author architekt1024
 * @since 0.1.10
 */
@ApiStatus.Experimental
public class EntryMapTableRecord {
	protected final StringProperty key = new SimpleStringProperty();
	protected final StringProperty value = new SimpleStringProperty();

	/**
	 * Create new empty record
	 *
	 * @since 0.1.10
	 */
	public EntryMapTableRecord() {
	}

	/**
	 * @param key
	 * @param value
	 *
	 * @since 0.1.10
	 */
	public EntryMapTableRecord(String key, String value) {
		this.key.setValue(key);
		this.value.setValue(value);
	}

	/**
	 * Create  new table record based on {@link Pair}
	 *
	 * @param pair
	 *
	 * @since 0.1.10
	 */
	public EntryMapTableRecord(Pair<String, String> pair) {
		key.setValue(pair.getKey());
		value.setValue(pair.getValue());
	}

	/**
	 * Create  new table record based on {@link javafx.util.Pair}
	 *
	 * @param pair
	 *
	 * @since 0.1.10
	 */
	public EntryMapTableRecord(javafx.util.Pair<String, String> pair) {
		key.setValue(pair.getKey());
		value.setValue(pair.getValue());
	}

	/**
	 * @return 'key' current value
	 *
	 * @since 0.1.10
	 */
	public String getKey() {
		return key.get();
	}

	/**
	 * @param key 'key' value
	 *
	 * @since 0.1.10
	 */
	public void setKey(String key) {
		this.key.setValue(key);
	}

	/**
	 * @return 'value' current value
	 *
	 * @since 0.1.10
	 */
	public String getValue() {
		return value.get();
	}

	/**
	 * @param value 'value' value
	 *
	 * @since 0.1.10
	 */
	public void setValue(String value) {
		this.value.setValue(value);
	}
}
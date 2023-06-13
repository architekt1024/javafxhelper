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
module io.github.architekt1024.javafxhelper {
	//modules
	requires java.desktop;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;
	requires javafx.swing;

	requires org.slf4j;

	requires org.jetbrains.annotations;
	opens io.github.architekt1024.javafxhelper to org.jetbrains.annotations;

	//jars
	requires org.apache.commons.lang3;
	requires org.apache.commons.text;

	//exports
	exports io.github.architekt1024.javafxhelper;
	exports io.github.architekt1024.javafxhelper.datepicker;
	exports io.github.architekt1024.javafxhelper.listview;
	exports io.github.architekt1024.javafxhelper.stage;
	exports io.github.architekt1024.javafxhelper.tableview;
	exports io.github.architekt1024.javafxhelper.treeview;
}

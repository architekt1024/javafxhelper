module io.github.architekt1024.javafxhelper {
	//modules
	requires java.desktop;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;
	requires javafx.swing;

	requires org.slf4j;

	//jars
	requires org.apache.commons.lang3;
	requires org.apache.commons.text;

	exports io.github.architekt1024.javafxhelper;
	exports io.github.architekt1024.javafxhelper.datepicker;
	exports io.github.architekt1024.javafxhelper.listview;
	exports io.github.architekt1024.javafxhelper.stage;
	exports io.github.architekt1024.javafxhelper.tableview;
	exports io.github.architekt1024.javafxhelper.treeview;
}

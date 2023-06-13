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
package io.github.architekt1024.javafxhelper.stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.jetbrains.annotations.NotNull;

/**
 * Build JavaFX window ({@link Stage}). Default stage is resizable, non-modal, decorated, without parent window.
 *
 * @author architekt1024
 * @since 0.1.6
 */
public class StageBuilder {
	private final URL fxml;
	private final String title;
	private Window parentWindow;
	private boolean resizable = true;
	private Modality modality = Modality.NONE;
	private StageStyle style = StageStyle.DECORATED;
	private List<String> stylesheets = new ArrayList<>();

	/**
	 * @param fxml  FXML {@link URL} For example: {@code ExampleClass.class.getResource(fxmlFile));}, cannot be null
	 * @param title window title, cannot be null
	 *
	 * @since 0.1.6
	 */
	public StageBuilder(@NotNull URL fxml, @NotNull String title) {
		this.fxml = Objects.requireNonNull(fxml);
		this.title = Objects.requireNonNull(title);
	}

	/**
	 * Build stage
	 *
	 * @return JavaFX stage
	 *
	 * @throws IOException fail load FXML file
	 * @since 0.1.6
	 */
	public Stage build() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(fxml);

		Parent parent = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setResizable(resizable);
		stage.setTitle(title);
		stage.initOwner(parentWindow);
		stage.initModality(modality);
		stage.initStyle(style);

		Scene scene = new Scene(parent);
		scene.getStylesheets().addAll(stylesheets);
		stage.setScene(scene);

		StageUtils.setStageForFXMLController(stage, fxmlLoader);
		return stage;
	}

	/**
	 * @return fxml URL
	 *
	 * @since 0.1.6
	 */
	public URL getFxml() {
		return fxml;
	}

	/**
	 * @return stage title
	 *
	 * @since 0.1.6
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return parent window or null
	 *
	 * @since 0.1.6
	 */
	public Window getParentWindow() {
		return parentWindow;
	}

	/**
	 * @param parentWindow parent window
	 *
	 * @return this builder
	 *
	 * @since 0.1.6
	 */
	public StageBuilder setParentWindow(Window parentWindow) {
		this.parentWindow = parentWindow;
		return this;
	}

	/**
	 * @return can resizable
	 *
	 * @since 0.1.6
	 */
	public boolean isResizable() {
		return resizable;
	}

	/**
	 * @param resizable can resizable
	 *
	 * @return this builder
	 *
	 * @since 0.1.6
	 */
	public StageBuilder setResizable(boolean resizable) {
		this.resizable = resizable;
		return this;
	}

	/**
	 * @return stage modality
	 *
	 * @since 0.1.6
	 */
	public Modality getModality() {
		return modality;
	}

	/**
	 * @param modality stage modality
	 *
	 * @return this builder
	 *
	 * @since 0.1.6
	 */
	public StageBuilder setModality(Modality modality) {
		Objects.requireNonNull(modality, "Modality is null");
		this.modality = modality;
		return this;
	}

	/**
	 * @return stage style
	 *
	 * @since 0.1.6
	 */
	public StageStyle getStyle() {
		return style;
	}

	/**
	 * @param style stage style
	 *
	 * @return this builder
	 *
	 * @since 0.1.6
	 */
	public StageBuilder setStyle(StageStyle style) {
		Objects.requireNonNull(modality, "style is null");
		this.style = style;
		return this;
	}

	/**
	 * @return CSS stylesheets
	 *
	 * @since 0.1.6
	 */
	public List<String> getStylesheets() {
		return Collections.unmodifiableList(stylesheets);
	}

	/**
	 * @param stylesheets CSS stylesheets
	 *
	 * @return this builder
	 *
	 * @since 0.1.6
	 */
	public StageBuilder setStylesheets(List<String> stylesheets) {
		if (stylesheets == null) {
			this.stylesheets = Collections.emptyList();
		} else {
			this.stylesheets = new ArrayList<>(stylesheets);
		}
		return this;
	}

	/**
	 * Add CSS stylesheet
	 *
	 * @param stylesheet CSS stylesheets
	 *
	 * @return this builder
	 *
	 * @since 0.1.10
	 */
	public StageBuilder addStylesheet(String stylesheet) {
		this.stylesheets.add(stylesheet);
		return this;
	}
}

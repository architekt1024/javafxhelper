/*
 * Copyright 2020-2026 architekt1024
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
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
	private ResourceBundle resources;

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
		FXMLLoader fxmlLoader = new FXMLLoader(fxml, resources);

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
	 * Get FXML url
	 *
	 * @return fxml URL
	 *
	 * @since 0.1.6
	 */
	public URL getFxml() {
		return fxml;
	}

	/**
	 * Get Stage title
	 *
	 * @return stage title
	 *
	 * @since 0.1.6
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Get parent windows
	 *
	 * @return parent window or null
	 *
	 * @since 0.1.6
	 */
	public Window getParentWindow() {
		return parentWindow;
	}

	/**
	 * Set parent window
	 *
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
	 * TODO description
	 *
	 * @return can resizable
	 *
	 * @since 0.1.6
	 */
	public boolean isResizable() {
		return resizable;
	}

	/**
	 * TODO description
	 *
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
	 * TODO description
	 *
	 * @return stage modality
	 *
	 * @since 0.1.6
	 */
	public Modality getModality() {
		return modality;
	}

	/**
	 * TODO description
	 *
	 * @param modality stage modality
	 *
	 * @return this builder
	 *
	 * @since 0.1.6
	 */
	public StageBuilder setModality(@NotNull Modality modality) {
		Objects.requireNonNull(modality, "Modality is null");
		this.modality = modality;
		return this;
	}

	/**
	 * TODO description
	 *
	 * @return stage style
	 *
	 * @since 0.1.6
	 */
	public StageStyle getStyle() {
		return style;
	}

	/**
	 * TODO description
	 *
	 * @param style stage style
	 *
	 * @return this builder
	 *
	 * @since 0.1.6
	 */
	public StageBuilder setStyle(@NotNull StageStyle style) {
		Objects.requireNonNull(modality, "style is null");
		this.style = style;
		return this;
	}

	/**
	 * TODO description
	 *
	 * @return CSS stylesheets
	 *
	 * @since 0.1.6
	 */
	public List<String> getStylesheets() {
		return Collections.unmodifiableList(stylesheets);
	}

	/**
	 * TODO description
	 *
	 * @param stylesheets CSS stylesheets
	 *
	 * @return this builder
	 *
	 * @since 0.1.6
	 */
	public StageBuilder setStylesheets(@Nullable List<String> stylesheets) {
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
	public StageBuilder addStylesheet(@Nullable String stylesheet) {
		if (stylesheets != null) {
			this.stylesheets.add(stylesheet);
		}
		return this;
	}

	/**
	 * TODO description
	 *
	 * @return resource bundle
	 *
	 * @since 0.1.11
	 */
	public ResourceBundle getResources() {
		return resources;
	}

	/**
	 * TODO description
	 *
	 * @param resources resource bundle
	 *
	 * @return this builder
	 *
	 * @since 0.1.11
	 */
	public StageBuilder setResources(@Nullable ResourceBundle resources) {
		this.resources = resources;
		return this;
	}
}

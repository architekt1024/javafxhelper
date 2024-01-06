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
package io.github.architekt1024.javafxhelper;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Window;

import io.github.architekt1024.javafxhelper.utils.WebUtils;

/**
 * {@link Alert} builder.
 * Example:
 * <pre>
 * Alert alert = new AlertBuilder()
 *         .setAlertType(Alert.AlertType.CONFIRMATION)
 *         .setTitle("Title")
 *         .setContentText("Content text")
 *         .setParentWindow(stage)
 *         .setButtons(ButtonType.OK)
 *         .build();
 * </pre>
 *
 * @author architekt1024
 * @see DialogFacade
 * @see io.github.architekt1024.javafxhelper.dialog.DialogService
 * @since 0.1.8
 */
public class AlertBuilder {
	private Alert.AlertType alertType;
	private String title;
	private String contentText;
	private String htmlText;
	private Double prefWidth;
	private Double prefHeight;
	private String headerText;
	private Window parentWindow;
	private ButtonType[] buttons;
	private Boolean resizable;
	private Modality modality;

	/**
	 * Alert without type
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder() {
		this(Alert.AlertType.NONE);
	}

	/**
	 * Alert with specified type
	 *
	 * @param alertType alert type
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder(Alert.AlertType alertType) {
		this.alertType = alertType;
	}

	/**
	 * Build the alert.
	 *
	 * @return new alert
	 *
	 * @since 0.1.8
	 */
	public Alert build() {
		final Alert alert = new Alert(alertType, contentText, buttons);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		if (resizable != null) {
			alert.setResizable(resizable);
		}
		if (htmlText != null) {
			final WebView webView = WebUtils.loadContent(htmlText, prefWidth, prefHeight);
			alert.getDialogPane().setContent(webView);
		}
		if (parentWindow != null) {
			alert.initOwner(parentWindow);
		}
		if (modality != null) {
			alert.initModality(modality);
		}
		final Window window = alert.getDialogPane().getScene().getWindow();
		window.setOnCloseRequest(event -> window.hide());
		return alert;
	}

	/**
	 * Build alert and wait for user response (in other words, brings up a blocking dialog, with the returned value the users input).
	 *
	 * @return {@link Optional} button clicked by user
	 *
	 * @since 0.1.8
	 */
	public Optional<ButtonType> buildAndShow() {
		return build().showAndWait();
	}

	/**
	 * @return
	 *
	 * @since 0.1.8
	 */
	public Alert.AlertType getAlertType() {
		return alertType;
	}

	/**
	 * @param alertType
	 *
	 * @return this builder
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder setAlertType(Alert.AlertType alertType) {
		this.alertType = Objects.requireNonNullElse(alertType, Alert.AlertType.NONE);
		return this;
	}

	/**
	 * @return
	 *
	 * @since 0.1.8
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *
	 * @return this builder
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	/**
	 * @return
	 *
	 * @since 0.1.8
	 */
	public String getContentText() {
		return contentText;
	}

	/**
	 * @param contentText
	 *
	 * @return this builder
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder setContentText(String contentText) {
		this.htmlText = null;
		this.contentText = contentText;
		return this;
	}

	/**
	 * @return
	 *
	 * @since 0.1.8
	 */
	public String getHeaderText() {
		return headerText;
	}

	/**
	 * @param headerText
	 *
	 * @return this builder
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder setHeaderText(String headerText) {
		this.headerText = headerText;
		return this;
	}

	/**
	 * @return alert parent window
	 *
	 * @since 0.1.8
	 */
	public Window getParentWindow() {
		return parentWindow;
	}

	/**
	 * @param parentWindow alert parent window
	 *
	 * @return this builder
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder setParentWindow(Window parentWindow) {
		this.parentWindow = parentWindow;
		return this;
	}

	/**
	 * @return
	 *
	 * @since 0.1.8
	 */
	public ButtonType[] getButtons() {
		return Arrays.copyOf(buttons, buttons.length);
	}

	/**
	 * @param buttons
	 *
	 * @return this builder
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder setButtons(ButtonType... buttons) {
		this.buttons = Arrays.copyOf(buttons, buttons.length);
		return this;
	}

	/**
	 * @return
	 *
	 * @since 0.1.8
	 */
	public String getHtmlText() {
		return htmlText;
	}

	/**
	 * @param htmlText
	 *
	 * @return this builder
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder setHtmlText(String htmlText) {
		this.contentText = null;
		this.htmlText = htmlText;
		return this;
	}

	/**
	 * @return
	 *
	 * @since 0.1.8
	 */
	public Double getPrefWidth() {
		return prefWidth;
	}

	/**
	 * @param prefWidth
	 *
	 * @return this builder
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder setPrefWidth(Double prefWidth) {
		this.prefWidth = prefWidth;
		return this;
	}

	/**
	 * @return
	 *
	 * @since 0.1.8
	 */
	public Double getPrefHeight() {
		return prefHeight;
	}

	/**
	 * @param prefHeight
	 *
	 * @return this builder
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder setPrefHeight(Double prefHeight) {
		this.prefHeight = prefHeight;
		return this;
	}

	/**
	 * @return
	 *
	 * @since 0.1.8
	 */
	public Boolean getResizable() {
		return resizable;
	}

	/**
	 * @param resizable
	 *
	 * @return this builder
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder setResizable(Boolean resizable) {
		this.resizable = resizable;
		return this;
	}


	/**
	 * @return
	 *
	 * @since 0.1.11
	 */
	public Modality getModality() {
		return modality;
	}

	/**
	 * @param modality
	 *
	 * @return this builder
	 *
	 * @since 0.1.11
	 */
	public AlertBuilder setModality(Modality modality) {
		this.modality = modality;
		return this;
	}
}

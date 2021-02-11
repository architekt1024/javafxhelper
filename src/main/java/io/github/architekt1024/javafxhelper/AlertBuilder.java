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
package io.github.architekt1024.javafxhelper;

import java.util.Objects;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.web.WebView;
import javafx.stage.Window;

/**
 * {@link Alert} builder.
 *
 * @author architekt1024
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

	/**
	 * Alert without type
	 */
	public AlertBuilder() {
		this(Alert.AlertType.NONE);
	}

	/**
	 * Alert with specified type
	 *
	 * @param alertType alert type
	 */
	public AlertBuilder(Alert.AlertType alertType) {
		this.alertType = alertType;
	}

	/**
	 * Build the alert.
	 *
	 * @return new alert
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
		return alert;
	}

	/**
	 * Build alert and wait for user.
	 *
	 * @return optional button clicked by user
	 */
	public Optional<ButtonType> buildAndShow() {
		return build().showAndWait();
	}


	public Alert.AlertType getAlertType() {
		return alertType;
	}

	public AlertBuilder setAlertType(Alert.AlertType alertType) {
		this.alertType = Objects.requireNonNullElse(alertType, Alert.AlertType.NONE);
		return this;
	}

	public String getTitle() {
		return title;
	}

	public AlertBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContentText() {
		return contentText;
	}

	public AlertBuilder setContentText(String contentText) {
		this.htmlText = null;
		this.contentText = contentText;
		return this;
	}

	public String getHeaderText() {
		return headerText;
	}

	public AlertBuilder setHeaderText(String headerText) {
		this.headerText = headerText;
		return this;
	}

	public Window getParentWindow() {
		return parentWindow;
	}

	public AlertBuilder setParentWindow(Window parentWindow) {
		this.parentWindow = parentWindow;
		return this;
	}

	public ButtonType[] getButtons() {
		return buttons;
	}

	public AlertBuilder setButtons(ButtonType... buttons) {
		this.buttons = buttons;
		return this;
	}

	public String getHtmlText() {
		return htmlText;
	}

	public AlertBuilder setHtmlText(String htmlText) {
		this.contentText = null;
		this.htmlText = htmlText;
		return this;
	}

	public Double getPrefWidth() {
		return prefWidth;
	}

	public AlertBuilder setPrefWidth(Double prefWidth) {
		this.prefWidth = prefWidth;
		return this;
	}

	public Double getPrefHeight() {
		return prefHeight;
	}

	public AlertBuilder setPrefHeight(Double prefHeight) {
		this.prefHeight = prefHeight;
		return this;
	}

	public Boolean getResizable() {
		return resizable;
	}

	public AlertBuilder setResizable(Boolean resizable) {
		this.resizable = resizable;
		return this;
	}
}

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

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.web.WebView;
import javafx.stage.Window;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * {@link Alert} builder.
 *
 * @author architekt1024
 * @since 0.1.8
 * @see DialogFacade
 * @see io.github.architekt1024.javafxhelper.dialog.DialogService
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
		return alert;
	}

	/**
	 * Build alert and wait for user.
	 *
	 * @return optional button clicked by user
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder setHeaderText(String headerText) {
		this.headerText = headerText;
		return this;
	}

	/**
	 * @return
	 *
	 * @since 0.1.8
	 */
	public Window getParentWindow() {
		return parentWindow;
	}

	/**
	 * @param parentWindow
	 *
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
	 *
	 * @since 0.1.8
	 */
	public AlertBuilder setResizable(Boolean resizable) {
		this.resizable = resizable;
		return this;
	}
}

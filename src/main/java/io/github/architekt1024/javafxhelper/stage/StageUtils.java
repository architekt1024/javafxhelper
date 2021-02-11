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
import java.util.Objects;
import java.util.function.Consumer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import io.github.architekt1024.javafxhelper.annotation.Nonnull;

/**
 * Create and show windows ({@link Stage})
 *
 * @author architekt1024
 * @since 0.1.6
 */
public final class StageUtils {
	private StageUtils() {
	}

	/**
	 * Load main stage. If controller implements {@link FXMLController}, it set stage.
	 *
	 * @param fxml        {@link URL} to FXML file For example: {@code ExampleClass.class.getResource(fxmlFile));}, cannot be null
	 * @param stage       JavaFX stage, cannot be null
	 * @param title       window title, cannot be null
	 * @param stylesheets stylesheet file path
	 *
	 * @throws IOException fail load FXML file
	 * @since 0.1.6
	 */
	public static void loadMainStage(URL fxml, Stage stage, String title, String stylesheets) throws IOException {
		FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(fxml));
		Parent root = loader.load();

		setStageForFXMLController(Objects.requireNonNull(stage), loader);

		Scene scene = new Scene(root);
		if (stylesheets != null) {
			scene.getStylesheets().add(stylesheets);
		}

		stage.setTitle(Objects.requireNonNull(title));
		stage.setScene(scene);

		stage.show();
	}

	/**
	 * Create utility window and return.
	 *
	 * @param fxml         fxml loader instance
	 * @param title        window title
	 * @param parentWindow parent window
	 * @param modal        is create modal window
	 *
	 * @return created stage
	 *
	 * @throws IOException fail load FXML file
	 * @since 0.1.6
	 */
	public static Stage prepareUtilityWindow(@Nonnull FXMLLoader fxml, @Nonnull String title, Window parentWindow, boolean modal) throws IOException {
		Stage stage = loadNonResizableStage(fxml, title, parentWindow);
		stage.initStyle(StageStyle.UTILITY);
		if (modal) {
			stage.initModality(Modality.APPLICATION_MODAL);
		} else {
			stage.initModality(Modality.NONE);
		}
		setStageForFXMLController(stage, fxml);
		return stage;
	}

	/**
	 * Create and show utility window. If controller implements {@link FXMLController}, it set stage.
	 *
	 * @param fxml         {@link URL} to FXML file For example: {@code ExampleClass.class.getResource(fxmlFile));}
	 * @param title        window title
	 * @param parentWindow parent window
	 * @param modal        is create modal window
	 * @param <T>          controller class
	 *
	 * @return stage controller or null if not set
	 *
	 * @throws IOException fail load FXML file
	 * @since 0.1.6
	 */
	public static <T> T showUtilityWindow(@Nonnull URL fxml, @Nonnull String title, Window parentWindow, boolean modal) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(fxml);
		Stage stage1 = prepareUtilityWindow(fxmlLoader, title, parentWindow, modal);
		setStageForFXMLController(stage1, fxmlLoader);
		stage1.show();
		return fxmlLoader.getController();
	}

	/**
	 * Create and show utility window. Wait for close. If controller implements {@link FXMLController}, it set stage.
	 *
	 * @param fxml         {@link URL} to FXML file For example: {@code ExampleClass.class.getResource(fxmlFile));}
	 * @param title        window title
	 * @param parentWindow parent window
	 * @param modal        is create modal window
	 * @param <T>          controller class
	 *
	 * @return stage controller or null if not set
	 *
	 * @throws IOException fail load FXML file
	 * @since 0.1.6
	 */
	public static <T> T showAndWaitUtilityWindow(@Nonnull URL fxml, @Nonnull String title, Window parentWindow, boolean modal) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(fxml);
		Stage stage1 = prepareUtilityWindow(fxmlLoader, title, parentWindow, modal);
		setStageForFXMLController(stage1, fxmlLoader);
		stage1.showAndWait();
		return fxmlLoader.getController();
	}

	static void setStageForFXMLController(Stage stage, @Nonnull FXMLLoader loader) {
		if (loader.getController() instanceof FXMLController) {
			FXMLController controller = loader.getController();
			controller.setStage(stage);
		}
	}

	/**
	 * TODO description
	 * If controller implements {@link FXMLController}, it set stage.
	 *
	 * @param fxmlLoader   fxml loader instance
	 * @param title        window title
	 * @param parentWindow parent window
	 *
	 * @return loaded stage
	 *
	 * @throws IOException fail load FXML file
	 * @since 0.1.6
	 */
	public static Stage loadNonResizableStage(@Nonnull FXMLLoader fxmlLoader, @Nonnull String title, Window parentWindow) throws IOException {
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.setTitle(title);
		stage.setScene(new Scene(root));
		stage.initOwner(parentWindow);
		setStageForFXMLController(stage, fxmlLoader);
		return stage;
	}

	/**
	 * TODO description
	 *
	 * @param fxml         {@link URL} to FXML file
	 * @param title        window title
	 * @param parentWindow parent window
	 *
	 * @return loaded stage
	 *
	 * @throws IOException fail load FXML file
	 * @since 0.1.6
	 */
	public static Stage showNonResizableStage(@Nonnull URL fxml, @Nonnull String title, Window parentWindow) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(fxml);
		Stage stage = loadNonResizableStage(fxmlLoader, title, parentWindow);
		stage.show();
		return stage;
	}

	/**
	 * TODO description
	 *
	 * @param fxml         {@link URL} to FXML file
	 * @param title        window title
	 * @param parentWindow parent window
	 * @param beforeShow   run before show stage
	 * @param afterShow    run after show stage
	 * @param <T>          controller class
	 *
	 * @return loaded stage
	 *
	 * @throws IOException fail load FXML file
	 * @since 0.1.6
	 */
	public static <T> Stage showNonResizableStageAndWait(@Nonnull URL fxml, @Nonnull String title, Window parentWindow,
														 Consumer<T> beforeShow, Consumer<T> afterShow) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(fxml);
		Stage stage = loadNonResizableStage(fxmlLoader, title, parentWindow);
		if (beforeShow != null) {
			beforeShow.accept(fxmlLoader.getController());
		}
		stage.showAndWait();
		if (afterShow != null) {
			afterShow.accept(fxmlLoader.getController());
		}
		return stage;
	}
}

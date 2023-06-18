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
package io.github.architekt1024.javafxhelper.stage;

import javafx.stage.Stage;

/**
 * Set {@link Stage} when use {@link StageUtils} or {@link StageBuilder}.
 *
 * @author architekt1024
 * @see StageUtils
 * @see StageBuilder
 */
public interface FXMLController {

	/**
	 * Set stage when use {@link StageUtils} or {@link StageBuilder}. It will be run after
	 * {@link javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)}
	 *
	 * @param stage current stage
	 */
	void setStage(Stage stage);
}

/*
 * All rights reserved.
 */
package io.github.architekt1024.javafxhelper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javafx.embed.swing.JFXPanel;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.BeforeAll;

public abstract class JavafxViewTest {
	@BeforeAll
	public static void initToolkit() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(() -> {
			new JFXPanel(); // initializes JavaFX environment
			latch.countDown();
		});

		if (!latch.await(5L, TimeUnit.SECONDS)) {
			throw new ExceptionInInitializerError();
		}
	}
}

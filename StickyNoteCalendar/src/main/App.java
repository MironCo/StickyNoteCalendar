/*
 * Program: StickyNoteCalendar
 * File: App.java
 * Usage: Main file for the application
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */

package main;

import main.calendar.Calendar;
import util.SaveManager;
import util.Vector2;

import java.util.ArrayList;
import java.util.List;

import gui.button.presets.PresetManager;
import gui.colors.ColorTheme;
import gui.colors.ColorThemeChangableUIElement;
import gui.colors.ColorThemeManager;
import gui.colors.DarkColorTheme;
import gui.toolbar.DayToolbar;
import gui.toolbar.MainToolbar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {

    private static final double referenceWidth = 1500.0; // not too big, not too small
    private static final double referenceHeight = 844.0;
    public static final double screenWidth = Screen.getPrimary().getBounds().getWidth()*(referenceWidth/1920.0);
    public static final double screenHeight = Screen.getPrimary().getBounds().getHeight()*(referenceHeight/1080.0);
    public static final double multiplier = screenWidth / 1280.0; // based off of 1280 screen size
    private static Vector2 mousePosition = new Vector2(0, 0);

    private static Stage mainStage = null;
    private static Scene scene;
    private static ObservableList<Node> objectList = null;
    private static List<ColorThemeChangableUIElement> changeableUIElements = new ArrayList<>();
    private static MainToolbar mainToolbar;
    private static DayToolbar dayToolbar;
    private static Rectangle backgroundRectangle;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Platform.runLater(() -> {
            mainStage = stage;
            stage.setTitle("Sticky Note Calendar");
            stage.setResizable(false);

            Pane layout = new Pane();
            objectList = layout.getChildren();
            backgroundRectangle = new Rectangle(0, 0, screenWidth+100, screenHeight+100); // fill in more than the background
            objectList.add(backgroundRectangle);

            scene = new Scene(layout, screenWidth, screenHeight);
            scene.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());

            if (ColorThemeManager.getCurrentColorTheme() == null) {
                ColorThemeManager.setCurrentColorTheme(new DarkColorTheme());
            }
            backgroundRectangle.setFill(ColorThemeManager.getCurrentColorTheme().backgroundColor);

            dayToolbar = new DayToolbar();
            dayToolbar.closeDayToolbar();

            Calendar.getInstance().Init();

            mainToolbar = new MainToolbar();

            PresetManager.getInstance().openDefaultPreset();

            updateColorTheme(ColorThemeManager.getCurrentColorTheme());

            scene.setOnMouseMoved(e -> {
                mousePosition.x = e.getX();
                mousePosition.y = e.getY();
            });

            scene.setOnMouseDragged(e -> {
                mousePosition.x = e.getX();
                mousePosition.y = e.getY();
            });

            scene.setOnMouseReleased(e -> {
                mousePosition.x = e.getX();
                mousePosition.y = e.getY();
            });

            KeyCombination saveCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
            Runnable save = () -> SaveManager.SaveData();
            scene.getAccelerators().put(saveCombination, save);

            stage.setScene(scene);
            stage.show();
        });
    }

    @Override
    public void stop() {
        SaveManager.SaveData();
    }

    public static void updateColorTheme(ColorTheme newColorTheme) {
        ColorThemeManager.setCurrentColorTheme(newColorTheme);
        getScene().setFill(ColorThemeManager.getCurrentColorTheme().backgroundColor);

        backgroundRectangle.setFill(ColorThemeManager.getCurrentColorTheme().backgroundColor);
        for (ColorThemeChangableUIElement element : changeableUIElements) {
            element.updateColors();
        }
    }

    public static void AddToScene(List<Node> nodes) {
        objectList.addAll(nodes);
    }

    public static void AddToScene(Pane pane) {
        objectList.addAll(pane);
    }

    public static void addColorThemeChangeable(ColorThemeChangableUIElement newElement) {
        changeableUIElements.add(newElement);
    }

    public static Stage getStage() {
        return mainStage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static Vector2 getMousePosition() {
        return mousePosition;
    }

    public static MainToolbar getMainToolbar() {
        return mainToolbar;
    }

    public static DayToolbar getDayToolbar() {
        return dayToolbar;
    }
}

/*
 * Program: StickyNoteCalendar
 * File: App.java
 * Usage: Main file for the application
 * Author: Miron Sulicz
 * Copyright: 2022 Miron Sulicz, All Rights Reserved
 */

package main;

import main.calendar.Calendar;
import util.SaveManager;
import util.Vector2;

import java.util.ArrayList;
import java.util.List;

import gui.colors.ColorTheme;
import gui.colors.ColorThemeChangableUIElement;
import gui.colors.ColorThemeManager;
import gui.colors.DarkColorTheme;
import gui.popupmenu.PopupMenu;
import gui.toolbar.Toolbar;
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
import javafx.stage.Stage;

public class App extends Application {
    public static double screenWidth = 1280;
    public static double screenHeight = 720;
    private static Vector2 mousePosition = new Vector2(0, 0);

    private static Stage mainStage = null;
    private static Scene mainScene = null;
    private static ObservableList<Node> objectList = null;
    private static List<ColorThemeChangableUIElement> changeableUIElements = new ArrayList<>();
    private static List<PopupMenu> popupMenus = new ArrayList<>();
    private static Toolbar mainToolbar;
    private static Rectangle backgroundRectangle;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Platform.runLater(() -> {
            mainStage = stage;
            stage.setTitle("Sticky Note Calendar");

            Pane layout = new Pane();
            objectList = layout.getChildren();
            backgroundRectangle = new Rectangle(0, 0, screenWidth, screenHeight);
            objectList.add(backgroundRectangle);

            Scene scene = new Scene(layout, screenWidth, screenHeight);
            System.out.println(System.getProperty("user.dir"));
            scene.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
            mainScene = scene;

            if (ColorThemeManager.getCurrentColorTheme() == null) {
                ColorThemeManager.setCurrentColorTheme(new DarkColorTheme());
            }
            backgroundRectangle.setFill(ColorThemeManager.getCurrentColorTheme().backgroundColor);

            Calendar.getInstance().Init();

            mainToolbar = new Toolbar();         
            mainToolbar.openDefaultPreset();

            updateColorTheme(ColorThemeManager.getCurrentColorTheme());

            scene.setOnMouseMoved(e -> {
                mousePosition.x = e.getX();
                mousePosition.y = e.getY();
            });

            scene.setOnMouseDragged(e -> {
                mousePosition.x = e.getX();
                mousePosition.y = e.getY();
            });

            KeyCombination saveCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
            Runnable save = ()-> SaveManager.SaveData();
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

    public static void hidePopupMenus() {
        for (PopupMenu menu : popupMenus) {
            menu.hide();
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

    public static void addPopupMenu(PopupMenu popupMenu) {
        popupMenus.add(popupMenu);
    }

    public static Stage getStage() {
        return mainStage;
    }

    public static Scene getScene() {
        return mainScene;
    }

    public static Vector2 getMousePosition() {
        return mousePosition;
    }

    public static Toolbar getMainToolbar() {
        return mainToolbar;
    }
}

/*
 * Program: StickyNoteCalendar
 * File: App.java
 * Usage: Main file for the application
 * Author: Miron Sulicz
 * Copyright: 2022 Miron Sulicz, All Rights Reserved
 */

package main;

import main.calendar.Calendar;
import util.Vector2;

import java.util.List;

import gui.colors.ColorThemeManager;
import gui.colors.DarkColorTheme;
import gui.stickynote.StickyNote;
import gui.toolbars.Toolbar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
    public static double screenWidth = 1280;
    public static double screenHeight = 720;
    private static Vector2 mousePosition = new Vector2(0, 0);

    private static Stage mainStage = null;
    private static Scene mainScene = null;
    private static ObservableList<Node> objectList = null;

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

            StackPane root = new StackPane(layout);
            NumberBinding maxScale = Bindings.min(root.widthProperty().divide(1280), root.heightProperty().divide(720));

            layout.scaleXProperty().bind(maxScale);
            layout.scaleYProperty().bind(maxScale);

            Scene scene = new Scene(root, screenWidth, screenHeight);
            mainScene = scene;

            ColorThemeManager.setCurrentColorTheme(new DarkColorTheme());

            scene.setFill(ColorThemeManager.getCurrentColorTheme().backgroundColor);

            Calendar.getInstance().Init();

            Toolbar toolbar = new Toolbar();

            scene.setOnMouseMoved(e -> {
                mousePosition.x = e.getX();
                mousePosition.y = e.getY();
            });

            scene.setOnMouseDragged(e -> {
                mousePosition.x = e.getX();
                mousePosition.y = e.getY();
            });

            stage.setScene(scene);
            stage.show();
        });
    }

    public static void AddToScene(List<Node> nodes) {
        objectList.addAll(nodes);
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
}

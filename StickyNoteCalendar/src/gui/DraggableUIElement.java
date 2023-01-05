/*
 * Program: StickyNoteCalendar
 * File: DraggableUIElement.java
 * Usage: Parent class for UI Elements meant to be draggable.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import main.App;

public abstract class DraggableUIElement extends DrawableUIElement {

    protected double startX = 0;
    protected double startY = 0;

    public DraggableUIElement() {
        //do nothing
    }

    protected void makeDraggable(Node node) {
        node.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
                startX = e.getSceneX() - node.getTranslateX();
                startY = e.getSceneY() - node.getTranslateY();
                node.toFront();
            }
        });

        node.setOnMouseDragged(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                node.setTranslateX(Math.max(0, Math.min(App.screenWidth, e.getSceneX() - startX)));
                node.setTranslateY(Math.max(0, Math.min(App.screenHeight, e.getSceneY() - startY)));
            }
        });
    }
} 

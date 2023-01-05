/*
 * Program: StickyNoteCalendar
 * File: Toolbar.java
 * Usage: Base class for Toolbars.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.toolbar;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import main.App;
import util.Vector2;

import java.util.ArrayList;
import java.util.List;

import gui.DrawableUIElement;
import gui.button.GUIButton;
import gui.colors.ColorThemeChangableUIElement;
import gui.colors.ColorThemeManager;

public abstract class Toolbar extends DrawableUIElement implements ColorThemeChangableUIElement {

    public static final Vector2 DIMENSIONS = new Vector2(200 * App.multiplier, (int) App.screenHeight);
    protected Pane toolbarPane;
    protected Rectangle toolbarGraphic;

    public List<GUIButton> buttons = new ArrayList<>();
    protected Vector2 toolbarPadding = new Vector2(10, 8);

    public Toolbar() {
        toolbarPane = new Pane();
        toolbarPane.setLayoutX(position.x);
        toolbarPane.setLayoutY(position.y);
        toolbarGraphic = new Rectangle(position.x, position.y, DIMENSIONS.x, DIMENSIONS.y);
        toolbarGraphic.setFill(ColorThemeManager.getCurrentColorTheme().toolbarColor);

        toolbarPane.getChildren().add(toolbarGraphic);

        nodes.add(toolbarPane);
        addNodesToScene();
        App.addColorThemeChangeable(this);
    }

    protected abstract void AddButtons();

    public List<GUIButton> getButtons() {
        return this.buttons;
    }

    public void addButton(GUIButton button) {
        buttons.add(button);
    }

    public double getButtonX() {
        return (double) toolbarPane.getTranslateX() + toolbarPadding.x;
    }

    public int getButtonWidth() {
        return (int)(getWidth() - (int)(toolbarPadding.x * 2));
    }

    public double getWidth() {
        return DIMENSIONS.x;
    }

    public double getNextY() {
        double lastY = toolbarPadding.y;
        if (!buttons.isEmpty()) {
            GUIButton lastButton = buttons.get(buttons.size()-1);
            lastY += lastButton.getPosition().y + GUIButton.BUTTON_HEIGHT; 
        }
        return lastY;
    }

    public double getBottomY() {
        return toolbarGraphic.getBoundsInLocal().getHeight() - toolbarPadding.y - GUIButton.BUTTON_HEIGHT;
    }

    public double getNextY(int buttonNumber) {
        double lastY = toolbarPadding.y;
        if (!buttons.isEmpty()) {
            lastY += buttonNumber * (GUIButton.BUTTON_HEIGHT + toolbarPadding.y);
        }
        return lastY;
    }

    @Override
    public void updateColors() {
        toolbarGraphic.setFill(ColorThemeManager.getCurrentColorTheme().toolbarColor);
    }

    public Rectangle getToolbarGraphic() {
        return toolbarGraphic;
    }

    protected void setXPosition(double x) {
        toolbarPane.setTranslateX(x);
    }
}

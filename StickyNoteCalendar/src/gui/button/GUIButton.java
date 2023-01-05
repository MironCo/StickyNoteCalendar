/*
 * Program: StickyNoteCalendar
 * File: GUIButton.java
 * Usage: Base class for GUIButtons.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.button;

import gui.DrawableUIElement;
import gui.colors.ColorThemeChangableUIElement;
import gui.colors.ColorThemeManager;
import javafx.geometry.VPos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.App;
import util.FontManager;
import util.Vector2;

public abstract class GUIButton extends DrawableUIElement implements ColorThemeChangableUIElement {
    public static final double BUTTON_HEIGHT = 50.0 * App.multiplier;
    private Pane buttonPane = new Pane();
    protected Rectangle graphic = new Rectangle();
    protected Text buttonText = new Text();
    private Color originalColor;

    public GUIButton(String buttonName, double x, double y, int width) {
        graphic = new Rectangle(width, BUTTON_HEIGHT, ColorThemeManager.getCurrentColorTheme().buttonColor);
        originalColor = ColorThemeManager.getCurrentColorTheme().buttonColor;

        buttonText = new Text(buttonName);
        buttonText.setFont(FontManager.loadFont("Nunito-Regular.ttf", (int)(20 * App.multiplier)));
        buttonText.setTextAlignment(TextAlignment.CENTER);
        buttonText.setTextOrigin(VPos.CENTER);
        buttonText.setTranslateX(graphic.getLayoutX() + (graphic.getWidth() - buttonText.getBoundsInLocal().getWidth()) / 2);
        buttonText.setTranslateY(graphic.getLayoutY() + (graphic.getHeight() / 2));
        buttonText.setFill(ColorThemeManager.getCurrentColorTheme().textColor);
        
        buttonPane.getChildren().addAll(graphic, buttonText);
        addNode(buttonPane);
        buttonPane.setLayoutX(x);
        buttonPane.setLayoutY(y);

        App.addColorThemeChangeable(this);
        setActions();
        addNodesToScene();
    }

    private void setActions() {
        buttonPane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                performAction();
            }
        });

        buttonPane.setOnMouseEntered(e -> {
            highlightButton();
        });

        buttonPane.setOnMouseExited(e -> {
            unHighlightButton();
        });
    }

    public abstract void performAction();

    @Override
    public Vector2 getPosition() {
        return new Vector2(buttonPane.getLayoutX(), buttonPane.getLayoutY());
    }

    protected void highlightButton() {
        graphic.setFill(ColorThemeManager.getCurrentColorTheme().buttonHighlightColor);
    }

    protected void unHighlightButton() {
        graphic.setFill(originalColor);
    }

    public void updateColors() {
        originalColor = ColorThemeManager.getCurrentColorTheme().buttonColor;
        graphic.setFill(originalColor);
        buttonText.setFill(ColorThemeManager.getCurrentColorTheme().textColor);
    }

    public void setOriginalColor(Color newColor) {
        originalColor = newColor;
        graphic.setFill(originalColor);
    }

    protected Pane getButtonPane() {
        return buttonPane;
    }
} 

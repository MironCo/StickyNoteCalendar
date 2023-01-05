/*
 * Program: StickyNoteCalendar
 * File: DayTitleButton.java
 * Usage: Button to show the title of the open day in the toolbar.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.button;

import gui.colors.ColorThemeManager;
import gui.popupmenu.DayTitlePopupMenu;
import gui.popupmenu.PopuppableUIElement;
import gui.toolbar.Toolbar;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Rectangle;
import util.Vector2;

public class DayTitleButton extends GUIButton implements PopuppableUIElement  {
    private static final String TITLE = "Day";
    Vector2 dimesions = new Vector2(50, 0);
    private Rectangle hackRectangle;

    public DayTitleButton(double x, double y, int width) {
        super(TITLE, x, y, width);
        graphic.setVisible(false);

        hackRectangle = new Rectangle(-10, 0, Toolbar.DIMENSIONS.x, BUTTON_HEIGHT);
        hackRectangle.setFill(ColorThemeManager.getCurrentColorTheme().toolbarColor);

        setPopupMenu();

        getButtonPane().getChildren().add(hackRectangle);
        buttonText.toFront();

        getButtonPane().setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                performAction();
            }
        });
    }

    public void setText(String text) {
        buttonText.setText(text);
        buttonText.setTranslateX(graphic.getLayoutX() + (graphic.getWidth() - buttonText.getBoundsInLocal().getWidth()) / 2);
        buttonText.setTranslateY(graphic.getLayoutY() + (graphic.getHeight() / 2));
    }

    @Override
    public void performAction() {

    }

    @Override
    public void setPopupMenu() {
        popupMenu = DayTitlePopupMenu.getInstance().getContextMenu();
        
        getButtonPane().setOnContextMenuRequested(e -> {
            popupMenu.show(getButtonPane(), e.getScreenX(), e.getScreenY());
        });
    }

    @Override
    public void updateColors() {
        buttonText.setFill(ColorThemeManager.getCurrentColorTheme().textColor);
        hackRectangle.setFill(ColorThemeManager.getCurrentColorTheme().toolbarColor);
    }

    public void bringToFront() {
        getButtonPane().toFront();
    }
}

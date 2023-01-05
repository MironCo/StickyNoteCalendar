/*
 * Program: StickyNoteCalendar
 * File: TitleButton.java
 * Usage: Button to show the title of the program.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.button;

import gui.popupmenu.PopuppableUIElement;
import gui.popupmenu.TitlePopupMenu;
import javafx.scene.input.MouseButton;
import util.Vector2;

public class TitleButton extends GUIButton implements PopuppableUIElement  {
    private static final String TITLE = "Sticky Note Calendar";
    Vector2 dimesions = new Vector2(50, 0);

    public TitleButton(double x, double y, int width) {
        super(TITLE, x, y, width);
        graphic.setVisible(false);

        setPopupMenu();

        getButtonPane().setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                performAction();
            }
        });
    }

    @Override
    public void performAction() {

    }

    @Override
    public void setPopupMenu() {
        popupMenu = TitlePopupMenu.getInstance().getContextMenu();
        
        getButtonPane().setOnContextMenuRequested(e -> {
            popupMenu.show(getButtonPane(), e.getScreenX(), e.getScreenY());
        });
    }
}

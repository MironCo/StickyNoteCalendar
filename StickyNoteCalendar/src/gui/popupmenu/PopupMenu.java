/*
 * Program: StickyNoteCalendar
 * File: PopupMenu.java
 * Usage: Base class for all popupmenus.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import gui.DrawableUIElement;
public abstract class PopupMenu extends DrawableUIElement {
    private ContextMenu menu;

    public PopupMenu() {
        menu = new ContextMenu();
    }

    public void addMenuItem(MenuItem newMenuItem) {
        menu.getItems().add(newMenuItem);
    }

    public ContextMenu getContextMenu() {
        return menu;
    }

    protected abstract void addButtons();
}

/*
 * Program: StickyNoteCalendar
 * File: PresetPopupMenu.java
 * Usage: Class that contains the Context menu for Presets.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu;

import gui.popupmenu.popupmenuitems.AddPresetMenuItem;
import gui.popupmenu.popupmenuitems.DeletePresetMenuItem;
import gui.popupmenu.popupmenuitems.OpenPresetMenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class PresetPopupMenu extends PopupMenu {
    private final static PresetPopupMenu instance = new PresetPopupMenu();

    private PresetPopupMenu() {
        super();
        addButtons();
    }

    public static final PresetPopupMenu getInstance() {
        return instance;
    }

    public void addPresetButton(OpenPresetMenuItem item) {
        addMenuItem(item.getMenuItem());
    }

    public void removePresetButton(OpenPresetMenuItem item) {
        if (getContextMenu().getItems().contains(item.getMenuItem())) getContextMenu().getItems().remove(item.getMenuItem());
    }

    @Override
    protected void addButtons() {
        addMenuItem(new AddPresetMenuItem().getMenuItem());
        addMenuItem(new DeletePresetMenuItem().getMenuItem());
        getContextMenu().getItems().add(new SeparatorMenuItem());
    }
}
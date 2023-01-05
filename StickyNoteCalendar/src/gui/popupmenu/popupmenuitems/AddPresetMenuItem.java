/*
 * Program: StickyNoteCalendar
 * File: AddPresetMenuItem.java
 * Usage: Class that contains the MenuItem to add a new Preset.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu.popupmenuitems;

import gui.button.presets.Preset;
import gui.button.presets.PresetManager;

public class AddPresetMenuItem extends PopupMenuItem {
    private static final String TITLE = "Add New Preset";
    
    public AddPresetMenuItem() {
        super(TITLE);
    }

    @Override
    public void performAction() {
        Preset newPreset = new Preset("New Preset");
        PresetManager.getInstance().addPreset(newPreset);
        PresetManager.getInstance().openPreset(newPreset);
    }
}

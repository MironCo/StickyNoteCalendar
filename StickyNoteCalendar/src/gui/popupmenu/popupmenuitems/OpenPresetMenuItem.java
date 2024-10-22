/*
 * Program: StickyNoteCalendar
 * File: OpenPresetMenuItem.java
 * Usage: Class that contains the MenuItem to open a preset.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu.popupmenuitems;

import gui.button.presets.Preset;
import gui.button.presets.PresetManager;
import main.App;

public class OpenPresetMenuItem extends PopupMenuItem {
    Preset preset;
    
    public OpenPresetMenuItem(Preset preset) {
        super("Open Preset: " + preset.getName());
        this.preset = preset;
        preset.setConnectedMenuItem(this);
    }

    public void setPreset(Preset newPreset) {
        preset = newPreset;
    }

    public void updateText(String title) {
        menuItem.setText("Open Preset: " + title);
    }

    @Override
    public void performAction() {
        PresetManager.getInstance().openPreset(preset);
        App.getMainToolbar().setPresetTitle(preset.getName());
    }
}


/*
 * Program: StickyNoteCalendar
 * File: DeletePresetMenuItem.java
 * Usage: Class that contains the MenuItem to delete a preset.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu.popupmenuitems;

import gui.button.presets.PresetManager;

public class DeletePresetMenuItem extends PopupMenuItem {
    private static final String TITLE = "Delete This Preset";
    
    public DeletePresetMenuItem() {
        super(TITLE);
    }

    @Override
    public void performAction() {
        PresetManager.getInstance().deletePreset(PresetManager.getInstance().getCurrentPreset());
    }
}

/*
 * Program: StickyNoteCalendar
 * File: DeletePresetStickyNoteMenu.java
 * Usage: Class that contains the MenuItem to delete a preset sticky note.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu.popupmenuitems;

import gui.button.presets.PresetManager;

public class DeletePresetStickyNoteMenuItem extends PopupMenuItem{

    private static final String TITLE = "Delete";

    public DeletePresetStickyNoteMenuItem() {
        super(TITLE);
    }

    @Override
    public void performAction() {
        PresetManager.getInstance().deletePresetStickyNote(PresetManager.getInstance().getRightClickedPresetStickyNote());
    }

}

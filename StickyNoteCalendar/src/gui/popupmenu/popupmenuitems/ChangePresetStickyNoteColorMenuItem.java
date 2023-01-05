/*
 * Program: StickyNoteCalendar
 * File: ChangePresetStickyNoteColorMenuItem.java
 * Usage: Class that contains the MenuItem to change the color of a preset sticky note.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu.popupmenuitems;

import gui.button.presets.PresetManager;
import gui.stickynote.NoteColor;

public class ChangePresetStickyNoteColorMenuItem extends PopupMenuItem {
    NoteColor color;

    public ChangePresetStickyNoteColorMenuItem(NoteColor color) {
        super("Change Color: " + color.getName());
        this.color = color;
    }

    @Override
    public void performAction() {
        PresetManager.getInstance().getRightClickedPresetStickyNote().setColor(color);
    }
}

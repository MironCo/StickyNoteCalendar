/*
 * Program: StickyNoteCalendar
 * File: ChangeStickyNoteColorMenuItem.java
 * Usage: Class that contains the MenuItem to change the color of a sticky note.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu.popupmenuitems;

import gui.stickynote.NoteColor;
import gui.stickynote.StickyNoteManager;

public class ChangeStickyNoteColorMenuItem extends PopupMenuItem{
    NoteColor color;

    public ChangeStickyNoteColorMenuItem(NoteColor color) {
        super("Change Color: " + color.getName());
        this.color = color;
    }

    @Override
    public void performAction() {
        StickyNoteManager.getInstance().getRightClickedStickyNote().setColor(color);
    }
}
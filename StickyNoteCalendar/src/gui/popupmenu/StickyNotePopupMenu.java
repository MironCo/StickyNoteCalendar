/*
 * Program: StickyNoteCalendar
 * File: StickyNotePopupMenu.java
 * Usage: Class that contains the Context menu for StickyNotes.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu;

import gui.popupmenu.popupmenuitems.ChangeStickyNoteColorMenuItem;
import gui.popupmenu.popupmenuitems.DeleteStickyNoteMenuItem;
import gui.stickynote.NoteColor;

public class StickyNotePopupMenu extends PopupMenu {
    private final static StickyNotePopupMenu instance = new StickyNotePopupMenu();

    private StickyNotePopupMenu() {
        super();
        addButtons();
    }

    public static final StickyNotePopupMenu getInstance() {
        return instance;
    }

    @Override
    protected void addButtons() {
        addMenuItem(new ChangeStickyNoteColorMenuItem(NoteColor.PURPLE).getMenuItem());
        addMenuItem(new ChangeStickyNoteColorMenuItem(NoteColor.BLUE).getMenuItem());
        addMenuItem(new ChangeStickyNoteColorMenuItem(NoteColor.YELLOW).getMenuItem());
        addMenuItem(new ChangeStickyNoteColorMenuItem(NoteColor.GREEN).getMenuItem());
        addMenuItem(new ChangeStickyNoteColorMenuItem(NoteColor.BEIGE).getMenuItem());

        addMenuItem(new DeleteStickyNoteMenuItem().getMenuItem());
    }
}

/*
 * Program: StickyNoteCalendar
 * File: ClearStickyNotesMenuItem.java
 * Usage: Class that contains the MenuItem to clear the StickyNotes of a day.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu.popupmenuitems;

import main.App;

public class ClearStickyNotesMenuItem extends PopupMenuItem {
    private static final String TITLE = "Clear Sticky Notes";
    
    public ClearStickyNotesMenuItem() {
        super(TITLE);
    }

    @Override
    public void performAction() {
        App.getDayToolbar().clearStickyNotes();
    }
}

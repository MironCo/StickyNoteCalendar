/*
 * Program: StickyNoteCalendar
 * File: DeleteStickyNoteMenuItem.java
 * Usage: Class that contains the MenuItem to delete a StickyNote.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu.popupmenuitems;

import gui.stickynote.StickyNoteManager;

public class DeleteStickyNoteMenuItem extends PopupMenuItem{

    private static final String TITLE = "Delete";

    public DeleteStickyNoteMenuItem() {
        super(TITLE);
    }

    @Override
    public void performAction() {
        StickyNoteManager.getInstance().deleteStickyNote(StickyNoteManager.getInstance().getRightClickedStickyNote());
    }

}

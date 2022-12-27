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

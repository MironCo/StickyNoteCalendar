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
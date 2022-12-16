package gui.popupmenu.popupmenuitems;

import gui.stickynote.NoteColor;
import gui.stickynote.StickyNoteManager;

public class ChangeStickyNoteColorMenuItem extends PopupMenuItem {
    NoteColor color;
    
    public ChangeStickyNoteColorMenuItem(NoteColor color) {
        super(color.getName().toUpperCase());
        this.color = color;
        
        getGraphic().setFill(color.getColor());
    }

    @Override
    public void performAction() {
        // TODO Auto-generated method stub
        StickyNoteManager.getInstance().getRightClickedStickyNote().setColor(color);
        connectedPopupMenu.hide();
    }
}

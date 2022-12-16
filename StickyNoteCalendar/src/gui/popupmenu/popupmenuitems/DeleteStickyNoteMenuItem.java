package gui.popupmenu.popupmenuitems;

import gui.stickynote.StickyNoteManager;
import javafx.scene.paint.Color;

public class DeleteStickyNoteMenuItem extends PopupMenuItem{

    private static final String TITLE = "Delete";

    public DeleteStickyNoteMenuItem() {
        super(TITLE);

        getGraphic().setFill(Color.BLACK);
        getText().setFill(Color.WHITESMOKE);
    }

    @Override
    public void performAction() {
        StickyNoteManager.getInstance().deleteStickyNote(StickyNoteManager.getInstance().getRightClickedStickyNote());
    }

}

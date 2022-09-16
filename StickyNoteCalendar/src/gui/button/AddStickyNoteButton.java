package gui.button;

import gui.stickynote.StickyNote;
import gui.stickynote.StickyNoteManager;
import util.Vector2;

public class AddStickyNoteButton extends GUIButton {
    Vector2 dimesions = new Vector2(50, 0);

    public AddStickyNoteButton(String buttonName, double x, double y, int width, int height) {
        super(buttonName, x, y, width, height);
        graphic.setArcHeight(height / 2);
        graphic.setArcWidth(width / 10);
    }

    @Override
    public void performAction() {
        StickyNote note = new StickyNote();
        StickyNoteManager.getInstance().addStickyNote(note);
    }
    
}

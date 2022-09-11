package main.calendar.day;

import java.util.ArrayList;
import java.util.List;

import gui.DrawableUIElement;
import gui.stickynote.StickyNote;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Day extends DrawableUIElement {
    public Integer day = 0;
    public Rectangle rectangle = new Rectangle();
    public Rectangle dayStickyNote = new Rectangle();
    public Text dayNumberText = new Text();
    public List<StickyNote> stickyNotes = new ArrayList<StickyNote>();

    public Day() {
        
    }

    public void AddStickyNote(StickyNote note) {
        stickyNotes.add(note);
        note.hideMainStickyNote();
        updateStickyNoteGraphic();
    }

    public Day getDay() {
        return this;
    }

    public void updateStickyNoteGraphic() {
        if (stickyNotes.isEmpty()) {
            dayStickyNote.setVisible(false);
        } else {
            dayStickyNote.setFill(stickyNotes.get(0).getRectangle().getFill());
            dayStickyNote.setVisible(true);
        }
    }
}

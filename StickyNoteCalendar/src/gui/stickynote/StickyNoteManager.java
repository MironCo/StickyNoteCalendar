package gui.stickynote;

import java.util.List;

public class StickyNoteManager {
    public static StickyNoteManager instance = new StickyNoteManager();

    private List<StickyNote> stickyNotes;

    private StickyNoteManager() {

    }

    public static StickyNoteManager getInstance() {
        return instance;
    }

    public void addStickyNote(StickyNote note) {
        stickyNotes.add(note);
    }

    public List<StickyNote> getStickyNotes() {
        return stickyNotes;
    }
}

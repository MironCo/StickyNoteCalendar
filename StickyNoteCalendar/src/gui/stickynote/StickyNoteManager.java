package gui.stickynote;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import main.App;
import main.calendar.day.Day;

public class StickyNoteManager {
    public static final StickyNoteManager instance = new StickyNoteManager();

    private List<StickyNote> stickyNotes;
    private Day hoveredDay = null;
    private StickyNote draggedStickyNote;
    private static StickyNote currentlyEditingStickyNote = null;

    private StickyNoteManager() {
        setKeyListener();
    }

    private void setKeyListener() {
        App.getStage().addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (currentlyEditingStickyNote != null && currentlyEditingStickyNote.isEditing()) {
                if (key.getCode() != KeyCode.ESCAPE) {
                    currentlyEditingStickyNote.ChangeStickNoteText(key);
                }
            }
        });
        App.getScene().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (currentlyEditingStickyNote != null && currentlyEditingStickyNote.isEditing()) {
                    if (e.getButton().equals(MouseButton.PRIMARY)) {
                        if (e.getClickCount() == 1) {
                            currentlyEditingStickyNote.stopEditingText();
                            currentlyEditingStickyNote = null;
                        }
                    } 
                }
            }
        });
    }

    public static final StickyNoteManager getInstance() {
        return instance;
    }

    public void addStickyNote(StickyNote note) {
        stickyNotes.add(note);
    }

    public List<StickyNote> getStickyNotes() {
        return stickyNotes;
    }

    public void setHoveredDay(Day day) {
        hoveredDay = day;
    } 

    public void addStickyNoteToDay(StickyNote note) {
        hoveredDay.stickyNotes.add(note);
    }

    public StickyNote getDraggedStickyNote() {
        return draggedStickyNote;
    } 

    public void setDraggedStickyNote(StickyNote note) {
        draggedStickyNote = note;
    }

    public void setCurrentlyEditingStickyNote(StickyNote note) {
        currentlyEditingStickyNote = note;
    }

    public StickyNote getCurrentlyEditingStickyNote() {
        return currentlyEditingStickyNote;
    }
}
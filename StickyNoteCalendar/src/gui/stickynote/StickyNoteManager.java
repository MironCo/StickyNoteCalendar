package gui.stickynote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gui.popupmenu.StickyNotePopupMenu;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import main.App;
import main.calendar.day.Day;

public class StickyNoteManager {
    public static final StickyNoteManager instance = new StickyNoteManager();

    private List<StickyNote> stickyNotes = new ArrayList<StickyNote>();
    private Day hoveredDay = null;
    private StickyNote draggedStickyNote;
    private static StickyNote currentlyEditingStickyNote = null;
    private static StickyNote rightClickedStickyNote = null;
    private static final NoteColor[] NOTE_COLORS = {NoteColor.BEIGE, NoteColor.PURPLE, NoteColor.BLUE, NoteColor.YELLOW};
    private static final Random RANDOM = new Random();

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
                if (e.getButton() == MouseButton.PRIMARY) {
                    StickyNotePopupMenu.getInstance().hide();
                }
                if (e.getButton() == MouseButton.SECONDARY) {

                }
            } 
        });
    }

    public static NoteColor getRandomNoteColor() {
        return NOTE_COLORS[RANDOM.nextInt(0, NOTE_COLORS.length)];
    }

    public static NoteColor getNoteColorByName(String name) {
        for (NoteColor noteColor : NOTE_COLORS) {
            if (noteColor.getName().equals(name)) {
                return noteColor;
            }
        }
        return NoteColor.BEIGE;
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
        if (note != null) {
            draggedStickyNote.getNodes().forEach(e-> {
                e.toFront();
            });
        }
    }

    public void deleteStickyNote(StickyNote deleted) {
        deleted.hideMainStickyNote();
        stickyNotes.remove(deleted);
    }

    public void setCurrentlyEditingStickyNote(StickyNote note) {
        currentlyEditingStickyNote = note;
    }

    public StickyNote getCurrentlyEditingStickyNote() {
        return currentlyEditingStickyNote;
    }

    public void setRightClickedStickyNote(StickyNote stickyNote) {
        rightClickedStickyNote = stickyNote;
    }
    
    public StickyNote getRightClickedStickyNote() {
        return rightClickedStickyNote;
    }
}

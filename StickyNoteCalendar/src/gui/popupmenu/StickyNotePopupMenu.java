package gui.popupmenu;

import gui.popupmenu.popupmenuitems.ChangeStickyNoteColorMenuItem;
import gui.popupmenu.popupmenuitems.DeleteStickyNoteMenuItem;
import gui.stickynote.NoteColor;

public class StickyNotePopupMenu extends PopupMenu {
    private final static StickyNotePopupMenu instance = new StickyNotePopupMenu();

    private StickyNotePopupMenu() {
        super();
        addButtons();
    }

    public static final StickyNotePopupMenu getInstance() {
        return instance;
    }

    @Override
    protected void addButtons() {
        addMenuItem(new ChangeStickyNoteColorMenuItem(NoteColor.PURPLE));
        addMenuItem(new ChangeStickyNoteColorMenuItem(NoteColor.BLUE));
        addMenuItem(new ChangeStickyNoteColorMenuItem(NoteColor.YELLOW));
        addMenuItem(new ChangeStickyNoteColorMenuItem(NoteColor.BEIGE));
        addMenuItem(new DeleteStickyNoteMenuItem());
        calculateHeight();
    }
}

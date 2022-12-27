package gui.popupmenu;

import gui.popupmenu.popupmenuitems.ChangePresetStickyNoteColorMenuItem;
import gui.popupmenu.popupmenuitems.DeletePresetStickyNoteMenuItem;
import gui.stickynote.NoteColor;

public class PresetStickyNotePopupMenu extends PopupMenu {
    private final static PresetStickyNotePopupMenu instance = new PresetStickyNotePopupMenu();

    private PresetStickyNotePopupMenu() {
        super();
        addButtons();
    }

    public static final PresetStickyNotePopupMenu getInstance() {
        return instance;
    }

    @Override
    protected void addButtons() {
        addMenuItem(new ChangePresetStickyNoteColorMenuItem(NoteColor.PURPLE).getMenuItem());
        addMenuItem(new ChangePresetStickyNoteColorMenuItem(NoteColor.BLUE).getMenuItem());
        addMenuItem(new ChangePresetStickyNoteColorMenuItem(NoteColor.YELLOW).getMenuItem());
        addMenuItem(new ChangePresetStickyNoteColorMenuItem(NoteColor.GREEN).getMenuItem());
        addMenuItem(new ChangePresetStickyNoteColorMenuItem(NoteColor.BEIGE).getMenuItem());

        addMenuItem(new DeletePresetStickyNoteMenuItem().getMenuItem());
    }
}
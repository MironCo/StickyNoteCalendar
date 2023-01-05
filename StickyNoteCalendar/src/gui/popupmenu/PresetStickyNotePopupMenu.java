/*
 * Program: StickyNoteCalendar
 * File: PresetStickyNotePopupMenu.java
 * Usage: Class that contains the Context menu for Preset Sticky Notes.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
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
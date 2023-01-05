/*
 * Program: StickyNoteCalendar
 * File: Preset.java
 * Usage: Base class for built in presets and added presets.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.button.presets;

import java.util.ArrayList;
import java.util.List;

import gui.popupmenu.PresetPopupMenu;
import gui.popupmenu.popupmenuitems.OpenPresetMenuItem;
import gui.toolbar.Toolbar;
import main.App;

public class Preset {
    private String presetName = "";
    public List<PresetStickyNoteBean> presetStickyNoteData = new ArrayList<>();
    public List<AddPresetStickyNote> presetStickyNotes = new ArrayList<>();
    private OpenPresetMenuItem connectedItem;

    public Preset(String name) {
        presetName = name;
        PresetPopupMenu.getInstance().addPresetButton(new OpenPresetMenuItem(this));
    }

    public void addPresetStickyNote(PresetStickyNoteBean note) {
        presetStickyNoteData.add(note);
    }   

    public void addNewPresetStickyNote(PresetStickyNoteBean newNote) {
        presetStickyNoteData.add(newNote);

        AddPresetStickyNote newAddPresetStickyNote = new AddPresetStickyNote(newNote, App.getMainToolbar().getButtonX(), App.getMainToolbar().getNextY(presetStickyNotes.size()+4), App.getMainToolbar().getButtonWidth());
        presetStickyNotes.add(newAddPresetStickyNote);
    }

    public void addToToolbar(Toolbar toolbar) {
        int index = 4;

        for (PresetStickyNoteBean bean : presetStickyNoteData) {
            AddPresetStickyNote newAddPresetStickyNote = new AddPresetStickyNote(bean, toolbar.getButtonX(), toolbar.getNextY(index), toolbar.getButtonWidth());
            newAddPresetStickyNote.stopEditingText();
                presetStickyNotes.add(newAddPresetStickyNote);
            index++;
        }
    }

    public void setName(String newName) {
        presetName = newName;
        if (connectedItem != null) connectedItem.updateText(newName);
    }

    public String getName() {
        return presetName;
    }

    public void showPresetStickyNotes() {
        presetStickyNotes.forEach(note -> {
            note.setVisible(true);
        });
    }

    public void hidePresetStickyNotes() {
        presetStickyNotes.forEach(note -> {
            note.setVisible(false);
            note.stopEditingText();
        });
    }

    public void deletePresetStickyNote(AddPresetStickyNote presetStickyNote) {
        if (presetStickyNotes.contains(presetStickyNote)){
            presetStickyNotes.remove(presetStickyNote);
            presetStickyNoteData.remove(presetStickyNote.getBean());
            presetStickyNote.setVisible(false);
            refreshStickyNotePositions();
        }
    }

    public void refreshStickyNotePositions() {
        int index = 4;

        for (AddPresetStickyNote presetStickyNote : presetStickyNotes) {
            presetStickyNote.getNodes().get(0).setLayoutY(App.getMainToolbar().getNextY(index));
            index++;
        }
    }

    public void setConnectedMenuItem(OpenPresetMenuItem item) {
        this.connectedItem = item;
    }

    public OpenPresetMenuItem getConnectedMenuItem() {
        return connectedItem;
    }
}

/*
 * Program: StickyNoteCalendar
 * File: PresetManager.java
 * Usage: Class to manage the loaded preset and preset sticky notes.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.button.presets;

import java.util.ArrayList;
import java.util.List;

import gui.button.presets.defaults.DefaultPreset;
import gui.button.presets.defaults.StudentPreset;
import gui.popupmenu.PresetPopupMenu;
import main.App;

public class PresetManager {
    private static PresetManager instance = new PresetManager();

    public static final int MAX_NOTES = 7;

    private final List<Preset> presets = new ArrayList<>();
    private Preset currentPreset;

    private AddPresetStickyNote editingNote = null;
    private AddPresetStickyNote rightClickedNote = null;

    private PresetManager() {
        
    }

    public static PresetManager getInstance() {
        return instance;
    }

    public void addPreset(Preset preset) {
        presets.add(preset);
    }

    public List<Preset> getPresets() {
        return presets;
    }

    public Preset getCurrentPreset() {
        return currentPreset;
    }

    public void setCurrentPreset(Preset preset) {
        currentPreset = preset;
    }

    public void setRightClickedPresetStickyNote(AddPresetStickyNote note) {
        rightClickedNote = note;
    } 
    
    public AddPresetStickyNote getRightClickedPresetStickyNote() {
        return rightClickedNote;
    }

    public void openPreset(Preset preset) {
        getPresets().forEach(p -> {
            p.hidePresetStickyNotes();
        });
        
        if (getPresets().contains(preset)) {
            setCurrentPreset(preset);
            getPresets().get(getPresets().indexOf(currentPreset)).showPresetStickyNotes();
            App.getMainToolbar().setPresetTitle(getCurrentPreset().getName());
        }
    }

    public void openDefaultPreset() {
        openPreset(getPresets().get(0));
    }

    public void loadDefaultPresets() {
        addPreset(new DefaultPreset());
        addPreset(new StudentPreset());
    }

    public void setCurrentlyEditingStickyNote(AddPresetStickyNote note) {
        clearCurrentlyEditingStickyNote();
        editingNote = note;
        editingNote.startEditingText();
    }

    public void clearCurrentlyEditingStickyNote() {
        editingNote = null;
        for (Preset preset : presets) {
            for (AddPresetStickyNote note : preset.presetStickyNotes) {
                note.stopEditingText();
            }
        }
    }

    public void deletePresetStickyNote(AddPresetStickyNote presetStickyNote) {
        currentPreset.deletePresetStickyNote(presetStickyNote);
    }

    public void deletePreset(Preset preset) {
        int index = getPresets().indexOf(preset);
        if (index > -1) {
            preset.hidePresetStickyNotes();
            PresetPopupMenu.getInstance().removePresetButton(preset.getConnectedMenuItem());
            getPresets().remove(index);
            if (presets.size() > 0) { 
                openDefaultPreset();
            } else {
                App.getMainToolbar().setPresetTitle("No Presets");
            }
        }
    }
}
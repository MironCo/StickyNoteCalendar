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

    public Preset(String name) {
        presetName = name;
    }

    public void addPresetStickyNote(PresetStickyNoteBean note) {
        presetStickyNoteData.add(note);
    }   

    public void addNewPresetStickyNote(PresetStickyNoteBean newNote) {
        presetStickyNoteData.add(newNote);

        AddPresetStickyNote newAddPresetStickyNote = new AddPresetStickyNote(newNote.getPresetStickyNoteText(),
        newNote.getPresetStickyNoteColor(), App.getMainToolbar().getButtonX(), App.getMainToolbar().getNextY(presetStickyNotes.size()+4), App.getMainToolbar().getButtonWidth());
        presetStickyNotes.add(newAddPresetStickyNote);
    }

    public void addToToolbar(Toolbar toolbar) {
        int index = 4;

        for (PresetStickyNoteBean bean : presetStickyNoteData) {
            AddPresetStickyNote newAddPresetStickyNote = new AddPresetStickyNote(bean.getPresetStickyNoteText(),
                bean.getPresetStickyNoteColor(), toolbar.getButtonX(), toolbar.getNextY(index), toolbar.getButtonWidth());
            newAddPresetStickyNote.stopEditingText();
                presetStickyNotes.add(newAddPresetStickyNote);
            index++;
        }
        PresetPopupMenu.getInstance().addPresetButton(new OpenPresetMenuItem(this));
    }

    public void setName(String newName) {
        presetName = newName;
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
        });
    }
}

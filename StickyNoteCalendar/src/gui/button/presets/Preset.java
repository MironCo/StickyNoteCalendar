package gui.button.presets;

import java.util.ArrayList;
import java.util.List;

import gui.popupmenu.PresetPopupMenu;
import gui.popupmenu.popupmenuitems.OpenPresetMenuItem;
import gui.toolbar.Toolbar;

public class Preset {
    private String presetName = "";
    public List<PresetStickyNoteBean> presetStickyNoteData = new ArrayList<>();
    public List<AddPresetStickyNote> presetStickyNotes = new ArrayList<>();

    public void addPresetStickyNote(PresetStickyNoteBean note) {
        presetStickyNoteData.add(note);
    }   

    public void addToToolbar(Toolbar toolbar) {
        int index = 4;

        for (PresetStickyNoteBean bean : presetStickyNoteData) {
            AddPresetStickyNote newAddPresetStickyNote = new AddPresetStickyNote(bean.getPresetStickyNoteText(),
                bean.getPresetStickyNoteColor(), toolbar.getButtonX(), toolbar.getNextY(index), toolbar.getButtonWidth());
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

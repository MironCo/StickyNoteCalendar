package gui.button;

import gui.button.presets.PresetManager;
import gui.button.presets.PresetStickyNoteBean;
import gui.stickynote.StickyNoteManager;

public class AddNewPresetStickyNoteButton extends GUIButton {
    public AddNewPresetStickyNoteButton(double x, double y, int width) {
        super("New Preset Note", x, y, width);
    }

    @Override
    public void performAction() {
        if (PresetManager.getInstance().getCurrentPreset().presetStickyNotes.size() < PresetManager.MAX_NOTES && !PresetManager.getInstance().getPresets().isEmpty()) {
            PresetManager.getInstance().getCurrentPreset().addNewPresetStickyNote(new PresetStickyNoteBean("New Preset Sticky Note", StickyNoteManager.getRandomNoteColor()));
        }
    }
}
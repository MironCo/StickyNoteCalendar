package gui.button;

import gui.button.presets.PresetStickyNoteBean;
import gui.stickynote.StickyNoteManager;
import main.App;

public class AddNewPresetStickyNoteButton extends GUIButton {
    public AddNewPresetStickyNoteButton(double x, double y, int width) {
        super("New Preset Note", x, y, width);
    }

    @Override
    public void performAction() {
        App.getMainToolbar().currentPreset.addNewPresetStickyNote(new PresetStickyNoteBean("New Preset Sticky Note", StickyNoteManager.getRandomNoteColor()));
    }
}
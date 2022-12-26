package gui.button.presets.defaults;

import gui.button.presets.Preset;
import gui.button.presets.PresetStickyNoteBean;
import gui.stickynote.NoteColor;

public class StudentPreset extends Preset {
    public StudentPreset() {
        super("Student");
        
        addPresetStickyNote(new PresetStickyNoteBean("Paper Due", NoteColor.PURPLE));
        addPresetStickyNote(new PresetStickyNoteBean("Test", NoteColor.BEIGE));
    }
}

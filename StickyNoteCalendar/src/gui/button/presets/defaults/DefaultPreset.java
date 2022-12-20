package gui.button.presets.defaults;

import gui.button.presets.Preset;
import gui.button.presets.PresetStickyNoteBean;
import gui.stickynote.NoteColor;

public class DefaultPreset extends Preset {
    public DefaultPreset() {
        super();
        
        setName("Default");
        addPresetStickyNote(new PresetStickyNoteBean("This is a test Sticky Note (:", NoteColor.BLUE));
        addPresetStickyNote(new PresetStickyNoteBean("Other Test :D", NoteColor.GREEN));
    }
}

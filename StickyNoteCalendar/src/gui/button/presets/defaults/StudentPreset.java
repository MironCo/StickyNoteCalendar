/*
 * Program: StickyNoteCalendar
 * File: StudentPreset.java
 * Usage: Second Default Preset for testing.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
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

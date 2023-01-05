/*
 * Program: StickyNoteCalendar
 * File: DefaultPreset.java
 * Usage: Default Preset for testing.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.button.presets.defaults;

import gui.button.presets.Preset;
import gui.button.presets.PresetStickyNoteBean;
import gui.stickynote.NoteColor;

public class DefaultPreset extends Preset {
    public DefaultPreset() {
        super("Default");
        
        addPresetStickyNote(new PresetStickyNoteBean("This is a test Sticky Note (:", NoteColor.BLUE));
        addPresetStickyNote(new PresetStickyNoteBean("Other Test :D", NoteColor.GREEN));
    }
}

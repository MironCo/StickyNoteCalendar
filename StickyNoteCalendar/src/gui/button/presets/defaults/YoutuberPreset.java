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

public class YoutuberPreset extends Preset {
    public YoutuberPreset() {
        super("Youtuber");
        
        addPresetStickyNote(new PresetStickyNoteBean("Video Release: [Video Name]", NoteColor.PURPLE));
        addPresetStickyNote(new PresetStickyNoteBean("Video Shooting Day: [Video Name]", NoteColor.YELLOW));
        addPresetStickyNote(new PresetStickyNoteBean("Editing Deadline", NoteColor.GREEN));
        addPresetStickyNote(new PresetStickyNoteBean("Podcast Recording", NoteColor.BLUE));
        addPresetStickyNote(new PresetStickyNoteBean("Employee Meeting", NoteColor.BEIGE));
    }
}

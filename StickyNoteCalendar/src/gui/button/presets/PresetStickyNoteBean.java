/*
 * Program: StickyNoteCalendar
 * File: PresetStickyNoteBean.java
 * Usage: Bean to contain data for preset sticky notes.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.button.presets;

import gui.stickynote.NoteColor;

public class PresetStickyNoteBean {
    private String presetStickyNoteText = "";
    private NoteColor presetStickyNoteColor;

    public PresetStickyNoteBean() {
        presetStickyNoteText= "";
        presetStickyNoteColor = NoteColor.BEIGE;
    }

    public PresetStickyNoteBean(String noteText, NoteColor color) {
        presetStickyNoteText = noteText;
        presetStickyNoteColor = color;        
    }

    public String getPresetStickyNoteText() {
        return this.presetStickyNoteText;
    }

    public void setPresetStickyNoteText(String presetStickyNoteText) {
        this.presetStickyNoteText = presetStickyNoteText;
    }

    public NoteColor getPresetStickyNoteColor() {
        return this.presetStickyNoteColor;
    }

    public void setPresetStickyNoteColor(NoteColor presetStickyNoteColor) {
        this.presetStickyNoteColor = presetStickyNoteColor;
    }
}
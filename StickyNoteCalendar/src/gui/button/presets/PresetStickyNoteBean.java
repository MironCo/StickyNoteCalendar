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
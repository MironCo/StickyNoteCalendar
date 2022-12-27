package gui.popupmenu.popupmenuitems;

import gui.button.presets.PresetManager;
import gui.stickynote.NoteColor;

public class ChangePresetStickyNoteColorMenuItem extends PopupMenuItem {
    NoteColor color;

    public ChangePresetStickyNoteColorMenuItem(NoteColor color) {
        super("Change Color: " + color.getName());
        this.color = color;
    }

    @Override
    public void performAction() {
        PresetManager.getInstance().getRightClickedPresetStickyNote().setColor(color);
    }
}

package gui.popupmenu.popupmenuitems;

import gui.button.presets.PresetManager;

public class DeletePresetStickyNoteMenuItem extends PopupMenuItem{

    private static final String TITLE = "Delete";

    public DeletePresetStickyNoteMenuItem() {
        super(TITLE);
    }

    @Override
    public void performAction() {
        PresetManager.getInstance().deletePresetStickyNote(PresetManager.getInstance().getRightClickedPresetStickyNote());
    }

}

package gui.popupmenu.popupmenuitems;

import gui.button.presets.Preset;
import gui.button.presets.PresetManager;

public class DeletePresetMenuItem extends PopupMenuItem {
    private static final String TITLE = "Delete This Preset";
    
    public DeletePresetMenuItem() {
        super(TITLE);
    }

    @Override
    public void performAction() {
        PresetManager.getInstance().deletePreset(PresetManager.getInstance().getCurrentPreset());
    }
}

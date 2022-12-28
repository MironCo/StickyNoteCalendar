package gui.popupmenu.popupmenuitems;

import gui.button.presets.Preset;
import gui.button.presets.PresetManager;

public class AddPresetMenuItem extends PopupMenuItem {
    private static final String TITLE = "Add New Preset";
    
    public AddPresetMenuItem() {
        super(TITLE);
    }

    @Override
    public void performAction() {
        Preset newPreset = new Preset("New Preset");
        PresetManager.getInstance().addPreset(newPreset);
        PresetManager.getInstance().openPreset(newPreset);
    }
}

package gui.popupmenu.popupmenuitems;

import gui.button.presets.Preset;
import gui.button.presets.PresetManager;
import main.App;

public class OpenPresetMenuItem extends PopupMenuItem {
    Preset preset;
    
    public OpenPresetMenuItem(Preset preset) {
        super("Open Preset: " + preset.getName());
        this.preset = preset;
    }

    public void setPreset(Preset newPreset) {
        preset = newPreset;
    }

    @Override
    public void performAction() {
        PresetManager.getInstance().openPreset(preset);
        App.getMainToolbar().setPresetTitle(preset.getName());
    }
}


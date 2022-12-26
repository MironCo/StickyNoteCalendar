package gui.popupmenu.popupmenuitems;

import gui.button.presets.Preset;
import gui.button.presets.PresetManager;
import gui.colors.ColorThemeChangableUIElement;
import gui.colors.ColorThemeManager;
import main.App;

public class OpenPresetMenuItem extends PopupMenuItem implements ColorThemeChangableUIElement{
    Preset preset;
    
    public OpenPresetMenuItem(Preset preset) {
        super(preset.getName());
        this.preset = preset;

        App.addColorThemeChangeable(this);
        updateColors();
    }

    public void setPreset(Preset newPreset) {
        preset = newPreset;
    }

    @Override
    public void performAction() {
        PresetManager.getInstance().openPreset(preset);
        App.getMainToolbar().setPresetTitle(preset.getName());
    }
    
    @Override
    public void updateColors() {
        getText().setFill(ColorThemeManager.getCurrentColorTheme().textColor);
        getGraphic().setFill(ColorThemeManager.getCurrentColorTheme().buttonColor);
    }
}


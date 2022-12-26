package gui.button.presets;

import java.util.ArrayList;
import java.util.List;

import gui.button.presets.defaults.DefaultPreset;
import gui.button.presets.defaults.StudentPreset;

public class PresetManager {
    private static PresetManager instance = new PresetManager();

    private final List<Preset> presets = new ArrayList<>();

    private PresetManager() {

    }

    public static PresetManager getInstance() {
        return instance;
    }

    public void addPreset(Preset preset) {
        presets.add(preset);
    }

    public List<Preset> getPresets() {
        return presets;
    }

    public void loadDefaultPresets() {
        addPreset(new DefaultPreset());
        addPreset(new StudentPreset());
    }
}

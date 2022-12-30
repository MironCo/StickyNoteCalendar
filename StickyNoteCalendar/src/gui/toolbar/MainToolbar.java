package gui.toolbar;

import gui.button.AddNewPresetStickyNoteButton;
import gui.button.AddStickyNoteButton;
import gui.button.LastMonthButton;
import gui.button.NextMonthButton;
import gui.button.TitleButton;
import gui.button.presets.Preset;
import gui.button.presets.PresetManager;
import gui.button.presets.PresetTitleButton;

public class MainToolbar extends Toolbar {
    private PresetTitleButton presetTitleButton;

    public MainToolbar() {
        super();
        setXPosition(0);
        AddButtons();
    }

    @Override
    protected void AddButtons() {
        addButton(new TitleButton(getButtonX(), getNextY(), getButtonWidth()));

        addButton(new LastMonthButton(getButtonX(), getNextY(), getButtonWidth() / 2));
        
        addButton(new NextMonthButton(getButtons().get(1).getPosition().x + getButtonWidth() / 2, getButtons().get(1).getPosition().y, 
            getButtonWidth() / 2));
        
        addButton(new AddStickyNoteButton(getButtonX(), getNextY(), getButtonWidth()));

        presetTitleButton = new PresetTitleButton(getButtonX(), getNextY(), getButtonWidth());
        addButton(presetTitleButton);

        if (PresetManager.getInstance().getPresets().isEmpty()) {
            PresetManager.getInstance().loadDefaultPresets();
        }
        for (Preset preset : PresetManager.getInstance().getPresets()) {
            addPreset(preset);
        }

        addButton(new AddNewPresetStickyNoteButton(getButtonX(), getBottomY(), getButtonWidth()));
    }
    
    public void addPreset(Preset newPreset) {
        newPreset.addToToolbar(this);
    }

    public void setPresetTitle(String name) {
        presetTitleButton.setText(name);
    }

    public void stopEditingText() {
        presetTitleButton.stopEditingText();
        PresetManager.getInstance().getCurrentPreset().presetStickyNotes.forEach(preset -> {
            preset.stopEditingText();
        });
    }
}

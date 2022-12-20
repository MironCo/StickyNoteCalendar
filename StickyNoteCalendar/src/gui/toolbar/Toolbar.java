package gui.toolbar;

import javafx.scene.shape.Rectangle;
import main.App;
import util.Vector2;

import java.util.ArrayList;
import java.util.List;

import gui.DrawableUIElement;
import gui.button.AddStickyNoteButton;
import gui.button.GUIButton;
import gui.button.LastMonthButton;
import gui.button.NextMonthButton;
import gui.button.TitleButton;
import gui.button.presets.Preset;
import gui.button.presets.PresetTitleButton;
import gui.button.presets.defaults.DefaultPreset;
import gui.button.presets.defaults.StudentPreset;
import gui.colors.ColorThemeChangableUIElement;
import gui.colors.ColorThemeManager;

public class Toolbar extends DrawableUIElement implements ColorThemeChangableUIElement {

    public static Vector2 dimensions = new Vector2(200, (int) App.screenHeight);
    public Rectangle toolbarGraphic;

    public Preset currentPreset;
    private List<Preset> presets = new ArrayList<>();

    public List<GUIButton> buttons = new ArrayList<>();
    private Vector2 toolbarPadding = new Vector2(10, 10);
    private PresetTitleButton presetTitleButton;

    public Toolbar() {
        toolbarGraphic = new Rectangle(position.x, position.y, dimensions.x, dimensions.y);
        toolbarGraphic.setFill(ColorThemeManager.getCurrentColorTheme().toolbarColor);
        nodes.add(toolbarGraphic);
        addNodesToScene();
        App.addColorThemeChangeable(this);

        AddButtons();
    }

    private void AddButtons() {
        addButton(new TitleButton(getButtonX(), getNextY(), getButtonWidth()));

        addButton(new LastMonthButton(getButtonX(), getNextY(), getButtonWidth() / 2));
        
        addButton(new NextMonthButton(getButtons().get(1).getPosition().x + getButtonWidth() / 2, getButtons().get(1).getPosition().y, 
            getButtonWidth() / 2));
        
        addButton(new AddStickyNoteButton(getButtonX(), getNextY(), getButtonWidth()));

        presetTitleButton = new PresetTitleButton(getButtonX(), getNextY(), getButtonWidth());
        addButton(presetTitleButton);
    
        addPreset(new DefaultPreset());
        addPreset(new StudentPreset());
    }

    public List<GUIButton> getButtons() {
        return this.buttons;
    }

    public void addButton(GUIButton button) {
        buttons.add(button);
    }

    public double getButtonX() {
        return (double) toolbarGraphic.getLayoutX() + toolbarPadding.x;
    }

    public int getButtonWidth() {
        return (int)(getWidth() - (int)(toolbarPadding.x * 2));
    }

    public double getWidth() {
        return dimensions.x;
    }

    public double getNextY() {
        double lastY = toolbarPadding.y;
        if (!buttons.isEmpty()) {
            GUIButton lastButton = buttons.get(buttons.size()-1);
            lastY += lastButton.getPosition().y + GUIButton.BUTTON_HEIGHT; 
        }
        return lastY;
    }

    public double getNextY(int buttonNumber) {
        double lastY = toolbarPadding.y;
        if (!buttons.isEmpty()) {
            lastY += buttonNumber * (GUIButton.BUTTON_HEIGHT + toolbarPadding.y);
        }
        return lastY;
    }

    public void openPreset(Preset preset) {
        presets.forEach(p -> {
            p.hidePresetStickyNotes();
        });
        
        if (presets.contains(preset)) {
            currentPreset = preset;
        }
        presets.get(presets.indexOf(currentPreset)).showPresetStickyNotes();
        presetTitleButton.setText(currentPreset.getName());
    }

    public void openDefaultPreset() {
        openPreset(presets.get(0));
    }

    public List<Preset> getPresets() {
        return presets;
    }

    public void addPreset(Preset newPreset) {
        presets.add(newPreset);
        newPreset.addToToolbar(this);
    }

    @Override
    public void updateColors() {
        toolbarGraphic.setFill(ColorThemeManager.getCurrentColorTheme().toolbarColor);
    }
}

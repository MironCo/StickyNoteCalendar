package gui.toolbar;

import javafx.scene.shape.Rectangle;
import main.App;
import util.Vector2;

import java.util.ArrayList;
import java.util.List;

import gui.DrawableUIElement;
import gui.button.AddNewPresetStickyNoteButton;
import gui.button.AddStickyNoteButton;
import gui.button.GUIButton;
import gui.button.LastMonthButton;
import gui.button.NextMonthButton;
import gui.button.TitleButton;
import gui.button.presets.Preset;
import gui.button.presets.PresetManager;
import gui.button.presets.PresetTitleButton;
import gui.colors.ColorThemeChangableUIElement;
import gui.colors.ColorThemeManager;

public class Toolbar extends DrawableUIElement implements ColorThemeChangableUIElement {

    public static Vector2 dimensions = new Vector2(200, (int) App.screenHeight);
    public Rectangle toolbarGraphic;

    public List<GUIButton> buttons = new ArrayList<>();
    private Vector2 toolbarPadding = new Vector2(10, 8);
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

        if (PresetManager.getInstance().getPresets().isEmpty()) {
            PresetManager.getInstance().loadDefaultPresets();
        }
        for (Preset preset : PresetManager.getInstance().getPresets()) {
            System.out.println(preset.getName());
            addPreset(preset);
        }

        addButton(new AddNewPresetStickyNoteButton(getButtonX(), getBottomY(), getButtonWidth()));
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

    public double getBottomY() {
        return toolbarGraphic.getBoundsInLocal().getHeight() - toolbarPadding.y - GUIButton.BUTTON_HEIGHT;
    }

    public double getNextY(int buttonNumber) {
        double lastY = toolbarPadding.y;
        if (!buttons.isEmpty()) {
            lastY += buttonNumber * (GUIButton.BUTTON_HEIGHT + toolbarPadding.y);
        }
        return lastY;
    }

    public void addPreset(Preset newPreset) {
        newPreset.addToToolbar(this);
    }

    public void setPresetTitle(String name) {
        presetTitleButton.setText(name);
    }

    @Override
    public void updateColors() {
        toolbarGraphic.setFill(ColorThemeManager.getCurrentColorTheme().toolbarColor);
    }
}

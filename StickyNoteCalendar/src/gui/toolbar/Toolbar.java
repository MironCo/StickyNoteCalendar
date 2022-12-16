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
import gui.colors.ColorThemeChangableUIElement;
import gui.colors.ColorThemeManager;

public class Toolbar extends DrawableUIElement implements ColorThemeChangableUIElement {

    public static Vector2 dimensions = new Vector2(200, (int) App.screenHeight);
    public Rectangle toolbarGraphic;

    public List<GUIButton> buttons = new ArrayList<>();
    private Vector2 toolbarPadding = new Vector2(10, 10);

    public Toolbar() {
        toolbarGraphic = new Rectangle(position.x, position.y, dimensions.x, dimensions.y);
        toolbarGraphic.setFill(ColorThemeManager.getCurrentColorTheme().toolbarColor);
        nodes.add(toolbarGraphic);
        addNodesToScene();
        App.addColorThemeChangeable(this);

        AddButtons();
    }

    private void AddButtons() {
        addButton(new TitleButton(getButtonX(), getNextY(0), getButtonWidth()));

        addButton(new LastMonthButton(getButtonX(), getNextY(1), getButtonWidth() / 2));
        
        addButton(new NextMonthButton(getButtons().get(1).getPosition().x + getButtonWidth() / 2, getButtons().get(1).getPosition().y, 
            getButtonWidth() / 2));
        
        addButton(new AddStickyNoteButton(getButtonX(), getNextY(2), getButtonWidth()));
    }

    public List<GUIButton> getButtons() {
        return this.buttons;
    }

    public void addButton(GUIButton button) {
        buttons.add(button);
    }

    private double getButtonX() {
        return (double) toolbarGraphic.getLayoutX() + toolbarPadding.x;
    }

    private int getButtonWidth() {
        return (int)(getWidth() - (int)(toolbarPadding.x * 2));
    }

    public double getWidth() {
        return dimensions.x;
    }

    public double getNextY(int buttonNumber) {
        double lastY = toolbarPadding.y;
        if (!buttons.isEmpty()) {
            lastY += buttonNumber * (GUIButton.BUTTON_HEIGHT + toolbarPadding.y);
        }
        return lastY;
    }

    @Override
    public void updateColors() {
        toolbarGraphic.setFill(ColorThemeManager.getCurrentColorTheme().toolbarColor);
    }
}

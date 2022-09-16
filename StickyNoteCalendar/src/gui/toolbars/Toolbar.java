package gui.toolbars;

import javafx.scene.shape.Rectangle;
import main.App;
import util.Vector2;

import java.util.ArrayList;
import java.util.List;

import gui.DrawableUIElement;
import gui.button.AddStickyNoteButton;
import gui.button.GUIButton;
import gui.colors.ColorThemeManager;

public class Toolbar extends DrawableUIElement {

    public static Vector2 dimensions = new Vector2(200, (int) App.screenHeight);
    public Rectangle toolbarGraphic;

    public List<GUIButton> buttons = new ArrayList<>();
    private Vector2 toolbarPadding = new Vector2(10, 10);

    public Toolbar() {
        toolbarGraphic = new Rectangle(position.x, position.y, dimensions.x, dimensions.y);
        toolbarGraphic.setFill(ColorThemeManager.getCurrentColorTheme().toolbarColor);
        nodes.add(toolbarGraphic);
        addNodesToScene();

        addButton(new AddStickyNoteButton("Add Sticky Note", toolbarGraphic.getLayoutX() + toolbarPadding.x, getNextY(),
                (int) getWidth() - (int) (toolbarPadding.x * 2), 50));
        addButton(new AddStickyNoteButton("Add Sticky Note", toolbarGraphic.getLayoutX() + toolbarPadding.x, getNextY(),
                (int) getWidth() - (int) (toolbarPadding.x * 2), 50));
    }

    public List<GUIButton> getButtons() {
        return this.buttons;
    }

    public void addButton(GUIButton button) {
        buttons.add(button);
    }

    public double getWidth() {
        return dimensions.x;
    }

    public double getNextY() {
        double lastY = toolbarPadding.y;
        if (!buttons.isEmpty()) {
            lastY += buttons.get(buttons.size() - 1).getNodes().get(0).getBoundsInLocal().getHeight();
            lastY += toolbarPadding.y;
        }
        return lastY;
    }
}

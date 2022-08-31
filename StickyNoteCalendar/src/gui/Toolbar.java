package gui;

import javafx.scene.shape.Rectangle;
import main.App;
import util.Vector2;

import java.util.List;

import gui.button.GUIButton;
import gui.colors.ColorThemeManager;

public class Toolbar extends DrawableUIElement {

    public static Vector2 dimensions = new Vector2(200, (int)App.screenHeight);
    public Rectangle toolbarGraphic;

    public List<GUIButton> buttons;

    public Toolbar() {
        toolbarGraphic = new Rectangle(position.x, position.y, dimensions.x, dimensions.y);
        toolbarGraphic.setFill(ColorThemeManager.getInstance().getCurrentColorTheme().toolbarColor);
        nodes.add(toolbarGraphic);

        addNodesToScene();
    }
}

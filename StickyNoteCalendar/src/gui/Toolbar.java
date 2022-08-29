package gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.App;
import util.Vector2;

public class Toolbar extends DrawableUIElement {

    public static Vector2 dimensions = new Vector2(200, (int)App.screenHeight);
    public Rectangle toolbarGraphic;

    public Toolbar() {
        toolbarGraphic = new Rectangle(position.x, position.y, dimensions.x, dimensions.y);
        toolbarGraphic.setFill(new Color(0.85,0.86,0.89,1.0f));
        nodes.add(toolbarGraphic);

        addNodesToScene();
    }
}

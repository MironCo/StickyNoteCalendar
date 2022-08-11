package gui.stickynote;

import java.util.List;

import gui.DraggableUIElement;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import util.Vector2;

public class StickyNote extends DraggableUIElement {
    private Vector2 dimensions = new Vector2(150, 150);
    private Rectangle rectangle = new Rectangle();
    private NoteColor color = null;

    public StickyNote() {
        nodes.add(rectangle);

        color = NoteColor.PURPLE;
        position = new Vector2(10, 10);
        rectangle = new Rectangle(position.x, position.y, dimensions.x, dimensions.y);
        nodes.add(rectangle);
        rectangle.setFill(new Color(color.getColor().getRed() / 255.0f, color.getColor().getGreen() / 255.0f, color.getColor().getBlue() / 255.0f, 1));

        makeDraggable(rectangle);
    }
}

package gui.stickynote;

import gui.DraggableUIElement;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.App;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import util.FontManager;
import util.Vector2;

public class StickyNote extends DraggableUIElement {
    private Vector2 dimensions = new Vector2(150, 150);
    private Rectangle rectangle = new Rectangle();
    private NoteColor color = null;

    private Text noteText = new Text();
    private String noteTextContents; 
    private Vector2 noteTextOffset = new Vector2(7, 22);
    private boolean isEditing = false;

    public StickyNote() {
        nodes.add(rectangle);

        color = NoteColor.PURPLE;
        position = new Vector2(10, 10);
        rectangle = new Rectangle(position.x, position.y, dimensions.x, dimensions.y);
        rectangle.setFill(new Color(color.getColor().getRed() / 255.0f, color.getColor().getGreen() / 255.0f, color.getColor().getBlue() / 255.0f, 1));

        noteText = new Text("Sticky Note");
        noteText.setFont(FontManager.loadFont("Nunito-Regular.ttf", 20));
        noteText.setX(rectangle.getX() + noteTextOffset.x);
        noteText.setY(rectangle.getY() + noteTextOffset.y);

        nodes.add(rectangle);
        nodes.add(noteText);

        makeDraggable(rectangle);

        setClickAction();
    }

    private void setClickAction() {
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getButton().equals(MouseButton.PRIMARY)){
                    if (e.getClickCount() == 1) {

                    } else if (e.getClickCount() > 1) {
                        startEditingText();
                    }
                } 
            }
        });
    }

    public boolean isTextOutsideBounds() {
        return noteText.getBoundsInLocal().getWidth() > rectangle.getBoundsInLocal().getWidth();
    }

    public void startEditingText() {
        isEditing = true;
        App.setCurrentStickyNote(this);
    }

    public void stopEditingText() {
        isEditing = false;
        App.setCurrentStickyNote(null);
    }

    public boolean isEditing() {
        return isEditing;
    }

    public Text getStickyNoteText() {
        return noteText;
    }
}

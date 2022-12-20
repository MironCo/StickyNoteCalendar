package gui.button.presets;

import gui.button.GUIButton;
import gui.stickynote.NoteColor;
import gui.stickynote.StickyNote;
import gui.stickynote.StickyNoteManager;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import main.App;

public class AddPresetStickyNote extends GUIButton {
    String textContents = "";
    NoteColor buttonColor;
    StickyNote createdStickyNote;

    public AddPresetStickyNote(String textContents, NoteColor color, double x, double y, int width) {
        super(textContents, x, y, width);
        this.textContents = textContents;
        buttonColor = color;
        
        setOriginalColor(buttonColor.getColor());

        getButtonPane().setOnMouseDragged(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (!App.getMainToolbar().toolbarGraphic.contains(new Point2D(App.getMousePosition().x, App.getMousePosition().y))) {
                    double centerOffset = 0;
                    
                    if (createdStickyNote == null) {
                        createdStickyNote = new StickyNote(textContents);
                        if (buttonColor != null) createdStickyNote.setColor(buttonColor);
                        StickyNoteManager.getInstance().addStickyNote(createdStickyNote);
                        StickyNoteManager.getInstance().setDraggedStickyNote(createdStickyNote);
                    }

                    if (createdStickyNote != null) {
                        centerOffset = createdStickyNote.getRectangle().getBoundsInLocal().getHeight() / 2;
                        createdStickyNote.getNodes().get(0).setTranslateX(e.getSceneX() - centerOffset);
                        createdStickyNote.getNodes().get(0).setTranslateY(e.getSceneY() - centerOffset);
                    }
                } else {
                    if (createdStickyNote != null) {
                        StickyNoteManager.getInstance().deleteStickyNote(createdStickyNote);
                        createdStickyNote = null;
                    }
                }
            }
        });

        getButtonPane().setOnMouseReleased(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (createdStickyNote != null) {
                    createdStickyNote.ReleaseStickyNote();
                    createdStickyNote = null;
                }
            }
        });
    }

    @Override
    public void performAction() {

    }

    @Override
    public void updateColors() {
        buttonText.setFill(Color.BLACK);
    }
}

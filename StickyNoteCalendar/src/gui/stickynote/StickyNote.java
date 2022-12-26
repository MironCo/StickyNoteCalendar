/*
 * Program: StickyNoteCalendar
 * File: StickyNote.java
 * Usage: Contains data for the object StickyNote
 * Author: Miron Sulicz
 * Copyright: 2022 Miron Sulicz, All Rights Reserved
 */

package gui.stickynote;

import gui.DraggableUIElement;
import gui.popupmenu.PopuppableUIElement;
import gui.popupmenu.StickyNotePopupMenu;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import main.calendar.day.Day;
import main.calendar.day.DayManager;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import util.FontManager;
import util.Vector2;

public class StickyNote extends DraggableUIElement implements PopuppableUIElement {
    private Vector2 dimensions = new Vector2(150, 150);
    private Pane stickyNotePane;
    private Rectangle rectangle = new Rectangle();
    private Vector2 rectanglePadding = new Vector2(5, 5);

    private NoteColor color = null;

    private Text noteText = new Text();
    private TextArea textArea;
    private Vector2 noteTextOffset = new Vector2(7, 22);
    private boolean isEditing = false;
    private boolean isFull = false;

    public StickyNote() {
        stickyNotePane = new Pane();
        color = StickyNoteManager.getRandomNoteColor();
        position = new Vector2(10, 10);

        rectangle = new Rectangle(position.x, position.y, dimensions.x, dimensions.y);

        rectangle.setFill(color.getColor());

        noteText = new Text("Sticky Note");
        noteText.setVisible(false);

        textArea = new TextArea("test");
        textArea.setFont(FontManager.loadFont("Nunito-Regular.ttf", 19));
        textArea.setLayoutX(rectangle.getX() + rectanglePadding.x);
        textArea.setLayoutY(rectangle.getY() + rectanglePadding.y);

        textArea.setEditable(false);
        textArea.setMouseTransparent(true);

        double size = rectangle.getBoundsInLocal().getWidth() - (2*rectanglePadding.x);

        textArea.setMaxSize(size, size);
        textArea.setWrapText(true);

        stickyNotePane.getChildren().addAll(rectangle, noteText, textArea);
        nodes.add(stickyNotePane);
        makeDraggable(stickyNotePane);

        setClickAction();
        setReleaseAction();
        setPopupMenu();

        addNodesToScene();
    }

    public StickyNote(String noteText) {
        this();
        this.textArea.setText(noteText);
    }

    private void setReleaseAction() {
        stickyNotePane.setOnMouseReleased(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                ReleaseStickyNote();
            }
        });
    }

    private void setClickAction() {
        for (Node n : getNodes()) {
            n.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                    if (e.getButton().equals(MouseButton.PRIMARY)) {
                        if (e.getClickCount() == 1) {
                            stopEditingText();
                        } else if (e.getClickCount() > 1) {
                            startEditingText();
                        }
                    }
                    setAsRightClicked(e);
                }
            });
        }
    }

    public boolean isTextOutsideBounds() {
        return noteText.getBoundsInLocal()
                .getWidth() > (rectangle.getBoundsInLocal().getWidth() - rectanglePadding.x * 2);
    }

    public boolean isStickyNoteFull() {
        return noteText.getBoundsInLocal()
                .getHeight() > (rectangle.getBoundsInLocal().getHeight() - rectanglePadding.y * 2);
    }

    public void startEditingText() {
        isEditing = true;
        if (textArea.getText().equals("Sticky Note")) {
            textArea.setText("");
        }
        textArea.setMouseTransparent(false);
        textArea.setEditable(true);
        StickyNoteManager.getInstance().setCurrentlyEditingStickyNote(this);
    }

    public void stopEditingText() {
        isEditing = false;
        textArea.setMouseTransparent(true);
        textArea.setEditable(false);
        StickyNoteManager.getInstance().setCurrentlyEditingStickyNote(null);
    }

    public boolean isEditing() {
        return isEditing;
    }

    public String getStickyNoteText() {
        return textArea.getText();
    }

    public void ChangeStickNoteText(KeyEvent key) {
        if (key.getCode() == KeyCode.BACK_SPACE && noteText.getText().length() > 0) {
            noteText.setText(noteText.getText().substring(0, noteText.getText().length() - 1));
            if (isFull) isFull = false;
        } else if (!isFull) {
            noteText.setText(noteText.getText() + key.getText());
            if (isTextOutsideBounds()) {
                noteText.setText(noteText.getText().substring(0, noteText.getText().length() - 1));
                noteText.setText(noteText.getText() + "\n");
                noteText.setText(noteText.getText() + key.getText());
            }
            if (isStickyNoteFull()) {
                noteText.setText(noteText.getText().substring(0, noteText.getText().length() - 1));
                isFull = true;
            }
        }
    }

    public void ReleaseStickyNote() {
        Day mouseOverDay = DayManager.getInstance().getHoveredDay();
        setMouseTransparent(false);
        if (mouseOverDay != null) mouseOverDay.AddStickyNote(this);
        StickyNoteManager.getInstance().setDraggedStickyNote(null);
    }

    @Override
    public void setPosition(Vector2 _position) {
        position = _position;
    }

    public void hideMainStickyNote() {
        for (Node n : nodes) {
            n.setVisible(false);
        }
    }

    public void showMainStickyNote() {
        for (Node n : nodes) {
            n.setVisible(true);
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public NoteColor getColor() {
        return color;
    }

    public void setColor(NoteColor newColor) {
        this.color = newColor;
        rectangle.setFill(color.getColor());
    }

    @Override
    public void setPopupMenu() {
        popupMenu = StickyNotePopupMenu.getInstance();
    }

    @Override
    public void setAsRightClicked(MouseEvent e) {
        if (popupMenu != null) {
            popupMenu.show(e.getSceneX(), e.getSceneY());
            StickyNoteManager.getInstance().setRightClickedStickyNote(this);
        }
    }
}
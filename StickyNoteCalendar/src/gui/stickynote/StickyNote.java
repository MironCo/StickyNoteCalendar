/*
 * Program: StickyNoteCalendar
 * File: StickyNote.java
 * Usage: Contains data for the object StickyNote
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */

package gui.stickynote;

import gui.DraggableUIElement;
import gui.popupmenu.PopuppableUIElement;
import gui.popupmenu.StickyNotePopupMenu;
import javafx.scene.shape.Rectangle;
import main.App;
import main.calendar.day.Day;
import main.calendar.day.DayManager;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import util.FontManager;
import util.Vector2;

public class StickyNote extends DraggableUIElement implements PopuppableUIElement {
    public static final Vector2 DIMENSIONS = new Vector2(150 * App.multiplier, 150 * App.multiplier);
    private Pane stickyNotePane;
    private Rectangle rectangle = new Rectangle();
    private Vector2 rectanglePadding = new Vector2(5, 5);

    private NoteColor color = null;

    private TextArea textArea;
    private boolean isEditing = false;
    public boolean isOnToolbar = false;

    private Day connectedDay = null;

    public StickyNote() {
        stickyNotePane = new Pane();
        color = StickyNoteManager.getRandomNoteColor();
        position = new Vector2(10, 10);

        rectangle = new Rectangle(position.x, position.y, DIMENSIONS.x, DIMENSIONS.y);

        rectangle.setFill(color.getColor());

        textArea = new TextArea("Sticky Note");
        textArea.setFont(FontManager.loadFont("Nunito-Regular.ttf", 19));
        textArea.setLayoutX(rectangle.getX() + rectanglePadding.x);
        textArea.setLayoutY(rectangle.getY() + rectanglePadding.y);

        textArea.setEditable(false);
        textArea.setMouseTransparent(true);

        double size = rectangle.getBoundsInLocal().getWidth() - (2*rectanglePadding.x);

        textArea.setMaxSize(size, size);
        textArea.setWrapText(true);

        stickyNotePane.getChildren().addAll(rectangle, textArea);
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
                }
            });
        }

        stickyNotePane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.ESCAPE) {
                stopEditingText();
                if (connectedDay != null) {
                    connectedDay.updateStickyNoteGraphic();
                }
            }
        });

        stickyNotePane.setOnScroll(e -> {
            if (isOnToolbar) {
                App.getDayToolbar().scrollToolbar(e);
            }
        });
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

    public void ReleaseStickyNote() {     
        setMouseTransparent(true);
        boolean isOnStickyNote = false;

        if (isOnToolbar) { 
            for (StickyNote other : App.getDayToolbar().getOpenDay().getStickyNotes()) {
                if (other != this && other.isOnToolbar && other.isMouseOver()) {
                    isOnStickyNote = true;
                    Day currentDay = App.getDayToolbar().getOpenDay();
                    int index = currentDay.getStickyNotes().indexOf(this);
                    currentDay.getStickyNotes().set(currentDay.getStickyNotes().indexOf(other), this);
                    currentDay.getStickyNotes().set(index, other);
                    currentDay.updateStickyNoteGraphic();
                    App.getDayToolbar().refreshStickyNotes();
                    break;
                }
            }
        
            if (!isOnStickyNote && !App.getDayToolbar().isMouseOver()) {
                isOnToolbar = false; 
                App.getDayToolbar().removeStickyNote(this);
            } else if (!isOnStickyNote) {
                App.getDayToolbar().refreshStickyNotes();
            }
        } else if (!isOnToolbar && App.getDayToolbar().isMouseOver()) {
            App.getDayToolbar().getOpenDay().AddStickyNote(this);
            App.getDayToolbar().refreshStickyNotes();
        }

        if (!isOnToolbar) {
            connectedDay = DayManager.getInstance().getHoveredDay();
            if (connectedDay != null) {
                connectedDay.AddStickyNote(this);
                if (App.getDayToolbar().getOpenDay() == connectedDay && App.getDayToolbar().isOpen()) {
                    App.getDayToolbar().refreshStickyNotes();
                }
            }
        }

        StickyNoteManager.getInstance().setDraggedStickyNote(null);
        setMouseTransparent(false);
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
        stickyNotePane.toFront();
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

        if (isOnToolbar) {
            connectedDay.updateStickyNoteGraphic();
        }
    }

    public Pane getGraphic() {
        return stickyNotePane;
    }

    public boolean isOnToolbar() {
        return isOnToolbar;
    }

    public void setConnectedDay(Day day) {
        this.connectedDay = day;
    }

    @Override
    public void setPopupMenu() {
        popupMenu = StickyNotePopupMenu.getInstance().getContextMenu();

        stickyNotePane.setOnContextMenuRequested(e -> {
            StickyNoteManager.getInstance().setRightClickedStickyNote(this);
            popupMenu.show(stickyNotePane, e.getScreenX(), e.getScreenY());
        });
    }

    public void delete() {
        hideMainStickyNote();
        if (connectedDay != null) {
            connectedDay.getStickyNotes().remove(this);
            connectedDay.updateStickyNoteGraphic();
        }
        if (isOnToolbar) {
            isOnToolbar = false;
            App.getDayToolbar().removeStickyNote(this);
            if (connectedDay != null && connectedDay.getStickyNotes().isEmpty()) {
                App.getDayToolbar().closeDayToolbar();
            }
        }
    }

    public boolean isMouseOver() {
        boolean contains = false;
        
        double mouseX = App.getMousePosition().x;
        double mouseY = App.getMousePosition().y;

        if (mouseX > stickyNotePane.getTranslateX() && mouseX < stickyNotePane.getTranslateX() + StickyNote.DIMENSIONS.x) {
            if (mouseY > stickyNotePane.getTranslateY() && mouseY < stickyNotePane.getTranslateY() + StickyNote.DIMENSIONS.y) {
                contains = true;
            }
        }

        return contains;
    }

    public Pane getStickyNotePane() {
        return stickyNotePane;
    }

    public void bringToFront() {
        stickyNotePane.toFront();
    }
}
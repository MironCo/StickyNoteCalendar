/*
 * Program: StickyNoteCalendar
 * File: DayStickyNoteGraphic.java
 * Usage: Contains data for the object DayStickyNoteGraphic
 * Author: Miron Sulicz
 * Copyright: 2022 Miron Sulicz, All Rights Reserved
 */

package gui.stickynote;

import gui.DraggableUIElement;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.App;
import main.calendar.Calendar;
import main.calendar.day.Day;
import util.Vector2;

public class DayStickyNoteGraphic extends DraggableUIElement {
    public Rectangle rectangle = null;
    private Day connectedDay = null;
    private StickyNote connectedStickyNote = null;
    private boolean isOnDay = true;
    private Vector2 rectPosition;
    private Vector2 connectedOffset = new Vector2(0, 0);

    public DayStickyNoteGraphic(Vector2 _pos) {
        Calendar calendar = Calendar.getInstance();
        rectPosition = new Vector2(_pos.x + ((calendar.dayDimensions.x - Calendar.DAY_STICKY_NOTE_SIZE) / 2), 
            _pos.y + ((calendar.dayDimensions.y - Calendar.DAY_STICKY_NOTE_SIZE) / 2));
        rectangle = new Rectangle(rectPosition.x, rectPosition.y, Calendar.DAY_STICKY_NOTE_SIZE,
        Calendar.DAY_STICKY_NOTE_SIZE);
        rectangle.setFill(Color.WHITE);
        
        addNode(rectangle);

        rectangle.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                startX = e.getSceneX() - rectPosition.x; 
                startY = e.getSceneY() - rectPosition.y;

                connectedOffset.x = (startX / rectangle.getBoundsInLocal().getWidth()) * connectedStickyNote.getRectangle().getBoundsInLocal().getWidth();
                connectedOffset.y = (startY / rectangle.getBoundsInLocal().getHeight()) * connectedStickyNote.getRectangle().getBoundsInLocal().getHeight();
            }
        });

        rectangle.setOnMouseDragged(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (!connectedDay.rectangle.contains(new Point2D(App.getMousePosition().x, App.getMousePosition().y))) {
                    removeFromTop();
                } else {
                    returnToTop();
                }
                if (connectedStickyNote.isVisible()) {
                    for (Node n : connectedStickyNote.getNodes()) {
                        n.setTranslateX(e.getSceneX() - connectedOffset.x);
                        n.setTranslateY(e.getSceneY() - connectedOffset.y);
                    }
                }
            }
        });

        rectangle.setOnMouseReleased(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (connectedStickyNote.isVisible()) {
                    connectedStickyNote.ReleaseStickyNote();
                }
            }
        });

        rectangle.setOnScroll(e -> {
            connectedDay.scrollThroughStickyNotes(e.getDeltaY());
        });
    }

    @Override
    public void setVisible(boolean isVisible) {
        rectangle.setVisible(isVisible);
        if (isVisible) isOnDay = isVisible;
    }

    public void setDay(Day newDay) {
        connectedDay = newDay;
    }

    public void setStickyNote(StickyNote note) {
        connectedStickyNote = note;
    }

    public void removeFromTop() {
        if (isOnDay && connectedStickyNote != null && StickyNoteManager.getInstance().getDraggedStickyNote() == null) {
            isOnDay = false;
            connectedStickyNote = connectedDay.stickyNotes.remove(0);
            StickyNoteManager.getInstance().setDraggedStickyNote(connectedStickyNote);
            connectedStickyNote.setVisible(true);
            connectedDay.updateStickyNoteGraphic();
        }
    }

    public void returnToTop() {
        if (!isOnDay && connectedStickyNote != null) {
            isOnDay = true;
            connectedDay.stickyNotes.add(0, connectedStickyNote);
            StickyNoteManager.getInstance().setDraggedStickyNote(null);
            connectedStickyNote.setVisible(false);
            connectedDay.updateStickyNoteGraphic();
        }
    }
}

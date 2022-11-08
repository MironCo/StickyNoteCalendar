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

    public DayStickyNoteGraphic(Vector2 _pos) {
        Calendar calendar = Calendar.getInstance();

        rectangle = new Rectangle(_pos.x + ((calendar.dayDimensions.x - calendar.dayStickyNoteSize) / 2),
        _pos.y + ((calendar.dayDimensions.y - calendar.dayStickyNoteSize) / 2), calendar.dayStickyNoteSize,
        calendar.dayStickyNoteSize);
        rectangle.setFill(Color.WHITE);
        
        addNode(rectangle);

        rectangle.setOnMouseClicked(e -> {
            startX = e.getSceneX() - rectangle.getTranslateX();
            startY = e.getSceneY() - rectangle.getTranslateY();
        });

        rectangle.setOnMouseDragged(e -> {
            if (!connectedDay.rectangle.contains(new Point2D(App.getMousePosition().x, App.getMousePosition().y))) {
                removeFromTop();
            } else {
                returnToTop();
            }
            if (connectedStickyNote.isVisible()) {
                for (Node n : connectedStickyNote.getNodes()) {
                    n.setTranslateX(e.getSceneX());
                    n.setTranslateY(e.getSceneY());
                }
            }
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

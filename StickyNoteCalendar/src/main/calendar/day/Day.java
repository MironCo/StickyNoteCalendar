/*
 * Program: StickyNoteCalendar
 * File: Day.java
 * Usage: Contains data for the object Day
 * Author: Miron Sulicz
 * Copyright: 2022 Miron Sulicz, All Rights Reserved
 */

package main.calendar.day;

import java.util.ArrayList;
import java.util.List;

import gui.DrawableUIElement;
import gui.colors.ColorThemeChangableUIElement;
import gui.colors.ColorThemeManager;
import gui.stickynote.DayStickyNoteGraphic;
import gui.stickynote.StickyNote;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Day extends DrawableUIElement implements ColorThemeChangableUIElement{
    public Integer day = 0;
    public Rectangle rectangle = new Rectangle();
    public DayStickyNoteGraphic dayStickyNote = null;
    public Text dayNumberText = new Text();
    public List<StickyNote> stickyNotes = new ArrayList<StickyNote>();

    public Day() {

    }

    public void AddStickyNote(StickyNote note) {
        stickyNotes.add(0, note);
        note.hideMainStickyNote();
        updateStickyNoteGraphic();
    }

    public Day getDay() {
        return this;
    }

    @Override
    public void setVisible(boolean isVisible) {
        for(Node n : getNodes()) {
            n.setVisible(isVisible);
        }

        updateStickyNoteGraphic();

        if (!isVisible) {
            dayStickyNote.setVisible(false);
        }
    }
    
    @Override
    public boolean isVisible() {
        boolean isVisible = true;
        isVisible &= rectangle.isVisible();
        isVisible &= dayNumberText.isVisible();

        return isVisible;
    } 

    public void updateStickyNoteGraphic() {
        if (stickyNotes.isEmpty()) {
            rectangle.setMouseTransparent(false);
            dayStickyNote.setVisible(false);
        } else {
            rectangle.setMouseTransparent(true);
            dayStickyNote.rectangle.setFill(stickyNotes.get(0).getRectangle().getFill());
            dayStickyNote.setVisible(true);
            dayStickyNote.setStickyNote(stickyNotes.get(0));
        }
    }

    public void scrollThroughStickyNotes(double direction) {
        if (direction > 0) {
            stickyNotes.add(stickyNotes.remove(0));
        } else if (direction < 0) {
            stickyNotes.add(0, stickyNotes.remove(stickyNotes.size()-1));
        }
        updateStickyNoteGraphic();
    }

    public List<StickyNote> getStickyNotes() {
        return stickyNotes;
    }

    @Override
    public void updateColors() {
        dayNumberText.setFill(ColorThemeManager.getCurrentColorTheme().textColor);
        rectangle.setStroke(ColorThemeManager.getCurrentColorTheme().borderColor);
    }
}

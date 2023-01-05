/*
 * Program: StickyNoteCalendar
 * File: AddStickyNoteButton.java
 * Usage: Button to add a new Sticky Note to the calendar.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.button;

import gui.stickynote.StickyNote;
import gui.stickynote.StickyNoteManager;
import util.Vector2;

public class AddStickyNoteButton extends GUIButton {
    Vector2 dimesions = new Vector2(50, 0);

    public AddStickyNoteButton(double x, double y, int width) {
        super("Add Sticky Note", x, y, width);
    }

    @Override
    public void performAction() {
        StickyNote note = new StickyNote();
        StickyNoteManager.getInstance().addStickyNote(note);
    }
}

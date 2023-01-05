/*
 * Program: StickyNoteCalendar
 * File: NextMonthButton.java
 * Usage: Button to go to the next month.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.button;

import main.calendar.Calendar;

public class NextMonthButton extends GUIButton {

    public NextMonthButton(double x, double y, int width) {
        super("->", x, y, width);
    }

    @Override
    public void performAction() {
        Calendar.getInstance().goToNextMonth();
    }
    
}

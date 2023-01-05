/*
 * Program: StickyNoteCalendar
 * File: LastMonthButton.java
 * Usage: Button to go to the previous month.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.button;

import main.calendar.Calendar;

public class LastMonthButton extends GUIButton {

    public LastMonthButton(double x, double y, int width) {
        super("<-", x, y, width);
    }

    @Override
    public void performAction() {
        // TODO Auto-generated method stub
        Calendar.getInstance().goToLastMonth();
    }
}

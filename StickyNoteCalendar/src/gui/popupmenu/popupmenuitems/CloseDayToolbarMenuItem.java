/*
 * Program: StickyNoteCalendar
 * File: CloseDayToolbarMenuItem.java
 * Usage: Class that contains the MenuItem to close the DayToolbar.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu.popupmenuitems;

import main.App;

public class CloseDayToolbarMenuItem extends PopupMenuItem {
    private static final String TITLE = "Close";
    
    public CloseDayToolbarMenuItem() {
        super(TITLE);
    }

    @Override
    public void performAction() {
        App.getDayToolbar().closeDayToolbar();
    }
}

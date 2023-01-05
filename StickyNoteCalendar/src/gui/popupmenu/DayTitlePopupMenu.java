/*
 * Program: StickyNoteCalendar
 * File: DayTitlePopupMenu.java
 * Usage: Class that contains the Context menu for the DayTitle.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu;

import gui.popupmenu.popupmenuitems.ClearStickyNotesMenuItem;
import gui.popupmenu.popupmenuitems.CloseDayToolbarMenuItem;

public class DayTitlePopupMenu extends PopupMenu {
    private final static DayTitlePopupMenu instance = new DayTitlePopupMenu();


    private DayTitlePopupMenu() {
        super();
        addButtons();
    }

    public static final DayTitlePopupMenu getInstance() {
        return instance;
    }

    @Override
    protected void addButtons() {
        addMenuItem(new ClearStickyNotesMenuItem().getMenuItem());
        addMenuItem(new CloseDayToolbarMenuItem().getMenuItem());
    }
}

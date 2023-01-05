/*
 * Program: StickyNoteCalendar
 * File: TitlePopupMenu.java
 * Usage: Class that contains the Context menu for the Title.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu;

import gui.colors.DarkColorTheme;
import gui.colors.LightColorTheme;
import gui.popupmenu.popupmenuitems.ChangeColorThemeMenuItem;

public class TitlePopupMenu extends PopupMenu {
    private final static TitlePopupMenu instance = new TitlePopupMenu();


    private TitlePopupMenu() {
        super();
        addButtons();
    }

    public static final TitlePopupMenu getInstance() {
        return instance;
    }

    @Override
    protected void addButtons() {
        addMenuItem(new ChangeColorThemeMenuItem(new LightColorTheme()).getMenuItem());
        addMenuItem(new ChangeColorThemeMenuItem(new DarkColorTheme()).getMenuItem());
    }
}

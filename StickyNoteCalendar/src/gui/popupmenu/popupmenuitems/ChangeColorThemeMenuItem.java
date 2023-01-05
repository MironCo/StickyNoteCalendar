/*
 * Program: StickyNoteCalendar
 * File: ChangeColorThemeMenuItem.java
 * Usage: Class that contains the MenuItem to change the color theme.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.popupmenu.popupmenuitems;

import gui.colors.ColorTheme;
import main.App;

public class ChangeColorThemeMenuItem extends PopupMenuItem {
    ColorTheme colorTheme;
    
    public ChangeColorThemeMenuItem(ColorTheme colorTheme) {
        super(colorTheme.themeName);
        this.colorTheme = colorTheme;
    }

    @Override
    public void performAction() {
        App.updateColorTheme(colorTheme);
    }
}

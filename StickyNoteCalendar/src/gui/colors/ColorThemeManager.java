/*
 * Program: StickyNoteCalendar
 * File: ColorThemeManager.java
 * Usage: Manager of color themes that contains the current ColorTheme.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.colors;

public class ColorThemeManager {
    private static ColorTheme currentColorTheme;
    
    public static ColorTheme getCurrentColorTheme() {
        return currentColorTheme;
    }

    public static void setCurrentColorTheme(ColorTheme colorTheme) {
        currentColorTheme = colorTheme;
    }

    public static ColorTheme getColorThemeFromName(String name) {
        if (name.equals("Light")) {
            return new LightColorTheme();
        } else if (name.equals("Dark")) {
            return new DarkColorTheme();
        }
        else return new DarkColorTheme();
    }
}  

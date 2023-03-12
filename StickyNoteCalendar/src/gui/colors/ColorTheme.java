/*
 * Program: StickyNoteCalendar
 * File: ColorTheme.java
 * Usage: Base class of all ColorThemes.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.colors;

import javafx.scene.paint.Color;

public abstract class ColorTheme {
    public String themeName;
    public Color backgroundColor;
    public Color textColor;
    public Color borderColor;
    public Color toolbarColor;
    public Color buttonColor;
    public Color buttonHighlightColor;
    public Color popupMenuColor;
    public Color popupMenuTextColor;
    public Color highlightedColor;
}

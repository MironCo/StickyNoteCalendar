/*
 * Program: StickyNoteCalendar
 * File: LightColorTheme.java
 * Usage: Class that defines the colors of the Light color theme.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.colors;

import javafx.scene.paint.Color;

public class LightColorTheme extends ColorTheme {
    public LightColorTheme() {
        themeName = "Light";
        backgroundColor = new Color(0.92,0.92,0.95,1.0f);;
        textColor = Color.BLACK;
        borderColor = Color.DARKGRAY;
        toolbarColor = Color.WHITESMOKE;
        buttonColor = Color.rgb(210, 210, 220);
        buttonHighlightColor = Color.rgb(210, 210, 250);
        popupMenuColor = Color.rgb(200, 200, 230);
        popupMenuTextColor = Color.BLACK;
    }
} 

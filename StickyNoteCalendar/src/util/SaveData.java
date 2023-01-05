/*
 * Program: StickyNoteCalendar
 * File: SaveData.java
 * Usage: Utility class containing data for SNC saves.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package util;

public class SaveData {
    public static final String USER_DIRECTORY = System.getProperty("user.home");
    public static final String SAVE_FOLDER = "StickyNoteCalendar";
    public static final String SAVE_FILE_NAME = "SNC.save";

    public static final String COLOR_THEME_TYPE = "COLOR_THEME:";
    public static final String MONTH_TYPE = "MONTH:";
    public static final String DAY_TYPE = "DAY:";
    public static final String STICKY_NOTE_TYPE = "STICKY_NOTE:";

    public static final String PRESET_FILE_NAME = "SNC.presets";
    public static final String PRESET_TYPE = "PRESET:";
    public static final String PRESET_STICKY_NOTE_TYPE="PSN:";
}

/*
 * Program: StickyNoteCalendar
 * File: FontManager.java
 * Usage: Utility class for loading fonts.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.FileSystems;

import javafx.scene.text.Font;

public class FontManager {
    private static final String FONT_FOLDER_PATH = FileSystems.getDefault().getPath("").toAbsolutePath().toString() + File.separatorChar + "Fonts";

    public static Font loadFont(String name, int size) {
        try {
            File fontFile = new File(FONT_FOLDER_PATH + File.separatorChar + name);
            InputStream fontInput = null;
            fontInput = new FileInputStream(fontFile);
            Font fontToLoad = Font.loadFont(fontInput, size);
            return fontToLoad;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Font.getDefault();
        }
    }
}

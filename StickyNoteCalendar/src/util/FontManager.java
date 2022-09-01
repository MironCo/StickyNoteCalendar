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

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

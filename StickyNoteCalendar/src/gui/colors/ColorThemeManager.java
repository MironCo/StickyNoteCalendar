package gui.colors;


public class ColorThemeManager {
    private static ColorTheme currentColorTheme;
    
    public static ColorTheme getCurrentColorTheme() {
        return currentColorTheme;
    }

    public static void setCurrentColorTheme(ColorTheme colorTheme) {
        currentColorTheme = colorTheme;
    }
}  

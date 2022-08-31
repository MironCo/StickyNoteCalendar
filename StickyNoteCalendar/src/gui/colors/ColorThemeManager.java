package gui.colors;

public class ColorThemeManager {
    private static ColorThemeManager instance = new ColorThemeManager();

    private ColorTheme currentColorTheme;

    private ColorThemeManager() {
        setCurrentColorTheme(new DarkColorTheme());
    }

    public static ColorThemeManager getInstance() {
        return instance;
    }

    public ColorTheme getCurrentColorTheme() {
        return currentColorTheme;
    }

    public void setCurrentColorTheme(ColorTheme colorTheme) {
        this.currentColorTheme = colorTheme;
    }
}  

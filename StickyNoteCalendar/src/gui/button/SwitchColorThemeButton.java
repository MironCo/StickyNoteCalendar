package gui.button;

import gui.colors.ColorTheme;
import gui.colors.ColorThemeManager;
import gui.toolbar.Toolbar;
import javafx.scene.shape.Rectangle;
import main.App;

public class SwitchColorThemeButton extends GUIButton {
    private ColorTheme theme;

    private SwitchColorThemeButton otherThemeButton;
    public Rectangle bottomRect;

    public SwitchColorThemeButton(ColorTheme theme, double x, double y, int width) {
        super(theme.themeName, x, y, width);
        this.theme = theme;
        bottomRect = new Rectangle(-10, -10, Toolbar.DIMENSIONS.x/2, BUTTON_HEIGHT*1.5);
        bottomRect.setMouseTransparent(true);
        bottomRect.setFill(ColorThemeManager.getCurrentColorTheme().toolbarColor);
        getButtonPane().getChildren().add(bottomRect);

        graphic.toFront();
        buttonText.toFront();
    }

    @Override
    public void performAction() {
        if (theme != null) {
            App.updateColorTheme(theme);
            graphic.setFill(ColorThemeManager.getCurrentColorTheme().buttonHighlightColor);

            if (otherThemeButton != null) {
                otherThemeButton.graphic.setFill(ColorThemeManager.getCurrentColorTheme().buttonColor);
            }
        }
    }

    @Override
    public void updateColors() {
        bottomRect.setFill(ColorThemeManager.getCurrentColorTheme().toolbarColor);
        buttonText.setFill(ColorThemeManager.getCurrentColorTheme().textColor);
        graphic.setFill(ColorThemeManager.getCurrentColorTheme().buttonColor);
        setOriginalColor(ColorThemeManager.getCurrentColorTheme().buttonColor);
    }

    public void bringToFront() {
        getButtonPane().toFront();
    }
}

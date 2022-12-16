package gui.popupmenu.popupmenuitems;

import gui.colors.ColorTheme;
import main.App;

public class ChangeColorThemeMenuItem extends PopupMenuItem {
    ColorTheme colorTheme;
    
    public ChangeColorThemeMenuItem(ColorTheme colorTheme) {
        super(colorTheme.themeName);
        this.colorTheme = colorTheme;
        
        getGraphic().setFill(colorTheme.backgroundColor);
        getText().setFill(colorTheme.textColor);
    }

    @Override
    public void performAction() {
        App.updateColorTheme(colorTheme);
        connectedPopupMenu.hide();
    }
}

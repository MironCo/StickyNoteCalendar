package gui.popupmenu;

import gui.colors.DarkColorTheme;
import gui.colors.LightColorTheme;
import gui.popupmenu.popupmenuitems.ChangeColorThemeMenuItem;

public class TitlePopupMenu extends PopupMenu {
    private final static TitlePopupMenu instance = new TitlePopupMenu();


    private TitlePopupMenu() {
        super();
        addButtons();
    }

    public static final TitlePopupMenu getInstance() {
        return instance;
    }

    @Override
    protected void addButtons() {
        addMenuItem(new ChangeColorThemeMenuItem(new LightColorTheme()));
        addMenuItem(new ChangeColorThemeMenuItem(new DarkColorTheme()));
        calculateHeight();
    }
}

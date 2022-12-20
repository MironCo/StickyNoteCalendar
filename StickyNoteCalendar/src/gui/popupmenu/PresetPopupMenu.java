package gui.popupmenu;

import gui.popupmenu.popupmenuitems.OpenPresetMenuItem;

public class PresetPopupMenu extends PopupMenu {
    private final static PresetPopupMenu instance = new PresetPopupMenu();

    private PresetPopupMenu() {
        super();
    }

    public static final PresetPopupMenu getInstance() {
        return instance;
    }

    public void addPresetButton(OpenPresetMenuItem item) {
        addMenuItem(item);
        calculateHeight();
    }

    @Override
    protected void addButtons() {
        calculateHeight();
    }
}

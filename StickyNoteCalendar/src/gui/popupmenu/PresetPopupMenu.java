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
        addMenuItem(item.getMenuItem());
    }

    @Override
    protected void addButtons() {
        
    }
}
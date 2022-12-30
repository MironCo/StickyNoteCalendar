package gui.popupmenu.popupmenuitems;

import main.App;

public class CloseDayToolbarMenuItem extends PopupMenuItem {
    private static final String TITLE = "Close";
    
    public CloseDayToolbarMenuItem() {
        super(TITLE);
    }

    @Override
    public void performAction() {
        App.getDayToolbar().closeDayToolbar();
    }
}

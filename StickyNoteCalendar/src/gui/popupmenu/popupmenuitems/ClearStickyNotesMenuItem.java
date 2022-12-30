package gui.popupmenu.popupmenuitems;

import main.App;

public class ClearStickyNotesMenuItem extends PopupMenuItem {
    private static final String TITLE = "Clear Sticky Notes";
    
    public ClearStickyNotesMenuItem() {
        super(TITLE);
    }

    @Override
    public void performAction() {
        App.getDayToolbar().clearStickyNotes();
    }
}

package gui.popupmenu;

public class PresetPopupMenu extends PopupMenu {
    private final static PresetPopupMenu instance = new PresetPopupMenu();


    private PresetPopupMenu() {
        super();
        addButtons();
    }

    public static final PresetPopupMenu getInstance() {
        return instance;
    }

    @Override
    protected void addButtons() {
        
        calculateHeight();
    }
}

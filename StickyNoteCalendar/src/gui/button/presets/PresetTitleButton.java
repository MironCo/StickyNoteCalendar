package gui.button.presets;

import gui.button.GUIButton;
import gui.popupmenu.PopuppableUIElement;
import gui.popupmenu.PresetPopupMenu;
import javafx.scene.input.MouseButton;
import util.Vector2;

public class PresetTitleButton extends GUIButton implements PopuppableUIElement  {
    private static final String TITLE = "Presets";
    Vector2 dimesions = new Vector2(50, 0);

    public PresetTitleButton(double x, double y, int width) {
        super(TITLE, x, y, width);
        graphic.setVisible(false);

        setPopupMenu();

        getNodes().get(0).setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                performAction();
            }
        });
    }

    @Override
    public void performAction() {

    }

    @Override
    public void setPopupMenu() {
        popupMenu = PresetPopupMenu.getInstance().getContextMenu();

        getButtonPane().setOnContextMenuRequested(e -> {
            popupMenu.show(getButtonPane(), e.getScreenX(), e.getScreenY());
        });
    }

    public void setText(String text) {
        buttonText.setText(text);
    }
}

package gui.button.presets;

import gui.button.GUIButton;
import gui.popupmenu.PopuppableUIElement;
import gui.popupmenu.TitlePopupMenu;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import util.Vector2;

public class PresetTitleButton extends GUIButton implements PopuppableUIElement  {
    private static final String TITLE = "Presets";
    Vector2 dimesions = new Vector2(50, 0);

    public PresetTitleButton(double x, double y, int width) {
        super(TITLE, x, y, width);
        graphic.setVisible(false);

        setPopupMenu();

        getNodes().get(0).setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                setAsRightClicked(e);
            } else if (e.getButton() == MouseButton.PRIMARY) {
                performAction();
            }
        });
    }

    @Override
    public void performAction() {

    }

    @Override
    public void setPopupMenu() {
        popupMenu = TitlePopupMenu.getInstance();
    }

    @Override
    public void setAsRightClicked(MouseEvent e) {
        if (popupMenu != null) {
            popupMenu.show(e.getSceneX(), e.getSceneY());
        }
    }

    public void setText(String text) {
        buttonText.setText(text);
    }
}

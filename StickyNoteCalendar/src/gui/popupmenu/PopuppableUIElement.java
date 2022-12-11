package gui.popupmenu;

import javafx.scene.input.MouseEvent;

public interface PopuppableUIElement {
    public abstract void setPopupMenu();

    public abstract void setAsRightClicked(MouseEvent e);
}

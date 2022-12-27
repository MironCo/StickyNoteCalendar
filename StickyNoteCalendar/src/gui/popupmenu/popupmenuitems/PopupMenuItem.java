package gui.popupmenu.popupmenuitems;

import gui.DrawableUIElement;
import javafx.scene.control.MenuItem;

public abstract class PopupMenuItem extends DrawableUIElement {
    MenuItem menuItem;

    public PopupMenuItem(String title) {
        menuItem = new MenuItem(title);

        menuItem.setOnAction(e -> {
            performAction();
        });
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public abstract void performAction();
}

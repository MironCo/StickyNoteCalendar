package gui.popupmenu;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import gui.DrawableUIElement;
import gui.colors.ColorThemeManager;
import gui.popupmenu.popupmenuitems.PopupMenuItem;
import util.Vector2;

public abstract class PopupMenu extends DrawableUIElement {
    public static final double POPUP_MENU_WIDTH = 200;
    public static final Vector2 padding = new Vector2(10.0, 10.0);

    Pane popupMenu;
    Rectangle popupMenuWindow;
    List<PopupMenuItem> menuItems;
    Vector2 dimensions = new Vector2(POPUP_MENU_WIDTH, 300);

    public PopupMenu() {
        popupMenu = new Pane();
        popupMenuWindow = new Rectangle(position.x, position.y, dimensions.x, dimensions.y);
        popupMenuWindow.setFill(ColorThemeManager.getCurrentColorTheme().popupMenuColor);
        
        menuItems = new ArrayList<>();
        popupMenu.getChildren().addAll(popupMenuWindow);
        addNode(popupMenu);

        addNodesToScene();
        hide();
    }

    protected void addMenuItem(PopupMenuItem newMenuItem) {
        menuItems.add(newMenuItem);
    }

    protected abstract void addButtons();

    public void hide() {
        if (isVisible()) {
            setVisible(false);
        }
    }

    public void show(double x, double y) {
        popupMenu.setLayoutX(x);
        popupMenu.setLayoutY(y);
        getNodes().forEach(node -> {
            node.toFront();
        });
        setVisible(true);
    }

    protected void calculateHeight() {
        double yHeight = padding.y + ((PopupMenuItem.POPUP_MENU_ITEM_HEIGHT + padding.y) * menuItems.size());
        popupMenuWindow.setHeight(yHeight);

        for (int i = 0; i < menuItems.size(); i++) {
            menuItems.get(i).parentToPane(popupMenu, padding.x, i * (padding.y + PopupMenuItem.POPUP_MENU_ITEM_HEIGHT) + padding.y);
        }
    }
}
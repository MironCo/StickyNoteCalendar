package gui.popupmenu.popupmenuitems;

import gui.DrawableUIElement;
import gui.colors.ColorThemeManager;
import gui.popupmenu.PopupMenu;
import javafx.geometry.VPos;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import util.FontManager;

public abstract class PopupMenuItem extends DrawableUIElement {
    public static final double POPUP_MENU_ITEM_HEIGHT = 35;

    protected PopupMenu connectedPopupMenu;
    private Pane buttonPane = new Pane();
    private Rectangle graphic = new Rectangle();
    private Text buttonText = new Text();

    public PopupMenuItem(String title) {
        buttonPane = new Pane();
        graphic = new Rectangle(PopupMenu.POPUP_MENU_WIDTH - PopupMenu.padding.y * 2, POPUP_MENU_ITEM_HEIGHT);
        graphic.setFill(ColorThemeManager.getCurrentColorTheme().buttonColor);
            
        buttonText = new Text(title);
        buttonText.setFont(FontManager.loadFont("Nunito-Regular.ttf", 20));
        buttonText.setFill(ColorThemeManager.getCurrentColorTheme().popupMenuTextColor);
        buttonText.setTextAlignment(TextAlignment.CENTER);
        buttonText.setTextOrigin(VPos.CENTER);
        buttonText.setLayoutX(graphic.getLayoutX() + (graphic.getWidth() - buttonText.getBoundsInLocal().getWidth()) / 2);
        buttonText.setLayoutY(graphic.getLayoutY() + (graphic.getHeight() / 2));

        buttonPane.getChildren().addAll(graphic, buttonText);

        buttonPane.setOnMouseClicked(e -> {
            performAction();
        });

        addNode(buttonPane);
        addNodesToScene();
    }

    public void parentToPane(Pane parent, double x, double y) {
        parent.getChildren().add(buttonPane);
        buttonPane.setLayoutX(x);
        buttonPane.setLayoutY(y);
    }

    public void setConnectedPopupMenu(PopupMenu menu) {
        connectedPopupMenu = menu;
    }

    protected Rectangle getGraphic() {
        return graphic;
    }

    protected Text getText() {
        return buttonText;
    }

    public void setText(String title) {
        buttonText.setText(title);
        buttonText.setLayoutX(graphic.getLayoutX() + (graphic.getWidth() - buttonText.getBoundsInLocal().getWidth()) / 2);
        buttonText.setLayoutY(graphic.getLayoutY() + (graphic.getHeight() / 2));
    }

    public abstract void performAction();
}

package gui.button;

import gui.DrawableUIElement;
import gui.colors.ColorThemeManager;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import util.FontManager;
import util.Vector2;

public abstract class GUIButton extends DrawableUIElement {
    protected Rectangle graphic = new Rectangle();
    protected Text buttonText = new Text();
    private Color originalColor = null;

    private Vector2 textPadding = new Vector2(10, 10);

    public GUIButton(String buttonName, double x, double y, int width, int height) {
        graphic = new Rectangle(width, height, ColorThemeManager.getCurrentColorTheme().buttonColor);
        originalColor = ColorThemeManager.getCurrentColorTheme().buttonColor;
        
        setPosition(new Vector2(x, y));
        graphic.setLayoutX(x);
        graphic.setLayoutY(y);

        buttonText = new Text(buttonName);
        buttonText.setFont(FontManager.loadFont("Nunito-Regular.ttf", 20));
        buttonText.setTextAlignment(TextAlignment.CENTER);
        buttonText.setTextOrigin(VPos.CENTER);
        buttonText.setTranslateX(graphic.getLayoutX() + (graphic.getWidth() - buttonText.getBoundsInLocal().getWidth()) / 2);
        buttonText.setTranslateY(graphic.getLayoutY() + (graphic.getHeight() / 2));
        buttonText.setFill(ColorThemeManager.getCurrentColorTheme().textColor);
        
        addNode(graphic);
        addNode(buttonText);
        
        for (Node n : getNodes()) {
            n.setOnMouseClicked(e -> {
                performAction();
            });
        }

        for (Node n : getNodes()) {
            n.setOnMouseEntered(e -> {
                highlightButton();
            });
        }

        for (Node n : getNodes()) {
            n.setOnMouseExited(e -> {
                unHighlightButton();
            });
        }

        addNodesToScene();
    }

    public abstract void performAction();
    
    protected void highlightButton() {
        graphic.setFill(ColorThemeManager.getCurrentColorTheme().buttonHighlightColor);
    }

    protected void unHighlightButton() {
        graphic.setFill(ColorThemeManager.getCurrentColorTheme().buttonColor);
    }
}

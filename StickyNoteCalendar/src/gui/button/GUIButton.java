package gui.button;

import gui.DrawableUIElement;
import gui.colors.ColorThemeChangableUIElement;
import gui.colors.ColorThemeManager;
import javafx.geometry.VPos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.App;
import util.FontManager;
import util.Vector2;

public abstract class GUIButton extends DrawableUIElement implements ColorThemeChangableUIElement {
    public static final double BUTTON_HEIGHT = 50.0;

    private Pane buttonPane = new Pane();
    protected Rectangle graphic = new Rectangle();
    protected Text buttonText = new Text();

    public GUIButton(String buttonName, double x, double y, int width) {
        graphic = new Rectangle(width, BUTTON_HEIGHT, ColorThemeManager.getCurrentColorTheme().buttonColor);

        buttonText = new Text(buttonName);
        buttonText.setFont(FontManager.loadFont("Nunito-Regular.ttf", 20));
        buttonText.setTextAlignment(TextAlignment.CENTER);
        buttonText.setTextOrigin(VPos.CENTER);
        buttonText.setTranslateX(graphic.getLayoutX() + (graphic.getWidth() - buttonText.getBoundsInLocal().getWidth()) / 2);
        buttonText.setTranslateY(graphic.getLayoutY() + (graphic.getHeight() / 2));
        buttonText.setFill(ColorThemeManager.getCurrentColorTheme().textColor);
        
        buttonPane.getChildren().addAll(graphic, buttonText);
        addNode(buttonPane);
        buttonPane.setLayoutX(x);
        buttonPane.setLayoutY(y);

        App.addColorThemeChangeable(this);
        setActions();
        addNodesToScene();
    }

    private void setActions() {
        buttonPane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                performAction();
            }
        });

        buttonPane.setOnMouseEntered(e -> {
            highlightButton();
        });

        buttonPane.setOnMouseExited(e -> {
            unHighlightButton();
        });
    }

    public abstract void performAction();

    @Override
    public Vector2 getPosition() {
        return new Vector2(buttonPane.getLayoutX(), buttonPane.getLayoutY());
    }

    protected void highlightButton() {
        graphic.setFill(ColorThemeManager.getCurrentColorTheme().buttonHighlightColor);
    }

    protected void unHighlightButton() {
        graphic.setFill(ColorThemeManager.getCurrentColorTheme().buttonColor);
    }

    public void updateColors() {
        graphic.setFill(ColorThemeManager.getCurrentColorTheme().buttonColor);
        buttonText.setFill(ColorThemeManager.getCurrentColorTheme().textColor);
    }
}

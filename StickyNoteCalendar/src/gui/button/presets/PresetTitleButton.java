package gui.button.presets;

import gui.button.GUIButton;
import gui.colors.ColorThemeManager;
import gui.popupmenu.PopuppableUIElement;
import gui.popupmenu.PresetPopupMenu;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import main.App;
import util.FontManager;
import util.Vector2;

public class PresetTitleButton extends GUIButton implements PopuppableUIElement {
    private static final String TITLE = "Presets";
    Vector2 dimesions = new Vector2(50, 0);
    TextField textField;

    public PresetTitleButton(double x, double y, int width) {
        super(TITLE, x, y, width);
        graphic.setVisible(false);

        setPopupMenu();
        buttonText.setVisible(false);

        textField = new TextField();
        textField.setFont(FontManager.loadFont("Nunito-Regular.ttf", (int)(20 * App.multiplier)));
        textField.setLayoutX(graphic.getLayoutX());
        textField.setMaxWidth(graphic.getWidth());
        textField.setLayoutY(graphic.getLayoutY());
        textField.setAlignment(Pos.CENTER);
        getButtonPane().getChildren().add(textField);

        updateColors();

        stopEditingText();

        getButtonPane().setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() > 1 && !PresetManager.getInstance().getPresets().isEmpty()) {
                    startEditingText();
                }
            }
        });

        textField.textProperty().addListener((o, oldValue, newValue) -> {
            Preset currentPreset = PresetManager.getInstance().getCurrentPreset();
            currentPreset.setName(newValue);
        });

        textField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.ESCAPE) {
                stopEditingText();
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
        textField.setText(text);
    }

    public void startEditingText() {
        textField.setEditable(true);
        textField.setMouseTransparent(false);
        textField.selectEnd();
    }

    public void stopEditingText() {
        textField.setEditable(false);
        textField.setMouseTransparent(true);
        textField.deselect();
    }

    @Override
    public void updateColors() {
        textField.setStyle("-fx-text-fill: " + ColorThemeManager.getCurrentColorTheme().textColor.toString().replace("0x", "#") + ";");
    }
}

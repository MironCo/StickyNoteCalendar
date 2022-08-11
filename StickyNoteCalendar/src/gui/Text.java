package gui;

import javax.swing.JLabel;

import javafx.scene.Node;
import util.Vector2;

public class Text extends DrawableUIElement {
    private JLabel label;

    public Text(String message, Vector2 _position, int size) {
        setPosition(_position);

        label.setVisible(true);
    }

    public String getText() {
        return label.getText();
    }
}

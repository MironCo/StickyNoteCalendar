package gui;

import javax.swing.JLabel;

import util.Vector2;

public class Text extends UIElement {
    private JLabel label;

    public Text(String message, Vector2 _position, int size) {
        setPosition(_position);

        label = new JLabel(message);
        label.setBounds((int)position.x, (int)position.y, (int)size*message.length(), (int)size);

        label.setVisible(true);
    }

    @Override
    public void addToWindow(Window window) {
        window.getPanel().add(label);
    }

    public String getText() {
        return label.getText();
    }
}

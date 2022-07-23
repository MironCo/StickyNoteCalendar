package gui;

import util.Vector2;
import javax.swing.JPanel;
import java.awt.Graphics;

public abstract class DrawableUIElement extends JPanel {

    protected Vector2 position;

    /**
     * Method to set the position of the UI element
     * @param _position new position as a Vector2
     */
    public void setPosition(Vector2 _position) {
        this.position = _position;
    }

    /**
     * Method to set the position of the UI element
     * @param _position new position as X and Y
     */
    public void setPosition(int x, int y) {
        this.position = new Vector2(x, y);
    }

    /**
     * Standard getter for the position of the UI Element
     * @return The position of the UI Element as a Vector2
     */
    public Vector2 getPosition() {
        return position;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
};

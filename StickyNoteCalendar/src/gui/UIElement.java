package gui;

import util.Vector2;

public abstract class UIElement {
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

    /**
     * Method to add UIElement to window
     * @param window - Window to add the element to
     */
    public abstract void addToWindow(Window window);
};
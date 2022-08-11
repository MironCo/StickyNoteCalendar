package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import util.Vector2;

public abstract class DrawableUIElement {

    protected List<Node> nodes = new ArrayList<Node>();
    protected Vector2 position = new Vector2(0, 0);
    
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

    public List<Node> getNodes() {
        return nodes;
    }

    public void addTo(List<Node> drawableNodes) {
        drawableNodes.addAll(getNodes());
    }
};

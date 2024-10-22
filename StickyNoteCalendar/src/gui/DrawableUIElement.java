/*
 * Program: StickyNoteCalendar
 * File: DrawableUIElement.java
 * Usage: Base Class for UI Elements in SNC.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import main.App;
import util.Vector2;

public abstract class DrawableUIElement {

    protected List<Node> nodes = new ArrayList<Node>();
    protected Vector2 position = new Vector2(0, 0);
    
    protected ContextMenu popupMenu;

    public DrawableUIElement() {
        
    }

    /**
     * Method to set the position of the UI element
     * @param _position new position as a Vector2
     */
    public void setPosition(Vector2 _position) {
        Vector2 changePosition = new Vector2(position.x - _position.x, position.y - _position.y);
        for (Node node : nodes) {
            node.setTranslateX(node.getTranslateX() + changePosition.x);
            node.setTranslateY(node.getTranslateY() + changePosition.y);
        }
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

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addNodeToFront(Node node) {
        nodes.add(0, node);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void addTo(List<Node> drawableNodes) {
        drawableNodes.addAll(getNodes());
    }

    public void addNodesToScene() {
        App.AddToScene(nodes);
    }

    public void setMouseTransparent(boolean b) {
        for(Node node : nodes) {
            node.setMouseTransparent(b);
        }
    }

    public void setVisible(boolean isVisible) {
        for(Node n : nodes) {
            n.setVisible(isVisible);
        }
    }

    public boolean isVisible() {
        boolean isVisible = true;
        for (Node n : nodes) {
            isVisible &= n.isVisible();
        }
        return isVisible;
    }
};

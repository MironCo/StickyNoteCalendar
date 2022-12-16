package gui;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;

public abstract class DraggableUIElement extends DrawableUIElement {

    protected double startX = 0;
    protected double startY = 0;

    public DraggableUIElement() {
        //do nothing
    }

    protected void makeDraggable(Node node) {
        node.setOnMousePressed(e -> {
            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();
        });

        node.setOnMouseDragged(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                for(Node n : getNodes()) {
                    n.setTranslateX(e.getSceneX() - startX);
                    n.setTranslateY(e.getSceneY() - startY);
                }
            }
        });
    }
} 

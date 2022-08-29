package gui;

import javafx.scene.Node;

public abstract class DraggableUIElement extends DrawableUIElement {

    private double startX = 0;
    private double startY = 0;

    public DraggableUIElement() {
        //do nothing
    }

    protected void makeDraggable(Node node) {
        node.setOnMousePressed(e -> {

            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();
        });

        node.setOnMouseDragged(e -> {
            for(Node n : getNodes()) {
                n.setTranslateX(e.getSceneX() - startX);
                n.setTranslateY(e.getSceneY() - startY);
            }
        });
    }
} 

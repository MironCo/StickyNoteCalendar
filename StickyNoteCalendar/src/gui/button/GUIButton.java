package gui.button;

import gui.DrawableUIElement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public abstract class GUIButton extends DrawableUIElement{
    protected Button button = new Button();

    public GUIButton(String buttonName) {
        button = new Button(buttonName);
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                performAction();
            }
        };

        button.setOnAction(event);

        nodes.add(button);
    }
    
    public abstract void performAction();
}

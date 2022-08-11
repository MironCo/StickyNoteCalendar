package main.calendar;

import gui.DrawableUIElement;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Day extends DrawableUIElement {
    public Integer day = 0;
    public Rectangle rectangle = new Rectangle();
    public Text dayNumberText = new Text();

    public Day() {
        
    }

    public void resize() {

    }
}

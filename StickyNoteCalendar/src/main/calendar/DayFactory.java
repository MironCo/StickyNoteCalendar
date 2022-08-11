package main.calendar;

import gui.Toolbar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import main.App;
import util.FontManager;
import util.Vector2;

public class DayFactory {
    public static final DayFactory instance = new DayFactory();
    
    private DayFactory() {

    }

    public static DayFactory getInstance() {
        return instance;
    }

    public Day buildDay(int day) {
        Day newDay = new Day();

        int numberOfRows = (int)Math.ceil(31.0 / 7.0);
        double size = (App.screenHeight - ((numberOfRows * Calendar.dayOffset.y) + Calendar.dayOffset.y)) / numberOfRows;
        
        //Calendar.dayXCenterOffs = (App.screenWidth - (Calendar.dayDimensions.x + Calendar.dayOffset.x) * 7) / 2;

        Calendar.dayDimensions = new Vector2((float)size, (float)size);
        Calendar.dayXCenterOffset = Toolbar.dimensions.x + Calendar.dayOffset.x + ((App.screenWidth-Toolbar.dimensions.x-(Calendar.dayDimensions.x + Calendar.dayOffset.x)*7)/2);
        Vector2 position = new Vector2(((Calendar.dayDimensions.x + Calendar.dayOffset.x) * (day % 7)) + Calendar.dayXCenterOffset, ((Calendar.dayDimensions.y + Calendar.dayOffset.y) * (day / 7)) + Calendar.dayOffset.y);
        
        newDay.day = day + 1;
        
        newDay.dayNumberText = new Text(newDay.day.toString());
        newDay.dayNumberText.setFont(FontManager.getInstance().loadFont("Nunito-ExtraLight.ttf", 20));
        newDay.dayNumberText.setX(position.x + Calendar.dayTextOffset.x);
        newDay.dayNumberText.setY(position.y + Calendar.dayTextOffset.y);
        newDay.getNodes().add(newDay.dayNumberText);

        newDay.rectangle = new Rectangle(position.x, position.y, Calendar.dayDimensions.x, Calendar.dayDimensions.y);
        newDay.rectangle.setFill(Color.TRANSPARENT);
        newDay.rectangle.setStroke(Color.DARKGRAY);
        newDay.rectangle.setStrokeWidth(2);
        newDay.getNodes().add(newDay.rectangle);
        return newDay;
    }
}

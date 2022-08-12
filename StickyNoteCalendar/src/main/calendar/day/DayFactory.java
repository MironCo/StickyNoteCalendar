package main.calendar.day;

import gui.Toolbar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import main.App;
import main.calendar.Calendar;
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
        double size = (App.screenHeight - ((numberOfRows * Calendar.getInstance().dayOffset.y) + Calendar.getInstance().dayOffset.y + Calendar.getInstance().textHeight + Calendar.getInstance().textHeight)) / numberOfRows;
        
        //Calendar.dayXCenterOffs = (App.screenWidth - (Calendar.getInstance().dayDimensions.x + Calendar.getInstance().dayOffset.x) * 7) / 2;

        Calendar.getInstance().dayDimensions = new Vector2((float)size, (float)size);
        //Calendar.getInstance().dayXCenterOffset = Toolbar.dimensions.x + Calendar.getInstance().dayOffset.x + ((App.screenWidth-Toolbar.dimensions.x-(Calendar.getInstance().dayDimensions.x + Calendar.getInstance().dayOffset.x)*7)/2);
        Calendar.getInstance().dayXCenterOffset = Toolbar.dimensions.x + (Calendar.getInstance().dayOffset.x*2);
        Vector2 position = new Vector2(((Calendar.getInstance().dayDimensions.x + Calendar.getInstance().dayOffset.x) * (day % 7)) + Calendar.getInstance().dayXCenterOffset, ((Calendar.getInstance().dayDimensions.y + Calendar.getInstance().dayOffset.y) * (day / 7)) + (Calendar.getInstance().dayOffset.y + Calendar.getInstance().textHeight + Calendar.getInstance().dayYPadding));
        
        newDay.day = day + 1;
        
        newDay.dayNumberText = new Text(newDay.day.toString());
        newDay.dayNumberText.setFont(FontManager.loadFont("Nunito-ExtraLight.ttf", 20));
        newDay.dayNumberText.setX(position.x + Calendar.getInstance().dayTextOffset.x);
        newDay.dayNumberText.setY(position.y + Calendar.getInstance().dayTextOffset.y);
        newDay.getNodes().add(newDay.dayNumberText);

        newDay.rectangle = new Rectangle(position.x, position.y, Calendar.getInstance().dayDimensions.x, Calendar.getInstance().dayDimensions.y);
        newDay.rectangle.setFill(Color.TRANSPARENT);
        newDay.rectangle.setStroke(Color.DARKGRAY);
        newDay.rectangle.setStrokeWidth(2);
        newDay.getNodes().add(newDay.rectangle);
        return newDay;
    }
}

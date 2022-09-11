package main.calendar.day;

import java.util.List;

import javafx.geometry.Point2D;
import main.App;

public class DayManager {
    public static final DayManager instance = new DayManager();

    private Day hoveredDay;
    public List<Day> currentDays;

    private DayManager() {
        
    }

    public static final DayManager getInstance() {
        return instance;
    }

    public Day getHoveredDay() {
        Day hoveredDay = null;
        for(Day day : currentDays) {
            if (day.rectangle.contains(new Point2D(App.getMousePosition().x, App.getMousePosition().y))) {
                return day;
            }
        }
        return hoveredDay;
    }

    public void setHoveredDay(Day day) {
        hoveredDay = day;
    }    
    
    public List<Day> getCurrentDays() {
        return this.currentDays;
    }

    public void setCurrentDays(List<Day> currentDays) {
        this.currentDays = currentDays;
    }
}

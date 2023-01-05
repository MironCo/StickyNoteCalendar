/*
 * Program: StickyNoteCalendar
 * File: DayManager.java
 * Usage: Utility class for containing and managing gui Days.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package main.calendar.day;

import java.util.List;

import javafx.geometry.Point2D;
import main.App;

public class DayManager {
    public static final DayManager instance = new DayManager();
    public List<Day> currentDays;

    private DayManager() {

    }

    public static final DayManager getInstance() {
        return instance;
    }

    public Day getHoveredDay() {
        for (Day day : currentDays) {
            if (day.isVisible() && day.rectangle.contains(new Point2D(App.getMousePosition().x, App.getMousePosition().y))) {
                return day;
            }
        }
        return null;
    }

    public List<Day> getCurrentDays() {
        return this.currentDays;
    }

    public void setCurrentDays(List<Day> currentDays) {
        this.currentDays = currentDays;
    }
}

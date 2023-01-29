/*
 * Program: StickyNoteCalendar
 * File: Month.java
 * Usage: Class that contains the data for Months and the guiDays in the month.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package main.calendar.month;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import main.calendar.day.Day;
import main.calendar.day.DayFactory;

public class Month {
    private String name;
    private List<Day> daysInMonth = new ArrayList<Day>();
    private YearMonth yearMonth;

    public Month(YearMonth yearMonth, String name, int weekdayOffset) {
        this.yearMonth = yearMonth;

        int numberOfDays = YearMonth.of(yearMonth.getYear(), yearMonth.getMonthValue()).lengthOfMonth();        
        this.name = name;

        for (int i = 0; i < numberOfDays; i++) {
            Day day = DayFactory.buildDay(i, numberOfDays, weekdayOffset);
            daysInMonth.add(day);
        }
        AddDaysToScene();
    }

    private void AddDaysToScene() {
        for (Day d : daysInMonth) {
            d.addNodesToScene();
        }
    }

    protected void addNodes(List<Node> drawList) {
        for (Day d : daysInMonth) {
            drawList.addAll(d.getNodes());
        }
    }

    public void hideMonth() {
        for (Day d : daysInMonth) {
            d.setVisible(false);
        }
    }

    public void showMonth() {
        for (Day d :daysInMonth) {
            d.setVisible(true);
        }
    }

    public String getName() {
        return name;
    }

    public List<Day> getDays() {
        return daysInMonth;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }
}  

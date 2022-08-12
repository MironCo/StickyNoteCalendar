package main.calendar;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import main.calendar.day.Day;
import main.calendar.day.DayFactory;

public class Month {
    private String name;
    private List<Day> daysInMonth = new ArrayList();

    public Month(String monthName, int numberOfDays) {
        this.name = monthName;
        for (int i = 0; i < numberOfDays; i++) {
            Day day = DayFactory.getInstance().buildDay(i);
            daysInMonth.add(day);
        }
    }

    protected void addNodes(List<Node> drawList) {
        for (Day d : daysInMonth) {
            drawList.addAll(d.getNodes());
        }
    }

    public void resizeMonth() {
        for (Day d : daysInMonth) {
            d.resize();
        }
    }

    public String getName() {
        return name;
    }
} 

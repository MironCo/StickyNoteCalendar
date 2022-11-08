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
            //long startTime = System.currentTimeMillis();

            Day day = DayFactory.buildDay(i, weekdayOffset);
            daysInMonth.add(day);

            //long endTime = System.currentTimeMillis();
            //System.out.println("That took " + (endTime - startTime) + " milliseconds");
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

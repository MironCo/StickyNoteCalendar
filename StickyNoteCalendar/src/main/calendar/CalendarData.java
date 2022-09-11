package main.calendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import gui.stickynote.StickyNote;
import gui.stickynote.StickyNoteManager;
import main.calendar.day.Day;
import util.Vector2;

public class CalendarData {
    public static final String[] daysOfTheWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public static final String[] monthsOfTheYear = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return date.format(formatter);
    }

    public static int findWeekdayIndex(String weekdayString) {
        for (int i = 0; i < 7; i++) {
            if (daysOfTheWeek[i].equals(weekdayString)) {
                return i;
            }
        }
        return -1;
    }
}

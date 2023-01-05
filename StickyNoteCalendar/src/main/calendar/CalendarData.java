/*
 * Program: StickyNoteCalendar
 * File: CalendarData.java
 * Usage: Utility class for Calendar data.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package main.calendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public static int findMonthIndex(String monthName) {
        for (int i = 0; i < 12; i++) {
            if (monthsOfTheYear[i].equals(monthName)) {
                return i + 1;
            }
        }
        return -1;
    }
}

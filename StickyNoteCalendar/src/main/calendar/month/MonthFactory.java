package main.calendar.month;

import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

import main.calendar.CalendarData;
import main.calendar.day.DayManager;

public class MonthFactory {
    public static Month buildMonth(YearMonth newMonth) {
        int weekdayOffset = CalendarData.findWeekdayIndex(newMonth.atDay(1)
            .getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US));
        
        Month month = new Month(YearMonth.of(newMonth.getYear(), newMonth.getMonth()), generateMonthName(newMonth), weekdayOffset);
        DayManager.getInstance().setCurrentDays(month.getDays());
        return month;
    }

    public static String generateMonthName(YearMonth yearMonth) {
        return CalendarData.monthsOfTheYear[yearMonth.getMonthValue() - 1] + " " + yearMonth.getYear();
    }
}

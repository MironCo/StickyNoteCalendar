package main.calendar;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import gui.DrawableUIElement;
import gui.colors.ColorThemeManager;
import javafx.scene.Node;
import javafx.scene.text.Text;
import main.calendar.day.DayFactory;
import main.calendar.day.DayManager;
import util.FontManager;
import util.Vector2;

public class Calendar extends DrawableUIElement {
   public static final Calendar instance = new Calendar();

   private LocalDate today;
   private HashMap<String, Month> months = new HashMap<String, Month>();
   private Month currentMonth;

   public Vector2 dayOffset = new Vector2(10, 10);
   public Vector2 dayTextOffset = new Vector2(5, dayOffset.y * 2);
   public Vector2 dayDimensions = new Vector2(100, 100);
   public double dayXCenterOffset = 10;
   public double dayYPadding = 10;
   public double dayStickyNoteSize = 60;

   private Text monthName = new Text();
   private List<Text> weekdayNames = new ArrayList<Text>();
   public double weekdayNamesYPadding = 20;
   public int weekdayNamesSize = 30;
   public float weekdayNamesExtraOffsetX = 4;

   private Vector2 textPosition = new Vector2(0, 60);
   public double textHeight = textPosition.y;

   private Calendar() {
      // singleton class - do nothing
   }

   public static final Calendar getInstance() {
      return instance;
   }

   public void Init() {
      today = LocalDate.now();

      monthName.setFont(FontManager.loadFont("Nunito-ExtraLight.ttf", 70));
      monthName.setX(textPosition.x);
      textHeight = monthName.getFont().getSize();
      monthName.setY(textPosition.y);
      monthName.setFill(ColorThemeManager.getCurrentColorTheme().textColor);

      for (int i = 0; i < 7; i++) {
         weekdayNames.add(DayFactory.buildDayOfWeekText(i));
      }

      YearMonth todayYearMonth = YearMonth.of(today.getYear(), today.getMonthValue());
      AddNewMonth(todayYearMonth);
      setCurrentMonth(months.get(generateMonthName(todayYearMonth)));
      
      nodes.add(monthName);
      nodes.addAll(weekdayNames);

      addNodesToScene();
   }

   public void AddNewMonth(YearMonth newMonth) {
      int weekdayOffset = CalendarData.findWeekdayIndex(newMonth.atDay(1)
            .getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US));

      Month initMonth = new Month(YearMonth.of(newMonth.getYear(), newMonth.getMonth()), generateMonthName(newMonth), weekdayOffset);
      months.putIfAbsent(generateMonthName(newMonth), initMonth);
      DayManager.getInstance().setCurrentDays(initMonth.getDays());
   }

   public void goToNextMonth() {
      currentMonth.hideMonth();

      YearMonth nextYearMonth = null;
      if (getCurrentMonth().getYearMonth().getMonthValue() + 1 >= 13) {
         nextYearMonth = YearMonth.of(getCurrentMonth().getYearMonth().getYear() + 1, 1);
      } else if (getCurrentMonth().getYearMonth().getMonthValue() + 1 < 13) {
         nextYearMonth = YearMonth.of(getCurrentMonth().getYearMonth().getYear(),
               getCurrentMonth().getYearMonth().getMonthValue() + 1);
      }

      if (nextYearMonth != null && months.get(generateMonthName(nextYearMonth)) != null) {
         Month nextMonth = months.get(generateMonthName(nextYearMonth));
         nextMonth.showMonth();
         setCurrentMonth(nextMonth);
      } else {
         AddNewMonth(nextYearMonth);
         setCurrentMonth(months.get(generateMonthName(nextYearMonth)));
         currentMonth.showMonth();
      }
   }

   public void goToLastMonth() {
      currentMonth.hideMonth();

      YearMonth lastYearMonth = null;
      if (getCurrentMonth().getYearMonth().getMonthValue() - 1 < 1) {
         lastYearMonth = YearMonth.of(getCurrentMonth().getYearMonth().getYear() - 1, 12);
      } else if (getCurrentMonth().getYearMonth().getMonthValue() - 1 >= 1) {
         lastYearMonth = YearMonth.of(getCurrentMonth().getYearMonth().getYear(),
               getCurrentMonth().getYearMonth().getMonthValue() - 1);
      }

      if (lastYearMonth != null && months.get(generateMonthName(lastYearMonth)) != null) {
         Month lastMonth = months.get(generateMonthName(lastYearMonth));
         lastMonth.showMonth();
         setCurrentMonth(lastMonth);
         return;
      } else {
         AddNewMonth(lastYearMonth);
         setCurrentMonth(months.get(generateMonthName(lastYearMonth)));
         currentMonth.showMonth();
      }
   }

   public Month getCurrentMonth() {
      return currentMonth;
   }

   public void setCurrentMonth(Month month) {
      if (currentMonth != null) currentMonth.hideMonth();
      currentMonth = month;
      monthName.setX(dayXCenterOffset);
      monthName.setText(month.getName());
      textHeight = monthName.getBoundsInLocal().getHeight();
      DayManager.getInstance().setCurrentDays(currentMonth.getDays());
   }

   private String generateMonthName(YearMonth yearMonth) {
      return CalendarData.monthsOfTheYear[yearMonth.getMonthValue() - 1] + " " + yearMonth.getYear();
   }

   public void addNodes(List<Node> drawList) {
      for (Node n : getNodes()) {
         drawList.add(n);
      }
      for (Month m : months.values()) {
         m.addNodes(drawList);
      }
   }

   public LocalDate getToday() {
      return today;
   }

   public List<Text> getWeekdayNames() {
      return weekdayNames;
   }
}

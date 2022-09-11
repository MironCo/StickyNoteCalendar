package main.calendar;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
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
   private List<Month> months = new ArrayList<Month>();
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

   private Vector2 textPosition = new Vector2(0,60);
   public double textHeight = textPosition.y;

   private Calendar() {
      //singleton class - do nothing
   }

   public static final Calendar getInstance() {
      return instance;
   }

   public void Init() {
      today = LocalDate.now();

      monthName.setFont(FontManager.loadFont("Nunito-ExtraLight.ttf",70));
      monthName.setX(textPosition.x);
      textHeight = monthName.getFont().getSize();
      monthName.setY(textPosition.y);
      monthName.setFill(ColorThemeManager.getInstance().getCurrentColorTheme().textColor);

      for (int i = 0; i < 7; i++) {
         weekdayNames.add(DayFactory.buildDayOfWeekText(i));
      }

      System.out.println(YearMonth.of(today.getYear(), today.getMonthValue()).atDay(1).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US));
      int weekdayOffset = CalendarData.findWeekdayIndex(YearMonth.of(today.getYear(), today.getMonthValue()).atDay(1).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US));
      Month initMonth = new Month(CalendarData.monthsOfTheYear[today.getMonthValue()-1], YearMonth.of(today.getYear(), today.getMonthValue()).lengthOfMonth(), weekdayOffset);
      months.add(initMonth);
      DayManager.getInstance().setCurrentDays(initMonth.getDays());

      System.out.println();
      
      setCurrentMonth(initMonth);

      nodes.add(monthName);
      nodes.addAll(weekdayNames);
   }

   public Month getCurrentMonth() {
      return currentMonth;
   }

   public void setCurrentMonth(Month month) {
      currentMonth = month;
      monthName.setText(currentMonth.getName());
      monthName.setX(dayXCenterOffset);
      textHeight = monthName.getBoundsInLocal().getHeight();
   }
 
   public void addNodes(List<Node> drawList) {
      for(Node n : getNodes()) {
         drawList.add(n);
      }
      for (Month m : months) {
         m.addNodes(drawList);
      }
   }

   public void resizeCalendar() {
      for (Month m : months) {
         m.resizeMonth();
      }
   }

   public LocalDate getToday() {
      return today;
   }
}

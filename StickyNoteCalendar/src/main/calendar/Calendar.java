package main.calendar;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import gui.DrawableUIElement;
import javafx.scene.Node;
import javafx.scene.text.Text;
import util.Vector2;


public class Calendar extends DrawableUIElement {
   public static final Calendar instance = new Calendar();

   private LocalDate today;
   private List<Month> months = new ArrayList();
   private Month currentMonth;

   public static Vector2 dayOffset = new Vector2(10, 10);
   public static Vector2 dayTextOffset = new Vector2(5, dayOffset.y * 2);
   public static Vector2 dayDimensions = new Vector2(100, 100);
   public static double dayXCenterOffset = 10;

   private Text monthName;

   private Calendar() {
      //singleton class - do nothing
   }

   public static final Calendar getInstance() {
      return instance;
   }

   public void Init() {
      today = LocalDate.now();

      System.out.println(CalendarData.formatDate(today));

      currentMonth = new Month(CalendarData.monthsOfTheYear[today.getMonthValue()-1], YearMonth.of(today.getYear(), today.getMonthValue()).lengthOfMonth());
      months.add(currentMonth);
   }

   public void addNodes(List<Node> drawList) {
      for (Month m : months) {
         m.addNodes(drawList);
      }
   }

   public void resizeCalendar() {
      for (Month m : months) {
         m.resizeMonth();
      }
   }
}

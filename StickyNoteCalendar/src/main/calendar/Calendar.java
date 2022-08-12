package main.calendar;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.tools.Tool;

import gui.DrawableUIElement;
import gui.Toolbar;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.App;
import util.FontManager;
import util.Vector2;


public class Calendar extends DrawableUIElement {
   public static final Calendar instance = new Calendar();

   private LocalDate today;
   private List<Month> months = new ArrayList();
   private Month currentMonth;

   public Vector2 dayOffset = new Vector2(10, 10);
   public Vector2 dayTextOffset = new Vector2(5, dayOffset.y * 2);
   public Vector2 dayDimensions = new Vector2(100, 100);
   public double dayXCenterOffset = 10;
   public double dayYPadding = 10;

   private Text monthName = new Text();
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

      System.out.println(CalendarData.formatDate(today));

      monthName.setFont(FontManager.loadFont("Nunito-ExtraLight.ttf",70));
      monthName.setX(textPosition.x);
      textHeight = monthName.getFont().getSize();
      monthName.setY(textPosition.y);

      Month initMonth = new Month(CalendarData.monthsOfTheYear[today.getMonthValue()-1], YearMonth.of(today.getYear(), today.getMonthValue()).lengthOfMonth());
      months.add(initMonth);

      
      setCurrentMonth(initMonth);

      nodes.add(monthName);
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
}

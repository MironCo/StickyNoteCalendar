package main.calendar.day;

import gui.colors.ColorThemeManager;
import gui.stickynote.DayStickyNoteGraphic;
import gui.toolbar.Toolbar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.App;
import main.calendar.Calendar;
import main.calendar.CalendarData;
import util.FontManager;
import util.Vector2;

public class DayFactory {
    public static Text buildDayOfWeekText(int day) {
        Calendar calendar = Calendar.getInstance();

        Text dayOfWeekText = new Text();
        dayOfWeekText.setText(abbreviateWeekday(CalendarData.daysOfTheWeek[day]));
        dayOfWeekText.setFont(FontManager.loadFont("Nunito-ExtraLight.ttf", Calendar.WEEKDAY_NAMES_SIZE));
        dayOfWeekText.setFill(ColorThemeManager.getCurrentColorTheme().textColor);
        dayOfWeekText.setTextAlignment(TextAlignment.CENTER);

        calendar.dayXCenterOffset = Toolbar.DIMENSIONS.x + (Calendar.dayOffset.x * 2);
        Vector2 position = new Vector2(
                ((calendar.dayDimensions.x + Calendar.dayOffset.x + Calendar.WEEKDAY_NAMES_EXTRA_OFFSET_X) * (day % 7))
                        + calendar.dayXCenterOffset,
                Calendar.DAY_Y_PADDING + calendar.textHeight + Calendar.WEEKDAY_NAMES_Y_PADDING);

        dayOfWeekText.setX(position.x);
        dayOfWeekText.setY(position.y);

        return dayOfWeekText;
    }

    private static String abbreviateWeekday(String weekdayString) {
        return weekdayString.substring(0, 3);
    }

    public static Day buildDay(int day, int numberOfDays, int weekdayOffset) {
        Calendar calendar = Calendar.getInstance();

        Day newDay = new Day();

        int numberOfRows = (int) Math.ceil((numberOfDays + weekdayOffset) / 7.0);
         double size = (App.screenHeight - (((numberOfRows+1) * Calendar.dayOffset.y) + calendar.textHeight * 2)) / numberOfRows;
        int adjustedDay = day + weekdayOffset;

        calendar.dayDimensions = new Vector2(size, size);

        Vector2 position = new Vector2(
                Calendar.getInstance().getWeekdayNames().get(adjustedDay % 7).getX(),
                ((calendar.dayDimensions.y + Calendar.dayOffset.y) * (adjustedDay / 7)) + (Calendar.dayOffset.y + calendar.textHeight + Calendar.DAY_Y_PADDING + Calendar.WEEKDAY_NAMES_Y_PADDING));
                        
        newDay.setPosition(position);
        newDay.day = day + 1;

        newDay.dayNumberText = new Text(newDay.day.toString());
        newDay.dayNumberText.setFont(FontManager.loadFont("Nunito-ExtraLight.ttf", 20));
        newDay.dayNumberText.setX(position.x + Calendar.dayTextOffset.x);
        newDay.dayNumberText.setY(position.y + Calendar.dayTextOffset.y);
        newDay.dayNumberText.setFill(ColorThemeManager.getCurrentColorTheme().textColor);
        newDay.addNode(newDay.dayNumberText);

        newDay.rectangle = new Rectangle(position.x, position.y, calendar.dayDimensions.x, calendar.dayDimensions.y);
        newDay.rectangle.setFill(Color.TRANSPARENT);
        newDay.rectangle.setStroke(ColorThemeManager.getCurrentColorTheme().borderColor);
        newDay.rectangle.setStrokeWidth(2);
        newDay.rectangle.setViewOrder(0);
        newDay.addNode(newDay.rectangle);

        newDay.dayStickyNote = new DayStickyNoteGraphic(position);
        newDay.dayStickyNote.setDay(newDay.getDay());
        newDay.addNodeToFront(newDay.dayStickyNote.rectangle);
        newDay.dayStickyNote.setVisible(false);

        App.addColorThemeChangeable(newDay);
        return newDay;
    }
}

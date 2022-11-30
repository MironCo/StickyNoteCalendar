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
        dayOfWeekText.setFont(FontManager.loadFont("Nunito-ExtraLight.ttf", calendar.weekdayNamesSize));
        dayOfWeekText.setFill(ColorThemeManager.getCurrentColorTheme().textColor);
        dayOfWeekText.setTextAlignment(TextAlignment.CENTER);

        calendar.dayXCenterOffset = Toolbar.dimensions.x + (calendar.dayOffset.x * 2);
        Vector2 position = new Vector2(
                ((calendar.dayDimensions.x + calendar.dayOffset.x + calendar.weekdayNamesExtraOffsetX) * (day % 7))
                        + calendar.dayXCenterOffset,
                calendar.dayYPadding + calendar.textHeight + calendar.weekdayNamesYPadding);

        dayOfWeekText.setX(position.x);
        dayOfWeekText.setY(position.y);

        return dayOfWeekText;
    }

    private static String abbreviateWeekday(String weekdayString) {
        return weekdayString.substring(0, 3);
    }

    public synchronized static Day buildDay(int day, int weekdayOffset) {
        Calendar calendar = Calendar.getInstance();

        Day newDay = new Day();

        int numberOfRows = (int) Math.ceil((31.0 + weekdayOffset) / 7.0);
        double size = (App.screenHeight - ((numberOfRows * calendar.dayOffset.y) + calendar.dayOffset.y
                + calendar.textHeight + calendar.textHeight)) / numberOfRows;
        int adjustedDay = day + weekdayOffset;

        calendar.dayDimensions = new Vector2((float) size, (float) size);
        Vector2 position = new Vector2(
                //((calendar.dayDimensions.x + calendar.dayOffset.x) * (adjustedDay % 7)) + calendar.dayXCenterOffset,
                Calendar.getInstance().getWeekdayNames().get(adjustedDay % 7).getX(),
                ((calendar.dayDimensions.y + calendar.dayOffset.y) * (adjustedDay / 7)) + (calendar.dayOffset.y
                        + calendar.textHeight + calendar.dayYPadding + calendar.weekdayNamesYPadding));
        newDay.setPosition(position);
        newDay.day = day + 1;


        newDay.dayNumberText = new Text(newDay.day.toString());
        newDay.dayNumberText.setFont(FontManager.loadFont("Nunito-ExtraLight.ttf", 20));
        newDay.dayNumberText.setX(position.x + calendar.dayTextOffset.x);
        newDay.dayNumberText.setY(position.y + calendar.dayTextOffset.y);
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
        return newDay;
    }
}

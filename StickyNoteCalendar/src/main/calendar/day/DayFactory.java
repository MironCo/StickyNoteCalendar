/*
 * Program: StickyNoteCalendar
 * File: DayFactory.java
 * Usage: Factory for creating gui Days.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package main.calendar.day;

import java.lang.invoke.StringConcatFactory;

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

    public static Day buildDay(final int day, final int numberOfDays, final int weekdayOffset) {
        Calendar calendar = Calendar.getInstance();

        Day newDay = new Day();

        final int numberOfRows = (int) Math.ceil((numberOfDays + weekdayOffset) / 7.0);
        final double size = (App.screenHeight - (((numberOfRows+1) * Calendar.dayOffset.y) + calendar.textHeight * 2)) / numberOfRows;
        final int adjustedDay = day + weekdayOffset;

        calendar.dayDimensions = new Vector2(size, size);

        Vector2 position = new Vector2(
                Calendar.getInstance().getWeekdayNames().get(adjustedDay % 7).getX(),
                ((calendar.dayDimensions.y + Calendar.dayOffset.y) * (adjustedDay / 7)) + (Calendar.dayOffset.y + calendar.textHeight + Calendar.DAY_Y_PADDING + Calendar.WEEKDAY_NAMES_Y_PADDING));
                        
        newDay.day = day + 1;

        Text dayNumberText = new Text(position.x + Calendar.dayTextOffset.x, position.y + Calendar.dayTextOffset.y, newDay.day.toString());
        dayNumberText.setFont(FontManager.loadFont("Nunito-ExtraLight.ttf", 20));
        dayNumberText.setFill(ColorThemeManager.getCurrentColorTheme().textColor);
        newDay.dayNumberText = dayNumberText;

        Text stickyNoteNumberText = new Text("x0");
        stickyNoteNumberText.setId("number-text");
        stickyNoteNumberText.setFont(FontManager.loadFont("Nunito-ExtraLight.ttf", 15));
        newDay.baseStickyNoteNumberTextX = position.x + calendar.dayDimensions.x - Calendar.dayTextOffset.x;
        stickyNoteNumberText.setX(newDay.baseStickyNoteNumberTextX + stickyNoteNumberText.getBoundsInLocal().getWidth());
        stickyNoteNumberText.setY(position.y + calendar.dayDimensions.y - (Calendar.dayTextOffset.x));
        stickyNoteNumberText.setFill(ColorThemeManager.getCurrentColorTheme().textColor);
        stickyNoteNumberText.setTextAlignment(TextAlignment.RIGHT);
        newDay.stickyNoteNumberText = stickyNoteNumberText;

        Rectangle rectangle = new Rectangle(position.x, position.y, calendar.dayDimensions.x, calendar.dayDimensions.y);
        rectangle.setId("day-rect");
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(ColorThemeManager.getCurrentColorTheme().borderColor);
        newDay.rectangle = rectangle;

        DayStickyNoteGraphic dayStickyNote = new DayStickyNoteGraphic(position, newDay);
        dayStickyNote.setVisible(false);
        newDay.dayStickyNote = dayStickyNote;
        
        newDay.addNodeToFront(newDay.dayStickyNote.rectangle);
        newDay.addNode(newDay.stickyNoteNumberText);
        newDay.addNode(newDay.rectangle);
        newDay.addNode(newDay.dayStickyNote.textArea);
        newDay.addNode(newDay.dayNumberText);
        App.addColorThemeChangeable(newDay);
        return newDay;
    }
}

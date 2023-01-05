/*
 * Program: StickyNoteCalendar
 * File: DayToolbar.java
 * Usage: Class that creates a Toolbar which shows a list view of a certain day.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package gui.toolbar;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;


import gui.button.DayTitleButton;
import gui.button.GUIButton;
import gui.button.SwitchColorThemeButton;
import gui.colors.DarkColorTheme;
import gui.colors.LightColorTheme;
import gui.stickynote.StickyNote;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.App;
import main.calendar.Calendar;
import main.calendar.day.Day;

public class DayToolbar extends Toolbar {
    private static final double SCROLL_AMOUNT = 15;
    private Pane scrollPane;    
    private Rectangle scrollPaneRectangle;

    private Day openDay;
    private List<StickyNote> openStickyNotes = new ArrayList<>();

    private DayTitleButton dayTitleButton;
    private SwitchColorThemeButton lightButton;
    private SwitchColorThemeButton darkButton;
    private double dayTitleButtonY = 0;

    public DayToolbar() {
        super();
        setXPosition(App.screenWidth - toolbarGraphic.getWidth());
         
        scrollPane = new Pane();
        scrollPane.setLayoutX(toolbarGraphic.getLayoutX());
        scrollPane.setLayoutY(0);
        scrollPane.setMaxWidth(toolbarGraphic.getWidth());
        scrollPane.setMaxHeight(toolbarGraphic.getHeight());

        scrollPaneRectangle = new Rectangle(scrollPane.getLayoutX(), scrollPane.getLayoutY(), scrollPane.getMaxWidth(), scrollPane.getMaxHeight());
        scrollPaneRectangle.setFill(Color.TRANSPARENT);
        scrollPane.getChildren().addAll(scrollPaneRectangle);

        AddButtons();

        toolbarPane.getChildren().addAll(scrollPane);

        scrollPane.setOnScroll(e -> {
            scrollToolbar(e);
        });
    }

    public void scrollToolbar(ScrollEvent e) {
        double zoomFactor = e.getDeltaY() > 0 ? 1 : -1;
        if (zoomFactor < 0 && scrollAtMin()) {
            for (StickyNote currentNote : openStickyNotes) {
                currentNote.getNodes().get(0).setTranslateY(currentNote.getNodes().get(0).getTranslateY() + SCROLL_AMOUNT);
            }
        } else if (zoomFactor > 0 && !scrollAtMax()) {
            for (StickyNote currentNote : openStickyNotes) {
                currentNote.getNodes().get(0).setTranslateY(currentNote.getNodes().get(0).getTranslateY() - SCROLL_AMOUNT);
            }             
        } 

        dayTitleButton.bringToFront();
        darkButton.bringToFront();
        lightButton.bringToFront();
    }

    public void openDayToolbar(Day day) {
        openDay = day;
        
        for (StickyNote note : openStickyNotes) {
            note.setVisible(false);
            note.isOnToolbar = false;
        }

        openStickyNotes = openDay.getStickyNotes();
        YearMonth openedYearMonth = Calendar.getInstance().getCurrentMonth().getYearMonth();
        dayTitleButton.setText(openedYearMonth.getMonthValue() + "/" + day.day + "/" + openedYearMonth.getYear());
        dayTitleButton.bringToFront();
        showDayToolbar();

        dayTitleButton.bringToFront();
        darkButton.bringToFront();
        lightButton.bringToFront();
    }

    public void closeDayToolbar() {
        openDay = null;
        hideDayToolbar();
        openStickyNotes = new ArrayList<>();
    } 

    private void showDayToolbar() {
        toolbarPane.setVisible(true);
        int index = 0;

        for (GUIButton button : getButtons()) {
            button.setVisible(true);
        }

        for (StickyNote note : openStickyNotes) {
            note.showMainStickyNote();
            note.getGraphic().setTranslateX(getCenterX(StickyNote.DIMENSIONS.x + toolbarPadding.x));
            note.getGraphic().setTranslateY(getStickyNoteY(index));
            note.isOnToolbar = true;
            index ++;
        }
    }

    private void hideDayToolbar() {
        toolbarPane.setVisible(false);
        
        for (GUIButton button : getButtons()) {
            button.setVisible(false);
        }

        for (StickyNote note : openStickyNotes) {
            note.hideMainStickyNote();
            note.isOnToolbar = false;
        }
    }

    private void refreshStickyNotePosition() {
        int index = 0;
        for (StickyNote note : openStickyNotes) {
            note.getGraphic().setTranslateX(getCenterX(StickyNote.DIMENSIONS.x + toolbarPadding.x));
            note.getGraphic().setTranslateY(getStickyNoteY(index));
            index ++;
        }
    }

    public void refreshStickyNotes() {
        if (openDay != null && isOpen()) {
            openStickyNotes = openDay.getStickyNotes();
        
            for (StickyNote note : openStickyNotes) {
                note.showMainStickyNote();
                note.isOnToolbar = true;
            }

            refreshStickyNotePosition();
            dayTitleButton.bringToFront();
        }

        dayTitleButton.bringToFront();
        darkButton.bringToFront();
        lightButton.bringToFront();
    }

    public void removeStickyNote(StickyNote note) {
        if (openStickyNotes.contains(note)) {
            openStickyNotes.remove(note);
            openDay.removeStickyNote(note);
            if (openDay.getStickyNotes().isEmpty()) {
                closeDayToolbar();
            }
        }

        refreshStickyNotes();
    } 

    public void clearStickyNotes() {
        while(!openStickyNotes.isEmpty()) {
            openStickyNotes.get(0).delete();
        }
    }

    private boolean scrollAtMin() {
        if (!openStickyNotes.isEmpty()) {
            return (openStickyNotes.get(0).getGraphic().getTranslateY() < dayTitleButtonY + toolbarPadding.y);
        }
        return false;
    }

    private boolean scrollAtMax() {
        if (!openStickyNotes.isEmpty()) {
            return (openStickyNotes.get(openStickyNotes.size()-1).getGraphic().getTranslateY() < dayTitleButtonY + toolbarPadding.y);
        }
        return false;
    }

    private double getCenterX(double width) {
        return toolbarPane.getTranslateX() + ((toolbarGraphic.getWidth() - width) / 2);
    }

    private double getStickyNoteY(int index) {
        return (StickyNote.DIMENSIONS.y + toolbarPadding.y) * index + dayTitleButtonY + toolbarPadding.y;
    }

    public boolean isOpen() {
        return toolbarGraphic.isVisible();
    }

    public Day getOpenDay() {
        return openDay;
    }

    public boolean isMouseOver() {
        boolean contains = false;
        if (App.getMousePosition().x > toolbarPane.getTranslateX()) {
            contains = true;
        } 
        return contains;
    }

    @Override
    protected void AddButtons() {
        dayTitleButton = new DayTitleButton(getButtonX(), 0, getButtonWidth());
        addButton(dayTitleButton);
        dayTitleButtonY = (dayTitleButton.getNodes().get(0).getLayoutY() + GUIButton.BUTTON_HEIGHT);

        lightButton = new SwitchColorThemeButton(new LightColorTheme(), getButtonX(), getBottomY(), getButtonWidth()/2);
        darkButton = new SwitchColorThemeButton(new DarkColorTheme(), lightButton.getPosition().x + getButtonWidth() / 2, getBottomY(), getButtonWidth()/2);

        addButton(lightButton);
        addButton(darkButton);
    }
}

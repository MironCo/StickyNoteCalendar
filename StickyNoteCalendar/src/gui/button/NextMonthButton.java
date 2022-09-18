package gui.button;

import main.calendar.Calendar;

public class NextMonthButton extends GUIButton {

    public NextMonthButton(double x, double y, int width, int height) {
        super("->", x, y, width, height);
        graphic.setArcHeight(height / 4);
        graphic.setArcWidth(width / 5);
    }

    @Override
    public void performAction() {
        Calendar.getInstance().goToNextMonth();
    }
    
}

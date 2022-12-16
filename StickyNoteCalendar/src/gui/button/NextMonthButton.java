package gui.button;

import main.calendar.Calendar;

public class NextMonthButton extends GUIButton {

    public NextMonthButton(double x, double y, int width) {
        super("->", x, y, width);
    }

    @Override
    public void performAction() {
        Calendar.getInstance().goToNextMonth();
    }
    
}

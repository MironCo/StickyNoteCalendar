package gui.button;

import main.calendar.Calendar;

public class LastMonthButton extends GUIButton {

    public LastMonthButton(double x, double y, int width) {
        super("<-", x, y, width);
    }

    @Override
    public void performAction() {
        // TODO Auto-generated method stub
        Calendar.getInstance().goToLastMonth();
    }
}

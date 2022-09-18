package gui.button;

import main.calendar.Calendar;

public class LastMonthButton extends GUIButton {

    public LastMonthButton(double x, double y, int width, int height) {
        super("<-", x, y, width, height);
        graphic.setArcHeight(height / 4);
        graphic.setArcWidth(width / 5);
    }

    @Override
    public void performAction() {
        // TODO Auto-generated method stub
        Calendar.getInstance().goToLastMonth();
    }
}

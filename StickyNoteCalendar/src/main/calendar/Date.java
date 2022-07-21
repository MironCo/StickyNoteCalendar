package main.calendar;

public class Date {
    protected int month;
    protected int day;
    protected int year;

    public void setDate(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public String getDateString() {
        String dateString = String.format(Integer.toString(month), "%02d") + "/" + String.format(Integer.toString(day), "%02d") + "/" + Integer.toString(year);
        return dateString;
    }

    public boolean isEqual(Date dateToCompare) {
        return (month == dateToCompare.month && day == dateToCompare.day && month == dateToCompare.year);
    }

    public int compareDay(Date dateToCompare) {
        if (this.day == dateToCompare.day) {
            return 0;
        } else if (this.day > dateToCompare.day) {
            return 1;
        } else if (this.day < dateToCompare.day){
            return -1;
        } else return 0;
    }

    public int compareMonth(Date dateToCompare) {
        if (this.month == dateToCompare.month) {
            return 0;
        } else if (this.month > dateToCompare.month) {
            return 1;
        } else if (this.month < dateToCompare.month){
            return -1;
        } else return 0;
    }

    public int compareYear(Date dateToCompare) {
        if (this.year == dateToCompare.year) {
            return 0;
        } else if (this.year > dateToCompare.year) {
            return 1;
        } else if (this.year < dateToCompare.year){
            return -1;
        } else return 0;
    }

    public int compare(Date dateToCompare) {
        int outcome = 0;
        if (this.isEqual(dateToCompare)) {
            outcome = 0;
        } else {
            outcome = this.compareYear(dateToCompare);
            if (outcome == 0) {
                outcome = this.compareMonth(dateToCompare);
                if (outcome == 0) {
                    outcome = this.compareDay(dateToCompare);
                }
            }
        }
        return outcome;
    }
}

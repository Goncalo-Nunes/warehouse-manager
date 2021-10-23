package ggc.core;

public class Date {
    private int _days;

    public Date() {
        _days = 0;
    }

    public Date(int days) {
        _days = days;
    }

    public int getDays() {
        return _days;
    }

    public Date add(int days) {
        _days += days;

        return this;
    }

    /**
   * @param Date
   * @return day difference between both dates
   */
    public int difference(Date date) {
        return _days - date.getDays();
    }

    public String toString() {
        return "" + _days;
    }
}